package com.hero.herolanding.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
// 전체 코로나정보 크롤링

@Component
public class Covid {
	private WebDriver driver;
	
//	private Action action;
	private Actions actions;

	private static final String url = "https://news.google.com/covid19/map?hl=ko&gl=KR&ceid=KR%3Ako";

	private static final CharSequence Enter = null;

	public void process(String country) {
		// 크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)
		System.setProperty("webdriver.chrome.driver", "C:\\herolanding\\chromedriver.exe");
		// 브라우저 켜지않고 크롤링하는 옵션
		 ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--headless");
		 chromeOptions.addArguments("--no-sandbox");
		
		driver = new ChromeDriver();
		
		// 브라우저 선택

		try {
			getDataList(country);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.close(); // 탭 닫기
		driver.quit(); // 브라우저 닫기
	}

	/**
	 * data가져오기
	 */
	private List<String> getDataList(String country) throws InterruptedException {
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		 

		driver.get(url); // 브라우저에서 url로 이동한다.
		
		
		
		
//		코로나정보		
		WebElement tab = driver.findElement(By.className("pH8O4c"));
		Actions action = new Actions(driver) ;

		action.moveToElement(tab).build().perform();
		
		List<WebElement> elements  = driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[2]"));
//		((JavascriptExecutor)driver).executeScript("window.scrollTo(0)", tab);
		// 백신탭
//		WebElement tab = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[1]/div[2]"));
//		tab.click();
		
//		List<WebElement> elementsVac  = driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[2]"));
		
		Thread.sleep(3000); // 브라우저 로딩될때까지 잠시 기다린다.

//		int eleint = elements.size();
//		String ele = elements.get(0).getText();
//		String eleVac = elementsVac.get(0).getText();
//		System.out.print("전세계 코로나 정보" + ele + "끝");
//		System.out.print("전세계 코로나 정보" + ele + "끝");
		
		for (int i = 0; i < elements.size(); i++) {
			String element = elements.get(i).getText();
			list.add(element);
//			String element2 = elementsVac.get(i).getText();
//			list.add(element);
		}
		for(int i = 0 ; i < list.size() ; i ++) {
			System.out.println("제발 : " + list.get(i) );
//			System.out.println("제발 : " + list2.get(i) );
		}

		return list;
	}
}