package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.model.request.TripRequestModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;

import java.io.IOException;
import java.util.Date;
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

public interface TripService {
    TripResponseModel create(TripRequestModel tripRequestModel);

    Long count();

    TripResponseModel getAll();

    TripResponseModel getAll(Pageable pageable);

    TripResponseModel findById(Long id);

    TripResponseModel update(Long id, TripRequestModel tripRequestModel);

    TripResponseModel delete(TripRequestModel tripRequestModel);

    List<TripResponseModel> findAllIdDesc();

    String uploadFile(MultipartFile file, String path) throws IOException;

    TripResponseModel getAllTripByTourId(Long tourId, Pageable pageable);

    TripResponseModel findAllById(Long id);

    TripResponseModel searchTrip(Long tourId, String keyword, Date from, Date to, Pageable pageable);

}
