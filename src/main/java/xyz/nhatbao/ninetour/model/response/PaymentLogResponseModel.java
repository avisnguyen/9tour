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
public class PaymentLogResponseModel extends BaseResponseModel<PaymentLogResponseModel> {
    private String method;
    private String content;
    private String description;
    private String note;
    private String type;
    private Double amount;
    private String transactionNumber;
    private String billNumber;
    private String bankCode;
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date payDate;

    private BillResponseModel billResponseModel;
    private Long billId;
}
