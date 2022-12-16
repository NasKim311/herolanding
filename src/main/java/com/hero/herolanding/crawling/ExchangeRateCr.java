package com.hero.herolanding.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.hero.herolanding.domain.ExchangeRate;

@Component
public class ExchangeRateCr {

	// 정보 가져올 url
    private static final String url = "https://www.kita.net/cmmrcInfo/ehgtGnrlzInfo/rltmEhgt.do";
    
    @Transactional
    public List<ExchangeRate> process() {
    	// jsoup  연결
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 나라 정보 가져옴
        return getDataList(document);
 
        
    }

    private List<ExchangeRate> getDataList(Document document) {
    	// Elements로 넘어온 나라별 정보 담아줄 ArrayList
        List<String> country = new ArrayList<String>();
        // country에 나라별로 담긴 정보를  split(" ") 잘라서 만든 String배열을 담아줄 ArrayList
        List<String[]> exchangeRateStirng = new ArrayList<String[]>();
        // exchangeRateStirng에 잘라서 담은 정보를 필요한 형태로 변환해 저장할 ExchangeRate
//        ExchangeRate exchangeRate   = new ExchangeRate();
        // ExchangeRate객체로 저장한 값을 담아줄 ArrayList
        List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>(); 
        
        // url에서 document.select("tbody") 담긴 Elements
        Elements all = document.select("tbody"); 
        // all에 담긴 Elements중 "tr" 태그에 있는 값만 가져옴
        Elements countrys = all.select("tr"); 
        
		for(int i = 0 ; i < 28;i++ ) {
			// 반복문으로 country에 countrys.get(i).text()를 넣어준다.
			country.add(i,countrys.get(i).text());
			// String[]인 exchange를 만들어  countrys.get(i).text()의 값을 (" ") /띄어쓰기/ 로 잘라 하나씩 담아준다.
			// split(" ")은 String배열 형식으로 return 된다.
			// exchange는 한 나라의 값들이 띄워쓰기로 나누어져 들어있다.
			// ex) {INR, 인도, 15.81, ▼, 0.15, -0.94, 17.23, 14.39, 15.96, 15.66, 바로가기}
			String[] exchange = country.get(i).split(" ");
			exchangeRateStirng.add(exchange);
//			System.out.println("크롤링되냐 : " + exchange);

		}

		
		for(int i = 0 ; i < exchangeRateStirng.size();i++) {
			ExchangeRate exchangeRate   = new ExchangeRate();
			exchangeRate.setCurrencyName(exchangeRateStirng.get(i)[0]+" "+exchangeRateStirng.get(i)[1]);
			exchangeRate.setTradingStandardRate(exchangeRateStirng.get(i)[2]);
			exchangeRate.setJoenIlDaeBi(exchangeRateStirng.get(i)[3]+" "+exchangeRateStirng.get(i)[4] );
			exchangeRate.setJoenIlDaeBiRate(exchangeRateStirng.get(i)[5]);
			exchangeRate.setBuyingCash(exchangeRateStirng.get(i)[6]);
			exchangeRate.setSellingCash(exchangeRateStirng.get(i)[7]);
			exchangeRate.setTakeMoney(exchangeRateStirng.get(i)[8]);
			exchangeRate.setGiveMoney(exchangeRateStirng.get(i)[9]);
			// 
			exchangeRates.add(exchangeRate);
//			System.out.println("통화명 " + exchangeRate.getCurrencyName());
//			System.out.println("매매기준율 " + exchangeRate.getTradingStandardRate());
//			System.out.println("전일대비 " + exchangeRate.getJoenIlDaeBi());
//			System.out.println("등락율 " + exchangeRate.getJoenIlDaeBiRate());
//			System.out.println("현찰구입 " + exchangeRate.getBuyingCash());
//			System.out.println("현찰판매 " + exchangeRate.getSellingCash());
//			System.out.println("송금받을때 " + exchangeRate.getTakeMoney());
//			System.out.println("송금보낼때 " + exchangeRate.getGiveMoney());
		}

        return exchangeRates;
    }


}
