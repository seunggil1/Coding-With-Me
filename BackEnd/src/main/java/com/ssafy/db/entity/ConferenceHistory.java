package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConferenceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long conferenceId;
    Long classId;
    @Temporal(TemporalType.DATE)
    Date date;
    String ownerId;
    @Temporal(TemporalType.TIME)
    Date confStartTime;
    @Temporal(TemporalType.TIME)
    Date confEndTime;
    String thumbnailPath;
    String conferenceName;
    boolean isActive;

}
