package xyz.nhatbao.ninetour.controller.admin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.RoleResponseModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;
import xyz.nhatbao.ninetour.other.UserExcelExporter;
import xyz.nhatbao.ninetour.security.mvc.SecurityUtil;
import xyz.nhatbao.ninetour.service.RoleService;
import xyz.nhatbao.ninetour.service.UserService;
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

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PageUtil pageUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    private MessageUtil messageUtil = new MessageUtil();

    @GetMapping("/list")
    public ModelAndView listUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 @RequestParam(value = "sort", required = false) String sortBy,
                                 @RequestParam(value = "role", required = false) Long roleId,
                                 @RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc,
                                 @ModelAttribute("message") String message,
                                 Model model) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/user/list");
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        UserResponseModel userResponseModel = userService.searchUsersForAdmin(roleId, keyword, pageable);
        Map<String, String> messageModel = new HashMap<>();
        if (message.isEmpty()) messageModel = messageUtil.getMessage(userResponseModel.getMessage());
        else messageModel = messageUtil.getMessage(message);
        userResponseModel.setPageAndSort(page, limit, sortBy, desc);

        RoleResponseModel roleResponseModel = roleService.getAll();
        JSONArray allRoles = new JSONArray();
        for (RoleResponseModel role :
                roleResponseModel.getResults()) {
            Map<String, String> jsonRole = new HashMap<>();
            jsonRole.put("name", role.getRoleName());
            jsonRole.put("id", role.getId().toString());
            allRoles.put(new JSONObject(jsonRole));
        }

        if (roleId != null) {
            RoleResponseModel selectedRole = roleService.findById(roleId);
            modelAndView.addObject("role", selectedRole);
        }

        modelAndView.addObject("userRequestModel", new UserRequestModel());
        modelAndView.addObject("userModel", userResponseModel);
        modelAndView.addObject("allRoles", allRoles);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("message", messageModel);
        return modelAndView;
    }

    @GetMapping(value = {"/add", "/edit"})
    public ModelAndView editUser(@RequestParam(value = "id", required = false) Long id,
                                 @ModelAttribute("message") String messageModel) {
        ModelAndView modelAndView = new ModelAndView("layouts/admin/user/edit");
        UserResponseModel userResponseModel = new UserResponseModel();
        RoleResponseModel roleResponseModel = roleService.getAll();
        Map<String, String> message = new HashMap<>();
        if (id != null) userResponseModel = userService.findUserById(id);
        if (!messageModel.isEmpty()) message = messageUtil.getMessage(messageModel);
        else message = messageUtil.getMessage(userResponseModel.getMessage());
//        message = messageUtil.getMessage(userResponseModel.getMessage());
        Map<Long, Boolean> roleCheckList = new HashMap<>();
        for (RoleResponseModel role : userResponseModel.getRoleResponseModels()) {
            roleCheckList.put(role.getId(), true);
        }
        modelAndView.addObject("userModel", userResponseModel);
        modelAndView.addObject("roles", roleResponseModel);
        modelAndView.addObject("roleCheckList", roleCheckList);
        modelAndView.addObject("message", message);
        modelAndView.addObject("userRequestModel", new UserRequestModel());
        return modelAndView;
    }

    @PostMapping(value = {"/add", "/edit"})
    public ModelAndView processEditUser(@Valid @ModelAttribute("userRequestModel") UserRequestModel userRequestModel) {
        UserResponseModel userResponseModel = new UserResponseModel();
        userRequestModel.setCurrentUserId(SecurityUtil.getUserPrincipal().getId());
        ModelAndView modelAndView = new ModelAndView();
        if (userRequestModel.getId() != null) {
            userResponseModel = userService.updateUser(userRequestModel.getId(), userRequestModel);
            modelAndView.setViewName("redirect:/admin/user/edit?id=" + userResponseModel.getId().toString());
        } else {
            userResponseModel = userService.createUser(userRequestModel);
            if (userResponseModel.getMessage().equals("insert_success"))
                modelAndView.setViewName("redirect:/admin/user/edit?id=" + userResponseModel.getId().toString());
            else modelAndView.setViewName("redirect:/admin/user/add");
        }
        modelAndView.addObject("message", userResponseModel.getMessage());
        return modelAndView;
    }

    @PostMapping(value = {"/delete"})
    public ModelAndView processDeleteUser(@ModelAttribute UserRequestModel userRequestModel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/user/list");
        userRequestModel.setCurrentUserId(SecurityUtil.getUserPrincipal().getId());
        UserResponseModel userResponseModel = new UserResponseModel();
        List<Long> deleteList = userRequestModel.getIds();
        if (deleteList != null) userResponseModel = userService.deleteUsers(userRequestModel);
        modelAndView.addObject("message", userResponseModel.getMessage());
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
        String headerValue = "attachment; filename=users_" + currentTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        UserResponseModel users = userService.getAllUsers();

        UserExcelExporter excelExporter = new UserExcelExporter(users.getResults());
        excelExporter.export(response);
    }
}
