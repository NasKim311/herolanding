package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVisa is a Querydsl query type for Visa
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVisa extends EntityPathBase<Visa> {

    private static final long serialVersionUID = -619284717L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVisa visa = new QVisa("visa");

    public final QCountry country;

    public final StringPath diplomatPassportPeriod = createString("diplomatPassportPeriod");

    public final BooleanPath diplomatPassportStatus = createBoolean("diplomatPassportStatus");

    public final BooleanPath entranceVisaStatus = createBoolean("entranceVisaStatus");

    public final StringPath normalPassportPeriod = createString("normalPassportPeriod");

    public final BooleanPath normalPassportStatus = createBoolean("normalPassportStatus");

    public final StringPath officialPassportPeriod = createString("officialPassportPeriod");

    public final BooleanPath officialPassportStatus = createBoolean("officialPassportStatus");

    public final StringPath reasonForVisaFree = createString("reasonForVisaFree");

    public final StringPath visaNote = createString("visaNote");

    public final NumberPath<Integer> visaNum = createNumber("visaNum", Integer.class);

    public QVisa(String variable) {
        this(Visa.class, forVariable(variable), INITS);
    }

    public QVisa(Path<? extends Visa> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVisa(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVisa(PathMetadata metadata, PathInits inits) {
        this(Visa.class, metadata, inits);
    }

    public QVisa(Class<? extends Visa> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country"), inits.get("country")) : null;
    }

}

