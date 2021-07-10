package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.converter.UserConverter;
import xyz.nhatbao.ninetour.entity.Role;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.UserRequestModel;
import xyz.nhatbao.ninetour.model.response.UserResponseModel;
import xyz.nhatbao.ninetour.repository.RoleRepository;
import xyz.nhatbao.ninetour.repository.UserRepository;
import xyz.nhatbao.ninetour.security.mvc.UserPrincipal;
import xyz.nhatbao.ninetour.service.UserService;

import javax.transaction.Transactional;
import java.util.*;

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
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserConverter userConverter;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!user.getIsEnable()) {
            throw new UsernameNotFoundException("Account is banned");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleKey()));
        }

        UserPrincipal userPrincipal = new UserPrincipal(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                grantedAuthorities
        );
        userPrincipal.setId(user.getId());
        userPrincipal.setFirstName(user.getFirstName());
        userPrincipal.setLastName(user.getLastName());
        userPrincipal.setEmail(user.getEmail());
        userPrincipal.setAddress(user.getAddress());
        userPrincipal.setPhone(user.getPhone());
        userPrincipal.setSex(user.getSex());
        userPrincipal.setNationality(user.getNationality());

        return userPrincipal;
    }

    @Override
    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        UserResponseModel result = new UserResponseModel();
        User recentUser = userRepository.findByEmail(userRequestModel.getEmail());
        if (recentUser != null) {
            result.setMessage("email_available");
            return result;
        }
        userRequestModel.setPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));
        User userEntity = userConverter.toEntity(userRequestModel);


        Set<Role> roles = new HashSet<>();
        for (Long role : userRequestModel.getRoles()) {
            Optional<Role> roleEntity = roleRepository.findById(role);
            roleEntity.ifPresent(roles::add);
        }
        userEntity.setRoles(roles);
        if (userRequestModel.getCurrentUserId() != null) {
            User currentUser = userRepository.findById(userRequestModel.getCurrentUserId()).get();
            if (getMaxLevel(currentUser) <= getMaxLevel(userEntity)) {
                result.setMessage("not_permission_create_user");
                return result;
            }
        }

        result = userConverter.toModel(userRepository.save(userEntity));
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public UserResponseModel getAllUsers(Pageable pageable) {
        UserResponseModel result = new UserResponseModel();
        List<UserResponseModel> listResponse = new ArrayList<>();
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = new ArrayList<>();
        for (User user :
                userPage) {
            if (!users.contains(user)) {
                users.add(user);
            }
        }
        if (users.isEmpty()) return null;
        for (User user :
                users) {
            listResponse.add(userConverter.toModel(user));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public UserResponseModel getAllUsers() {
        UserResponseModel result = new UserResponseModel();
        List<UserResponseModel> listResponse = new ArrayList<>();
        List<User> users = userRepository.findAll();
        ;
        for (User user :
                users) {
            if (!users.contains(user)) {
                users.add(user);
            }
        }
        if (users.isEmpty()) return null;
        for (User user :
                users) {
            listResponse.add(userConverter.toModel(user));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public UserResponseModel searchUsersForAdmin(Long roleId, String keyword, Pageable pageable) {
        UserResponseModel result = new UserResponseModel();
        List<UserResponseModel> listResponse = new ArrayList<>();
        Page<User> userPage = userRepository.searchUserAdmin(roleId, keyword, pageable);
        List<User> users = new ArrayList<>();
        for (User user :
                userPage.getContent()) {
            if (!users.contains(user)) {
                users.add(user);
            }
        }
        if (users.isEmpty()) return null;
        Order order = userPage.getSort().getOrderFor("r.permissionLevel");
        if (order != null && !order.isDescending()) {
            users.sort(Comparator.comparingInt(this::getMaxLevel));
        }
        for (User user :
                users) {
            listResponse.add(userConverter.toModel(user));
        }
        result.setResults(listResponse);
        Long totalItem = userRepository.totalSearchUserAdmin(roleId, keyword);
        result.setTotalItems(totalItem);
        return result;
    }

    @Override
    public UserResponseModel findUserById(Long id) {
        UserResponseModel result = new UserResponseModel();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) result = userConverter.toModel(userOptional.get());
        return result;
    }

    @Override
    public UserResponseModel findUserByEmail(String email) {
        UserResponseModel result = new UserResponseModel();
        User user = userRepository.findByEmail(email);
        if (user == null) return null;
        result = userConverter.toModel(user);
        return result;
    }

    @Override
    public UserResponseModel updateUser(Long id, UserRequestModel userRequestModel) {
        UserResponseModel result = new UserResponseModel();
        if (!(userRequestModel.getPassword() == null))
            userRequestModel.setPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));
        User currentUser = userRepository.findById(userRequestModel.getCurrentUserId()).get();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User oldUser = userOptional.get();
            String prePassword = oldUser.getPassword();
            Set<Role> preRoles = oldUser.getRoles();
            Set<Long> preRoleIds = new HashSet<>();
            for (Role role :
                    preRoles) {
                preRoleIds.add(role.getId());
            }
            Set<Role> newRoles = new HashSet<>();
            for (Long roleId :
                    userRequestModel.getRoles()) {
                newRoles.add(roleRepository.getOne(roleId));
            }
            userRequestModel.setId(id);
            User newUser = new User();

            // Self edit
            if (userRequestModel.getCurrentUserId().equals(userRequestModel.getId())) {
                newUser = userConverter.toEntity(userRequestModel);
                String message = "";
                if (!preRoleIds.equals(userRequestModel.getRoles())) {
                    newUser.setRoles(preRoles);
                    newUser.setIsEnable(oldUser.getIsEnable());
                    message = "not_permission_self_role_update";
                } else if (userRequestModel.getIsEnable() != oldUser.getIsEnable()) {
                    newUser.setIsEnable(oldUser.getIsEnable());
                    newUser.setRoles(newRoles);
                    message = "not_permission_self_active_update";
                } else {
                    newUser.setRoles(newRoles);
                    message = "update_success";
                }
                if (userRequestModel.getPassword() == null) newUser.setPassword(prePassword);
                result = userConverter.toModel(userRepository.save(newUser));
                result.setMessage(message);
                return result;
            }

            // Higher or equal level edit
            if (getMaxLevel(oldUser) >= getMaxLevel(currentUser)) {
                result = userConverter.toModel(oldUser);
                result.setMessage("not_permission_update_user");
                return result;
            }

            newUser = userConverter.toEntity(userRequestModel);
            if (userRequestModel.getPassword() == null) newUser.setPassword(prePassword);
            newUser.setRoles(newRoles);
            newUser.setEmail(oldUser.getEmail()); // deny edit email
            newUser.setCreatedBy(oldUser.getCreatedBy()); // deny edit create by
            newUser.setCreatedDate(oldUser.getCreatedDate()); // deny edit create date


            // Update to equal or higher level
            if (getMaxLevel(newUser) >= getMaxLevel(currentUser)) {
                newUser.setRoles(preRoles);
                result = userConverter.toModel(userRepository.save(newUser));
                result.setMessage("not_permission_update_user_to_equal");
                return result;
            }

            // Normal update
            result = userConverter.toModel(userRepository.save(newUser));
            result.setMessage("update_success");


        } else {
            result.setMessage("user_not_found");
        }
        return result;
    }

    @Override
    public UserResponseModel deleteUsers(UserRequestModel userRequestModel) {
        UserResponseModel userResponseModel = new UserResponseModel();
        User currentUser = userRepository.findById(userRequestModel.getCurrentUserId()).get();
        boolean canNotDelExist = false;
        List<Long> deletedIds = new ArrayList<>();
        for (Long id :
                userRequestModel.getIds()) {
            Optional<User> willDelUser = userRepository.findById(id);
            if (willDelUser.isPresent() && (getMaxLevel(currentUser) > getMaxLevel(willDelUser.get()))) {
                userRepository.deleteById(id);
                deletedIds.add(id);
            } else canNotDelExist = true;
        }
        userResponseModel.setIds(deletedIds);
        if (canNotDelExist) userResponseModel.setMessage("delete_fail_some");
        return userResponseModel;
    }

    @Override
    public boolean isLogged() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("Không tìm thấy email: " + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


    @Override
    public List<User> listAll() {
        return userRepository.findAll(Sort.by("email").ascending());
    }


    private Byte getMaxLevel(User user) {
        Byte maxLevel = Byte.MIN_VALUE;
        for (Role role :
                user.getRoles()) {
            if (role.getPermissionLevel() > maxLevel) maxLevel = role.getPermissionLevel();
        }
        return maxLevel;
    }

}
