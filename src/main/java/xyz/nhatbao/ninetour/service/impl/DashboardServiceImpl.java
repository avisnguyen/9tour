package xyz.nhatbao.ninetour.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.model.response.DashboardModel;
import xyz.nhatbao.ninetour.repository.*;
import xyz.nhatbao.ninetour.service.DashboardService;

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
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    TourRepository tourRepository;

    @Autowired
    TripRepository tripRepository;


    @Autowired
    PostRepository postRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PaymentLogRepository paymentLogRepository;


    @Override
    public DashboardModel getData() {
        DashboardModel result = new DashboardModel();
        result.setTotalTour(tourRepository.count());
        result.setTotalTrip(tripRepository.count());
        result.setTotalPost(postRepository.count());
        result.setTotalTicket(ticketRepository.count());
        result.setTotalUser(userRepository.count());
        result.setTotalPassenger(passengerRepository.count());
        result.setTotalMoney(paymentLogRepository.getTotalMoney());
        return result;
    }
}
