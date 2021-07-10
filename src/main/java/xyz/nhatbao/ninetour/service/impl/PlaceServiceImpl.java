package xyz.nhatbao.ninetour.service.impl;

import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.config.CloudinaryConfig;
import xyz.nhatbao.ninetour.converter.PlaceConverter;
import xyz.nhatbao.ninetour.entity.Place;
import xyz.nhatbao.ninetour.entity.Region;
import xyz.nhatbao.ninetour.model.request.PlaceRequestModel;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;
import xyz.nhatbao.ninetour.repository.PlaceRepository;
import xyz.nhatbao.ninetour.repository.RegionRepository;
import xyz.nhatbao.ninetour.service.PlaceService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    PlaceConverter placeConverter;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @Override
    public PlaceResponseModel create(PlaceRequestModel placeRequestModel) {
        PlaceResponseModel result = new PlaceResponseModel();
        Place place = placeConverter.toEntity(placeRequestModel);

        // Mapper
        if (placeRequestModel.getRegion() != null) {
            Optional<Region> region = regionRepository.findById(placeRequestModel.getRegion());
            if (region.isPresent()) {
                place.setRegion(region.get());
            } else {
                result.setMessage("region_not_found");
                return result;
            }
        }
        if (placeRequestModel.getParentPlace() != null) {
            Optional<Place> parentPlace = placeRepository.findById(placeRequestModel.getParentPlace());
            if (parentPlace.isPresent()) {
                place.setParentPlace(parentPlace.get());
            } else {
                result.setMessage("place_not_found");
                return result;
            }
        }
        result = placeConverter.toModel(placeRepository.save(place));
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return placeRepository.count();
    }

    @Override
    public PlaceResponseModel getAll(Pageable pageable) {
        PlaceResponseModel result = new PlaceResponseModel();
        List<Place> places = placeRepository.findAll(pageable).getContent();
        List<PlaceResponseModel> listResponse = new ArrayList<>();
        for (Place place :
                places) {
            listResponse.add(placeConverter.toModel(place));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public PlaceResponseModel getAll() {
        PlaceResponseModel result = new PlaceResponseModel();
        List<PlaceResponseModel> listResponse = new ArrayList<>();
        List<Place> places = placeRepository.findAll();
        for (Place place :
                places) {
            listResponse.add(placeConverter.toModel(place));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public PlaceResponseModel findById(Long id) {
        PlaceResponseModel result = new PlaceResponseModel();
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            result = placeConverter.toModel(place.get());
        }
        return result;
    }

    @Override
    public PlaceResponseModel update(Long id, PlaceRequestModel placeRequestModel) {
        PlaceResponseModel result = new PlaceResponseModel();
        Optional<Place> placeOptional = placeRepository.findById(id);
        Place oldPlace = new Place();
        Place newPlace = new Place();

        //Place not found
        if (!placeOptional.isPresent()) {
            result = mapper.map(placeRequestModel, PlaceResponseModel.class);
            result.setMessage("place_not_found");
            return result;
        }
        oldPlace = placeOptional.get();
        newPlace = mapper.map(placeRequestModel, Place.class);

        //Set missed field
        if (placeRequestModel.getRegion() != null) {
            Optional<Region> region = regionRepository.findById(placeRequestModel.getRegion());
            if (region.isPresent()) {
                newPlace.setRegion(region.get());
            } else {
                result.setMessage("region_not_found");
                return result;
            }
        }
        if (placeRequestModel.getParentPlace() != null) {
            Optional<Place> parentPlace = placeRepository.findById(placeRequestModel.getParentPlace());
            if (parentPlace.isPresent()) {
                newPlace.setParentPlace(parentPlace.get());
            } else {
                result.setMessage("place_not_found");
                return result;
            }
        }
        if (placeRequestModel.getImageFile().getOriginalFilename().equals("")) {
            newPlace.setThumbnailUrl(oldPlace.getThumbnailUrl());
        }

        // Normal
        if (placeRequestModel.getImageFile().getOriginalFilename().equals("")) {
            newPlace.setThumbnailUrl(oldPlace.getThumbnailUrl());
        }
        newPlace.setCreatedBy(oldPlace.getCreatedBy());
        newPlace.setCreatedDate(oldPlace.getCreatedDate());
        result = placeConverter.toModel(placeRepository.save(newPlace));
        result.setMessage("update_success");
        return result;
    }

    @Override
    public PlaceResponseModel delete(PlaceRequestModel placeRequestModel) {
        PlaceResponseModel result = new PlaceResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                placeRequestModel.getIds()) {
            Optional<Place> willDelPlace = placeRepository.findById(id);
            if (willDelPlace.isPresent()) {
                placeRepository.deleteById(willDelPlace.get().getId());
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

}
