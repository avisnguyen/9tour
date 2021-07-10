package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.model.request.CategoryRequestModel;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;

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

public interface CategoryService {
    CategoryResponseModel create(CategoryRequestModel categoryRequestModel);

    Long count();

    CategoryResponseModel getAll(Pageable pageable);

    CategoryResponseModel getAll();

    CategoryResponseModel findById(Long id);

    CategoryResponseModel update(Long id, CategoryRequestModel categoryRequestModel);

    CategoryResponseModel delete(CategoryRequestModel categoryRequestModel);

    String uploadFile(MultipartFile file, String path) throws IOException;

}
