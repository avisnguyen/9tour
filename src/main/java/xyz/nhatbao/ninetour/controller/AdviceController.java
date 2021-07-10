package xyz.nhatbao.ninetour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import xyz.nhatbao.ninetour.model.response.RegionResponseModel;
import xyz.nhatbao.ninetour.service.RegionService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

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

@ControllerAdvice
public class AdviceController {

    private ConcurrentHashMap<String, LongAdder> counterMap = new ConcurrentHashMap<>();

    @Autowired
    private RegionService regionService;

    @ModelAttribute
    public void getUri(HttpServletRequest request, Model model) {
        String requestURI = request.getRequestURI();
        String query = request.getQueryString();
        if (query != null && query.contains("%26ref%3D")) query = query.substring(0, query.lastIndexOf("&ref="));
        String fullUri = requestURI + "?" + query;
        //counter increment for each access to a particular uri
        counterMap.computeIfAbsent(requestURI, key -> new LongAdder())
                .increment();
        //populating counter in the model
        model.addAttribute("counter", counterMap.get(requestURI).sum());
        //populating request URI in the model
        model.addAttribute("uri", requestURI);
        model.addAttribute("fullUri", fullUri);

    }

    @ModelAttribute
    public void getRegionAndPlace(Model model) {
        RegionResponseModel regionResponseModel = regionService.getAllWithPlaces();
//        RegionResponseModel regionResponseModel = new RegionResponseModel();
        model.addAttribute("regions", regionResponseModel);
    }
}
