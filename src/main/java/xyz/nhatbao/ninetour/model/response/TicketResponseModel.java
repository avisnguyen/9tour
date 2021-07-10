package xyz.nhatbao.ninetour.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

@EqualsAndHashCode(callSuper = true)
@Data
public class TicketResponseModel extends BaseResponseModel<TicketResponseModel> {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String nationality;
    private String pickupPlace;
    private Date pickupTime;
    private String returnPlace;
    private String customerNote;

    private BillResponseModel billResponseModel;

    private UserResponseModel userResponseModel;

    private Long userId;

//    private AnonymousUser anonymousUser;

    private List<PassengerResponseModel> passengerResponseModels = new ArrayList<>();
}
