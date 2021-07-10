package xyz.nhatbao.ninetour.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;
import xyz.nhatbao.ninetour.service.UserService;
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

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    UserService userService;

    @Autowired
    PageUtil pageUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public UserResponseModel createUser(@RequestBody UserRequestModel userRequestModel) {
        userRequestModel.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
        return userService.createUser(userRequestModel);
    }

    @GetMapping
    public UserResponseModel getAllUsers(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "sort", required = false) String sortBy,
                                         @RequestParam(value = "desc", required = false, defaultValue = "false") Boolean desc) {
        Pageable pageable = pageUtil.createPageable(page, limit, sortBy, desc);
        UserResponseModel responseModel = userService.getAllUsers(pageable);
        responseModel.setPageAndSort(page, limit, sortBy, desc);
        return responseModel;
    }

    @GetMapping("/{id}")
    public UserResponseModel findUserById(@PathVariable("id") Long id) {
        UserResponseModel responseModel = userService.findUserById(id);
        return responseModel;
    }

    @GetMapping("/email/{email}")
    public UserResponseModel findUserByEmail(@PathVariable("email") String email) {
        UserResponseModel responseModel = userService.findUserByEmail(email);
        return responseModel;
    }

    @PutMapping("/{id}")
    public UserResponseModel updateUser(@PathVariable("id") Long id, @RequestBody UserRequestModel userRequestModel) {
        userRequestModel.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
        UserResponseModel responseModel = userService.updateUser(id, userRequestModel);
        return responseModel;
    }

    @DeleteMapping
    public UserResponseModel deleteUsers(@RequestBody UserRequestModel requestModel) {
        UserResponseModel responseModel = new UserResponseModel();
        responseModel = userService.deleteUsers(requestModel);
        return responseModel;
    }
}
