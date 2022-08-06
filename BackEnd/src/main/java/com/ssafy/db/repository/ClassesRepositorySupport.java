package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.QClasses;
import com.ssafy.db.entity.QTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClassesRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QClasses qClasses = QClasses.classes;
    QTest qTest = QTest.test;

    //    @Query("select c from Classes c join fetch c.testList where c.classId=:classId")
    public Optional<Classes> findByClassIdWithTestList(Long classId) {
        Classes classes = jpaQueryFactory
                .select(qClasses)
                .from(qClasses)
                .leftJoin(qClasses.testList, qTest)
                .fetchJoin()
                .distinct()
                .where(qClasses.classId.eq(classId))
                .fetchOne();
        if (classes == null) return Optional.empty();
        return Optional.ofNullable(classes);
    }

    ;
}
