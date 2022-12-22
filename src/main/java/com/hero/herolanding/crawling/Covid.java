package com.hero.herolanding.crawling;

import org.hibernate.loader.plan.build.internal.returns.AbstractCompositeEntityIdentifierDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import com.hero.herolanding.dto.CovidDTO;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
// 전체 코로나정보 크롤링

@Component
public class Covid {
	private WebDriver driver;

	private static final String url = "https://news.google.com/covid19/map?hl=ko&gl=KR&ceid=KR%3Ako";

	public List<CovidDTO> process() {
		// 크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)
		System.setProperty("webdriver.chrome.driver", "C:\\herolanding\\chromedriver.exe");
		// 브라우저 켜지않고 크롤링하는 옵션
		 ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--headless");
		 chromeOptions.addArguments("--no-sandbox");
		
		driver = new ChromeDriver(chromeOptions);
		
		// 브라우저 선택

		try {
			return getDataList();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.close(); // 탭 닫기
		driver.quit(); // 브라우저 닫기
		
		return null;
	}

	/**
	 * data가져오기
	 */
	private List<CovidDTO> getDataList() throws InterruptedException {
		List<String> covid = new ArrayList<String>();
		List<String[]> covids = new ArrayList<String[]>();
		List<CovidDTO> covidDTO = new ArrayList<CovidDTO>();
		 

		driver.get(url); // 브라우저에서 url로 이동한다.
		
		// 백신 탭 xpath경로로 설정
		WebElement tab1 = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[1]/div[1]"));
		//*[@id="yDmH0d"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[1]/div[1]
		Actions action1 = new Actions(driver) ;
		// 클릭
		action1.click(tab1);
		
//		코로나 정보		
		WebElement tab2 = driver.findElement(By.className("pH8O4c"));
//		마우스를 해당 정보 위에 올려 활성화 시켜 모든 정보가 나오게한다.
		action1.moveToElement(tab2).build().perform();
		// 값을 가져올 xpath경로를 입력 후 값을 가지고온다. 
		Thread.sleep(3000); // 브라우저 로딩될때까지 잠시 기다린다.
		List<WebElement> elements  = driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[2]"));
		String element = elements.get(0).getText();
		String[] change_target = element.split("\\n");
		
//		for(int i = 1 ; i < change_target.length;i++) {
//			System.out.println(change_target[i]);
//		}
//		System.out.println(element);
		
		// 반복문으로 국가와 국가정보를 하나의 리스트에 담아준다
		for(int i = 7 ; i < change_target.length ; i+=2) {
			covid.add(change_target[i].replaceAll(" ", "")+" "+change_target[i+1].replace("데이터 없음", "데이터없음"));
		}
		for(int i = 0 ; i < covid.size();i++) {
			System.out.println("배열안에 있는 값 : " + covid.get(i));
		}
		// 반복문으로 String[] change_target에 (" ")로 값을 나눠 담아준다 ( 변수 재사용 )
		for(int i = 0 ; i < covid.size();i++) {
			change_target = covid.get(i).split(" ");
			// (" ")로 나눠준 배열을 String[]타입의 리스트에 담아준다.\
//			System.out.println("배열길이들 : " + change_target.length);
			covids.add(change_target);
//			change_targe에 값 들어갔는지 확인
//			for(int j = 0 ; j < change_target.length ; j++) {
//				System.out.println(change_target[0] + " :" + change_target[j]); 
//				System.out.println("배열길이  : " + change_target.length );
//			}
		}
		for(int i = 0 ; i < covids.size() ; i++) {
			CovidDTO dto = new CovidDTO();
			dto.setCountry(covids.get(i)[0]);
			dto.setTotalCovidCount(covids.get(i)[1]);
			dto.setNewCovidCount1(covids.get(i)[2]);
			dto.setMilionCount(covids.get(i)[3]);
			dto.setSamang(covids.get(i)[4]);
			covidDTO.add(dto);
		}

		return covidDTO;
	}
}