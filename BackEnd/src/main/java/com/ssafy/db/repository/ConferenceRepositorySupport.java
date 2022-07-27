package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.QConference;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConferenceRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QConference qConference = QConference.conference;

    public Optional<Conference> findByClassesClassIdActive(Long classId){

        Conference conference = jpaQueryFactory.select(qConference).from(qConference)
                .where(qConference.isActive.eq(true)).fetchOne();
        if(conference == null) return Optional.empty();
        return Optional.ofNullable(conference);
    };

    public Optional<List<Conference>> findByClassesClassIdNotActive(Long classId){

        List<Conference >conferences = jpaQueryFactory.select(qConference).from(qConference)
                .where(qConference.isActive.eq(false)).fetch();
        if(conferences == null) return Optional.empty();
        return Optional.ofNullable(conferences);
    };

    public Optional<List<Conference>> findByConferenceName(String conferenceName){
        List<Conference>conferences = jpaQueryFactory.select(qConference).from(qConference)
                .where(qConference.conferenceName.contains(conferenceName)).where(qConference.isActive.eq(true)).fetch();
        if(conferences == null) return Optional.empty();
        return Optional.ofNullable(conferences);
    }

    public Optional<Conference> findById(String id){
        Conference conference = jpaQueryFactory.select(qConference).from(qConference)
                .where(qConference.ownerId.eq(id)).where(qConference.isActive.eq(true)).fetchOne();
        if(conference == null) return Optional.empty();
        return Optional.ofNullable(conference);
    }

}
