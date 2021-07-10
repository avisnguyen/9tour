package xyz.nhatbao.ninetour.controller.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.BillRequestModel;
import xyz.nhatbao.ninetour.model.request.RefundRequestModel;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.BillResponseModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;
import xyz.nhatbao.ninetour.repository.TripRepository;
import xyz.nhatbao.ninetour.repository.UserRepository;
import xyz.nhatbao.ninetour.security.mvc.SecurityUtil;
import xyz.nhatbao.ninetour.security.mvc.UserPrincipal;
import xyz.nhatbao.ninetour.service.BillService;
import xyz.nhatbao.ninetour.service.RefundRequestService;
import xyz.nhatbao.ninetour.service.UserService;
import xyz.nhatbao.ninetour.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
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

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private RefundRequestService refundRequestService;

    @Autowired
    private ModelMapper mapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/tai-khoan/thong-tin")
    public String profilePage(Model model, Authentication authentication, @ModelAttribute("message") String messageModel) {
        String email = authentication.getName();
        UserResponseModel userResponseModel = userService.findUserByEmail(email);
        Map<String, String> message = new HashMap<>();
        if (!messageModel.isEmpty()) message = messageUtil.getMessage(messageModel);
        else message = messageUtil.getMessage(userResponseModel.getMessage());
        model.addAttribute("userCurrent", userResponseModel);
        model.addAttribute("pageTitle", "Thông tin tài khoản");
        model.addAttribute("message", message);
        return "layouts/web/account/profile";
    }

    @GetMapping("/tai-khoan/lich-su-dat-tour")
    public String historyPage(Model model, @ModelAttribute("message") String messageModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        long id = userPrincipal.getId();
//        List<Trip> tripList = tripRepository.tourBooked(id);
//        List<TripResponseModel> tripResponseModels = Arrays.asList(mapper.map(tripList, TripResponseModel[].class));
        List<BillResponseModel> billResponseModels = billService.getBillBooked(id);
        Map<String, String> message = new HashMap<>();
        if (!messageModel.isEmpty()) message = messageUtil.getMessage(messageModel);
        else message = messageUtil.getMessage(null);
        model.addAttribute("pageTitle", "Lịch sử đặt tour");
//        model.addAttribute("trips", tripResponseModels);
        model.addAttribute("bills", billResponseModels);
        model.addAttribute("now", new Date());
        model.addAttribute("billRequest", new BillRequestModel());
        model.addAttribute("message", message);
        return "layouts/web/account/history-tour";
    }


    @PostMapping("/tai-khoan/cap-nhat-thong-tin")
    public ModelAndView processUpdateInformation(@ModelAttribute("userCurrent") UserRequestModel userRequest) {
        ModelAndView modelAndView = new ModelAndView();
        userRequest.setCurrentUserId(SecurityUtil.getUserPrincipal().getId());
        userRequest.setIsEnable(true);
        UserResponseModel result = userService.updateUser(userRequest.getId(), userRequest);
        modelAndView.addObject("message", result.getMessage());
        modelAndView.setViewName("redirect:/tai-khoan/thong-tin");
        return modelAndView;
    }

    @PostMapping("tai-khoan/cap-nhat-mat-khau")
    public ModelAndView processUpdatePassword(Authentication authentication, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userRepository.findByEmail(authentication.getName());
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String message = "";
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            userService.updatePassword(user, newPassword);
            message = "update_success";
        } else message = "incorrect_password";
        modelAndView.addObject("message", message);
        modelAndView.setViewName("redirect:/tai-khoan/thong-tin");
        return modelAndView;
    }

    @GetMapping("tai-khoan/thanh-toan")
    public ModelAndView payment(@RequestParam("billId") Long billId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/error");
        BillResponseModel billResponseModel = billService.showBill(billId);
        if (billResponseModel.getStatus() == "Chưa thanh toán") {
            modelAndView.setViewName("layouts/web/account/payment");
            modelAndView.addObject("bill", billResponseModel);
            modelAndView.addObject("pageTitle", "Thanh toán");
            request.getSession().setAttribute("billId", billResponseModel.getId());
        }
        return modelAndView;
    }

    @GetMapping("tai-khoan/yeu-cau-huy-tour")
    public ModelAndView request(@RequestParam("billId") Long billId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/error");
        BillResponseModel billResponseModel = billService.showBill(billId);
        if (billResponseModel.getStatus() == "Chưa khởi hành") {
            modelAndView.setViewName("layouts/web/account/refund-request");
            modelAndView.addObject("pageTitle", "Yêu cầu hủy tour");
            modelAndView.addObject("refund", new RefundRequestModel());
            modelAndView.addObject("billId", billResponseModel.getId());
        }
        return modelAndView;
    }

    @PostMapping("/tai-khoan/huy-tour")
    public ModelAndView processRefund(RefundRequestModel refundRequestModel) {
        ModelAndView modelAndView = new ModelAndView();
        String message = refundRequestService.refund(refundRequestModel);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("redirect:/tai-khoan/lich-su-dat-tour");
        return modelAndView;
    }
}
