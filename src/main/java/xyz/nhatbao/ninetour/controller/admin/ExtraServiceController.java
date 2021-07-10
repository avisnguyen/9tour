package xyz.nhatbao.ninetour.controller.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.ExtraServiceRequestModel;
import xyz.nhatbao.ninetour.model.response.ExtraServiceResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.other.ExtraServiceExcelExporter;
import xyz.nhatbao.ninetour.service.ExtraServiceService;
import xyz.nhatbao.ninetour.service.TourService;
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

@Controller("adminExtraServiceController")
@RequestMapping("/admin/extra-service")
public class ExtraServiceController {
    @Autowired
    ExtraServiceService extraServiceService;

    @Autowired
    TourService tourService;

    PageUtil pageUtil = new PageUtil();

    MessageUtil messageUtil = new MessageUtil();

    @Value("${server.servlet.context-path}")
    String contextPath;

    @GetMapping("/list")
    ModelAndView listExtraService(@RequestParam(value = "tour", required = false) Long tourId,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  @RequestParam(value = "sort", required = false) String sortBy,
                                  @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                  @ModelAttribute(value = "ref") String ref,
                                  @RequestHeader(value = "referer", required = false) String referer,
                                  @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/extra-service/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        ExtraServiceResponseModel extraServiceResponseModel = new ExtraServiceResponseModel();
        if (tourId != null)
            extraServiceResponseModel = extraServiceService.searchExtraService(tourId, keyword, pageable);
        else extraServiceResponseModel = extraServiceService.getAll(pageable);//need feed
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(extraServiceResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        extraServiceResponseModel.setPageAndSort(page, limit, sortBy, desc);

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

        modelAndView.addObject("extraServiceRequestModel", new ExtraServiceRequestModel());
        modelAndView.addObject("extraServiceModel", extraServiceResponseModel);
        modelAndView.addObject("allTours", allTours);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    ModelAndView editExtraService(@RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "tour", required = false) Long tourId,
                                  @ModelAttribute(value = "ref") String ref,
                                  @RequestHeader(value = "referer", required = false) String referer,
                                  @ModelAttribute("message") String message) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/extra-service/edit");
        ExtraServiceResponseModel extraServiceResponseModel = new ExtraServiceResponseModel();
        if (id != null) extraServiceResponseModel = extraServiceService.findById(id);
        TourResponseModel tourResponseModel = tourService.getAll();
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(extraServiceResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);

        JSONArray allTours = new JSONArray();
        for (TourResponseModel tour :
                tourResponseModel.getResults()) {
            Map<String, String> jsonTour = new HashMap<>();
            jsonTour.put("name", tour.getName());
            jsonTour.put("id", tour.getId().toString());
            allTours.put(new JSONObject(jsonTour));
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

        String selectedTours = "";
        for (TourResponseModel place :
                extraServiceResponseModel.getTourResponseModels()) {
            selectedTours = selectedTours.concat(place.getId().toString()).concat(",");
        }
        if (selectedTours.endsWith(",")) selectedTours = selectedTours.substring(0, selectedTours.length() - 1);

        modelAndView.addObject("extraServiceModel", extraServiceResponseModel);
        modelAndView.addObject("tourId", tourId);
        modelAndView.addObject("allTours", allTours);
        modelAndView.addObject("selectedTours", selectedTours);
        modelAndView.addObject("message", messageModel);
        modelAndView.addObject("extraServiceRequestModel", new ExtraServiceRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    ModelAndView processEditExtraService(@Valid @ModelAttribute("extraServiceRequestModel") ExtraServiceRequestModel extraServiceRequestModel,
                                         @ModelAttribute("ref") String ref) throws IOException {
        ExtraServiceResponseModel extraServiceResponseModel = new ExtraServiceResponseModel();
        ModelAndView modelAndView = new ModelAndView();
        if (extraServiceRequestModel.getId() != null) {
            String fileName = extraServiceRequestModel.getImageFile().getOriginalFilename();
            if (fileName.equals("") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                String url = extraServiceService.uploadFile(extraServiceRequestModel.getImageFile(), "9tour/extra-service");
                extraServiceRequestModel.setThumbnailUrl(url);
                extraServiceResponseModel = extraServiceService.update(extraServiceRequestModel.getId(), extraServiceRequestModel);
            } else {
                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/extra-service/edit?id=" + extraServiceRequestModel.getId().toString());
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/extra-service/edit?id=" + extraServiceResponseModel.getId().toString());

        } else {
            String fileName = extraServiceRequestModel.getImageFile().getOriginalFilename();
            if (fileName.equals("") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                String url = extraServiceService.uploadFile(extraServiceRequestModel.getImageFile(), "9tour/extra-service"); // uploadfile
                extraServiceRequestModel.setThumbnailUrl(url);
                extraServiceResponseModel = extraServiceService.create(extraServiceRequestModel);
                if (extraServiceResponseModel.getMessage() != null && extraServiceResponseModel.getMessage().equals("insert_success"))
                    modelAndView.setViewName("redirect:/admin/extra-service/edit?id=" + extraServiceResponseModel.getId().toString());
                else modelAndView.setViewName("redirect:/admin/extra-service/add");
            } else {
                modelAndView.addObject("message", "image_file_not_accept");
                modelAndView.setViewName("redirect:/admin/extra-service/add");
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin/extra-service/edit?id=" + extraServiceResponseModel.getId().toString());
        }
        modelAndView.addObject("message", extraServiceResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteExtraService(@ModelAttribute ExtraServiceRequestModel extraServiceRequestModel,
                                                  @ModelAttribute("ref") String ref) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/extra-service/list");
        if (!ref.equals("")) modelAndView.setViewName("redirect:" + ref.substring(contextPath.length()));
        ExtraServiceResponseModel extraServiceResponseModel = new ExtraServiceResponseModel();
        List<Long> deleteList = extraServiceRequestModel.getIds();
        if (deleteList != null) extraServiceResponseModel = extraServiceService.delete(extraServiceRequestModel);
        if (!ref.equals("")) modelAndView.addObject("ref", ref);
        modelAndView.addObject("message", extraServiceResponseModel.getMessage());
        return modelAndView;
    }

    @GetMapping("/export")
    public void userExport(HttpServletResponse response,
                           @RequestParam(value = "tour", required = false) Long tourId,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                           @RequestParam(value = "sort", required = false) String sortBy,
                           @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                           @ModelAttribute(value = "ref") String ref) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=extra_services_" + currentTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        ExtraServiceResponseModel extraServices = extraServiceService.getAll();

        ExtraServiceExcelExporter excelExporter = new ExtraServiceExcelExporter(extraServices.getResults());
        excelExporter.export(response);
    }
}
