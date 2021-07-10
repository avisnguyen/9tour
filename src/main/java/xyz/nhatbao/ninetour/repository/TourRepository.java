package xyz.nhatbao.ninetour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.nhatbao.ninetour.entity.Tour;

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

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByOrderByIdDesc();

    @Query("select distinct (t) from Tour t join fetch t.trips trips join fetch t.places p " +
            "where (:keyword is null " +
            "       or t.name like %:keyword% " +
            "       or t.shortDescription like %:keyword% " +
            "       or t.destination.name like %:keyword% " +
            "       or t.departure.name like %:keyword% " +
            "       or p.name like %:keyword% ) " +
            "and (:from is null " +
            "       or :from < trips.departureTime " +
            "       or :from = trips.departureTime) " +
            "and (:to is null " +
            "       or :to > trips.returnTime" +
            "       or :to = trips.returnTime) " +
            "and (trips.departureTime > current_date )")
    List<Tour> searchTourWeb(@Param("keyword") String keyword,
                             @Param("from") Date from,
                             @Param("to") Date to,
                             Pageable pageable);

    @Query("select count(distinct t.id) from Tour t join t.trips tr join t.places p " +
            "where (:keyword is null " +
            "       or t.name like %:keyword% " +
            "       or t.shortDescription like %:keyword% " +
            "       or t.destination.name like %:keyword% " +
            "       or t.departure.name like %:keyword% " +
            "       or p.name like %:keyword% ) " +
            "and (:from is null " +
            "       or :from < tr.departureTime " +
            "       or :from = tr.departureTime) " +
            "and (:to is null " +
            "       or :to > tr.returnTime" +
            "       or :to = tr.returnTime) " +
            "and (tr.departureTime > current_date )")
    Long countSearchTourWeb(@Param("keyword") String keyword,
                            @Param("from") Date from,
                            @Param("to") Date to);

    List<Tour> findByDepartureIdOrderByIdDesc(Long id);

    @Query("select t from Tour t left join t.trips trips join t.places pl where pl.id = ?1")
    List<Tour> findByPlaceId(Long place, Pageable pageable);

    @Query("select count(distinct t) from Tour t join t.places pl where  pl.id = :pl")
    Long count(@Param("pl") Long id);

    @Query("select t from Tour t join t.extraServices ex where ex.id = :serviceId")
    List<Tour> searchTourByServiceId(Long serviceId);

    @Query("select t from Tour t left join t.trips trips")
    Page<Tour> searchAll(Pageable pageable);

}
