package xyz.nhatbao.ninetour.controller.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.TripRequestModel;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.TripResponseModel;
import xyz.nhatbao.ninetour.other.TripExcelExporter;
import xyz.nhatbao.ninetour.service.PlaceService;
import xyz.nhatbao.ninetour.service.TourService;
import xyz.nhatbao.ninetour.service.TripService;
import xyz.nhatbao.ninetour.util.MessageUtil;
import xyz.nhatbao.ninetour.util.PageUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

@Controller("tripAdminController")
@RequestMapping("/admin/trip")
public class TripController {
    @Autowired
    TripService tripService;

    @Autowired
    TourService tourService;

    @Autowired
    PlaceService placeService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @Value("${server.servlet.context-path}")
    String contextPath;

    @GetMapping("/list")
    ModelAndView listTrip(@RequestParam(value = "tour", required = false) Long tourId,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date from,
                          @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date to,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                          @RequestParam(value = "sort", required = false) String sortBy,
                          @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                          @ModelAttribute(value = "ref") String ref,
                          @RequestHeader(value = "referer", required = false) String referer,
                          @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/trip/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        TripResponseModel tripResponseModel = new TripResponseModel();
        tripResponseModel = tripService.searchTrip(tourId, keyword, from, to, pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(tripResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        tripResponseModel.setPageAndSort(page, limit, sortBy, desc);

        TourResponseModel tourResponseModel = tourService.getAll();
        JSONArray allTours = new JSONArray();
        for (TourResponseModel tour :
                tourResponseModel.getResults()) {
            Map<String, String> jsonTour = new HashMap<>();
            jsonTour.put("name", tour.getName());
            jsonTour.put("id", tour.getId().toString());
            allTours.put(new JSONObject(jsonTour));
        }

        if (tourId != null) {
            TourResponseModel selectedTour = tourService.findById(tourId);
            modelAndView.addObject("tour", selectedTour);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        if (from != null) {
            String fromDate = format.format(from);
            modelAndView.addObject("from", fromDate);
        } else {
            String fromDate = format.format(new Date());
            modelAndView.addObject("from", fromDate);
        }
        if (to != null) {
            String toDate = format.format(to);
            modelAndView.addObject("to", toDate);
        }

        //Add referer
        if (!ref.equals("")) {
            modelAndView.addObject("ref", ref);
        } else {
            try {
                URI referentURI = new URI(referer);
                referer = referentURI.getPath() + "?" + referentURI.getQuery();
            } catch (NullPointerException e) {
                referer = "";
            }
            modelAndView.addObject("ref", referer);
        }

        modelAndView.addObject("tripRequestModel", new TripRequestModel());
        modelAndView.addObject("tripModel", tripResponseModel);
        modelAndView.addObject("allTours", allTours);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editTrip(@RequestParam(value = "id", required = false) Long id,
                          @RequestParam(value = "tour", required = false) Long tourId,
                          @ModelAttribute(value = "ref") String ref,
                          @RequestHeader(value = "referer", required = false) String referer,
                          @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/trip/edit");
        TripResponseModel tripResponseModel = new TripResponseModel();
        if (id != null) tripResponseModel = tripService.findById(id);
        TourResponseModel tourResponseModel = tourService.getAll();
        PlaceResponseModel placeResponseModel = placeService.getAll();
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(tripResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);

        JSONArray allTours = new JSONArray();
        for (TourResponseModel tour :
                tourResponseModel.getResults()) {
            Map<String, String> jsonTour = new HashMap<>();
            jsonTour.put("name", tour.getName());
            jsonTour.put("id", tour.getId().toString());
            allTours.put(new JSONObject(jsonTour));
        }
        JSONArray allPlaces = new JSONArray();
        for (PlaceResponseModel place :
                placeResponseModel.getResults()) {
            Map<String, String> jsonPlace = new HashMap<>();
            jsonPlace.put("name", place.getName());
            jsonPlace.put("id", place.getId().toString());
            allPlaces.put(new JSONObject(jsonPlace));
        }

        //Add referer
        if (!ref.equals("")) {
            modelAndView.addObject("ref", ref);
        } else {
            try {
                URI referentURI = new URI(referer);
                referer = referentURI.getPath() + "?" + referentURI.getQuery();
            } catch (NullPointerException e) {
                referer = "";
            }
            modelAndView.addObject("ref", referer);
        }

        modelAndView.addObject("tripModel", tripResponseModel);
        modelAndView.addObject("tourId", tourId);
        modelAndView.addObject("allTours", allTours);
        modelAndView.addObject("allPlaces", allPlaces);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("tripRequestModel", new TripRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditTrip(@Valid @ModelAttribute("tripRequestModel") TripRequestModel tripRequestModel,
                                 @ModelAttribute("ref") String ref) throws IOException {
        TripResponseModel tripResponseModel = new TripResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        if (tripRequestModel.getId() != null) {
            tripResponseModel = tripService.update(tripRequestModel.getId(), tripRequestModel);
            modelAndView.setViewName("redirect:/admin/trip/edit?id=" + tripResponseModel.getId().toString());

        } else {
            tripResponseModel = tripService.create(tripRequestModel);
            if (tripResponseModel.getMessage().equals("insert_success")) {
                modelAndView.setViewName("redirect:/admin/trip/edit?id=" + tripResponseModel.getId().toString());
                if (!ref.equals("")) modelAndView.setViewName("redirect:" + ref.substring(contextPath.length()));
            } else modelAndView.setViewName("redirect:/admin/trip/add");
        }
        if (!ref.equals("")) modelAndView.addObject("ref", ref);
        modelAndView.addObject("message", tripResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteTrip(@ModelAttribute TripRequestModel tripRequestModel,
                                          @ModelAttribute("ref") String ref) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/trip/list");
        if (!ref.equals("")) modelAndView.setViewName("redirect:" + ref.substring(contextPath.length()));
        TripResponseModel tripResponseModel = new TripResponseModel();
        List<Long> deleteList = tripRequestModel.getIds();
        if (deleteList != null) tripResponseModel = tripService.delete(tripRequestModel);
        if (!ref.equals("")) modelAndView.addObject("ref", ref);
        modelAndView.addObject("message", tripResponseModel.getMessage());
        return modelAndView;
    }

    @GetMapping("/export")
    public void userExport(HttpServletResponse response,
                           @RequestParam(value = "tour", required = false) Long tourId,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date from,
                           @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date to,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           @RequestParam(value = "sort", required = false) String sortBy,
                           @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                           @ModelAttribute(value = "ref") String ref) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=trips_" + currentTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        TripResponseModel trips = tripService.getAll();

        TripExcelExporter excelExporter = new TripExcelExporter(trips.getResults());
        excelExporter.export(response);
    }
}