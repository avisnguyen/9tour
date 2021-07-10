package xyz.nhatbao.ninetour.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;
import xyz.nhatbao.ninetour.service.CategoryService;
import xyz.nhatbao.ninetour.service.PostService;
import xyz.nhatbao.ninetour.service.UserService;
import xyz.nhatbao.ninetour.util.PageUtil;

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

@Controller
public class BlogController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    PageUtil pageUtil = new PageUtil();

    @GetMapping("/blog")
    public ModelAndView blogPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                 @RequestParam(value = "desc", defaultValue = "true") Boolean desc) {
        ModelAndView modelAndView = new ModelAndView("layouts/web/blog/blog");
        Pageable pageable = pageUtil.createPageable(page, limit, desc);
        PostResponseModel postResponseModel = postService.getAll(pageable);
        postResponseModel.setPageAndSort(page, limit, desc);
        modelAndView.addObject("pageTitle", "Danh sách bài viết");
        modelAndView.addObject("posts", postResponseModel);
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }

    @GetMapping("/blog/{id}/{slug}")
    public ModelAndView blogDetailPage(@PathVariable Long id,
                                       @PathVariable String slug) {
        ModelAndView modelAndView = new ModelAndView();
        PostResponseModel postResponseModel = new PostResponseModel();
        if (id != null && slug != null) {
            postResponseModel = postService.findById(id);
            if (!postResponseModel.getSlug().equals(slug)) {
                modelAndView.addObject("pageTitle", "404 - Page Not Found");
                modelAndView.setViewName("layouts/web/error/404");
            } else {
                UserResponseModel userResponseModel = userService.findUserById(Long.parseLong(postResponseModel.getModifiedBy()));
                modelAndView.addObject("pageTitle", postResponseModel.getTitle());
                modelAndView.addObject("post", postResponseModel);
                modelAndView.addObject("newPost", postService.findAllIdDesc());
                modelAndView.addObject("postOfCategory", postService.findAllByCategoryId(postResponseModel.getCategoryResponseModel().getId()));
                modelAndView.addObject("author", userResponseModel.getLastName() + ' ' + userResponseModel.getFirstName());
                modelAndView.setViewName("layouts/web/blog/blog-detail");
            }
        }
        return modelAndView;
    }

    @GetMapping("/blog/category/{categoryId}/{categoryCode}")
    public ModelAndView blogOfCategoryPage(@PathVariable String categoryCode,
                                           @PathVariable Long categoryId,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                           @RequestParam(value = "desc", defaultValue = "true") Boolean desc) {
        ModelAndView modelAndView = new ModelAndView("layouts/web/blog/category-blog");
        PostResponseModel postResponseModel = new PostResponseModel();
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        if (categoryId != null) {
            Pageable pageable = pageUtil.createPageable(page, limit, desc);
            postResponseModel = postService.findAllByCategoryId(categoryId, pageable);
            categoryResponseModel = categoryService.findById(categoryId);
            postResponseModel.setPageAndSort(page, limit, desc);
            String title = categoryResponseModel.getName();
            String thumbnailUrl = categoryResponseModel.getThumbnailUrl();
            modelAndView.addObject("pageTitle", title);
            modelAndView.addObject("thumbnailUrl", thumbnailUrl);
            modelAndView.addObject("categoryId", categoryId);
            modelAndView.addObject("postOfCategory", postResponseModel);
            modelAndView.addObject("categories", categoryService.getAll());
            return modelAndView;
        } else return new ModelAndView("redirect:/error");
    }
}
