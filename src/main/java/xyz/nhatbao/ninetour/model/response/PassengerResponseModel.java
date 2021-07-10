package xyz.nhatbao.ninetour.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


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
public class PassengerResponseModel extends BaseResponseModel<PassengerResponseModel> {
    private String firstName;
    private String lastName;
    private Integer age;
    private String phone;
    private String sex;
    private String address;
    private String nationality;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private TicketResponseModel ticketResponseModel;
    private Long TicketId;

    private UserResponseModel userResponseModel;
}
