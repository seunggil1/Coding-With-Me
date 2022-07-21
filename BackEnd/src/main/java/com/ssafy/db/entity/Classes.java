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
    public void setUser(User user){
        this.user =user;
        //무한 루프 주의
        if(!user.getClasses().contains(this)){
            user.getClasses().add(this);
        }
    }

    @Column
    private String classDescription;
}
