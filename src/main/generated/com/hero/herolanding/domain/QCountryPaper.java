package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCountryPaper is a Querydsl query type for CountryPaper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCountryPaper extends EntityPathBase<CountryPaper> {

    private static final long serialVersionUID = 77291624L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCountryPaper countryPaper = new QCountryPaper("countryPaper");

    public final QCountry country;

    public final StringPath countryPaperExpiration = createString("countryPaperExpiration");

    public final NumberPath<Integer> countryPaperMinAge = createNumber("countryPaperMinAge", Integer.class);

    public final StringPath countryPaperNote = createString("countryPaperNote");

    public final NumberPath<Integer> countryPaperNum = createNumber("countryPaperNum", Integer.class);

    public final StringPath countryPaperSubmitType = createString("countryPaperSubmitType");

    public final QPaper paper;

    public QCountryPaper(String variable) {
        this(CountryPaper.class, forVariable(variable), INITS);
    }

    public QCountryPaper(Path<? extends CountryPaper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCountryPaper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCountryPaper(PathMetadata metadata, PathInits inits) {
        this(CountryPaper.class, metadata, inits);
    }

    public QCountryPaper(Class<? extends CountryPaper> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country"), inits.get("country")) : null;
        this.paper = inits.isInitialized("paper") ? new QPaper(forProperty("paper")) : null;
    }

}

