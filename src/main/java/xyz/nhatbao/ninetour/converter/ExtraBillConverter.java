package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Bill;
import xyz.nhatbao.ninetour.entity.ExtraBill;
import xyz.nhatbao.ninetour.entity.ExtraService;
import xyz.nhatbao.ninetour.model.request.ExtraBillRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.ExtraBillResponseModel;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;

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
public class ExtraBillConverter {
    @Autowired
    ModelMapper modelMapper;

    public ExtraBill toEntity(ExtraBillRequestModel extraBillRequestModel) {
        ExtraBill extraBill = modelMapper.map(extraBillRequestModel, ExtraBill.class);
        extraBill.setBill(modelMapper.map(extraBillRequestModel.getBill(), Bill.class));
        extraBill.setExtraService(modelMapper.map(extraBillRequestModel.getExtraService(), ExtraService.class));
        return extraBill;
    }

    public ExtraBillResponseModel toModel(ExtraBill extraBill) {
        ExtraBillResponseModel extraBillResponseModel = modelMapper.map(extraBill, ExtraBillResponseModel.class);
        extraBillResponseModel.setBillResponseModel(modelMapper.map(extraBill.getBill(), BillResponseModel.class));
        extraBillResponseModel.setExtraServiceResponseModel(modelMapper.map(extraBill.getExtraService(), ExtraServiceResponseModel.class));
        return extraBillResponseModel;
    }
}
