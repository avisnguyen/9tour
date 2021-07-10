//package xyz.nhatbao.ninetour.api.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import xyz.nhatbao.ninetour.entity.Token;
//import xyz.nhatbao.ninetour.model.request.UserRequestModel;
//import xyz.nhatbao.ninetour.security.jwt.JwtUtil;
//import xyz.nhatbao.ninetour.security.jwt.UserPrincipal;
//import xyz.nhatbao.ninetour.service.TokenService;
//import xyz.nhatbao.ninetour.service.AuthService;
//
//@RestController
//@RequestMapping("/api")
//public class AuthApi {
//    @Autowired
//    AuthService authService;
//
//    @Autowired
//    TokenService tokenService;
//
//    @Autowired
//    JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserRequestModel user){
//        UserPrincipal userPrincipal = authService.loadUserByEmail(user.getEmail());
//        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
//        }
//        Token token = new Token();
//        token.setToken(jwtUtil.generateToken(userPrincipal));
//        token.setCreatedBy(userPrincipal.getId());
//        token.setTokenExpDate(jwtUtil.generateExpirationDate());
//        tokenService.createToken(token);
//        return ResponseEntity.ok(token.getToken());
//    }
//
//    @GetMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('USER_DELETE')")
//    public ResponseEntity hello(){
//        return ResponseEntity.ok("hello");
////    }
//}
