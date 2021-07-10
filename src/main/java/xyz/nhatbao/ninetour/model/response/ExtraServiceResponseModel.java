package xyz.nhatbao.ninetour.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
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
public class ExtraServiceResponseModel extends BaseResponseModel<ExtraServiceResponseModel> {
    private String name;
    private String shortDescription;
    private String thumbnailUrl;
    private String description;
    private Double price;
    private Integer quantity;

    private List<TourResponseModel> tourResponseModels = new ArrayList<>();

    private List<ExtraBillResponseModel> extraBillResponseModels = new ArrayList<>();
}
