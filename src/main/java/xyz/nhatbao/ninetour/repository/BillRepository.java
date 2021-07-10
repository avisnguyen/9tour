package xyz.nhatbao.ninetour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.nhatbao.ninetour.entity.Bill;

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

public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("select sum(b.adultQuantity) from Bill b where b.trip.id = :id")
    Integer totalAdult(@Param("id") Long id);

    @Query("select sum(b.childQuantity) from Bill b where b.trip.id = :id")
    Integer totalChild(@Param("id") Long id);

    @Query("select sum(b.infantQuantity) from Bill b where b.trip.id = :id")
    Integer totalInfant(@Param("id") Long id);

    @Query("select b from Bill b " +
            "where (:tourId is null or b.trip.tour.id=:tourId) " +
            "and (:tripId is null or b.trip.id=:tripId) " +
            "and (:keyword is null " +
            "   or b.tourName like %:keyword% " +
            "   or b.codeOfTrip like %:keyword% " +
            "   or b.ticket.firstName like %:keyword% " +
            "   or b.ticket.email like %:keyword% " +
            "   or b.ticket.pickupPlace like %:keyword%) " +
            "and (:from is null or b.modifiedDate > :from or b.modifiedDate = :from) " +
            "and (:to is null or b.modifiedDate < :to or b.modifiedDate = :to)")
    Page<Bill> searchBill(@Param("tourId") Long tourId,
                          @Param("tripId") Long tripId,
                          @Param("keyword") String keyword,
                          @Param("from") Date from,
                          @Param("to") Date to,
                          Pageable pageable);

    @Query("select b from Bill b join b.refundRequests r " +
            "where (:tourId is null or b.trip.tour.id=:tourId) " +
            "and (:tripId is null or b.trip.id=:tripId) " +
            "and (:keyword is null " +
            "   or b.tourName like %:keyword% " +
            "   or b.codeOfTrip like %:keyword% " +
            "   or b.ticket.firstName like %:keyword% " +
            "   or b.ticket.email like %:keyword% " +
            "   or b.ticket.pickupPlace like %:keyword%) " +
            "and (:from is null or b.modifiedDate > :from or b.modifiedDate = :from) " +
            "and (:to is null or b.modifiedDate < :to or b.modifiedDate = :to) " +
            "and (r.status = 'waiting')")
    Page<Bill> searchRefundRequestBill(@Param("tourId") Long tourId,
                                       @Param("tripId") Long tripId,
                                       @Param("keyword") String keyword,
                                       @Param("from") Date from,
                                       @Param("to") Date to,
                                       Pageable pageable);

    @Query("select b from Bill b join b.ticket tk where tk.user.id = :userId order by b.id desc")
    List<Bill> getBillBooked(@Param("userId") Long userId);

    @Query("select b from Bill b where b.ticket.id = :billId")
    Bill showBill(@Param("billId") Long billId);

    @Query("select b from Bill b where b.createdDate > :lastYear")
    List<Bill> getBillInYear(Date lastYear);
}
