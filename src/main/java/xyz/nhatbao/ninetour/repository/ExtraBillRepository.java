package xyz.nhatbao.ninetour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.nhatbao.ninetour.entity.ExtraBill;

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

public interface ExtraBillRepository extends JpaRepository<ExtraBill, Long> {

    @Query("select eb from ExtraBill eb where eb.bill.id = :billId")
    List<ExtraBill> extraBill(@Param("billId") Long billId);

    List<ExtraBill> findByBillId(Long billId);
}
