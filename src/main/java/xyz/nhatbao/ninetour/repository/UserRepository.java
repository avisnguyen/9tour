package xyz.nhatbao.ninetour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.nhatbao.ninetour.entity.User;

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

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByResetPasswordToken(String token);

    @Query(value = "select u from User u join u.roles r " +
            "where (:roleId is null or r.id = :roleId ) " +
            "and (:keyword is null " +
            "   or u.email like %:keyword% " +
            "   or u.firstName like %:keyword%)")
    Page<User> searchUserAdmin(@Param("roleId") Long roleId,
                               @Param("keyword") String keyword,
                               Pageable pageable);

    @Query("select count(distinct u.id) from User u join u.roles r " +
            "where (:roleId is null or r.id = :roleId ) " +
            "and (:keyword is null " +
            "   or u.email like %:keyword% " +
            "   or u.firstName like %:keyword%)")
    Long totalSearchUserAdmin(@Param("roleId") Long roleId,
                              @Param("keyword") String keyword);
}