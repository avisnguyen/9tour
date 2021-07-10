package xyz.nhatbao.ninetour.converter;

import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Ticket;
import xyz.nhatbao.ninetour.model.response.BookingResponseModel;
import xyz.nhatbao.ninetour.model.response.TicketResponseModel;

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
public class TicketConverter {
    public TicketResponseModel toModel(BookingResponseModel booking) {
        TicketResponseModel result = new TicketResponseModel();
        result.setFirstName(booking.getFirstName());
        result.setLastName(booking.getLastName());
        result.setEmail(booking.getEmail());
        result.setPhone(booking.getPhone());
        result.setAddress(booking.getAddress());
        result.setUserId(booking.getUserId());
        result.setNationality(booking.getNationality());
        return result;
    }

    public Ticket toEntity(TicketResponseModel model) {
        Ticket result = new Ticket();
        result.setFirstName(model.getFirstName());
        result.setLastName(model.getLastName());
        result.setEmail(model.getEmail());
        result.setPhone(model.getPhone());
        result.setAddress(model.getAddress());
        result.setNationality(model.getNationality());
        return result;
    }
}
