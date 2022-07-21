package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConferenceHistory is a Querydsl query type for ConferenceHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConferenceHistory extends EntityPathBase<ConferenceHistory> {

    private static final long serialVersionUID = -321961898L;

    public static final QConferenceHistory conferenceHistory = new QConferenceHistory("conferenceHistory");

    public final NumberPath<Long> classId = createNumber("classId", Long.class);

    public final TimePath<java.util.Date> confEndTime = createTime("confEndTime", java.util.Date.class);

    public final NumberPath<Long> conferenceId = createNumber("conferenceId", Long.class);

    public final StringPath conferenceName = createString("conferenceName");

    public final TimePath<java.util.Date> confStartTime = createTime("confStartTime", java.util.Date.class);

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath ownerId = createString("ownerId");

    public final StringPath thumbnailPath = createString("thumbnailPath");

    public QConferenceHistory(String variable) {
        super(ConferenceHistory.class, forVariable(variable));
    }

    public QConferenceHistory(Path<? extends ConferenceHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConferenceHistory(PathMetadata metadata) {
        super(ConferenceHistory.class, metadata);
    }

}

