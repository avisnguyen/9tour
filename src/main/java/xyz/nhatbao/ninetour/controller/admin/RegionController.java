package xyz.nhatbao.ninetour.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.RegionRequestModel;
import xyz.nhatbao.ninetour.model.response.RegionResponseModel;
import xyz.nhatbao.ninetour.service.RegionService;
import xyz.nhatbao.ninetour.util.MessageUtil;
import xyz.nhatbao.ninetour.util.PageUtil;

import javax.validation.Valid;
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

@Controller("regionAdminController")
@RequestMapping("/admin/region")
public class RegionController {
    @Autowired
    RegionService regionService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/list")
    ModelAndView listRegion(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                            @RequestParam(value = "sort", required = false) String sortBy,
                            @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                            @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/region/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        RegionResponseModel regionResponseModel = regionService.getAll(pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(regionResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        regionResponseModel.setPageAndSort(page, limit, sortBy, desc);
        modelAndView.addObject("regionRequestModel", new RegionRequestModel());
        modelAndView.addObject("regionModel", regionResponseModel);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editRegion(@RequestParam(value = "id", required = false) Long id,
                            @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/region/edit");
        RegionResponseModel regionResponseModel = new RegionResponseModel();
        if (id != null) regionResponseModel = regionService.findById(id);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(regionResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        modelAndView.addObject("regionModel", regionResponseModel);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("regionRequestModel", new RegionRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditRegion(@Valid @ModelAttribute("regionRequestModel") RegionRequestModel regionRequestModel) {
        RegionResponseModel regionResponseModel = new RegionResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        if (regionRequestModel.getId() != null) {
            regionResponseModel = regionService.update(regionRequestModel.getId(), regionRequestModel);
            modelAndView.setViewName("redirect:/admin/region/edit?id=" + regionResponseModel.getId().toString());

        } else {
            regionResponseModel = regionService.create(regionRequestModel);
            if (regionResponseModel.getMessage().equals("insert_success"))
                modelAndView.setViewName("redirect:/admin/region/edit?id=" + regionResponseModel.getId().toString());
            else modelAndView.setViewName("redirect:/admin/region/add");
        }
        modelAndView.addObject("message", regionResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteRegion(@ModelAttribute RegionRequestModel regionRequestModel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/region/list");
        RegionResponseModel regionResponseModel = new RegionResponseModel();
        List<Long> deleteList = regionRequestModel.getIds();
        if (deleteList != null) regionResponseModel = regionService.delete(regionRequestModel);
        modelAndView.addObject("message", regionResponseModel.getMessage());
        return modelAndView;
    }
}
