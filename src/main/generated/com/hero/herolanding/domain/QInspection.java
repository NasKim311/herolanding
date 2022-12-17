package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInspection is a Querydsl query type for Inspection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInspection extends EntityPathBase<Inspection> {

    private static final long serialVersionUID = -296525114L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInspection inspection = new QInspection("inspection");

    public final QCity city;

    public final QCountry country;

    public final NumberPath<Integer> inspectionCount = createNumber("inspectionCount", Integer.class);

    public final StringPath inspectionNote = createString("inspectionNote");

    public final NumberPath<Integer> inspectionNum = createNumber("inspectionNum", Integer.class);

    public final NumberPath<Integer> isolationPeriod = createNumber("isolationPeriod", Integer.class);

    public QInspection(String variable) {
        this(Inspection.class, forVariable(variable), INITS);
    }

    public QInspection(Path<? extends Inspection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInspection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInspection(PathMetadata metadata, PathInits inits) {
        this(Inspection.class, metadata, inits);
    }

    public QInspection(Class<? extends Inspection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country"), inits.get("country")) : null;
    }

}

