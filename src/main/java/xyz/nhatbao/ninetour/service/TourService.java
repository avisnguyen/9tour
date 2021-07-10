package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.model.request.TourRequestModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;

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

public interface TourService {
    TourResponseModel create(TourRequestModel tourRequestModel);

    Long count();

    TourResponseModel getAll(Pageable pageable);

    TourResponseModel getAll();

    TourResponseModel findById(Long id);

    TourResponseModel update(Long id, TourRequestModel tourRequestModel);

    TourResponseModel delete(TourRequestModel tourRequestModel);

    String uploadFile(MultipartFile file, String path) throws IOException;

    List<TourResponseModel> findAllIdDesc();

    TourResponseModel listAll(Pageable pageable);

    TourResponseModel listAll(Date from, Pageable pageable);

    TourResponseModel findTourById(Long id);

    List<TourResponseModel> findByDepartureId(Long id);

    TourResponseModel listTourByPlace(Long placeId, Pageable pageable);

    TourResponseModel searchTour(String keyword, String from, String to, Pageable pageable);

    TourResponseModel searchTour(String keyword, Pageable pageable);
}
