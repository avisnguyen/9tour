package xyz.nhatbao.ninetour.controller.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.PostRequestModel;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;
import xyz.nhatbao.ninetour.service.CategoryService;
import xyz.nhatbao.ninetour.service.PostService;
import xyz.nhatbao.ninetour.util.MessageUtil;
import xyz.nhatbao.ninetour.util.PageUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller("blogAdminController")
@RequestMapping("/admin/post")
public class BlogController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/list")
    ModelAndView listPost(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          @RequestParam(value = "sort", required = false) String sortBy,
                          @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                          @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/post/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        PostResponseModel postResponseModel = postService.getAll(pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(postResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        postResponseModel.setPageAndSort(page, limit, sortBy, desc);
        modelAndView.addObject("postRequestModel", new PostRequestModel());
        modelAndView.addObject("postModel", postResponseModel);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editPost(@RequestParam(value = "id", required = false) Long id,
                          @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/post/edit");
        PostResponseModel postResponseModel = new PostResponseModel();
        CategoryResponseModel categoryResponseModel = categoryService.getAll();
        if (id != null) postResponseModel = postService.findById(id);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(postResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);

        JSONArray allCategories = new JSONArray();
        for (CategoryResponseModel cat :
                categoryResponseModel.getResults()) {
            Map<String, String> jsonCategory = new HashMap<>();
            jsonCategory.put("name", cat.getName());
            jsonCategory.put("id", cat.getId().toString());
            allCategories.put(new JSONObject(jsonCategory));
        }

        modelAndView.addObject("postModel", postResponseModel);
        modelAndView.addObject("allCategories", allCategories);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("postRequestModel", new PostRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditPost(@Valid @ModelAttribute("postRequestModel") PostRequestModel postRequestModel) throws IOException {
        PostResponseModel postResponseModel = new PostResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        String fileType = postRequestModel.getFile().getOriginalFilename();
        if (postRequestModel.getId() != null) {

            if (fileType.equals("") || fileType.endsWith(".jpg") || fileType.endsWith(".jpeg") || fileType.endsWith(".png") || fileType.endsWith(".gif")) {
                String url = postService.uploadFile(postRequestModel.getFile(), "9tour/post");
                postRequestModel.setThumbnailUrl(url);
                postResponseModel = postService.update(postRequestModel.getId(), postRequestModel);
            } else {

                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/post/edit?id=" + postRequestModel.getId().toString());
                return modelAndView;
            }

            modelAndView.setViewName("redirect:/admin/post/edit?id=" + postResponseModel.getId().toString());

        } else {
            if (fileType.equals("") || fileType.endsWith(".jpg") || fileType.endsWith(".jpeg") || fileType.endsWith(".png") || fileType.endsWith(".gif")) {
                String url = postService.uploadFile(postRequestModel.getFile(), "9tour/post");
                postRequestModel.setThumbnailUrl(url);
                postResponseModel = postService.create(postRequestModel);
                if (postResponseModel.getMessage().equals("insert_success"))
                    modelAndView.setViewName("redirect:/admin/post/edit?id=" + postResponseModel.getId().toString());
                else modelAndView.setViewName("redirect:/admin/post/add");
            } else {
                modelAndView.addObject("message", "file_exception");
                modelAndView.setViewName("redirect:/admin/post/add");
                return modelAndView;
            }
        }
        modelAndView.addObject("message", postResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeletePost(@ModelAttribute PostRequestModel postRequestModel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/post/list");
        PostResponseModel postResponseModel = new PostResponseModel();
        List<Long> deleteList = postRequestModel.getIds();
        if (deleteList != null) postResponseModel = postService.delete(postRequestModel);
        modelAndView.addObject("message", postResponseModel.getMessage());
        return modelAndView;
    }
}
