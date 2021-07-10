package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.converter.BillConverter;
import xyz.nhatbao.ninetour.converter.PassengerConverter;
import xyz.nhatbao.ninetour.converter.PaymentLogConverter;
import xyz.nhatbao.ninetour.converter.TicketConverter;
import xyz.nhatbao.ninetour.entity.*;
import xyz.nhatbao.ninetour.model.request.BookingRequestModel;
import xyz.nhatbao.ninetour.model.response.*;
import xyz.nhatbao.ninetour.repository.*;
import xyz.nhatbao.ninetour.service.BillService;
import xyz.nhatbao.ninetour.service.BookingService;
import xyz.nhatbao.ninetour.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BillService billService;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private BillConverter billConverter;

    @Autowired
    private PassengerConverter passengerConverter;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @Autowired
    private PaymentLogConverter paymentLogConverter;

    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    @Autowired
    private ExtraBillRepository extraBillRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public BookingResponseModel setPaxNo(BookingRequestModel bookingRequestModel) {
        BookingResponseModel result = new BookingResponseModel();
        double priceService = 0;
        if (bookingRequestModel.getAdultQuantity() == null) bookingRequestModel.setAdultQuantity(0);
        if (bookingRequestModel.getChildQuantity() == null) bookingRequestModel.setChildQuantity(0);
        if (bookingRequestModel.getInfantQuantity() == null) bookingRequestModel.setInfantQuantity(0);
        bookingRequestModel.setTotalPassenger(bookingRequestModel.getAdultQuantity() + bookingRequestModel.getChildQuantity() + bookingRequestModel.getInfantQuantity());
        if (bookingRequestModel.getExtraServices() != null) {
            for (int i = 0; i < bookingRequestModel.getExtraServices().size(); i++) {
                if (bookingRequestModel.getExtraServices().get(i).getQuantity() == null) {
                    bookingRequestModel.getExtraServices().get(i).setQuantity(0);
                }
                priceService += (bookingRequestModel.getExtraServices().get(i).getPrice()) * (bookingRequestModel.getExtraServices().get(i).getQuantity());
            }
        }
        bookingRequestModel.setTotalExtraService(priceService);
        bookingRequestModel.setPassengers(new ArrayList<>(bookingRequestModel.getTotalPassenger()));
        bookingRequestModel.setTotalPrice(bookingRequestModel.getAdultQuantity() * bookingRequestModel.getAdultPrice() +
                bookingRequestModel.getChildQuantity() * bookingRequestModel.getChildPrice() +
                bookingRequestModel.getInfantQuantity() * bookingRequestModel.getInfantPrice() + bookingRequestModel.getTotalExtraService());
        result = modelMapper.map(bookingRequestModel, BookingResponseModel.class);
        return result;
    }

    @Override
    public BookingResponseModel setPassengers(BookingRequestModel bookingRequestModel, HttpServletRequest request) {
        BookingResponseModel result = new BookingResponseModel();
        BookingResponseModel bookingResponseModel = (BookingResponseModel) request.getSession().getAttribute("booking");
        bookingRequestModel.setDescription("Thanh toan cho don hang: " + bookingRequestModel.getTripId() + ' ' + bookingRequestModel.getTotalPrice());
        result = modelMapper.map(bookingRequestModel, BookingResponseModel.class);
        result.setExtraServices(bookingResponseModel.getExtraServices());
        return result;
    }

    @Override
    public void processBooking(HttpServletRequest request) {
        BookingResponseModel bookingResponseModel = (BookingResponseModel) request.getSession().getAttribute("booking");
        TicketResponseModel ticketResponseModel = ticketConverter.toModel(bookingResponseModel);
        BillResponseModel billResponseModel = billConverter.bookingToBill(bookingResponseModel);
        List<PassengerResponseModel> passengerResponseModels = bookingResponseModel.getPassengers();

        ticketResponseModel.setUserId(bookingResponseModel.getUserId());
        Ticket ticket = ticketService.save(ticketResponseModel);
        billResponseModel.setTicketId(ticket.getId());
        Bill bill = billService.save(billResponseModel);
        for (PassengerResponseModel passengerResponseModel : passengerResponseModels) {
            Passenger passenger = passengerConverter.toEntity(passengerResponseModel);
            passenger.setTicket(ticket);
            passengerRepository.save(passenger);
        }

        if (bookingResponseModel.getExtraServices() != null) {
            ExtraBillResponseModel extraBillResponseModel = new ExtraBillResponseModel();
            ExtraBill extraBill = new ExtraBill();
            ExtraService extraService = new ExtraService();
            for (ExtraServiceResponseModel extraServiceResponseModel : bookingResponseModel.getExtraServices()) {
                if (extraServiceResponseModel.getQuantity() >= 1) {
                    extraBillResponseModel.setPrice(extraServiceResponseModel.getPrice());
                    extraBillResponseModel.setQuantity(extraServiceResponseModel.getQuantity());
                    extraService = extraServiceRepository.getOne(extraServiceResponseModel.getId());
                    extraBill.setPrice(extraServiceResponseModel.getPrice());
                    extraBill.setQuantity(extraBillResponseModel.getQuantity());
                    extraBill.setNameOfService(extraService.getName());
                    extraBill.setBill(bill);
                    extraBill.setExtraService(extraService);
                    extraBillRepository.save(extraBill);
                }
            }
        }
        bookingResponseModel.setBillId(bill.getId());
        request.getSession().setAttribute("billId", bookingResponseModel.getBillId());

    }

    @Override
    public void setPaymentLog(HttpServletRequest request) throws ParseException {
        Long billId = (Long) request.getSession().getAttribute("billId");
        Bill bill = billRepository.getOne(billId);
        boolean isExist = false;
        for (PaymentLog paymentLog : bill.getPaymentLogs()) {
            if (paymentLog.getTransactionNumber().equals(request.getParameter("vnp_TransactionNo"))) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            PaymentLogResponseModel paymentLogResponseModel = new PaymentLogResponseModel();
            paymentLogResponseModel.setMethod("VNPAY");
            double amount = Double.parseDouble(request.getParameter("vnp_Amount")) / 100;
            paymentLogResponseModel.setAmount(amount);
            paymentLogResponseModel.setDescription(request.getParameter("vnp_OrderInfo"));
            paymentLogResponseModel.setBankCode(request.getParameter("vnp_BankCode"));
            paymentLogResponseModel.setTransactionNumber(request.getParameter("vnp_TransactionNo"));
            paymentLogResponseModel.setBillNumber(request.getParameter("vnp_TxnRef"));
            Date payDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(request.getParameter("vnp_PayDate"));
            paymentLogResponseModel.setPayDate(payDate);

            PaymentLog paymentLog = paymentLogConverter.toEntity(paymentLogResponseModel);
            paymentLog.setBill(bill);
            paymentLogRepository.save(paymentLog);
        }
    }
}
