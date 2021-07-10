package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.PaymentLog;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.PaymentLogResponseModel;

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
public class PaymentLogConverter {
    @Autowired
    ModelMapper modelMapper;

    public PaymentLog toEntity(PaymentLogResponseModel model) {
        PaymentLog result = new PaymentLog();
        result.setAmount(model.getAmount());
        result.setBankCode(model.getBankCode());
        result.setDescription(model.getDescription());
        result.setTransactionNumber(model.getTransactionNumber());
        result.setBillNumber(model.getBillNumber());
        result.setPayDate(model.getPayDate());
        result.setMethod(model.getMethod());
        return result;
    }

    public PaymentLogResponseModel toModel(PaymentLog paymentLog) {
        PaymentLogResponseModel paymentLogResponseModel = modelMapper.map(paymentLog, PaymentLogResponseModel.class);
        paymentLogResponseModel.setBillResponseModel(modelMapper.map(paymentLog.getBill(), BillResponseModel.class));
        return paymentLogResponseModel;
    }
}
