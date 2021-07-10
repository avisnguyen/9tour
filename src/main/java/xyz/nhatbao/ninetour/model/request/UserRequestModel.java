package xyz.nhatbao.ninetour.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
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
public class UserRequestModel extends BaseRequestModel<UserRequestModel> {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String passport;
    private String sex;
    private String phone;
    private String address;
    private String nationality;
    private Boolean isEnable;

    private List<TicketRequestModel> tickets = new ArrayList<>();

//    private List<AnonymousUser> anonymousUsers = new ArrayList<>();

    private List<PassengerRequestModel> passengers = new ArrayList<>();

    private List<RatingRequestModel> ratings = new ArrayList<>();

    private List<Long> roles = new ArrayList<>();
}
