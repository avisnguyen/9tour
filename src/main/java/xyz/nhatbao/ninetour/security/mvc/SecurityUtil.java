package xyz.nhatbao.ninetour.security.mvc;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

@Component
public class SecurityUtil {
//    UserPrincipal principal = (UserPrincipal) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();

    public static UserPrincipal getUserPrincipal() {
        Object p = (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        if (p instanceof UserPrincipal) {
            return (UserPrincipal) p;
        }
        return new UserPrincipal("anonymous", "", false, false, false, false, new ArrayList<>());
    }

    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}
