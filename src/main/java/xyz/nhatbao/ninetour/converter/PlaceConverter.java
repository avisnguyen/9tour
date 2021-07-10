package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Place;
import xyz.nhatbao.ninetour.entity.Region;
import xyz.nhatbao.ninetour.model.request.PlaceRequestModel;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;
import xyz.nhatbao.ninetour.model.response.RegionResponseModel;

import java.util.List;

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

@Component
public class PlaceConverter {
    @Autowired
    ModelMapper modelMapper;

    public Place toEntity(PlaceRequestModel placeRequestModel) {
        Place place = modelMapper.map(placeRequestModel, Place.class);
        place.setRegion(modelMapper.map(placeRequestModel.getRegion(), Region.class));
        place.setParentPlace(modelMapper.map(placeRequestModel.getParentPlace(), Place.class));
        if (placeRequestModel.getChildPlaces().size() > 0)
            place.setChildPlaces(modelMapper.map(placeRequestModel.getChildPlaces(), new TypeToken<List<Place>>() {
            }.getType()));
        return place;
    }

    public PlaceResponseModel toModel(Place place) {
        PlaceResponseModel placeResponseModel = modelMapper.map(place, PlaceResponseModel.class);
        placeResponseModel.setRegionResponseModel(modelMapper.map(place.getRegion(), RegionResponseModel.class));
        if (place.getParentPlace() != null)
            placeResponseModel.setParentPlaceResponseModel(modelMapper.map(place.getParentPlace(), PlaceResponseModel.class));
        if (place.getChildPlaces().size() > 0)
            placeResponseModel.setChildPlaceResponseModels(modelMapper.map(place.getChildPlaces(), new TypeToken<List<Place>>() {
            }.getType()));
        return placeResponseModel;
    }
}
