package xyz.nhatbao.ninetour.model.request;

import lombok.Getter;
import lombok.Setter;

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

@Getter
@Setter
public class BookingRequestModel {
    private String tourName;
    private String codeOfTrip;
    private Date departureTimeOfTrip;

    //trip
    private Integer adultQuantity;
    private Integer childQuantity;
    private Integer infantQuantity;
    private Double adultPrice;
    private Double childPrice;
    private Double infantPrice;
    private Integer totalPassenger;
    private List<PassengerRequestModel> passengers;
    private List<ExtraServiceRequestModel> extraServices;
    private Double totalExtraService;
    private Double totalPrice;
    private Long tripId;


    //contact info
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String nationality;

    private Long billId;

    private long contactInfoId;

    private long userId;

    private long ticketId;

    private long paymentLogId;

    private String description;

    //Refund Request
    private String sender;
    private String contactMail;
    private String contactPhone;
    private String status;
    private String request;
    private String response;
    private String note;
    private String receiver;

}
