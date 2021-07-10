package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Rating;
import xyz.nhatbao.ninetour.entity.Tour;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.RatingRequestModel;
import xyz.nhatbao.ninetour.model.response.RatingResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;

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
public class RatingConverter {
    @Autowired
    ModelMapper modelMapper;

    public Rating toEntity(RatingRequestModel ratingRequestModel) {
        Rating rating = modelMapper.map(ratingRequestModel, Rating.class);
        rating.setUser(modelMapper.map(ratingRequestModel.getUserRequestModel(), User.class));
        rating.setTour(modelMapper.map(ratingRequestModel.getTourRequestModel(), Tour.class));
        return rating;
    }

    public RatingResponseModel toModel(Rating rating) {
        RatingResponseModel ratingResponseModel = modelMapper.map(rating, RatingResponseModel.class);
        ratingResponseModel.setUserResponseModel(modelMapper.map(rating.getUser(), UserResponseModel.class));
        ratingResponseModel.setTourResponseModel(modelMapper.map(rating.getTour(), TourResponseModel.class));
        return ratingResponseModel;
    }
}
