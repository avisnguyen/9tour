package xyz.nhatbao.ninetour.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.service.DashboardService;

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

@Controller(value = "homeAdminController")
public class HomeController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping(value = {"/admin/trang-chu", "/admin"})
    public ModelAndView adminHomePage() {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/home");
        modelAndView.addObject("data", dashboardService.getData());
        return modelAndView;
    }
}
