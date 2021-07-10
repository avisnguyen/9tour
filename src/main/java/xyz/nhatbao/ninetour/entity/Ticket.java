package xyz.nhatbao.ninetour.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
        "UPDATE ticket " +
                "SET is_deleted = true " +
                "WHERE id = ?")
public class Ticket extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String pickupPlace;
    private String nationality;
    private Date pickupTime;
    private String returnPlace;
    private String customerNote;

    @OneToOne(mappedBy = "ticket")
    private Bill bill;

    @ManyToOne
    @ToString.Exclude
    private User user;

//    @ManyToOne
//    private AnonymousUser anonymousUser;

    @OneToMany(mappedBy = "ticket")
    @ToString.Exclude
    private List<Passenger> passengers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;

        return Objects.equals(getId(), ticket.getId());
    }

    @Override
    public int hashCode() {
        return 1150304004;
    }
}
