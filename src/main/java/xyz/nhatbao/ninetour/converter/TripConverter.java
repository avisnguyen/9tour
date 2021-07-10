package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Bill;
import xyz.nhatbao.ninetour.entity.Tour;
import xyz.nhatbao.ninetour.entity.Trip;
import xyz.nhatbao.ninetour.model.request.TripRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;

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
public class TripConverter {
    @Autowired
    ModelMapper modelMapper;

    public Trip toEntity(TripRequestModel tripRequestModel) {
        Trip trip = modelMapper.map(tripRequestModel, Trip.class);
        trip.setTour(modelMapper.map(tripRequestModel.getTour(), Tour.class));
        if (tripRequestModel.getBills().size() > 0)
            trip.setBills(modelMapper.map(tripRequestModel.getBills(), new TypeToken<List<Bill>>() {
            }.getType()));
        return trip;
    }

    public TripResponseModel toModel(Trip trip) {
        TripResponseModel tripResponseModel = modelMapper.map(trip, TripResponseModel.class);
        tripResponseModel.setTourResponseModel(modelMapper.map(trip.getTour(), TourResponseModel.class));
        if (trip.getBills().size() > 0)
            tripResponseModel.setBillResponseModels(modelMapper.map(trip.getBills(), new TypeToken<List<BillResponseModel>>() {
            }.getType()));
        return tripResponseModel;
    }
}
