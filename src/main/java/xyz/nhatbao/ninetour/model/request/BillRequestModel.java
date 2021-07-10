package xyz.nhatbao.ninetour.model.request;

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
public class BillRequestModel extends BaseRequestModel<BillRequestModel> {
    private String tourName;
    private String codeOfTrip;
    private Date departureTimeOfTrip;
    private Double adultPrice;
    private Integer adultQuantity;
    private Double childPrice;
    private Integer childQuantity;
    private Double infantPrice;
    private Integer infantQuantity;

    private Long trip;

    private List<Long> extraBills = new ArrayList<>();

    private List<Long> paymentLogs = new ArrayList<>();

    private Long ticket;
    private List<RefundRequestModel> refundRequestModels = new ArrayList<>();
}
