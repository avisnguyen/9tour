package xyz.nhatbao.ninetour.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.nhatbao.ninetour.entity.Permission;
import xyz.nhatbao.ninetour.entity.Role;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.RoleRequestModel;
import xyz.nhatbao.ninetour.model.response.PermissionResponseModel;
import xyz.nhatbao.ninetour.model.response.RoleResponseModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;

import java.util.Set;


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

@Component
public class RoleConverter {
    @Autowired
    ModelMapper modelMapper;

    public Role toEntity(RoleRequestModel roleRequestModel) {
        Role role = modelMapper.map(roleRequestModel, Role.class);
        if (roleRequestModel.getUsers().size() > 0)
            role.setUsers(modelMapper.map(roleRequestModel.getUsers(), new TypeToken<Set<User>>() {
            }.getType()));
        if (roleRequestModel.getPermissions().size() > 0)
            role.setPermissions(modelMapper.map(roleRequestModel.getPermissions(), new TypeToken<Set<Permission>>() {
            }.getType()));
        return role;
    }

    private RoleResponseModel toModel(Role role) {
        RoleResponseModel roleResponseModel = modelMapper.map(role, RoleResponseModel.class);
        if (role.getUsers().size() > 0)
            roleResponseModel.setUserResponseModels(modelMapper.map(role.getUsers(), new TypeToken<Set<UserResponseModel>>() {
            }.getType()));
        if (role.getPermissions().size() > 0)
            roleResponseModel.setPermissionResponseModels(modelMapper.map(role.getPermissions(), new TypeToken<Set<PermissionResponseModel>>() {
            }.getType()));
        return roleResponseModel;
    }
}
