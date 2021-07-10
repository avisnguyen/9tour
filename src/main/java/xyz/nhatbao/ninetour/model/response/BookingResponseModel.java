package xyz.nhatbao.ninetour.model.response;

import lombok.Getter;
import lombok.Setter;

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
public class BookingResponseModel {

    private Integer adultQuantity;
    private Integer childQuantity;
    private Integer infantQuantity;
    private Double adultPrice;
    private Double childPrice;
    private Double infantPrice;
    private Double totalPrice;
    private Integer totalPassenger;
    private List<PassengerResponseModel> passengers;
    private List<ExtraServiceResponseModel> extraServices;
    private Double totalExtraService;
    private Long tripId;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String nationality;

    private long billId;

    private long contactInfoId;

    private long userId;

    private long ticketId;

    private long paymentLogId;

    private String description;

    private String tourName;

    private String codeOfTrip;

}
