package xyz.nhatbao.ninetour.service.impl;//package xyz.nhatbao.ninetour.service.impl;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import xyz.nhatbao.ninetour.entity.User;
//import xyz.nhatbao.ninetour.repository.UserRepository;
//import xyz.nhatbao.ninetour.security.jwt.UserPrincipal;
//import xyz.nhatbao.ninetour.service.AuthService;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class AuthServiceImpl implements AuthService{
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ModelMapper mapper;
//
//    @Override
//    public UserPrincipal loadUserByEmail(String email) {
//        UserPrincipal userPrincipal = new UserPrincipal();
//        User user = userRepository.findByEmail(email);
//        if (null != user) {
//            Set<String> authorities = new HashSet<>();
//            if (null != user.getRoles()) user.getRoles().forEach(r -> {
//                authorities.add(r.getRoleKey());
//                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
//            });
//
//            userPrincipal.setId(user.getId());
//            userPrincipal.setUsername(user.getEmail());
//            userPrincipal.setPassword(user.getPassword());
//            userPrincipal.setAuthorities(authorities);
//        }
//
//        return userPrincipal;
//    }
//}
