package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.model.request.ExtraServiceRequestModel;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;

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

public interface ExtraServiceService {
    ExtraServiceResponseModel create(ExtraServiceRequestModel extraServiceRequestModel);

    Long count();

    ExtraServiceResponseModel getAll(Pageable pageable);

    ExtraServiceResponseModel getAll();

    ExtraServiceResponseModel findById(Long id);

    ExtraServiceResponseModel update(Long id, ExtraServiceRequestModel extraServiceRequestModel);

    ExtraServiceResponseModel delete(ExtraServiceRequestModel extraServiceRequestModel);

    String uploadFile(MultipartFile file, String path) throws IOException;

    ExtraServiceResponseModel searchExtraService(Long tourId, String keyword, Pageable pageable);

    ExtraServiceResponseModel findServiceByTourId(Long id);
}
