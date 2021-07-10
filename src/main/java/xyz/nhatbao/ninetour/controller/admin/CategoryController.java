package xyz.nhatbao.ninetour.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.CategoryRequestModel;
import xyz.nhatbao.ninetour.model.response.CategoryResponseModel;
import xyz.nhatbao.ninetour.service.CategoryService;
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

@Controller("categoryAdminController")
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/list")
    ModelAndView listCategory(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                              @RequestParam(value = "sort", required = false) String sortBy,
                              @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                              @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/category/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        CategoryResponseModel categoryResponseModel = categoryService.getAll(pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(categoryResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        categoryResponseModel.setPageAndSort(page, limit, sortBy, desc);
        modelAndView.addObject("categoryRequestModel", new CategoryRequestModel());
        modelAndView.addObject("categoryModel", categoryResponseModel);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editCategory(@RequestParam(value = "id", required = false) Long id,
                              @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/category/edit");
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        if (id != null) categoryResponseModel = categoryService.findById(id);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(categoryResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        modelAndView.addObject("categoryModel", categoryResponseModel);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("categoryRequestModel", new CategoryRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditCategory(@Valid @ModelAttribute("categoryRequestModel") CategoryRequestModel categoryRequestModel) throws IOException {
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        String fileType = categoryRequestModel.getFile().getOriginalFilename();
        if (categoryRequestModel.getId() != null) {
            if (fileType.equals("") || fileType.endsWith(".jpg") || fileType.endsWith(".jpeg") || fileType.endsWith(".png") || fileType.endsWith(".gif")) {
                String url = categoryService.uploadFile(categoryRequestModel.getFile(), "9tour/category");
                categoryRequestModel.setThumbnailUrl(url);
                categoryResponseModel = categoryService.update(categoryRequestModel.getId(), categoryRequestModel);
            } else {
                modelAndView.addObject("message", "file_exception");
                modelAndView.setViewName("redirect:/admin/category/edit?id=" + categoryRequestModel.getId().toString());
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/category/edit?id=" + categoryResponseModel.getId().toString());
        } else {
            if (fileType.equals("") || fileType.endsWith(".jpg") || fileType.endsWith(".jpeg") || fileType.endsWith(".png") || fileType.endsWith(".gif")) {
                String url = categoryService.uploadFile(categoryRequestModel.getFile(), "9tour/category");
                categoryRequestModel.setThumbnailUrl(url);
                categoryResponseModel = categoryService.create(categoryRequestModel);
                if (categoryResponseModel.getMessage().equals("insert_success"))
                    modelAndView.setViewName("redirect:/admin/category/edit?id=" + categoryResponseModel.getId().toString());
                else modelAndView.setViewName("redirect:/admin/category/add");
            } else {
                modelAndView.addObject("message", "file_exception");
                modelAndView.setViewName("redirect:/admin/category/add");
                return modelAndView;
            }
        }
        modelAndView.addObject("message", categoryResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteCategory(@ModelAttribute CategoryRequestModel categoryRequestModel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/category/list");
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        List<Long> deleteList = categoryRequestModel.getIds();
        if (deleteList != null) categoryResponseModel = categoryService.delete(categoryRequestModel);
        modelAndView.addObject("message", categoryResponseModel.getMessage());
        return modelAndView;
    }
}
