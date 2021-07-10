package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.entity.*;
import xyz.nhatbao.ninetour.model.request.BookingRequestModel;
import xyz.nhatbao.ninetour.model.request.ExtraServiceRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.repository.*;
import xyz.nhatbao.ninetour.service.AdminBookingService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
public class AdminBookingServiceImpl implements AdminBookingService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    ExtraServiceRepository extraServiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExtraBillRepository extraBillRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    RefundRequestRepository refundRequestRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BillResponseModel order(BookingRequestModel bookingRequestModel) {
        BillResponseModel result = new BillResponseModel();
        Bill bill = handleBill(bookingRequestModel);

        if (bill == null) {
            result.setMessage("trip_not_found");
            return result;
        }
        bill = billRepository.saveAndFlush(bill);

        //Set service bill
        if (bookingRequestModel.getExtraServices() != null) {
            List<ExtraBill> extraBills = new ArrayList<>();
            for (ExtraServiceRequestModel extraService :
                    bookingRequestModel.getExtraServices()) {
                if (extraService.getQuantity() > 0) {
                    ExtraService service = extraServiceRepository.getOne(extraService.getId());
                    ExtraBill extraBill = new ExtraBill();
                    extraBill.setExtraService(service);
                    extraBill.setNameOfService(service.getName());
                    extraBill.setPrice(service.getPrice());
                    extraBill.setQuantity(extraService.getQuantity());
                    extraBill.setBill(bill);
                    extraBills.add(extraBillRepository.save(extraBill));
                }
            }
            bill.setExtraBills(extraBills);
        }

        result = modelMapper.map(bill, BillResponseModel.class);
        result.setMessage("insert_success");

        return result;
    }

    @Override
    public BillResponseModel update(Long id, BookingRequestModel bookingRequestModel) {
        BillResponseModel result = new BillResponseModel();

        Bill oldBill = new Bill();
        if (billRepository.findById(id).isPresent()) {
            oldBill = billRepository.findById(id).get();
        } else {
            result.setMessage("tour_not_found");
            return result;
        }

        Bill newBill = handleBill(bookingRequestModel);
        if (newBill == null) {
            result.setMessage("trip_not_found");
            return result;
        }

        newBill.setId(oldBill.getId());
        newBill.setAdultPrice(oldBill.getAdultPrice());
        newBill.setAdultQuantity(oldBill.getAdultQuantity());
        newBill.setChildPrice(oldBill.getChildPrice());
        newBill.setChildQuantity(oldBill.getChildQuantity());
        newBill.setInfantPrice(oldBill.getInfantPrice());
        newBill.setInfantQuantity(oldBill.getInfantQuantity());
        newBill.setExtraBills(oldBill.getExtraBills());
        newBill.setCreatedBy(oldBill.getCreatedBy());
        newBill.setCreatedDate(oldBill.getCreatedDate());
        newBill.getTicket().setId(oldBill.getId());

        newBill = billRepository.saveAndFlush(newBill);

        //Set passengers
        if (bookingRequestModel.getPassengers().size() > 0) {
            List<Passenger> recentPassengers = passengerRepository.findAllByTicketId(newBill.getTicket().getId());
            List<Passenger> newPassengers = new ArrayList<>();
            for (int i = 0; i < recentPassengers.size(); i++) {
                recentPassengers.get(i).setFirstName(bookingRequestModel.getPassengers().get(i).getFirstName());
                recentPassengers.get(i).setLastName(bookingRequestModel.getPassengers().get(i).getLastName());
                recentPassengers.get(i).setPhone(bookingRequestModel.getPassengers().get(i).getPhone());
                recentPassengers.get(i).setSex(bookingRequestModel.getPassengers().get(i).getSex());
                recentPassengers.get(i).setBirthday(bookingRequestModel.getPassengers().get(i).getBirthday());
                recentPassengers.get(i).setNationality(bookingRequestModel.getPassengers().get(i).getNationality());
                passengerRepository.save(recentPassengers.get(i));
            }
            for (int i = recentPassengers.size(); i < bookingRequestModel.getPassengers().size(); i++) {
                Passenger passenger = modelMapper.map(bookingRequestModel.getPassengers().get(i), Passenger.class);
                passenger.setTicket(newBill.getTicket());
                passengerRepository.save(passenger);
            }
            newBill.getTicket().setPassengers(passengerRepository.findAllByTicketId(newBill.getTicket().getId()));
        }

        //Set refund request
        if (bookingRequestModel.getStatus() != null && !bookingRequestModel.getStatus().equals("no-update")) {
            List<RefundRequest> refundRequests = refundRequestRepository.findAllByBillId(newBill.getId());
            RefundRequest refundRequest = new RefundRequest();
            if (refundRequests.size() > 0) {
                for (int i = 0; i < refundRequests.size(); i++) {
                    if (refundRequests.get(i).getStatus().equals("waiting") || refundRequests.get(i).getStatus().equals("")) {
                        refundRequests.get(i).setStatus("closed");
                    }
                }
                refundRequest = refundRequests.get(refundRequests.size() - 1);
            } else {
                refundRequest.setSender(bookingRequestModel.getSender());
                refundRequest.setContactMail(bookingRequestModel.getContactMail());
                refundRequest.setContactPhone(bookingRequestModel.getContactPhone());
            }
            refundRequest.setResponse(bookingRequestModel.getResponse());
            refundRequest.setReceiver(newBill.getTicket().getUser() != null ? newBill.getTicket().getUser().getEmail() : newBill.getTicket().getEmail());
            refundRequest.setNote(bookingRequestModel.getNote());
            refundRequest.setStatus(bookingRequestModel.getStatus());
            refundRequest.setBill(newBill);
            refundRequests.add(refundRequestRepository.save(refundRequest));
            newBill.setRefundRequests(refundRequests);
        }

        result = modelMapper.map(newBill, BillResponseModel.class);
        result.setMessage("update_success");

        return result;
    }

    private Bill handleBill(BookingRequestModel bookingRequestModel) {
        Optional<Trip> tripOptional = tripRepository.findById(bookingRequestModel.getTripId());
        if (!tripOptional.isPresent()) return null;
        Trip trip = tripOptional.get();
        List<ExtraService> extraServices = new ArrayList<>(trip.getTour().getExtraServices());

        if (bookingRequestModel.getAdultQuantity() == null) bookingRequestModel.setAdultQuantity(0);
        if (bookingRequestModel.getChildQuantity() == null) bookingRequestModel.setChildQuantity(0);
        if (bookingRequestModel.getInfantQuantity() == null) bookingRequestModel.setInfantQuantity(0);
        bookingRequestModel.setTotalPassenger(bookingRequestModel.getAdultQuantity() + bookingRequestModel.getChildQuantity() + bookingRequestModel.getInfantQuantity());
        if (bookingRequestModel.getExtraServices() != null) {
            for (int i = 0; i < bookingRequestModel.getExtraServices().size(); i++) {
                if (bookingRequestModel.getExtraServices().get(i).getQuantity() == null) {
                    bookingRequestModel.getExtraServices().get(i).setQuantity(0);
                }
                bookingRequestModel.getExtraServices().get(i).setPrice(extraServices.get(i).getPrice());
            }
        }

        Bill bill = new Bill();

        //Set tour trip
        bill.setTourName(trip.getTour().getName());
        bill.setCodeOfTrip(trip.getCode());
        bill.setDepartureTimeOfTrip(trip.getDepartureTime());
        bill.setTrip(trip);

        //Set price and quantity
        bill.setAdultQuantity(bookingRequestModel.getAdultQuantity());
        bill.setAdultPrice(trip.getAdultPrice());
        bill.setChildQuantity(bookingRequestModel.getChildQuantity());
        bill.setChildPrice(trip.getChildPrice());
        bill.setInfantQuantity(bookingRequestModel.getInfantQuantity());
        bill.setInfantPrice(trip.getInfantPrice());

        //Set ticket
        Ticket ticket = new Ticket();
        ticket.setFirstName(bookingRequestModel.getFirstName());
        ticket.setLastName(bookingRequestModel.getLastName());
        ticket.setEmail(bookingRequestModel.getEmail());
        ticket.setPhone(bookingRequestModel.getPhone());
        ticket.setAddress(bookingRequestModel.getAddress());
        ticket.setNationality(bookingRequestModel.getNationality());
        bill.setTicket(ticket);
        if (bookingRequestModel.getUserId() > 0) {
            Optional<User> user = userRepository.findById(bookingRequestModel.getUserId());
            user.ifPresent(value -> bill.getTicket().setUser(value));
        }
        return bill;
    }
}
