package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.entity.Role;
import xyz.nhatbao.ninetour.model.request.RoleRequestModel;
import xyz.nhatbao.ninetour.model.response.RoleResponseModel;
import xyz.nhatbao.ninetour.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

@Service
@Transactional
public class RoleServiceImpl implements xyz.nhatbao.ninetour.service.RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public RoleResponseModel create(RoleRequestModel roleRequestModel) {
        RoleResponseModel result = new RoleResponseModel();
        Role role = mapper.map(roleRequestModel, Role.class);
        result = mapper.map(roleRepository.save(role), RoleResponseModel.class);
        return result;
    }

    @Override
    public Long count() {
        return roleRepository.count();
    }

    @Override
    public RoleResponseModel getAll() {
        RoleResponseModel result = new RoleResponseModel();
        List<RoleResponseModel> listResponse = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        listResponse = mapper.map(roles, new TypeToken<List<RoleResponseModel>>() {
        }.getType());
        result.setResults(listResponse);
        return result;
    }

    @Override
    public RoleResponseModel findById(Long id) {
        RoleResponseModel result = new RoleResponseModel();
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) result = mapper.map(roleOptional.get(), RoleResponseModel.class);
        return result;
    }

    @Override
    public RoleResponseModel delete(List<Long> ids) {
        return null;
    }
}
