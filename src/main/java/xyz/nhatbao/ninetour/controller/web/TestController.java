package xyz.nhatbao.ninetour.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.repository.RegionRepository;

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
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("layouts/web/test");
        mav.addObject("regions", regionRepository.findAll());
        return mav;
    }
}
