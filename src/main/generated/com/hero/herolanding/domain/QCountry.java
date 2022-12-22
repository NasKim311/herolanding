package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCountry is a Querydsl query type for Country
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCountry extends EntityPathBase<Country> {

    private static final long serialVersionUID = -1735044220L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCountry country = new QCountry("country");

    public final ListPath<City, QCity> cities = this.<City, QCity>createList("cities", City.class, QCity.class, PathInits.DIRECT2);

    public final EnumPath<Continent> continent = createEnum("continent", Continent.class);

    public final StringPath countryName = createString("countryName");

    public final StringPath countryNote = createString("countryNote");

    public final NumberPath<Long> countryNum = createNumber("countryNum", Long.class);

    public final ListPath<CountryPaper, QCountryPaper> countryPapers = this.<CountryPaper, QCountryPaper>createList("countryPapers", CountryPaper.class, QCountryPaper.class, PathInits.DIRECT2);

    public final QCovidData covidData;

    public final QCovidVaccinData covidVaccinData;

    public final StringPath embassyLink = createString("embassyLink");

    public final QExchangeRate exchangeRate;

    public final ListPath<Inspection, QInspection> inspections = this.<Inspection, QInspection>createList("inspections", Inspection.class, QInspection.class, PathInits.DIRECT2);

    public final ListPath<Visa, QVisa> visas = this.<Visa, QVisa>createList("visas", Visa.class, QVisa.class, PathInits.DIRECT2);

    public QCountry(String variable) {
        this(Country.class, forVariable(variable), INITS);
    }

    public QCountry(Path<? extends Country> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCountry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCountry(PathMetadata metadata, PathInits inits) {
        this(Country.class, metadata, inits);
    }

    public QCountry(Class<? extends Country> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.covidData = inits.isInitialized("covidData") ? new QCovidData(forProperty("covidData")) : null;
        this.covidVaccinData = inits.isInitialized("covidVaccinData") ? new QCovidVaccinData(forProperty("covidVaccinData")) : null;
        this.exchangeRate = inits.isInitialized("exchangeRate") ? new QExchangeRate(forProperty("exchangeRate")) : null;
    }

}

