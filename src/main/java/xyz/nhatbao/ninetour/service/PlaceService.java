package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.model.request.PlaceRequestModel;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;

import java.io.IOException;

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

public interface PlaceService {
    PlaceResponseModel create(PlaceRequestModel placeRequestModel);

    Long count();

    PlaceResponseModel getAll(Pageable pageable);

    PlaceResponseModel getAll();

    PlaceResponseModel findById(Long id);

    PlaceResponseModel update(Long id, PlaceRequestModel placeRequestModel);

    PlaceResponseModel delete(PlaceRequestModel placeRequestModel);

    String uploadFile(MultipartFile file, String path) throws IOException;

}
