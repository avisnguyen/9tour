package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import xyz.nhatbao.ninetour.entity.Bill;
import xyz.nhatbao.ninetour.model.request.BillRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;

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

public interface BillService {
    BillResponseModel create(BillRequestModel billRequestModel);

    Long count();

    BillResponseModel getAll(Pageable pageable);

    BillResponseModel getAll();

    BillResponseModel searchBill(Long tourId, Long tripId, String keyword, Date from, Date to, Pageable pageable);

    BillResponseModel searchRefundRequestBill(Long tourId, Long tripId, String keyword, Date from, Date to, Pageable pageable);

    BillResponseModel findById(Long id);

    Long[] delete(Long[] ids);

    Bill save(BillResponseModel model);

    BillResponseModel update(Long id, BillRequestModel billRequestModel);

    BillResponseModel delete(BillRequestModel billRequestModel);

    List<BillResponseModel> getBillBooked(Long userId);

    BillResponseModel showBill(Long billId);
}
