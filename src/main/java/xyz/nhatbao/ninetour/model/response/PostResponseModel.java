package xyz.nhatbao.ninetour.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;


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
public class PostResponseModel extends BaseResponseModel<PostResponseModel> {
    private String title;
    private String shortDescription;
    private String thumbnailUrl;
    private String content;
    private String slug;

    private CategoryResponseModel categoryResponseModel;

    public MultipartFile file;
}
