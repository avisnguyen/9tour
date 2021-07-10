package xyz.nhatbao.ninetour.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.response.PlaceResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.service.PlaceService;
import xyz.nhatbao.ninetour.service.TourService;
import xyz.nhatbao.ninetour.service.TripService;
import xyz.nhatbao.ninetour.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

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
public class TourController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TourService tourService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PageUtil pageUtil;

    @GetMapping("/tour")
    public ModelAndView tourPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                 @RequestParam(value = "sortBy", defaultValue = "trips.departureTime") String sort) {
        ModelAndView modelAndView = new ModelAndView("layouts/web/tour/tour");
        int sortCode = 0;
        if (sort.equals("trips.adultPrice")) {
            sortCode = 1;
        } else {
            sortCode = 2;
        }
        Pageable pageable = pageUtil.createPageable(page, limit, sort);
        TourResponseModel tourResponseModel = tourService.listAll(pageable);
        tourResponseModel.setPageAndSort(page, limit, sort);
        modelAndView.addObject("pageTitle", "Danh sách tour");
        modelAndView.addObject("tours", tourResponseModel);
        modelAndView.addObject("sortCode", sortCode);
        return modelAndView;
    }

    @GetMapping("/tour/{id}/{slug}")
    public ModelAndView tourDetailPage(@PathVariable Long id,
                                       @PathVariable String slug) {
        ModelAndView modelAndView = new ModelAndView();
        TourResponseModel tourResponseModel = new TourResponseModel();
        TourResponseModel relationTour = new TourResponseModel();
        List<TourResponseModel> tourWithSameDeparturePlace = new ArrayList<>();
        if (id != null && slug != null) {
            tourResponseModel = tourService.findTourById(id);
            if (!tourResponseModel.getSlug().equals(slug)) {
                modelAndView.addObject("pageTitle", "404 - Page Not Found");
                modelAndView.setViewName("layouts/web/error/404");
            } else {
                modelAndView.addObject("tour", tourResponseModel);
                tourWithSameDeparturePlace = tourService.findByDepartureId(tourResponseModel.getDepartureModel().getId());
                modelAndView.addObject("sameDeparture", tourWithSameDeparturePlace);
                modelAndView.addObject("pageTitle", tourResponseModel.getName());
                modelAndView.setViewName("layouts/web/tour/tour-detail");
            }
        }
        return modelAndView;
    }

    @GetMapping("/tour/{placeId}")
    public ModelAndView tourOfPlacePage(@PathVariable Long placeId,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                        @RequestParam(value = "sortBy", defaultValue = "trips.departureTime") String sort) {
        ModelAndView modelAndView = new ModelAndView("layouts/web/tour/tour-place");
        int sortCode = 0;
        if (sort.equals("trips.adultPrice")) {
            sortCode = 1;
        } else {
            sortCode = 2;
        }
        Pageable pageable = pageUtil.createPageable(page, limit, sort);
        TourResponseModel tours = new TourResponseModel();
        if (placeId != null) {
            PlaceResponseModel placeResponseModel = placeService.findById(placeId);
            tours = tourService.listTourByPlace(placeId, pageable);
            tours.setPageAndSort(page, limit, sort);
            modelAndView.addObject("pageTitle", placeResponseModel.getName());
            modelAndView.addObject("tours", tours);
            modelAndView.addObject("placeId", placeId);
            modelAndView.addObject("sortCode", sortCode);
            modelAndView.addObject("place", placeResponseModel);
        }
        return modelAndView;
    }
}
