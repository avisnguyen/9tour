package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.*;
import xyz.nhatbao.ninetour.model.request.BillRequestModel;
import xyz.nhatbao.ninetour.model.response.*;

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

@Component
public class BillConverter {
    @Autowired
    ModelMapper modelMapper;

    public BillResponseModel bookingToBill(BookingResponseModel booking) {
        BillResponseModel result = new BillResponseModel();
        result.setAdultPrice(booking.getAdultPrice());
        result.setChildPrice(booking.getChildPrice());
        result.setInfantPrice(booking.getInfantPrice());
        result.setAdultQuantity(booking.getAdultQuantity());
        result.setChildQuantity(booking.getChildQuantity());
        result.setInfantQuantity(booking.getInfantQuantity());
        result.setTripId(booking.getTripId());
        result.setTourName(booking.getTourName());
        result.setCodeOfTrip(booking.getCodeOfTrip());
        return result;
    }

    public Bill responseToEntity(BillResponseModel model) {
        Bill result = new Bill();
        result.setAdultPrice(model.getAdultPrice());
        result.setChildPrice(model.getChildPrice());
        result.setInfantPrice(model.getInfantPrice());
        result.setAdultQuantity(model.getAdultQuantity());
        result.setChildQuantity(model.getChildQuantity());
        result.setInfantQuantity(model.getInfantQuantity());
        result.setTourName(model.getTourName());
        result.setCodeOfTrip(model.getCodeOfTrip());
        return result;
    }

    public Bill toEntity(BillRequestModel billRequestModel) {
        Bill bill = modelMapper.map(billRequestModel, Bill.class);
        bill.setTrip(modelMapper.map(billRequestModel.getTrip(), Trip.class));
        bill.setTicket(modelMapper.map(billRequestModel.getTicket(), Ticket.class));
        if (billRequestModel.getExtraBills().size() > 0)
            bill.setExtraBills(modelMapper.map(billRequestModel.getExtraBills(), new TypeToken<List<ExtraBill>>() {
            }.getType()));
        if (billRequestModel.getPaymentLogs().size() > 0)
            bill.setPaymentLogs(modelMapper.map(billRequestModel.getPaymentLogs(), new TypeToken<List<PaymentLog>>() {
            }.getType()));
        if (billRequestModel.getRefundRequestModels().size() > 0)
            bill.setRefundRequests(modelMapper.map(billRequestModel.getPaymentLogs(), new TypeToken<List<RefundRequest>>() {
            }.getType()));
        return bill;
    }

    public BillResponseModel toModel(Bill bill) {
        BillResponseModel billResponseModel = modelMapper.map(bill, BillResponseModel.class);
        billResponseModel.setTripResponseModel(modelMapper.map(bill.getTrip(), TripResponseModel.class));
        billResponseModel.setTicketResponseModel(modelMapper.map(bill.getTicket(), TicketResponseModel.class));
        if (bill.getExtraBills().size() > 0)
            billResponseModel.setExtraBillResponseModels(modelMapper.map(bill.getExtraBills(), new TypeToken<List<ExtraBillResponseModel>>() {
            }.getType()));
        if (bill.getPaymentLogs().size() > 0)
            billResponseModel.setPaymentLogResponseModels(modelMapper.map(bill.getPaymentLogs(), new TypeToken<List<PaymentLogResponseModel>>() {
            }.getType()));
        if (bill.getRefundRequests().size() > 0)
            billResponseModel.setRefundResponses(modelMapper.map(bill.getRefundRequests(), new TypeToken<List<RefundResponse>>() {
            }.getType()));
        return billResponseModel;
    }
}
