package xyz.nhatbao.ninetour.model.request;

import lombok.Data;

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

@Data
public abstract class BaseRequestModel<T> {
    private Long id;
    private String code;
    private Date createdDate;
    private Long createdBy;
    private Boolean isDeleted = false;
    private List<Long> ids = new ArrayList<>();
    private List<T> requests = new ArrayList<>();
    private Long currentUserId;
}
