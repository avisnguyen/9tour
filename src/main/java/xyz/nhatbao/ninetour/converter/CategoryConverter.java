package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Category;
import xyz.nhatbao.ninetour.entity.Post;
import xyz.nhatbao.ninetour.model.request.CategoryRequestModel;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;

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

@Component
public class CategoryConverter {
    @Autowired
    ModelMapper modelMapper;

    public Category toEntity(CategoryRequestModel categoryRequestModel) {
        Category category = modelMapper.map(categoryRequestModel, Category.class);
        if (categoryRequestModel.getPosts().size() > 0) {
            category.setPosts(modelMapper.map(categoryRequestModel.getPosts(), new TypeToken<List<Post>>() {
            }.getType()));
        }
        return category;
    }

    public CategoryResponseModel toModel(Category category) {
        CategoryResponseModel categoryResponseModel = modelMapper.map(category, CategoryResponseModel.class);
        if (category.getPosts().size() > 0) {
            categoryResponseModel.setPostResponseModels(modelMapper.map(category.getPosts(), new TypeToken<List<PostResponseModel>>() {
            }.getType()));
        }
        return categoryResponseModel;
    }
}
