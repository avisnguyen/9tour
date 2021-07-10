package xyz.nhatbao.ninetour.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nhatbao.ninetour.config.CloudinaryConfig;
import xyz.nhatbao.ninetour.converter.PostConverter;
import xyz.nhatbao.ninetour.entity.Category;
import xyz.nhatbao.ninetour.entity.Post;
import xyz.nhatbao.ninetour.model.request.PostRequestModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;
import xyz.nhatbao.ninetour.repository.CategoryRepository;
import xyz.nhatbao.ninetour.repository.PostRepository;
import xyz.nhatbao.ninetour.service.PostService;

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
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    PostConverter postConverter;

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    Slugify slugify = new Slugify();

    @Override
    public PostResponseModel create(PostRequestModel postRequestModel) {
        PostResponseModel result = new PostResponseModel();
        Post post = postConverter.toEntity(postRequestModel);
        if (postRequestModel.getCategory() == null) {
            result.setMessage("category_is_null");
            return result;
        }

        //Set missed field
        if (postRequestModel.getCategory() != null) {
            Optional<Category> category = categoryRepository.findById(postRequestModel.getCategory());
            if (category.isPresent()) {
                post.setCategory(category.get());
                result = postConverter.toModel(postRepository.save(post));
                result.setMessage("insert_success");
                return result;
            }
        }
        result.setMessage("category_not_found");
        return result;
    }

    @Override
    public Long count() {
        return postRepository.count();
    }

    @Override
    public PostResponseModel getAll(Pageable pageable) {
        PostResponseModel result = new PostResponseModel();
        List<PostResponseModel> listResponse = new ArrayList<>();
        List<Post> posts = postRepository.findAllByOrderByIdDesc(pageable);
        for (Post post :
                posts) {
            PostResponseModel postResponseModel = postConverter.toModel(post);
            postResponseModel.setSlug(slugify.slugify(postResponseModel.getTitle()));
            listResponse.add(postResponseModel);
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public PostResponseModel findById(Long id) {
        PostResponseModel result = new PostResponseModel();
// getOne
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            result = postConverter.toModel(post.get());
            result.setSlug(slugify.slugify(result.getTitle()));
        }
        return result;
    }

    @Override
    public PostResponseModel update(Long id, PostRequestModel postRequestModel) {
        PostResponseModel result = new PostResponseModel();
        Optional<Post> postOptional = postRepository.findById(id);
        Post newPost = new Post();
        Post oldPost = new Post();

        //Post not found
        if (!postOptional.isPresent()) {
            result = mapper.map(postRequestModel, PostResponseModel.class);
            result.setMessage("post_not_found");
            return result;
        }
        oldPost = postOptional.get();
        newPost = postConverter.toEntity(postRequestModel);

        //Set missed field
        if (postRequestModel.getCategory() != null) {
            Optional<Category> category = categoryRepository.findById(postRequestModel.getCategory());
            if (category.isPresent()) {
                newPost.setCategory(category.get());
            } else {
                result.setMessage("category_not_found");
                return result;
            }
        }
        if (postRequestModel.getFile().getOriginalFilename().equals("")) {
            newPost.setThumbnailUrl(oldPost.getThumbnailUrl());
        }

        // Normal
        newPost.setCreatedBy(oldPost.getCreatedBy());
        newPost.setCreatedDate(oldPost.getCreatedDate());
        result = postConverter.toModel(postRepository.save(newPost));
        result.setMessage("update_success");
        return result;

    }

    @Override
    public PostResponseModel delete(PostRequestModel postRequestModel) {
        PostResponseModel result = new PostResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                postRequestModel.getIds()) {
            Optional<Post> willDelPost = postRepository.findById(id);
            if (willDelPost.isPresent()) {
                postRepository.deleteById(willDelPost.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
        return result;
    }

    @Override
    public List<PostResponseModel> findAllIdDesc() {
        List<PostResponseModel> result = new ArrayList<>();
        List<Post> postEntites = postRepository.findAllByOrderByIdDesc();
        for (Post post : postEntites) {
            PostResponseModel postResponseModel = postConverter.toModel(post);
            postResponseModel.setSlug(slugify.slugify(postResponseModel.getTitle()));
            result.add(postResponseModel);
        }
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
            return null;
        }
        return url;
    }

    @Override
    public List<PostResponseModel> findAllByCategoryId(Long id) {
        List<PostResponseModel> postResponseModelList = new ArrayList<>();
        List<Post> postList = postRepository.findAllByCategoryIdOrderByIdDesc(id);
        for (Post post : postList) {
            PostResponseModel postResponseModel = postConverter.toModel(post);
            postResponseModel.setSlug(slugify.slugify(postResponseModel.getTitle()));
            postResponseModelList.add(postResponseModel);
        }
        return postResponseModelList;
    }

    @Override
    public PostResponseModel findAllByCategoryId(Long id, Pageable pageable) {
        PostResponseModel result = new PostResponseModel();
        List<PostResponseModel> postResponseModelList = new ArrayList<>();
        int totalItem = postRepository.findAllByCategoryId(id).size();
        List<Post> postList = postRepository.findAllByCategoryIdOrderByIdDesc(id, pageable);
        for (Post post : postList) {
            PostResponseModel postResponseModel = postConverter.toModel(post);
            postResponseModel.setSlug(slugify.slugify(postResponseModel.getTitle()));
            postResponseModelList.add(postResponseModel);
        }
        result.setResults(postResponseModelList);
        result.setTotalItems((long) totalItem);
        return result;
    }
}
