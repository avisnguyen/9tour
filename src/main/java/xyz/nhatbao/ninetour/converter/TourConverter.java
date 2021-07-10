package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.*;
import xyz.nhatbao.ninetour.model.request.TourRequestModel;
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
public class TourConverter {
    @Autowired
    ModelMapper modelMapper;

    public Tour toEntity(TourRequestModel tourRequestModel) {
        Tour tour = modelMapper.map(tourRequestModel, Tour.class);
        tour.setDeparture(modelMapper.map(tourRequestModel.getDeparture(), Place.class));
        tour.setDestination(modelMapper.map(tourRequestModel.getDestination(), Place.class));
        if (tourRequestModel.getPlaces().size() > 0)
            tour.setPlaces(modelMapper.map(tourRequestModel.getPlaces(), new TypeToken<Set<Place>>() {
            }.getType()));
        if (tourRequestModel.getServices().size() > 0)
            tour.setExtraServices(modelMapper.map(tourRequestModel.getServices(), new TypeToken<Set<ExtraService>>() {
            }.getType()));
        if (tourRequestModel.getTrips().size() > 0)
            tour.setTrips(modelMapper.map(tourRequestModel.getTrips(), new TypeToken<List<Trip>>() {
            }.getType()));
        if (tourRequestModel.getRatings().size() > 0)
            tour.setRatings(modelMapper.map(tourRequestModel.getRatings(), new TypeToken<List<Rating>>() {
            }.getType()));
        return tour;
    }

    public TourResponseModel toModel(Tour tour) {
        TourResponseModel tourResponseModel = modelMapper.map(tour, TourResponseModel.class);
        tourResponseModel.setDepartureModel(modelMapper.map(tour.getDeparture(), PlaceResponseModel.class));
        tourResponseModel.setDestinationModel(modelMapper.map(tour.getDestination(), PlaceResponseModel.class));
        if (tour.getPlaces().size() > 0)
            tourResponseModel.setPlaceResponseModels(new ArrayList<>(modelMapper.map(tour.getPlaces(), new TypeToken<Set<PlaceResponseModel>>() {
            }.getType())));
        if (tour.getExtraServices().size() > 0)
            tourResponseModel.setExtraServiceResponseModels(new ArrayList<>(modelMapper.map(tour.getExtraServices(), new TypeToken<Set<ExtraServiceResponseModel>>() {
            }.getType())));
        if (tour.getTrips().size() > 0)
            tourResponseModel.setTripResponseModels(modelMapper.map(tour.getTrips(), new TypeToken<List<TripResponseModel>>() {
            }.getType()));
        if (tour.getRatings().size() > 0)
            tourResponseModel.setRatingResponseModels(modelMapper.map(tour.getRatings(), new TypeToken<List<RatingResponseModel>>() {
            }.getType()));
        return tourResponseModel;
    }
}
