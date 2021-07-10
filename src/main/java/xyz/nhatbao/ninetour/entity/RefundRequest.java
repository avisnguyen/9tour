package xyz.nhatbao.ninetour.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
        "UPDATE refund_request " +
                "SET is_deleted = true " +
                "WHERE id = ?")
public class RefundRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String contactMail;
    private String contactPhone;
    private String sender;
    private String receiver;
    private String status;
    @Column(columnDefinition = "LONGTEXT")
    private String request;
    @Column(columnDefinition = "LONGTEXT")
    private String response;
    @Column(columnDefinition = "LONGTEXT")
    private String note;

    @ManyToOne
    private Bill bill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RefundRequest that = (RefundRequest) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 822553825;
    }
}
