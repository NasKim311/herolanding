package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1606310124L;

    public static final QMember member = new QMember("member1");

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    public final BooleanPath memberAdvAgree = createBoolean("memberAdvAgree");

    public final BooleanPath memberDataAgree = createBoolean("memberDataAgree");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberId = createString("memberId");

    public final BooleanPath memberIsjoin = createBoolean("memberIsjoin");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberNickName = createString("memberNickName");

    public final NumberPath<Long> memberNum = createNumber("memberNum", Long.class);

    public final StringPath memberPhoneNum = createString("memberPhoneNum");

    public final StringPath memberPw = createString("memberPw");

    public final BooleanPath memberUsingAgree = createBoolean("memberUsingAgree");

    public final ListPath<Reply, QReply> replys = this.<Reply, QReply>createList("replys", Reply.class, QReply.class, PathInits.DIRECT2);

    public final ListPath<Report, QReport> reports = this.<Report, QReport>createList("reports", Report.class, QReport.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> signUpDate = createDate("signUpDate", java.time.LocalDate.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

