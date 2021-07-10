package xyz.nhatbao.ninetour.service.impl;

import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.config.CloudinaryConfig;
import xyz.nhatbao.ninetour.converter.ExtraServiceConverter;
import xyz.nhatbao.ninetour.entity.ExtraService;
import xyz.nhatbao.ninetour.entity.Tour;
import xyz.nhatbao.ninetour.model.request.ExtraServiceRequestModel;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;
import xyz.nhatbao.ninetour.repository.ExtraServiceRepository;
import xyz.nhatbao.ninetour.repository.TourRepository;
import xyz.nhatbao.ninetour.service.ExtraServiceService;

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
public class ExtraServiceServiceImpl implements ExtraServiceService {
    @Autowired
    ExtraServiceRepository extraServiceRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    ExtraServiceConverter extraServiceConverter;

    @Autowired
    ModelMapper mapper;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @Override
    public ExtraServiceResponseModel create(ExtraServiceRequestModel extraServiceRequestModel) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        ExtraService extraService = extraServiceConverter.toEntity(extraServiceRequestModel);
        extraService = extraServiceRepository.saveAndFlush(extraService);

        //Set missed field
        if (extraServiceRequestModel.getTours() != null) {
            List<Tour> listTour = new ArrayList<>();
            for (Long tourId :
                    extraServiceRequestModel.getTours()) {
                Optional<Tour> tourOptional = tourRepository.findById(tourId);
                if (tourOptional.isPresent()) {
                    Tour tour = tourOptional.get();
                    List<ExtraService> extraServices = new ArrayList<>(tour.getExtraServices());
                    extraServices.add(extraService);
                    tour.setExtraServices(new HashSet<>(extraServices));
                    listTour.add(tourRepository.save(tour));
                }
            }
            extraService.setTours(new HashSet<>(listTour));
        }

        result = extraServiceConverter.toModel(extraService);
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return extraServiceRepository.count();
    }

    @Override
    public ExtraServiceResponseModel getAll(Pageable pageable) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        List<ExtraService> extraServices = extraServiceRepository.findAll(pageable).getContent();
        List<ExtraServiceResponseModel> listResponse = new ArrayList<>();
        for (ExtraService extrabill :
                extraServices) {
            listResponse.add(extraServiceConverter.toModel(extrabill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public ExtraServiceResponseModel getAll() {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        List<ExtraService> extraServices = extraServiceRepository.findAll();
        List<ExtraServiceResponseModel> listResponse = new ArrayList<>();
        for (ExtraService extrabill :
                extraServices) {
            listResponse.add(extraServiceConverter.toModel(extrabill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public ExtraServiceResponseModel searchExtraService(Long tourId, String keyword, Pageable pageable) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        Page<ExtraService> extraServicePage = extraServiceRepository.searchExtraServiceAdmin(tourId, keyword, pageable);
        List<ExtraService> extraServices = extraServicePage.getContent();
        List<ExtraServiceResponseModel> listResponse = new ArrayList<>();
        for (ExtraService extraService :
                extraServices) {
            listResponse.add(extraServiceConverter.toModel(extraService));
        }
        result.setResults(listResponse);
        result.setTotalItems(extraServicePage.getTotalElements());

        return result;
    }

    @Override
    public ExtraServiceResponseModel findById(Long id) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        Optional<ExtraService> extraService = extraServiceRepository.findById(id);
        if (extraService.isPresent()) {
            result = extraServiceConverter.toModel(extraService.get());
        }
        return result;
    }

    @Override
    public ExtraServiceResponseModel update(Long id, ExtraServiceRequestModel extraServiceRequestModel) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        Optional<ExtraService> extraServiceOptional = extraServiceRepository.findById(id);
        ExtraService oldExtraService = new ExtraService();
        ExtraService newExtraService = new ExtraService();

        //ExtraService not found
        if (!extraServiceOptional.isPresent()) {
            result = mapper.map(extraServiceRequestModel, ExtraServiceResponseModel.class);
            result.setMessage("extraService_not_found");
            return result;
        }
        oldExtraService = extraServiceOptional.get();
        newExtraService = extraServiceConverter.toEntity(extraServiceRequestModel);


        if (extraServiceRequestModel.getImageFile().getOriginalFilename().equals("")) {
            newExtraService.setThumbnailUrl(oldExtraService.getThumbnailUrl());
        }

        // Normal
        newExtraService.setCreatedBy(oldExtraService.getCreatedBy());
        newExtraService.setCreatedDate(oldExtraService.getCreatedDate());
        newExtraService = extraServiceRepository.saveAndFlush(newExtraService);


        //Update not contain tours
        List<Tour> oldTours = tourRepository.searchTourByServiceId(newExtraService.getId());
        List<Tour> willUpdateTours = new ArrayList<>();
        for (Tour tour : oldTours) {
            if (!extraServiceRequestModel.getTours().contains(tour.getId())) {
                tour.getExtraServices().remove(newExtraService);
                willUpdateTours.add(tour);
            }
        }
        tourRepository.saveAll(willUpdateTours);


        //Set missed field
        if (extraServiceRequestModel.getTours() != null) {
            List<Tour> listTour = new ArrayList<>();
            for (Long tourId :
                    extraServiceRequestModel.getTours()) {
                Optional<Tour> tourOptional = tourRepository.findById(tourId);
                if (tourOptional.isPresent()) {
                    Tour tour = tourOptional.get();
                    if (!tour.getExtraServices().contains(newExtraService)) {
                        tour.getExtraServices().add(newExtraService);
                    }
                    listTour.add(tour);
                }
            }
            newExtraService.setTours(new HashSet<>(tourRepository.saveAll(listTour)));
        }
        result = extraServiceConverter.toModel(newExtraService);
        result.setMessage("update_success");
        return result;
    }


    @Override
    public ExtraServiceResponseModel delete(ExtraServiceRequestModel extraServiceRequestModel) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                extraServiceRequestModel.getIds()) {
            Optional<ExtraService> willDelExtraService = extraServiceRepository.findById(id);
            if (willDelExtraService.isPresent()) {
                extraServiceRepository.deleteById(willDelExtraService.get().getId());
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
    public ExtraServiceResponseModel findServiceByTourId(Long id) {
        ExtraServiceResponseModel result = new ExtraServiceResponseModel();
        List<ExtraService> extraServiceList = extraServiceRepository.findServiceByTourId(id);
        List<ExtraServiceResponseModel> extraServiceResponseModelList = new ArrayList<>();
        for (ExtraService extraService : extraServiceList) {
            ExtraServiceResponseModel extraServiceResponseModel = extraServiceConverter.toModel(extraService);
            extraServiceResponseModelList.add(extraServiceResponseModel);
        }
        result.setResults(extraServiceResponseModelList);
        return result;
    }
}
