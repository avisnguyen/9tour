package xyz.nhatbao.ninetour.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql =
        "UPDATE bill " +
                "SET is_deleted = true " +
                "WHERE ticket_id = ?")
public class Bill extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String tourName;
    private String codeOfTrip;
    private Date departureTimeOfTrip;
    private Double adultPrice;
    private Integer adultQuantity;
    private Double childPrice;
    private Integer childQuantity;
    private Double infantPrice;
    private Integer infantQuantity;

    @ManyToOne
    @ToString.Exclude
    private Trip trip;

    @OneToMany(mappedBy = "bill")
    @ToString.Exclude
    private List<ExtraBill> extraBills = new ArrayList<>();

    @OneToMany(mappedBy = "bill")
    @ToString.Exclude
    private List<PaymentLog> paymentLogs = new ArrayList<>();

    @OneToOne
    @MapsId
    @ToString.Exclude
    private Ticket ticket;

    @OneToMany(mappedBy = "bill")
    @ToString.Exclude
    private List<RefundRequest> refundRequests = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bill bill = (Bill) o;

        return Objects.equals(getId(), bill.getId());
    }

    @Override
    public int hashCode() {
        return 1883064667;
    }
}
