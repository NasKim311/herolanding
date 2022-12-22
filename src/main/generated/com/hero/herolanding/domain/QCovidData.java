package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCovidData is a Querydsl query type for CovidData
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCovidData extends BeanPath<CovidData> {

    private static final long serialVersionUID = -201992739L;

    public static final QCovidData covidData = new QCovidData("covidData");

    public final StringPath milionCount = createString("milionCount");

    public final StringPath newCovidCount1 = createString("newCovidCount1");

    public final StringPath newCovidCount60 = createString("newCovidCount60");

    public final StringPath samang = createString("samang");

    public final StringPath totalCovidCount = createString("totalCovidCount");

    public QCovidData(String variable) {
        super(CovidData.class, forVariable(variable));
    }

    public QCovidData(Path<? extends CovidData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCovidData(PathMetadata metadata) {
        super(CovidData.class, metadata);
    }

}

