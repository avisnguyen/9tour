package xyz.nhatbao.ninetour.model.response;

import lombok.Data;

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

@Data
public class DashboardModel {
    private Long totalTour;
    private Long totalTrip;
    private Long totalPost;
    private Long totalTicket;
    private Long totalUser;
    private Long totalPassenger;
    private Double totalMoney;
}
