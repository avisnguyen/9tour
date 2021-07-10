package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.converter.BillConverter;
import xyz.nhatbao.ninetour.converter.PassengerConverter;
import xyz.nhatbao.ninetour.converter.TourConverter;
import xyz.nhatbao.ninetour.entity.Bill;
import xyz.nhatbao.ninetour.entity.Passenger;
import xyz.nhatbao.ninetour.entity.Ticket;
import xyz.nhatbao.ninetour.entity.Trip;
import xyz.nhatbao.ninetour.model.request.BillRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.PassengerResponseModel;
import xyz.nhatbao.ninetour.repository.*;
import xyz.nhatbao.ninetour.service.BillService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private BillConverter billConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerConverter passengerConverter;

    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @Autowired
    private RefundRequestRepository refundRequestRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ExtraBillRepository extraBillRepository;

    @Override
    public BillResponseModel create(BillRequestModel billRequestModel) {
        BillResponseModel result = new BillResponseModel();
        Bill bill = billConverter.toEntity(billRequestModel);
        result = billConverter.toModel(billRepository.save(bill));
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return billRepository.count();
    }

    @Override
    public BillResponseModel getAll(Pageable pageable) {
        BillResponseModel result = new BillResponseModel();
        List<Bill> bills = billRepository.findAll(pageable).getContent();
        List<BillResponseModel> listResponse = new ArrayList<>();
        for (Bill bill :
                bills) {
            listResponse.add(billConverter.toModel(bill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public BillResponseModel getAll() {
        BillResponseModel result = new BillResponseModel();
        List<Bill> bills = billRepository.findAll();
        List<BillResponseModel> listResponse = new ArrayList<>();
        for (Bill bill :
                bills) {
            listResponse.add(billConverter.toModel(bill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public BillResponseModel searchBill(Long tourId, Long tripId, String keyword, Date from, Date to, Pageable pageable) {
        BillResponseModel result = new BillResponseModel();
        List<Bill> bills = billRepository.searchBill(tourId, tripId, keyword, from, to, pageable).getContent();
        List<BillResponseModel> listResponse = new ArrayList<>();
        for (Bill bill :
                bills) {
            listResponse.add(billConverter.toModel(bill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public BillResponseModel searchRefundRequestBill(Long tourId, Long tripId, String keyword, Date from, Date to, Pageable pageable) {
        BillResponseModel result = new BillResponseModel();
        List<Bill> bills = billRepository.searchRefundRequestBill(tourId, tripId, keyword, from, to, pageable).getContent();
        List<BillResponseModel> listResponse = new ArrayList<>();
        for (Bill bill :
                bills) {
            listResponse.add(billConverter.toModel(bill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public BillResponseModel findById(Long id) {
        BillResponseModel result = new BillResponseModel();
        Optional<Bill> billOpt = billRepository.findById(id);
        if (billOpt.isPresent()) {
            Bill bill = billOpt.get();
            result = billConverter.toModel(billOpt.get());
            List<PassengerResponseModel> passengerResponseModels = new ArrayList<>();
            for (Passenger passenger :
                    passengerRepository.findAllByTicketId(id)) {
                passengerResponseModels.add(passengerConverter.toModel(passenger));
            }
            result.getTicketResponseModel().setPassengerResponseModels(passengerResponseModels);
        }
        return result;
    }

    @Override
    public Long[] delete(Long[] ids) {
        return new Long[0];
    }

    @Override
    public BillResponseModel update(Long id, BillRequestModel billRequestModel) {
        BillResponseModel result = new BillResponseModel();
        Optional<Bill> billOptional = billRepository.findById(id);
        Bill oldBill = new Bill();
        Bill newBill = new Bill();

        //Bill not found
        if (!billOptional.isPresent()) {
            result = mapper.map(billRequestModel, BillResponseModel.class);
            result.setMessage("bill_not_found");
            return result;
        }
        oldBill = billOptional.get();
        newBill = billConverter.toEntity(billRequestModel);

        // Normal
        newBill.setCreatedBy(oldBill.getCreatedBy());
        newBill.setCreatedDate(oldBill.getCreatedDate());
        result = billConverter.toModel(billRepository.save(newBill));
        result.setMessage("update_success");
        return result;
    }

    @Override
    public BillResponseModel delete(BillRequestModel billRequestModel) {
        BillResponseModel result = new BillResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                billRequestModel.getIds()) {
            Optional<Bill> willDelBill = billRepository.findById(id);
            if (willDelBill.isPresent()) {
                extraBillRepository.deleteAll(extraBillRepository.findByBillId(willDelBill.get().getId()));
                refundRequestRepository.deleteAll(refundRequestRepository.findAllByBillId(willDelBill.get().getId()));
                passengerRepository.deleteAll(passengerRepository.findAllByTicketId(willDelBill.get().getId()));
                billRepository.deleteById(willDelBill.get().getId());
                ticketRepository.deleteById(willDelBill.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
        return result;
    }

    @Override
    public Bill save(BillResponseModel model) {
        Bill bill = billConverter.responseToEntity(model);
        Ticket ticket = ticketRepository.getOne(model.getTicketId());
        Trip trip = tripRepository.getOne(model.getTripId());
        bill.setTicket(ticket);
        bill.setTrip(trip);
        billRepository.save(bill);
        return bill;
    }

    @Override
    public List<BillResponseModel> getBillBooked(Long userId) {
        List<BillResponseModel> result = new ArrayList<>();
        List<Bill> bills = billRepository.getBillBooked(userId);
        for (Bill bill : bills) {
//            bill.setRefundRequests(refundRequestRepository.findAllByBillId(bill.getId()));
//            bill.setExtraBills(extraBillRepository.findByBillId(bill.getId()));
//            bill.setPaymentLogs(paymentLogRepository.findByBillId(bill.getId()));
            BillResponseModel billResponseModel = billConverter.toModel(bill);
            billResponseModel.getTripResponseModel().setTourResponseModel(tourConverter.toModel(bill.getTrip().getTour()));
            double totalPrice = billResponseModel.getAdultPrice() * billResponseModel.getAdultQuantity() + billResponseModel.getChildPrice() * billResponseModel.getChildQuantity() + billResponseModel.getInfantPrice() * billResponseModel.getInfantQuantity();
            for (int i = 0; i < billResponseModel.getExtraBillResponseModels().size(); i++) {
                totalPrice += billResponseModel.getExtraBillResponseModels().get(i).getPrice() * billResponseModel.getExtraBillResponseModels().get(i).getQuantity();
            }
            billResponseModel.setTotalPrice(totalPrice);
            result.add(billResponseModel);
        }
        return result;
    }

    @Override
    public BillResponseModel showBill(Long billId) {
        BillResponseModel billResponseModel = billConverter.toModel(billRepository.showBill(billId));
        double totalPrice = billResponseModel.getAdultPrice() * billResponseModel.getAdultQuantity() + billResponseModel.getChildPrice() * billResponseModel.getChildQuantity() + billResponseModel.getInfantPrice() * billResponseModel.getInfantQuantity();
        for (int i = 0; i < billResponseModel.getExtraBillResponseModels().size(); i++) {
            totalPrice += billResponseModel.getExtraBillResponseModels().get(i).getPrice() * billResponseModel.getExtraBillResponseModels().get(i).getQuantity();
        }
        billResponseModel.setTotalPrice(totalPrice);
        billResponseModel.setDescriptionPayment("Thanh toan lai cho don hang: " + billResponseModel.getTripResponseModel().getId() + " " + billResponseModel.getTotalPrice());
        return billResponseModel;
    }
}
