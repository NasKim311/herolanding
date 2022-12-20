package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCovidVaccinData is a Querydsl query type for CovidVaccinData
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCovidVaccinData extends BeanPath<CovidVaccinData> {

    private static final long serialVersionUID = 211878509L;

    public static final QCovidVaccinData covidVaccinData = new QCovidVaccinData("covidVaccinData");

    public final StringPath injectionCompleteCount = createString("injectionCompleteCount");

    public final StringPath injectionCompletePercent = createString("injectionCompletePercent");

    public final StringPath newInjectionCount1 = createString("newInjectionCount1");

    public final StringPath newInjectionCount60 = createString("newInjectionCount60");

    public final StringPath totalInjectionCount = createString("totalInjectionCount");

    public QCovidVaccinData(String variable) {
        super(CovidVaccinData.class, forVariable(variable));
    }

    public QCovidVaccinData(Path<? extends CovidVaccinData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCovidVaccinData(PathMetadata metadata) {
        super(CovidVaccinData.class, metadata);
    }

}

