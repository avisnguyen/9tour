package xyz.nhatbao.ninetour.controller.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.TourRequestModel;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;
import xyz.nhatbao.ninetour.other.TourExcelExporter;
import xyz.nhatbao.ninetour.service.PlaceService;
import xyz.nhatbao.ninetour.service.TourService;
import xyz.nhatbao.ninetour.service.TripService;
import xyz.nhatbao.ninetour.util.MessageUtil;
import xyz.nhatbao.ninetour.util.PageUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@Controller("tourAdminController")
@RequestMapping("/admin/tour")
public class TourController {
    @Autowired
    TourService tourService;

    @Autowired
    TripService tripService;

    @Autowired
    PlaceService placeService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/list")
    ModelAndView listTour(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "sort", required = false) String sortBy,
                          @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                          @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/tour/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        TourResponseModel tourResponseModel = tourService.searchTour(keyword, pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(tourResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        tourResponseModel.setPageAndSort(page, limit, sortBy, desc);
        modelAndView.addObject("tourRequestModel", new TourRequestModel());
        modelAndView.addObject("tourModel", tourResponseModel);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editTour(@RequestParam(value = "id", required = false) Long id,
                          @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/tour/edit");
        TourResponseModel tourResponseModel = new TourResponseModel();
        TripResponseModel tripResponseModel = new TripResponseModel();
        if (id != null) tourResponseModel = tourService.findById(id);
        PlaceResponseModel placeResponseModel = placeService.getAll();
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(tourResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);

        JSONArray allPlaces = new JSONArray();
        for (PlaceResponseModel place :
                placeResponseModel.getResults()) {
            Map<String, String> jsonCategory = new HashMap<>();
            jsonCategory.put("name", place.getName());
            jsonCategory.put("id", place.getId().toString());
            allPlaces.put(new JSONObject(jsonCategory));
        }

        String selectedPlaces = "";
        for (PlaceResponseModel place :
                tourResponseModel.getPlaceResponseModels()) {
            selectedPlaces = selectedPlaces.concat(place.getId().toString()).concat(",");
        }
        if (selectedPlaces.endsWith(",")) selectedPlaces = selectedPlaces.substring(0, selectedPlaces.length() - 1);

        modelAndView.addObject("tourModel", tourResponseModel);
        modelAndView.addObject("allPlaces", allPlaces);
        modelAndView.addObject("selectedPlaces", selectedPlaces);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("tourRequestModel", new TourRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditTour(@Valid @ModelAttribute("tourRequestModel") TourRequestModel tourRequestModel) throws IOException {
        TourResponseModel tourResponseModel = new TourResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        if (tourRequestModel.getId() != null) {
            String fileName = tourRequestModel.getImageFile().getOriginalFilename();
            if (fileName.equals("") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                String url = tourService.uploadFile(tourRequestModel.getImageFile(), "9tour/tour");
                tourRequestModel.setThumbnailUrl(url);
                tourResponseModel = tourService.update(tourRequestModel.getId(), tourRequestModel);
            } else {
                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/tour/edit?id=" + tourRequestModel.getId().toString());
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/tour/edit?id=" + tourResponseModel.getId().toString());

        } else {
            String fileName = tourRequestModel.getImageFile().getOriginalFilename();
            if (fileName.equals("") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                String url = tourService.uploadFile(tourRequestModel.getImageFile(), "9tour/tour"); // uploadfile
                tourRequestModel.setThumbnailUrl(url);
                tourResponseModel = tourService.create(tourRequestModel);
                if (tourResponseModel.getMessage() != null && tourResponseModel.getMessage().equals("insert_success"))
                    modelAndView.setViewName("redirect:/admin/tour/edit?id=" + tourResponseModel.getId().toString());
                else modelAndView.setViewName("redirect:/admin/tour/add");
            } else {
                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/tour/edit?id=" + tourRequestModel.getId().toString());
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/tour/edit?id=" + tourResponseModel.getId().toString());
        }
        modelAndView.addObject("message", tourResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteTour(@ModelAttribute TourRequestModel tourRequestModel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/tour/list");
        TourResponseModel tourResponseModel = new TourResponseModel();
        List<Long> deleteList = tourRequestModel.getIds();
        if (deleteList != null) tourResponseModel = tourService.delete(tourRequestModel);
        modelAndView.addObject("message", tourResponseModel.getMessage());
        return modelAndView;
    }

    @GetMapping("/export")
    public void userExport(HttpServletResponse response,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           @RequestParam(value = "sort", required = false) String sortBy,
                           @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=tours_" + currentTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        TourResponseModel tours = tourService.getAll();

        TourExcelExporter excelExporter = new TourExcelExporter(tours.getResults());
        excelExporter.export(response);
    }
}