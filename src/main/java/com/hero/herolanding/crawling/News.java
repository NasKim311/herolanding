package com.hero.herolanding.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.hero.herolanding.dto.NewsDTO;


public class News {
	// 정보 가져올 url
    private static final String url = "https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=";
    
    @Transactional
    public List<NewsDTO>process(String country) {
    	// jsoup  연결
        Connection conn = Jsoup.connect(url + country);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 나라 정보 가져옴
        return getDataList(document);
 
        
    }

    private List<NewsDTO> getDataList(Document document) {
    	// Elements로 넘어온 뉴스를 담아줄 ArrayList
        List<String> country = new ArrayList<String>();
        List<NewsDTO> newsDTO = new ArrayList<NewsDTO>();
        
        Elements all = document.select(".news_tit"); 
//        System.out.println(all.get(1));
        
		for(int i = 0 ; i < all.size();i++ ) {
			NewsDTO dto = new NewsDTO();
			dto.setURL(all.get(i).attr("href"));
			dto.setName(all.get(i).attr("title"));
			
			newsDTO.add(dto);
		}

        return newsDTO;
    }


}



 