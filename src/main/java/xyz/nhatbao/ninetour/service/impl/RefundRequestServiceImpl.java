package xyz.nhatbao.ninetour.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.entity.Bill;
import xyz.nhatbao.ninetour.entity.RefundRequest;
import xyz.nhatbao.ninetour.model.request.RefundRequestModel;
import xyz.nhatbao.ninetour.repository.BillRepository;
import xyz.nhatbao.ninetour.repository.RefundRequestRepository;
import xyz.nhatbao.ninetour.service.RefundRequestService;

import javax.transaction.Transactional;

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

@Service
@Transactional
public class RefundRequestServiceImpl implements RefundRequestService {

    @Autowired
    private RefundRequestRepository refundRequestRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public String refund(RefundRequestModel refundRequestModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sender = authentication.getName();
        RefundRequest request = new RefundRequest();
        request.setContactMail(refundRequestModel.getContactMail());
        request.setContactPhone(refundRequestModel.getContactPhone());
        request.setRequest(refundRequestModel.getRequest());
        request.setStatus("waiting");
        request.setSender(sender);
        Bill bill = billRepository.getOne(refundRequestModel.getBillId());
        request.setBill(bill);
        refundRequestRepository.save(request);
        return "refund_success";
    }
}
