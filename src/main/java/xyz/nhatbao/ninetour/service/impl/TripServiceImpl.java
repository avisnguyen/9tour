package xyz.nhatbao.ninetour.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.config.CloudinaryConfig;
import xyz.nhatbao.ninetour.converter.TripConverter;
import xyz.nhatbao.ninetour.entity.Tour;
import xyz.nhatbao.ninetour.entity.Trip;
import xyz.nhatbao.ninetour.model.request.TripRequestModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;
import xyz.nhatbao.ninetour.repository.BillRepository;
import xyz.nhatbao.ninetour.repository.TourRepository;
import xyz.nhatbao.ninetour.repository.TripRepository;
import xyz.nhatbao.ninetour.service.TripService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

/*******************************************************************************
 <pre>

 Copyright (c) 2021 Nguyen Nhat Bao
 This project is licensed under the terms of the MIT license.

 Author: Nguyen Nhat Bao (Kian Nguyen)
 Website: https://kiandev.xyz
 Contact for work: kiannguyen.work@gmail.com
 Feedback to me: kiannguyen.dev@gmail.com
 Github: https://github.com/kian-nguyen

 Please do not remove.

 </pre>
 ******************************************************************************/

@Service
@Transactional
public class TripServiceImpl implements TripService {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @Autowired
    ModelMapper mapper;

    @Autowired
    TripConverter tripConverter;

    @Autowired
    private BillRepository billRepository;

    Slugify slugify = new Slugify();

    @Override
    public TripResponseModel create(TripRequestModel tripRequestModel) {
        TripResponseModel result = new TripResponseModel();
        Trip trip = mapper.map(tripRequestModel, Trip.class);
        //Set missed field
        if (tripRequestModel.getTour() != null) {
            Optional<Tour> tour = tourRepository.findById(tripRequestModel.getTour());
            if (!tour.isPresent()) {
                result.setMessage("tour_not_found");
                return result;
            }
            trip.setTour(tour.get());
        }
        result = tripConverter.toModel(tripRepository.save(trip));
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return tripRepository.count();
    }

    @Override
    public TripResponseModel getAll() {
        TripResponseModel result = new TripResponseModel();
        List<Trip> trips = tripRepository.findAll();
        List<TripResponseModel> listResponse = new ArrayList<>();
        for (Trip trip :
                trips) {
            listResponse.add(tripConverter.toModel(trip));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public TripResponseModel getAll(Pageable pageable) {
        TripResponseModel result = new TripResponseModel();
        List<Trip> trips = tripRepository.findAll(pageable).getContent();
        List<TripResponseModel> listResponse = new ArrayList<>();
        for (Trip trip :
                trips) {
            listResponse.add(tripConverter.toModel(trip));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public TripResponseModel getAllTripByTourId(Long tourId, Pageable pageable) {
        TripResponseModel result = new TripResponseModel();
        Page<Trip> tripPage = tripRepository.findAllByTourId(tourId, pageable);
        List<Trip> trips = tripPage.getContent();
        List<TripResponseModel> listResponse = new ArrayList<>();
        for (Trip trip :
                trips) {
            listResponse.add(tripConverter.toModel(trip));
        }
        result.setResults(listResponse);
        result.setTotalItems(tripPage.getTotalElements());
        return result;
    }

    @Override

    public TripResponseModel findAllById(Long id) {
        TripResponseModel result = new TripResponseModel();
        Trip trip = tripRepository.findAllById(id);
        result = tripConverter.toModel(trip);
        int adultAvailabe = result.getAdultMaximum();
        int childAvailabe = result.getChildMaximum();
        int infantAvailabe = result.getInfantMaximum();
        if (billRepository.totalAdult(result.getId()) != null ||
                billRepository.totalChild(result.getId()) != null ||
                billRepository.totalInfant(result.getId()) != null) {
            if (billRepository.totalAdult(result.getId()) != null)
                adultAvailabe -= billRepository.totalAdult(result.getId());
            if (billRepository.totalChild(result.getId()) != null)
                childAvailabe -= billRepository.totalChild(result.getId());
            if (billRepository.totalChild(result.getId()) != null)
                infantAvailabe -= billRepository.totalInfant(result.getId());
        }
        result.setAdultAvailable(adultAvailabe);
        result.setChildAvailable(childAvailabe);
        result.setInfantAvailable(infantAvailabe);
        result.getTourResponseModel().setSlug(slugify.slugify(result.getTourResponseModel().getName()));
        return result;
    }

    @Override
    public TripResponseModel searchTrip(Long tourId, String keyword, Date from, Date to, Pageable pageable) {
        TripResponseModel result = new TripResponseModel();
        Page<Trip> tripPage = tripRepository.searchTripAdmin(tourId, keyword, from, to, pageable);
        List<Trip> trips = tripPage.getContent();
        List<TripResponseModel> listResponse = new ArrayList<>();
        for (Trip trip :
                trips) {
            listResponse.add(tripConverter.toModel(trip));
        }
        result.setResults(listResponse);
        result.setTotalItems(tripPage.getTotalElements());

        return result;
    }

    @Override
    public TripResponseModel findById(Long id) {
        TripResponseModel result = new TripResponseModel();
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            result = tripConverter.toModel(trip.get());
        }
        return result;
    }

    @Override
    public TripResponseModel update(Long id, TripRequestModel tripRequestModel) {
        TripResponseModel result = new TripResponseModel();
        Optional<Trip> tripOptional = tripRepository.findById(id);
        Trip oldTrip = new Trip();
        Trip newTrip = new Trip();

        //Trip not found
        if (!tripOptional.isPresent()) {
            result = mapper.map(tripRequestModel, TripResponseModel.class);
            result.setMessage("trip_not_found");
            return result;
        }
        oldTrip = tripOptional.get();
        newTrip = mapper.map(tripRequestModel, Trip.class);

        //Set missed field
        if (tripRequestModel.getTour() != null) {
            Optional<Tour> tour = tourRepository.findById(tripRequestModel.getTour());
            if (!tour.isPresent()) {
                result.setMessage("tour_not_found");
                return result;
            }
            newTrip.setTour(tour.get());
        }

        // Normal
        newTrip.setCreatedBy(oldTrip.getCreatedBy());
        newTrip.setCreatedDate(oldTrip.getCreatedDate());
        if (oldTrip.getTour() != null) newTrip.setTour(oldTrip.getTour());
        result = tripConverter.toModel(tripRepository.save(newTrip));
        result.setMessage("update_success");
        return result;
    }

    @Override
    public TripResponseModel delete(TripRequestModel tripRequestModel) {
        TripResponseModel result = new TripResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                tripRequestModel.getIds()) {
            Optional<Trip> willDelTrip = tripRepository.findById(id);
            if (willDelTrip.isPresent()) {
                tripRepository.deleteById(willDelTrip.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
        return result;
    }

    @Override
    public List<TripResponseModel> findAllIdDesc() {
        List<TripResponseModel> result = new ArrayList<>();
        List<Trip> tripEntities = tripRepository.findAllByOrderByIdDesc();
        for (Trip trip : tripEntities) {
            TripResponseModel tripResponseModel = tripConverter.toModel(trip);
            tripResponseModel.setSlug(slugify.slugify(tripResponseModel.getTourResponseModel().getName()));
            result.add(tripResponseModel);
        }
        return result;
    }

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String url = null;
        if (file.isEmpty()) {
            return url;
        }
        try {
            Map params = ObjectUtils.asMap("folder", path,
                    "resource_type", "image");
            Map uploadResult = cloudinaryConfig.upload(file.getBytes(), params);
            url = uploadResult.get("url").toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }
}
