package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import xyz.nhatbao.ninetour.model.request.ExtraBillRequestModel;
import xyz.nhatbao.ninetour.model.response.ExtraBillResponseModel;

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

public interface ExtraBillService {
    ExtraBillResponseModel create(ExtraBillRequestModel extraBillRequestModel);

    Long count();

    ExtraBillResponseModel getAll(Pageable pageable);

    ExtraBillResponseModel findById(Long id);

    ExtraBillResponseModel delete(ExtraBillRequestModel extraBillRequestModel);
}
