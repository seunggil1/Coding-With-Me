package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QTest;
import com.ssafy.db.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QTest qTest = QTest.test;

    public boolean findByTestNameEquals(String testName){
        Test test = jpaQueryFactory.select(qTest).from(qTest)
                .where(qTest.testName.eq(testName)).fetchOne();

        if(test ==null) return true;
        return false;
    }
}
