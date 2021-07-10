package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Passenger;
import xyz.nhatbao.ninetour.model.response.PassengerResponseModel;
import xyz.nhatbao.ninetour.model.response.TicketResponseModel;
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
public class PassengerConverter {
    @Autowired
    ModelMapper modelMapper;

    public Passenger toEntity(PassengerResponseModel model) {
        Passenger result = new Passenger();
        result.setFirstName(model.getFirstName());
        result.setLastName(model.getLastName());
        result.setAge(model.getAge());
        result.setSex(model.getSex());
        result.setBirthday(model.getBirthday());
        result.setNationality(model.getNationality());
        result.setPhone(model.getPhone());
        return result;
    }

    public PassengerResponseModel toModel(Passenger passenger) {
        PassengerResponseModel passengerResponseModel = modelMapper.map(passenger, PassengerResponseModel.class);
        if (passenger.getUser() != null)
            passengerResponseModel.setUserResponseModel(modelMapper.map(passenger.getUser(), UserResponseModel.class));
        passengerResponseModel.setTicketResponseModel(modelMapper.map(passenger.getTicket(), TicketResponseModel.class));
        return passengerResponseModel;
    }
}
