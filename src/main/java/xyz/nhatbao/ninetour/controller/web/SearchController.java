package xyz.nhatbao.ninetour.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.service.TourService;
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
public class SearchController {
    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private TourService tourService;

    @GetMapping("/tour/search")
    public ModelAndView searchResultPage(@RequestParam("keyword") String keyword,
                                         @RequestParam("from") String from,
                                         @RequestParam("to") String to,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "6") Integer limit,
                                         @RequestParam(value = "sortBy", defaultValue = "trips.departureTime") String sort) {
        ModelAndView mav = new ModelAndView("layouts/web/tour/search");
        int sortCode;
        if (sort.equals("trips.adultPrice")) {
            sortCode = 1;
        } else {
            sortCode = 2;
        }
        Pageable pageable = pageUtil.createPageable(page, limit, sort);
        TourResponseModel tours = tourService.searchTour(keyword, from, to, pageable);
        tours.setPageAndSort(page, limit, sort);
        mav.addObject("pageTitle", "Kết quả tìm kiếm : " + keyword);
        mav.addObject("tours", tours);
        mav.addObject("keyword", keyword);
        mav.addObject("from", from);
        mav.addObject("to", to);
        mav.addObject("sortCode", sortCode);
        mav.addObject("sort", sort);
        return mav;
    }
}
