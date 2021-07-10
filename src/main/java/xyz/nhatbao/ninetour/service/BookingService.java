package xyz.nhatbao.ninetour.service;

import xyz.nhatbao.ninetour.model.request.BookingRequestModel;
import xyz.nhatbao.ninetour.model.response.BookingResponseModel;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

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

public interface BookingService {
    BookingResponseModel setPaxNo(BookingRequestModel bookingRequestModel);

    BookingResponseModel setPassengers(BookingRequestModel bookingRequestModel, HttpServletRequest request);

    void processBooking(HttpServletRequest request);

    void setPaymentLog(HttpServletRequest request) throws ParseException;
}
