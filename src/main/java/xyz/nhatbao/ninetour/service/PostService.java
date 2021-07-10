package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.model.request.PostRequestModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;

import java.io.IOException;
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

public interface PostService {
    PostResponseModel create(PostRequestModel postRequestModel);

    Long count();

    PostResponseModel getAll(Pageable pageable);

    PostResponseModel findById(Long id);

    PostResponseModel update(Long id, PostRequestModel postRequestModel);

    PostResponseModel delete(PostRequestModel postRequestModel);

    List<PostResponseModel> findAllIdDesc();

    String uploadFile(MultipartFile file, String path) throws IOException;

    List<PostResponseModel> findAllByCategoryId(Long id);

    PostResponseModel findAllByCategoryId(Long id, Pageable pageable);
}
