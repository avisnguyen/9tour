package xyz.nhatbao.ninetour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.nhatbao.ninetour.entity.ExtraService;

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

public interface ExtraServiceRepository extends JpaRepository<ExtraService, Long> {
    @Query("select e from ExtraService e join e.tours t " +
            "where (:tourId is null or t.id = :tourId) " +
            "and (:keyword is null or e.name like %:keyword% or e.shortDescription like %:keyword%)")
    Page<ExtraService> searchExtraServiceAdmin(@Param("tourId") Long tourId,
                                               @Param("keyword") String keyword,
                                               Pageable pageable);

    @Query("select distinct e from ExtraService e join e.tours t " +
            "where (:tourId is null or t.id = :tourId)")
    List<ExtraService> findServiceByTourId(@Param("tourId") Long tourId);
}
