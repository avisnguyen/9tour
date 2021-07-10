package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Category;
import xyz.nhatbao.ninetour.entity.Post;
import xyz.nhatbao.ninetour.model.request.PostRequestModel;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;

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

@Component
public class PostConverter {
    @Autowired
    ModelMapper modelMapper;

    public Post toEntity(PostRequestModel postRequestModel) {
        Post post = modelMapper.map(postRequestModel, Post.class);
        post.setCategory(modelMapper.map(postRequestModel.getCategory(), Category.class));
        return post;
    }

    public PostResponseModel toModel(Post post) {
        PostResponseModel postResponseModel = modelMapper.map(post, PostResponseModel.class);
        postResponseModel.setCategoryResponseModel(modelMapper.map(post.getCategory(), CategoryResponseModel.class));
        return postResponseModel;
    }
}
