package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.AdvertiseTopic;
import xyz.nhatbao.ninetour.model.request.AdvertiseTopRequestModel;
import xyz.nhatbao.ninetour.model.response.AdvertiseTopicResponseModel;
import xyz.nhatbao.ninetour.model.response.SubscriberResponseModel;

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
public class AdvertiseTopicConverter {
    @Autowired
    ModelMapper modelMapper;

    public AdvertiseTopic toEntity(AdvertiseTopRequestModel advertiseTopRequestModel) {
        AdvertiseTopic advertiseTopic = modelMapper.map(advertiseTopRequestModel, AdvertiseTopic.class);
        if (advertiseTopRequestModel.getSubscribers().size() > 0) {
            advertiseTopic.setSubscribers(modelMapper.map(advertiseTopic.getSubscribers(), new TypeToken<List<AdvertiseTopic>>() {
            }.getType()));
        }
        return advertiseTopic;
    }

    public AdvertiseTopicResponseModel toModel(AdvertiseTopic advertiseTopic) {
        AdvertiseTopicResponseModel advertiseTopicResponseModel = modelMapper.map(advertiseTopic, AdvertiseTopicResponseModel.class);
        if (advertiseTopic.getSubscribers().size() > 0) {
            advertiseTopicResponseModel.setSubscriberResponseModels(modelMapper.map(advertiseTopic.getSubscribers(), new TypeToken<List<SubscriberResponseModel>>() {
            }.getType()));
        }
        return advertiseTopicResponseModel;
    }
}
