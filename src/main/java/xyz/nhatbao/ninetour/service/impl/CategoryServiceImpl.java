package xyz.nhatbao.ninetour.service.impl;

import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.config.CloudinaryConfig;
import xyz.nhatbao.ninetour.entity.Category;
import xyz.nhatbao.ninetour.model.request.CategoryRequestModel;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;
import xyz.nhatbao.ninetour.repository.CategoryRepository;
import xyz.nhatbao.ninetour.service.CategoryService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    @Override
    public CategoryResponseModel create(CategoryRequestModel categoryRequestModel) {
        CategoryResponseModel result = new CategoryResponseModel();
        Category category = mapper.map(categoryRequestModel, Category.class);
        result = mapper.map(categoryRepository.save(category), CategoryResponseModel.class);
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return categoryRepository.count();
    }

    @Override
    public CategoryResponseModel getAll(Pageable pageable) {
        CategoryResponseModel result = new CategoryResponseModel();
        List<CategoryResponseModel> listResponse = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll(pageable).getContent();
        for (Category category :
                categories) {
            listResponse.add(mapper.map(category, CategoryResponseModel.class));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public CategoryResponseModel getAll() {
        CategoryResponseModel result = new CategoryResponseModel();
        List<CategoryResponseModel> listResponse = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category :
                categories) {
            listResponse.add(mapper.map(category, CategoryResponseModel.class));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public CategoryResponseModel findById(Long id) {
        CategoryResponseModel result = new CategoryResponseModel();
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            result = mapper.map(category.get(), CategoryResponseModel.class);
        }
        return result;
    }

    @Override
    public CategoryResponseModel update(Long id, CategoryRequestModel categoryRequestModel) {
        CategoryResponseModel result = new CategoryResponseModel();
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category newCategory = new Category();

        //Category not found
        if (!categoryOptional.isPresent()) {
            result = mapper.map(categoryRequestModel, CategoryResponseModel.class);
            result.setMessage("category_not_found");
            return result;
        }

        // Normal
        Category oldCategory = categoryOptional.get();
        newCategory = mapper.map(categoryRequestModel, Category.class);
        if (categoryRequestModel.getFile().getOriginalFilename().equals("")) {
            newCategory.setThumbnailUrl(oldCategory.getThumbnailUrl());
        }
        newCategory.setCreatedBy(oldCategory.getCreatedBy());
        newCategory.setCreatedDate(oldCategory.getCreatedDate());
        result = mapper.map(categoryRepository.save(newCategory), CategoryResponseModel.class);
        result.setMessage("update_success");
        return result;
    }

    @Override
    public CategoryResponseModel delete(CategoryRequestModel categoryRequestModel) {
        CategoryResponseModel result = new CategoryResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                categoryRequestModel.getIds()) {
            Optional<Category> willDelCategory = categoryRepository.findById(id);
            if (willDelCategory.isPresent()) {
                categoryRepository.deleteById(willDelCategory.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
        return result;
    }

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String url = null;
        if (file.isEmpty()) {
            return url;
        }
        try {
            Map params = ObjectUtils.asMap("folder", path,
                    "resource_type", "image");
            Map uploadResult = cloudinaryConfig.upload(file.getBytes(), params);
            url = uploadResult.get("url").toString();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
//            return null;
        }
        return url;
    }
}
