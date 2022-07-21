package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String phone;

    @Column(nullable = false)
    String nickname;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    Date birthDt;

    String profilePath;
    @Column(nullable = false)
    String role;

    @Column
    boolean is_deleted;

    @Column
    int verification_code;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
}
