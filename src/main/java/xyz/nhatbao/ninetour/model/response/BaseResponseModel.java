package xyz.nhatbao.ninetour.model.response;

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
public abstract class BaseResponseModel<T> {
    private Long id;
    private String code;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private Boolean isDeleted = false;

    private List<Long> ids = new ArrayList<>();
    private List<T> results = new ArrayList<>();
    private String message;
    private Integer page;
    private Integer maxPageItem;
    private Integer limit;
    private Integer totalPages;
    private Long totalItems;
    private Boolean isDesc;
    private String sortBy;
    private String type;

    public void setPageAndSort(Integer page, Integer limit, String sortBy, Boolean desc) {
        this.page = page;
        this.limit = limit;
        this.sortBy = sortBy;
        this.isDesc = desc;
        this.totalPages = (int) Math.ceil((double) this.totalItems / this.limit);
    }

    public void setPageAndSort(Integer page, Integer limit, Boolean desc) {
        this.page = page;
        this.limit = limit;
        this.isDesc = desc;
        this.totalPages = (int) Math.ceil((double) this.totalItems / this.limit);
    }

    public void setPageAndSort(Integer page, Integer limit, String sortBy) {
        this.page = page;
        this.limit = limit;
        this.sortBy = sortBy;
        this.totalPages = (int) Math.ceil((double) this.totalItems / this.limit);
    }
}
