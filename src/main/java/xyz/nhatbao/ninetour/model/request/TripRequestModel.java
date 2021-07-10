package xyz.nhatbao.ninetour.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
public class TripRequestModel extends BaseRequestModel<TripRequestModel> {
    private String departure;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date departureTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date returnTime;
    private Double adultPrice;
    private Integer adultMaximum;
    private Double childPrice;
    private Integer childMaximum;
    private Double infantPrice;
    private Integer infantMaximum;
    private String note;

    private Long tour;

    private List<Long> bills = new ArrayList<>();
}
