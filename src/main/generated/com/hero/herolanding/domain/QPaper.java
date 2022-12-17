package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaper is a Querydsl query type for Paper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaper extends EntityPathBase<Paper> {

    private static final long serialVersionUID = -2023739142L;

    public static final QPaper paper = new QPaper("paper");

    public final ListPath<CountryPaper, QCountryPaper> countryPapers = this.<CountryPaper, QCountryPaper>createList("countryPapers", CountryPaper.class, QCountryPaper.class, PathInits.DIRECT2);

    public final StringPath paperAuthorityLink = createString("paperAuthorityLink");

    public final StringPath paperAuthorityName = createString("paperAuthorityName");

    public final StringPath paperNote = createString("paperNote");

    public final NumberPath<Integer> paperNum = createNumber("paperNum", Integer.class);

    public final StringPath paperTitle = createString("paperTitle");

    public QPaper(String variable) {
        super(Paper.class, forVariable(variable));
    }

    public QPaper(Path<? extends Paper> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaper(PathMetadata metadata) {
        super(Paper.class, metadata);
    }

}

