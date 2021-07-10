package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;

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

public interface UserService extends UserDetailsService {
    UserResponseModel createUser(UserRequestModel userRequestModel);

    Long count();

    UserResponseModel getAllUsers(Pageable pageable);

    UserResponseModel getAllUsers();

    UserResponseModel searchUsersForAdmin(Long roleId, String keyword, Pageable pageable);

    UserResponseModel findUserById(Long id);

    UserResponseModel findUserByEmail(String email);

    UserResponseModel updateUser(Long id, UserRequestModel userRequestModel);

    UserResponseModel deleteUsers(UserRequestModel userRequestModel);

    boolean isLogged();

    void updateResetPasswordToken(String token, String email) throws Exception;

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

    List<User> listAll();
}
