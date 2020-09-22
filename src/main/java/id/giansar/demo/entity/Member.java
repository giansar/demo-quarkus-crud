package id.giansar.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Member extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String fullName;

    @Column(unique = true)
    public String email;

    public String password;

    public Boolean isAgreeTerms;
}
