package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.*;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.*;

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
public class UserConverter {
    @Autowired
    ModelMapper modelMapper;

    public User toEntity(UserRequestModel userRequestModel) {
        User user = modelMapper.map(userRequestModel, User.class);
        if (userRequestModel.getTickets().size() > 0)
            user.setTickets(modelMapper.map(userRequestModel.getTickets(), new TypeToken<List<Ticket>>() {
            }.getType()));
        if (userRequestModel.getPassengers().size() > 0)
            user.setPassengers(modelMapper.map(userRequestModel.getPassengers(), new TypeToken<List<Passenger>>() {
            }.getType()));
        if (userRequestModel.getRatings().size() > 0)
            user.setRatings(modelMapper.map(userRequestModel.getRatings(), new TypeToken<List<Rating>>() {
            }.getType()));
        if (userRequestModel.getRoles().size() > 0)
            user.setRoles(modelMapper.map(userRequestModel.getRoles(), new TypeToken<Set<Role>>() {
            }.getType()));
        return user;
    }

    public UserResponseModel toModel(User user) {
        UserResponseModel userResponseModel = modelMapper.map(user, UserResponseModel.class);
        if (user.getTickets().size() > 0)
            userResponseModel.setTicketResponseModels(modelMapper.map(user.getTickets(), new TypeToken<List<TicketResponseModel>>() {
            }.getType()));
        if (user.getPassengers().size() > 0)
            userResponseModel.setPassengerResponseModels(modelMapper.map(user.getPassengers(), new TypeToken<List<PassengerResponseModel>>() {
            }.getType()));
        if (user.getRatings().size() > 0)
            userResponseModel.setRatingResponseModels(modelMapper.map(user.getRatings(), new TypeToken<List<RatingResponseModel>>() {
            }.getType()));
        if (user.getRoles().size() > 0)
            userResponseModel.setRoleResponseModels(new ArrayList<>(modelMapper.map(user.getRoles(), new TypeToken<Set<RoleResponseModel>>() {
            }.getType())));
        return userResponseModel;
    }
}
