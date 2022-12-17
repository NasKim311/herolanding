package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCity is a Querydsl query type for City
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCity extends EntityPathBase<City> {

    private static final long serialVersionUID = -619850691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCity city = new QCity("city");

    public final StringPath cityName = createString("cityName");

    public final NumberPath<Long> cityNum = createNumber("cityNum", Long.class);

    public final QCountry country;

    public final EnumPath<EntranceLevel> entranceLevel = createEnum("entranceLevel", EntranceLevel.class);

    public final ListPath<Inspection, QInspection> inspections = this.<Inspection, QInspection>createList("inspections", Inspection.class, QInspection.class, PathInits.DIRECT2);

    public QCity(String variable) {
        this(City.class, forVariable(variable), INITS);
    }

    public QCity(Path<? extends City> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCity(PathMetadata metadata, PathInits inits) {
        this(City.class, metadata, inits);
    }

    public QCity(Class<? extends City> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country"), inits.get("country")) : null;
    }

}

