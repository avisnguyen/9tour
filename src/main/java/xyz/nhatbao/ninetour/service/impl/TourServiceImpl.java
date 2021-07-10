package xyz.nhatbao.ninetour.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.config.CloudinaryConfig;
import xyz.nhatbao.ninetour.converter.TourConverter;
import xyz.nhatbao.ninetour.converter.TripConverter;
import xyz.nhatbao.ninetour.entity.Place;
import xyz.nhatbao.ninetour.entity.Tour;
import xyz.nhatbao.ninetour.entity.Trip;
import xyz.nhatbao.ninetour.model.request.TourRequestModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;
import xyz.nhatbao.ninetour.repository.BillRepository;
import xyz.nhatbao.ninetour.repository.PlaceRepository;
import xyz.nhatbao.ninetour.repository.TourRepository;
import xyz.nhatbao.ninetour.repository.TripRepository;
import xyz.nhatbao.ninetour.service.TourService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
public class TourServiceImpl implements TourService {
    @Autowired
    TourRepository tourRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @Autowired
    ModelMapper mapper;

    @Autowired
    TourConverter tourConverter;

    @Autowired
    TripConverter tripConverter;

    @Autowired
    private TripRepository tripRepository;

    Slugify slugify = new Slugify();

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public TourResponseModel create(TourRequestModel tourRequestModel) {
        TourResponseModel result = new TourResponseModel();
        Tour tour = mapper.map(tourRequestModel, Tour.class);

        //Set missed field
        if (tourRequestModel.getPlaces() != null) {
            List<Place> listPlace = new ArrayList<>();
            for (Long placeId :
                    tourRequestModel.getPlaces()) {
                Optional<Place> place = placeRepository.findById(placeId);
                place.ifPresent(listPlace::add);
            }
            tour.setPlaces(new HashSet<>(listPlace));
        }

        if (tourRequestModel.getDeparture() != null) {
            Optional<Place> departure = placeRepository.findById(tourRequestModel.getDeparture());
            departure.ifPresent(tour::setDeparture);
        }

        if (tourRequestModel.getDestination() != null) {
            Optional<Place> destination = placeRepository.findById(tourRequestModel.getDestination());
            destination.ifPresent(tour::setDestination);
        }
        result = tourConverter.toModel(tourRepository.save(tour));
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return tourRepository.count();
    }

    @Override
    public TourResponseModel getAll(Pageable pageable) {
        TourResponseModel result = new TourResponseModel();
        List<Tour> tours = tourRepository.findAll(pageable).getContent();
        List<TourResponseModel> listResponse = new ArrayList<>();
        for (Tour tour :
                tours) {
            listResponse.add(tourConverter.toModel(tour));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public TourResponseModel getAll() {
        TourResponseModel result = new TourResponseModel();
        List<Tour> tours = tourRepository.findAll();
        List<TourResponseModel> listResponse = new ArrayList<>();
        for (Tour tour :
                tours) {
            listResponse.add(tourConverter.toModel(tour));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public TourResponseModel findById(Long id) {
        TourResponseModel result = new TourResponseModel();
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent()) {
            result = tourConverter.toModel(tour.get());
        }
        return result;
    }

    @Override
    public TourResponseModel update(Long id, TourRequestModel tourRequestModel) {
        TourResponseModel result = new TourResponseModel();
        Optional<Tour> tourOptional = tourRepository.findById(id);
        Tour oldTour = new Tour();
        Tour newTour = new Tour();

        //Tour not found
        if (!tourOptional.isPresent()) {
            result = mapper.map(tourRequestModel, TourResponseModel.class);
            result.setMessage("tour_not_found");
            return result;
        }
        oldTour = tourOptional.get();
        newTour = tourConverter.toEntity(tourRequestModel);

        //Set missed field
        if (tourRequestModel.getPlaces() != null) {
            List<Place> listPlace = new ArrayList<>();
            for (Long placeId :
                    tourRequestModel.getPlaces()) {
                Optional<Place> place = placeRepository.findById(placeId);
                place.ifPresent(listPlace::add);
            }
            newTour.setPlaces(new HashSet<>(listPlace));
        }

        if (tourRequestModel.getDeparture() != null) {
            Optional<Place> departure = placeRepository.findById(tourRequestModel.getDeparture());
            departure.ifPresent(newTour::setDeparture);
        }

        if (tourRequestModel.getDestination() != null) {
            Optional<Place> destination = placeRepository.findById(tourRequestModel.getDestination());
            destination.ifPresent(newTour::setDestination);
        }

        if (Objects.equals(tourRequestModel.getImageFile().getOriginalFilename(), "")) {
            newTour.setThumbnailUrl(oldTour.getThumbnailUrl());
        }

        // Normal
        newTour.setCreatedBy(oldTour.getCreatedBy());
        newTour.setCreatedDate(oldTour.getCreatedDate());
        result = tourConverter.toModel(tourRepository.save(newTour));
        result.setMessage("update_success");
        return result;
    }

    @Override
    public TourResponseModel delete(TourRequestModel tourRequestModel) {
        TourResponseModel result = new TourResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                tourRequestModel.getIds()) {
            Optional<Tour> willDelTour = tourRepository.findById(id);
            if (willDelTour.isPresent()) {
                tourRepository.deleteById(willDelTour.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
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

    @Override
    public List<TourResponseModel> findAllIdDesc() {
        List<TourResponseModel> result = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        try {
            now = format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(now);
//        Date now = new Date(2021, 4, 20, 12, 30, 00);
//        System.out.println(now);
        List<Tour> tourList = tourRepository.findAllByOrderByIdDesc();

        for (Tour tour : tourList) {
            TourResponseModel tourResponseModel = tourConverter.toModel(tour);
            Trip trip = tripRepository.findFirstByTourIdAndDepartureTimeAfterOrderByAdultPriceAsc(tour.getId(), now);
            if (trip != null) {
                tourResponseModel.setTripResponseModel(tripConverter.toModel(trip));
            }
            tourResponseModel.setSlug(slugify.slugify(tourResponseModel.getName()));
            result.add(tourResponseModel);
        }
        return result;
    }

    @Override
    public TourResponseModel listAll(Pageable pageable) {
        TourResponseModel result = new TourResponseModel();
        Page<Tour> tourPage = tourRepository.searchAll(pageable);
        List<Tour> tours = new ArrayList<>();
        for (Tour tour :
                tourPage.getContent()) {

            if (!tours.contains(tour)) tours.add(tour);
        }
        List<TourResponseModel> listResponse = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        try {
            now = format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Tour tour : tours) {
            TourResponseModel tourResponseModel = tourConverter.toModel(tour);
            Trip trip = tripRepository.findFirstByTourIdAndDepartureTimeAfterOrderByAdultPriceAsc(tour.getId(), now);
            if (trip != null) {
                TripResponseModel tripResponseModel = tripConverter.toModel(trip);
                tourResponseModel.setTripResponseModel(tripResponseModel);
            } else {

            }
            tourResponseModel.setSlug(slugify.slugify(tourResponseModel.getName()));
            listResponse.add(tourResponseModel);
        }

        TourResponseModel tmp = new TourResponseModel();
        Sort.Order order = tourPage.getSort().getOrderFor("trips.adultPrice");
        if (order != null) {
            for (int i = 0; i < listResponse.size() - 1; i++) {
                for (int j = i + 1; j < listResponse.size(); j++) {
                    if (listResponse.get(j).getTripResponseModel() == null) {
                        tmp = listResponse.get(j);
                        listResponse.set(j, listResponse.get(listResponse.size() - 1));
                        listResponse.set(listResponse.size() - 1, tmp);
                    }
                    if (listResponse.get(i).getTripResponseModel() != null && listResponse.get(j).getTripResponseModel() != null && listResponse.get(i).getTripResponseModel().getAdultPrice() > listResponse.get(j).getTripResponseModel().getAdultPrice()) {
                        tmp = listResponse.get(i);
                        listResponse.set(i, listResponse.get(j));
                        listResponse.set(j, tmp);
                    }
                }
            }
        }
        order = tourPage.getSort().getOrderFor("trips.departureTime");
        if (order != null) {
            for (int i = 0; i < listResponse.size() - 1; i++) {
                for (int j = i + 1; j < listResponse.size(); j++) {
                    if (listResponse.get(j).getTripResponseModel() == null) {
                        tmp = listResponse.get(j);
                        listResponse.set(j, listResponse.get(listResponse.size() - 1));
                        listResponse.set(listResponse.size() - 1, tmp);
                    }
                    if (listResponse.get(i).getTripResponseModel() != null && listResponse.get(j).getTripResponseModel() != null && listResponse.get(i).getTripResponseModel().getDepartureTime().after(listResponse.get(j).getTripResponseModel().getDepartureTime())) {
                        tmp = listResponse.get(i);
                        listResponse.set(i, listResponse.get(j));
                        listResponse.set(j, tmp);
                    }
                }
            }
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public TourResponseModel listAll(Date from, Pageable pageable) {
        return null;
    }

    @Override
    public TourResponseModel findTourById(Long id) {
        TourResponseModel result = new TourResponseModel();
        List<TripResponseModel> tripResponseModels = new ArrayList<>();
        Optional<Tour> tour = tourRepository.findById(id);
        LocalDateTime date = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        try {
            now = format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (tour.isPresent()) {
            result = tourConverter.toModel(tour.get());
            List<Trip> trips = tripRepository.findAllByTourIdAndDepartureTimeAfterOrderByDepartureTimeAsc(id, now);
            for (Trip trip : trips) {
                TripResponseModel tripResponseModel = mapper.map(trip, TripResponseModel.class);
                int adultAvailabe = tripResponseModel.getAdultMaximum();
                int childAvailabe = tripResponseModel.getChildMaximum();
                int infantAvailabe = tripResponseModel.getInfantMaximum();
                if (billRepository.totalAdult(tripResponseModel.getId()) != null ||
                        billRepository.totalChild(tripResponseModel.getId()) != null ||
                        billRepository.totalInfant(tripResponseModel.getId()) != null) {
                    if (billRepository.totalAdult(tripResponseModel.getId()) != null)
                        adultAvailabe -= billRepository.totalAdult(tripResponseModel.getId());
                    if (billRepository.totalChild(tripResponseModel.getId()) != null)
                        childAvailabe -= billRepository.totalChild(tripResponseModel.getId());
                    if (billRepository.totalChild(tripResponseModel.getId()) != null)
                        infantAvailabe -= billRepository.totalInfant(tripResponseModel.getId());
                }
                tripResponseModel.setAdultAvailable(adultAvailabe);
                tripResponseModel.setChildAvailable(childAvailabe);
                tripResponseModel.setInfantAvailable(infantAvailabe);
                tripResponseModels.add(tripResponseModel);
            }
//            tripResponseModels = Arrays.asList(mapper.map(trips, TripResponseModel[].class));
            result.setTripResponseModels(tripResponseModels);
            result.setSlug(slugify.slugify(result.getName()));
        }
        return result;
    }

    @Override
    public List<TourResponseModel> findByDepartureId(Long id) {
        List<TourResponseModel> result = new ArrayList<>();
        List<Tour> tourList = tourRepository.findByDepartureIdOrderByIdDesc(id);
        LocalDateTime date = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        try {
            now = format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Tour tour : tourList) {
            TourResponseModel tourResponseModel = tourConverter.toModel(tour);
            Trip trip = tripRepository.findFirstByTourIdOrderByAdultPrice(tour.getId());
            if (trip != null) {
                TripResponseModel tripResponseModel = tripConverter.toModel(trip);
                tourResponseModel.setTripResponseModel(tripResponseModel);
            }
            tourResponseModel.setSlug(slugify.slugify(tourResponseModel.getName()));
            result.add(tourResponseModel);
        }
        return result;
    }

    @Override
    public TourResponseModel listTourByPlace(Long placeId, Pageable pageable) {
        TourResponseModel result = new TourResponseModel();
        List<Tour> tours = tourRepository.findByPlaceId(placeId, pageable);
        List<Tour> tourList = new ArrayList<>();
        for (Tour tour :
                tours) {
            if (!tourList.contains(tour)) tourList.add(tour);
        }
        long totalItems = tourRepository.count(placeId);
        List<TourResponseModel> listResponse = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date now = new Date();
        try {
            now = format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Tour tour : tourList) {
            TourResponseModel tourResponseModel = tourConverter.toModel(tour);
            Trip trip = tripRepository.findFirstByTourIdAndDepartureTimeAfterOrderByAdultPriceAsc(tour.getId(), now);
            if (trip != null) {
                TripResponseModel tripResponseModel = tripConverter.toModel(trip);
                tourResponseModel.setTripResponseModel(tripResponseModel);
            }
            tourResponseModel.setSlug(slugify.slugify(tourResponseModel.getName()));
            listResponse.add(tourResponseModel);
        }

        TourResponseModel tmp = new TourResponseModel();
        Sort.Order order = pageable.getSort().getOrderFor("trips.adultPrice");
        if (order != null) {
            for (int i = 0; i < listResponse.size() - 1; i++) {
                for (int j = i + 1; j < listResponse.size(); j++) {
                    if (listResponse.get(j).getTripResponseModel() == null) {
                        tmp = listResponse.get(j);
                        listResponse.set(j, listResponse.get(listResponse.size() - 1));
                        listResponse.set(listResponse.size() - 1, tmp);
                    }
                    if (listResponse.get(i).getTripResponseModel() != null && listResponse.get(j).getTripResponseModel() != null && listResponse.get(i).getTripResponseModel().getAdultPrice() > listResponse.get(j).getTripResponseModel().getAdultPrice()) {
                        tmp = listResponse.get(i);
                        listResponse.set(i, listResponse.get(j));
                        listResponse.set(j, tmp);
                    }
                }
            }
        }
        order = pageable.getSort().getOrderFor("trips.departureTime");
        if (order != null) {
            for (int i = 0; i < listResponse.size() - 1; i++) {
                for (int j = i + 1; j < listResponse.size(); j++) {
                    if (listResponse.get(j).getTripResponseModel() == null) {
                        tmp = listResponse.get(j);
                        listResponse.set(j, listResponse.get(listResponse.size() - 1));
                        listResponse.set(listResponse.size() - 1, tmp);
                    }
                    if (listResponse.get(i).getTripResponseModel() != null && listResponse.get(j).getTripResponseModel() != null && listResponse.get(i).getTripResponseModel().getDepartureTime().after(listResponse.get(j).getTripResponseModel().getDepartureTime())) {
                        tmp = listResponse.get(i);
                        listResponse.set(i, listResponse.get(j));
                        listResponse.set(j, tmp);
                    }
                }
            }
        }
        result.setResults(listResponse);
        result.setTotalItems(totalItems);
        return result;
    }

    @Override
    public TourResponseModel searchTour(String keyword, String from, String to, Pageable pageable) {
        TourResponseModel result = new TourResponseModel();
        Date fromDate = null;
        Date toDate = null;
        List<TourResponseModel> listResponse = new ArrayList<>();
        try {
            Date today = new Date();
            if (from == "") fromDate = today;
            else
                fromDate = formatter.parse(from);
            if (fromDate.before(today)) fromDate = today;
            if (to == "") to = "01/01/2999";
            toDate = formatter.parse(to);
            List<Tour> tourList = tourRepository.searchTourWeb(keyword, fromDate, toDate, pageable);
            List<Tour> tours = new ArrayList<>();
            for (Tour tour :
                    tourList) {

                if (!tours.contains(tour)) tours.add(tour);
            }
            long totalItems = tourRepository.countSearchTourWeb(keyword, fromDate, toDate);
            for (Tour tour : tours) {
                TourResponseModel tourResponseModel = tourConverter.toModel(tour);
                Trip trip = tripRepository.findFirstByTourIdAndDepartureTimeBetweenOrderByAdultPriceAsc(tour.getId(), fromDate, toDate);
                if (trip != null) {
                    TripResponseModel tripResponseModel = tripConverter.toModel(trip);
                    tourResponseModel.setTripResponseModel(tripResponseModel);
                } else {

                }
                tourResponseModel.setSlug(slugify.slugify(tourResponseModel.getName()));
                listResponse.add(tourResponseModel);
            }

            TourResponseModel tmp = new TourResponseModel();
            Sort.Order order = pageable.getSort().getOrderFor("trips.adultPrice");
            if (order != null) {
                for (int i = 0; i < listResponse.size() - 1; i++) {
                    for (int j = i + 1; j < listResponse.size(); j++) {
                        if (listResponse.get(j).getTripResponseModel() == null) {
                            tmp = listResponse.get(j);
                            listResponse.set(j, listResponse.get(listResponse.size() - 1));
                            listResponse.set(listResponse.size() - 1, tmp);
                        }
                        if (listResponse.get(i).getTripResponseModel() != null && listResponse.get(j).getTripResponseModel() != null && listResponse.get(i).getTripResponseModel().getAdultPrice() > listResponse.get(j).getTripResponseModel().getAdultPrice()) {
                            tmp = listResponse.get(i);
                            listResponse.set(i, listResponse.get(j));
                            listResponse.set(j, tmp);
                        }
                    }
                }
            }
            order = pageable.getSort().getOrderFor("trips.departureTime");
            if (order != null) {
                for (int i = 0; i < listResponse.size() - 1; i++) {
                    for (int j = i + 1; j < listResponse.size(); j++) {
                        if (listResponse.get(j).getTripResponseModel() == null) {
                            tmp = listResponse.get(j);
                            listResponse.set(j, listResponse.get(listResponse.size() - 1));
                            listResponse.set(listResponse.size() - 1, tmp);
                        }
                        if (listResponse.get(i).getTripResponseModel() != null && listResponse.get(j).getTripResponseModel() != null && listResponse.get(i).getTripResponseModel().getDepartureTime().after(listResponse.get(j).getTripResponseModel().getDepartureTime())) {
                            tmp = listResponse.get(i);
                            listResponse.set(i, listResponse.get(j));
                            listResponse.set(j, tmp);
                        }
                    }
                }
            }
            result.setResults(listResponse);
            result.setTotalItems(totalItems);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TourResponseModel searchTour(String keyword, Pageable pageable) {
        TourResponseModel result = new TourResponseModel();
        List<Tour> tours = tourRepository.searchTourWeb(keyword, null, null, pageable);
        List<TourResponseModel> listResponse = new ArrayList<>();
        for (Tour tour :
                tours) {
            listResponse.add(tourConverter.toModel(tour));
        }
        result.setResults(listResponse);
        result.setTotalItems(tourRepository.countSearchTourWeb(keyword, null, null));
        return result;
    }
}
