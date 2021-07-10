package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.AdvertiseTopic;
import xyz.nhatbao.ninetour.entity.Subscriber;
import xyz.nhatbao.ninetour.model.request.SubscriberRequestModel;
import xyz.nhatbao.ninetour.model.response.AdvertiseTopicResponseModel;
import xyz.nhatbao.ninetour.model.response.SubscriberResponseModel;

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
public class SubscriberConverter {
    @Autowired
    ModelMapper modelMapper;

    public Subscriber toEntity(SubscriberRequestModel subscriberRequestModel) {
        Subscriber subscriber = modelMapper.map(subscriberRequestModel, Subscriber.class);
        if (subscriberRequestModel.getAdvertiseTopics().size() > 0)
            subscriber.setAdvertiseTopics(modelMapper.map(subscriberRequestModel.getAdvertiseTopics(), new TypeToken<Set<AdvertiseTopic>>() {
            }.getType()));
        return subscriber;
    }

    public SubscriberResponseModel toModel(Subscriber subscriber) {
        SubscriberResponseModel subscriberResponseModel = modelMapper.map(subscriber, SubscriberResponseModel.class);
        if (subscriber.getAdvertiseTopics().size() > 0)
            subscriberResponseModel.setAdvertiseTopicResponseModels(modelMapper.map(subscriber.getAdvertiseTopics(), new TypeToken<Set<AdvertiseTopicResponseModel>>() {
            }.getType()));
        return subscriberResponseModel;
    }
}
