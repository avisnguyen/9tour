package xyz.nhatbao.ninetour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.nhatbao.ninetour.entity.PaymentLog;

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

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
    @Query("select sum(p.amount) from PaymentLog p where size(p.bill.refundRequests)=0")
    Double getTotalMoney();

    List<PaymentLog> findByBillId(Long billId);
}
