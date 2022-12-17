package com.hero.herolanding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExchangeRate is a Querydsl query type for ExchangeRate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExchangeRate extends EntityPathBase<ExchangeRate> {

    private static final long serialVersionUID = 39767669L;

    public static final QExchangeRate exchangeRate = new QExchangeRate("exchangeRate");

    public final StringPath buyingCash = createString("buyingCash");

    public final ListPath<Country, QCountry> countries = this.<Country, QCountry>createList("countries", Country.class, QCountry.class, PathInits.DIRECT2);

    public final StringPath currencyName = createString("currencyName");

    public final NumberPath<Long> currencyNum = createNumber("currencyNum", Long.class);

    public final StringPath giveMoney = createString("giveMoney");

    public final StringPath joenIlDaeBi = createString("joenIlDaeBi");

    public final StringPath joenIlDaeBiRate = createString("joenIlDaeBiRate");

    public final StringPath sellingCash = createString("sellingCash");

    public final StringPath takeMoney = createString("takeMoney");

    public final StringPath tradingStandardRate = createString("tradingStandardRate");

    public QExchangeRate(String variable) {
        super(ExchangeRate.class, forVariable(variable));
    }

    public QExchangeRate(Path<? extends ExchangeRate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExchangeRate(PathMetadata metadata) {
        super(ExchangeRate.class, metadata);
    }

}

