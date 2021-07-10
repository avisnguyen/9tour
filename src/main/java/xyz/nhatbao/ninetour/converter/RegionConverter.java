package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Place;
import xyz.nhatbao.ninetour.entity.Region;
import xyz.nhatbao.ninetour.model.request.RegionRequestModel;
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
public class RegionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Region toEntity(RegionRequestModel regionRequestModel) {
        Region region = modelMapper.map(regionRequestModel, Region.class);
        region.setPlaces(modelMapper.map(regionRequestModel.getPlaces(), new TypeToken<List<Place>>() {
        }.getType()));
        return region;
    }

    public RegionResponseModel toModel(Region region) {
        RegionResponseModel regionResponseModel = modelMapper.map(region, RegionResponseModel.class);
        regionResponseModel.setPlaceResponseModels(modelMapper.map(region.getPlaces(), new TypeToken<List<PlaceResponseModel>>() {
        }.getType()));
        return regionResponseModel;
    }
}
