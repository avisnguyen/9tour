package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.ExtraBill;
import xyz.nhatbao.ninetour.entity.ExtraService;
import xyz.nhatbao.ninetour.entity.Tour;
import xyz.nhatbao.ninetour.model.request.ExtraServiceRequestModel;
import xyz.nhatbao.ninetour.model.response.ExtraBillResponseModel;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class ExtraServiceConverter {
    @Autowired
    ModelMapper modelMapper;

    public ExtraService toEntity(ExtraServiceRequestModel extraServiceRequestModel) {
        ExtraService extraService = modelMapper.map(extraServiceRequestModel, ExtraService.class);
        if (extraServiceRequestModel.getTours().size() > 0)
            extraService.setTours(modelMapper.map(extraServiceRequestModel.getTours(), new TypeToken<Set<Tour>>() {
            }.getType()));
        if (extraServiceRequestModel.getExtraBills().size() > 0)
            extraService.setExtraBills(modelMapper.map(extraServiceRequestModel.getExtraBills(), new TypeToken<List<ExtraBill>>() {
            }.getType()));
        return extraService;
    }

    public ExtraServiceResponseModel toModel(ExtraService extraService) {
        ExtraServiceResponseModel extraServiceResponseModel = modelMapper.map(extraService, ExtraServiceResponseModel.class);
        if (extraService.getExtraBills().size() > 0)
            extraServiceResponseModel.setExtraBillResponseModels(modelMapper.map(extraService.getExtraBills(), new TypeToken<List<ExtraBillResponseModel>>() {
            }.getType()));
        if (extraService.getTours().size() > 0)
            extraServiceResponseModel.setTourResponseModels(new ArrayList<>(modelMapper.map(extraService.getTours(), new TypeToken<Set<TourResponseModel>>() {
            }.getType())));
        return extraServiceResponseModel;
    }
}
