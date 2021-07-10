package xyz.nhatbao.ninetour.controller.web;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.PostResponseModel;
import xyz.nhatbao.ninetour.model.response.TourResponseModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;
import xyz.nhatbao.ninetour.repository.RoleRepository;
import xyz.nhatbao.ninetour.repository.UserRepository;
import xyz.nhatbao.ninetour.service.MailService;
import xyz.nhatbao.ninetour.service.PostService;
import xyz.nhatbao.ninetour.service.TourService;
import xyz.nhatbao.ninetour.service.UserService;
import xyz.nhatbao.ninetour.util.MessageUtil;
import xyz.nhatbao.ninetour.util.Utility;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TourService tourService;

    @Autowired
    private PostService postService;

    MessageUtil messageUtil = new MessageUtil();

    @GetMapping(value = {"/", "/trang-chu"})
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("layouts/web/home");
        TourResponseModel tourResponseModel = new TourResponseModel();
        PostResponseModel postResponseModel = new PostResponseModel();
        tourResponseModel.setResults(tourService.findAllIdDesc());
        postResponseModel.setResults(postService.findAllIdDesc());
        mav.addObject("pageTitle", "Trang chủ");
        mav.addObject("tours", tourResponseModel);
        mav.addObject("posts", postResponseModel);
        return mav;
    }

    @GetMapping("/dang-nhap")
    public ModelAndView loginPage(@RequestParam(required = false) String message) {
        ModelAndView mav = new ModelAndView("layouts/web/account/login");
        if (userService.isLogged()) return new ModelAndView("redirect:/");
        Map<String, String> messageModel = new HashMap<>();
        if (message != null && !message.isEmpty()) {
//            if (message.equals("timeout")) {
//                mav.addObject("message", "Hết phiên làm việc!");
//            }
//            if (message.equals("max_session")) {
//                mav.addObject("message", "Tài khoản của bạn đang được đăng nhập ở thiết bị khác!");
//            }
//            if (message.equals("logout_success")) {
//                mav.addObject("message", "Tài khoản của bạn đã đăng xuất!");
//            }
//            if (message.equals("error")) {
//                mav.addObject("message", "Tài khoản hoặc mật khẩu không chính xác!");
//            }
            messageModel = messageUtil.getMessage(message);
        }
        mav.addObject("pageTitle", "Đăng nhập");
        mav.addObject("message", messageModel);
        return mav;
    }

    @GetMapping("/dang-ky")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("layouts/web/account/register");
        if (userService.isLogged()) return new ModelAndView("redirect:/dang-xuat");
        UserRequestModel userRequestModel = new UserRequestModel();
        mav.addObject("pageTitle", "Đăng ký");
        mav.addObject("user", userRequestModel);
        return mav;
    }

    @GetMapping("/dang-xuat")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("redirect:/dang-nhap?message=logout_success");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return modelAndView;
    }

    @GetMapping("/403")
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView("layouts/web/error/403");
        mav.addObject("pageTitle", "403 - Forbidden");
        return mav;
    }

    @PostMapping("/dang-ky")
    public ModelAndView processRegister(@ModelAttribute("user") UserRequestModel userRequestModel, BindingResult result, Model model) {
        ModelAndView mav = new ModelAndView("layouts/web/account/register");
        UserResponseModel userResponseModel = userService.findUserByEmail(userRequestModel.getEmail());
        if (userResponseModel != null) {
            userResponseModel.setMessage("Email này đã tồn tại trong hệ thống");
            mav.addObject("message", userResponseModel.getMessage());
            mav.addObject("pageTitle", "Đăng ký thất bại");
            return mav;
        } else {
            mav = new ModelAndView("redirect:/dang-nhap?message=register_success");
            List<Long> roles = new ArrayList<>();
            roles.add(roleRepository.findAllByRoleKey("USER").getId());
            userRequestModel.setRoles(roles);
            userService.createUser(userRequestModel);
            mailService.sendMail(userRequestModel.getEmail());
            return mav;
        }
    }

    @GetMapping("/quen-mat-khau")
    public String forgotPasswordPage(Model model) {
        model.addAttribute("pageTitle", "Quên mật khẩu");
        return "layouts/web/account/forgot-password";
    }

    @PostMapping("/quen-mat-khau")
    public ModelAndView processForgotPassword(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("layouts/web/account/forgot-password");
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;
            mailService.sendMailResetPassword(email, resetPasswordLink);
            modelAndView.addObject("message", "Đường dẫn lấy lại mật khẩu đã được gửi đi. Vui lòng kiểm tra Email");
        } catch (UsernameNotFoundException e) {
            modelAndView.addObject("error", e.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            modelAndView.addObject("error", "Error while sending email");
        }
        modelAndView.addObject("pageTitle", "Quên mật khẩu");
        return modelAndView;
    }

    @GetMapping("/reset-password")
    public String resetForgotPasswordPage(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Đặt lại mật khẩu");
        if (user == null) {
            model.addAttribute("pageTitle", "Invalid Token");
            return "layouts/web/error/invalid";
        }
        return "layouts/web/account/reset-password";
    }

    @PostMapping("/reset-password")
    public ModelAndView processResetPassword(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("layouts/web/error/invalid");
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            modelAndView.addObject("message", "Invalid Token");
            modelAndView.addObject("pageTitle", "Invalid Token");
            return modelAndView;
        } else {
            userService.updatePassword(user, password);
            modelAndView.addObject("message", "Đã cập nhật mật khẩu thành công");
            return new ModelAndView("redirect:/dang-nhap?message=reset_success");
        }
    }

}
