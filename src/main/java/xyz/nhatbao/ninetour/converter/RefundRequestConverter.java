package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Bill;
import xyz.nhatbao.ninetour.entity.RefundRequest;
import xyz.nhatbao.ninetour.model.request.RefundRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.RefundResponse;

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

@Component
public class RefundRequestConverter {
    @Autowired
    ModelMapper modelMapper;

    public RefundRequest toEntity(RefundRequestModel refundRequestModel) {
        RefundRequest request = modelMapper.map(refundRequestModel, RefundRequest.class);
        request.setBill(modelMapper.map(refundRequestModel.getBillRequestModel(), Bill.class));
        return request;
    }

    public RefundResponse toModel(RefundRequest request) {
        RefundResponse response = modelMapper.map(request, RefundResponse.class);
        response.setBillResponseModel(modelMapper.map(request.getBill(), BillResponseModel.class));
        return response;
    }
}
