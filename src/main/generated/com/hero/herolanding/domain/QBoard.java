package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -2036265388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath boardContents = createString("boardContents");

    public final NumberPath<Long> boardCount = createNumber("boardCount", Long.class);

    public final NumberPath<Long> boardNum = createNumber("boardNum", Long.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final EnumPath<BoardType> boardType = createEnum("boardType", BoardType.class);

    public final EnumPath<Continent> continent = createEnum("continent", Continent.class);

    public final StringPath insertDate = createString("insertDate");

    public final QMember member;

    public final ListPath<Reply, QReply> replys = this.<Reply, QReply>createList("replys", Reply.class, QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> reportCount = createNumber("reportCount", Long.class);

    public final ListPath<Report, QReport> reports = this.<Report, QReport>createList("reports", Report.class, QReport.class, PathInits.DIRECT2);

    public final StringPath updateDate = createString("updateDate");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

