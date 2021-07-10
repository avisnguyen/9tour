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
        "UPDATE rating " +
                "SET is_deleted = true " +
                "WHERE id = ?")
public class Rating extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "LONGTEXT")
    private String comment;
    private Integer rating;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tour tour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rating rating = (Rating) o;

        return Objects.equals(getId(), rating.getId());
    }

    @Override
    public int hashCode() {
        return 708680207;
    }
}
