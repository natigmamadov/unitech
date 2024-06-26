package com.example.unitech.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String pin;
    private String password;

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = ALL)
    private List<AccountEntity> accountList;

}
