package xyz.nhatbao.ninetour.service;

import xyz.nhatbao.ninetour.model.request.RoleRequestModel;
import xyz.nhatbao.ninetour.model.response.RoleResponseModel;

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

public interface RoleService {
    RoleResponseModel create(RoleRequestModel roleRequestModel);

    Long count();

    RoleResponseModel getAll();

    RoleResponseModel findById(Long id);

    RoleResponseModel delete(List<Long> ids);
}
