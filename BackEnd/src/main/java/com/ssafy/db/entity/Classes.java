package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long classId;

    @ManyToOne(fetch=LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String className;

    @Column
    private String classDescription;
}
