package xyz.nhatbao.ninetour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.nhatbao.ninetour.entity.Trip;

import java.util.Date;
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

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByOrderByIdDesc();

    Page<Trip> findAllByTourId(Long tourId, Pageable pageable);

    @Query("select t from Trip t " +
            "where (:tourId is null or t.tour.id = :tourId) " +
            "and " +
            "(:keyword is null or t.tour.name like %:keyword% or t.code like %:keyword% or t.note like %:keyword%) " +
            "and (:from is null or t.departureTime > :from) " +
            "and (:to is null or t.returnTime < :to)")
    Page<Trip> searchTripAdmin(@Param("tourId") Long tourId,
                               @Param("keyword") String keyword,
                               @Param("from") Date from,
                               @Param("to") Date to,
                               Pageable pageable);

    Trip findFirstByTourIdAndDepartureTimeAfterOrderByAdultPriceAsc(Long tourId, Date now);

    @Query(name = "select tr from Trip tr where tr.tour.id = ?1 and tr.departureTime > current_date order by tr.adultPrice limit 0,1", nativeQuery = true)
    Trip findFirstByTourIdOrderByAdultPrice(Long tourId);

    Trip findFirstByTourIdAndDepartureTimeBetweenOrderByAdultPriceAsc(Long tourId, Date from, Date to);

    List<Trip> findAllByTourIdAndDepartureTimeAfterOrderByDepartureTimeAsc(Long tourId, Date now);

    Trip findAllById(Long id);

    @Query("select tr from Trip tr join tr.bills b join b.ticket tk where tk.user.id = :userId")
    List<Trip> tourBooked(@Param("userId") Long userId);
}
