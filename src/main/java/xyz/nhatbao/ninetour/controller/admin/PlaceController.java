package xyz.nhatbao.ninetour.controller.admin;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.PlaceRequestModel;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;
import xyz.nhatbao.ninetour.model.response.RegionResponseModel;
import xyz.nhatbao.ninetour.service.PlaceService;
import xyz.nhatbao.ninetour.service.RegionService;
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

@Controller("placeAdminController")
@RequestMapping("/admin/place")
public class PlaceController {
    @Autowired
    PlaceService placeService;
    @Autowired
    RegionService regionService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/list")
    ModelAndView listPlace(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           @RequestParam(value = "sort", required = false) String sortBy,
                           @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                           @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/place/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        PlaceResponseModel placeResponseModel = placeService.getAll(pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(placeResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        placeResponseModel.setPageAndSort(page, limit, sortBy, desc);
        modelAndView.addObject("placeRequestModel", new PlaceRequestModel());
        modelAndView.addObject("placeModel", placeResponseModel);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editPlace(@RequestParam(value = "id", required = false) Long id,
                           @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/place/edit");
        PlaceResponseModel placeResponseModel = new PlaceResponseModel();
        if (id != null) placeResponseModel = placeService.findById(id);
        RegionResponseModel regionResponseModel = regionService.getAll();
        PlaceResponseModel placeResponseModel1 = placeService.getAll();
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(placeResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);

        JSONArray allRegions = new JSONArray();
        for (RegionResponseModel region :
                regionResponseModel.getResults()) {
            Map<String, String> jsonRegion = new HashMap<>();
            jsonRegion.put("name", region.getName());
            jsonRegion.put("id", region.getId().toString());
            allRegions.put(new JSONObject(jsonRegion));
        }
        JSONArray allPlaces = new JSONArray();
        for (PlaceResponseModel place :
                placeResponseModel1.getResults()) {
            Map<String, String> jsonPlace = new HashMap<>();
            jsonPlace.put("name", place.getName());
            jsonPlace.put("id", place.getId().toString());
            allPlaces.put(new JSONObject(jsonPlace));
        }
        modelAndView.addObject("placeModel", placeResponseModel);
        modelAndView.addObject("allRegions", allRegions);
        modelAndView.addObject("allPlaces", allPlaces);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("placeRequestModel", new PlaceRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditPlace(@Valid @ModelAttribute("placeRequestModel") PlaceRequestModel placeRequestModel) throws IOException {
        PlaceResponseModel placeResponseModel = new PlaceResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        if (placeRequestModel.getId() != null) {
            String fileName = placeRequestModel.getImageFile().getOriginalFilename();
            if (fileName.equals("") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                String url = placeService.uploadFile(placeRequestModel.getImageFile(), "9tour/place");
                placeRequestModel.setThumbnailUrl(url);
                placeResponseModel = placeService.update(placeRequestModel.getId(), placeRequestModel);
            } else {
                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/place/edit?id=" + placeRequestModel.getId().toString());
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/place/edit?id=" + placeResponseModel.getId().toString());

        } else {
            String fileName = placeRequestModel.getImageFile().getOriginalFilename();
            if (fileName.equals("") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.equals(".gif")) {
                String url = placeService.uploadFile(placeRequestModel.getImageFile(), "9tour/place"); // uploadfile
                placeRequestModel.setThumbnailUrl(url);
                placeResponseModel = placeService.create(placeRequestModel);
                if (placeResponseModel.getMessage() != null && placeResponseModel.getMessage().equals("insert_success"))
                    modelAndView.setViewName("redirect:/admin/place/edit?id=" + placeResponseModel.getId().toString());
                else modelAndView.setViewName("redirect:/admin/place/add");
            } else {
                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/place/edit?id=" + placeRequestModel.getId().toString());
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/place/edit?id=" + placeResponseModel.getId().toString());
        }
        modelAndView.addObject("message", placeResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeletePlace(@ModelAttribute PlaceRequestModel placeRequestModel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/place/list");
        PlaceResponseModel placeResponseModel = new PlaceResponseModel();
        List<Long> deleteList = placeRequestModel.getIds();
        if (deleteList != null) placeResponseModel = placeService.delete(placeRequestModel);
        modelAndView.addObject("message", placeResponseModel.getMessage());
        return modelAndView;
    }
}
