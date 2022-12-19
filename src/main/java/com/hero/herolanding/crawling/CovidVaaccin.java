package com.hero.herolanding.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import com.hero.herolanding.dto.vaccinDTO;

import java.util.ArrayList;
import java.util.List;

// 셀레니움으로 전체 백신정보 크롤링
@Component
public class CovidVaaccin {
	private WebDriver driver;
	// 크롤링 할 주소
	private static final String url = "https://news.google.com/covid19/map?hl=ko&gl=KR&ceid=KR%3Ako";

	public void process() {
		// 크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)
		System.setProperty("webdriver.chrome.driver", "C:\\herolanding\\chromedriver.exe");
		// 크롭창 켜지않고 크롤링 하는 옵션
		 ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--headless");
		 chromeOptions.addArguments("--no-sandbox");
		
		 // 브라우저 선택
		driver = new ChromeDriver(chromeOptions);

		try {
			getDataList();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.close(); // 탭 닫기
		driver.quit(); // 브라우저 닫기
	}

	/**
	 * data가져오기
	 */
	private List<vaccinDTO> getDataList() throws InterruptedException {
		List<String> vaccin = new ArrayList<>();
		List<String[]> vaccins = new ArrayList<String[]>();
		List<vaccinDTO> vaccinDTO = new ArrayList<vaccinDTO>();
		
		driver.get(url); // 브라우저에서 url로 이동한다.
		
		// 백신 탭 xpath경로로 설정
		WebElement tab = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[1]/div[2]"));
		Actions action = new Actions(driver) ;
		// 클릭
		action.click(tab);
		
		// 백신 정보 화면위에 커서 올라가야 전체정보가 나오기 때문에 정보화면 className 설정
		WebElement tab2 = driver.findElement(By.className("pH8O4c"));
		// 마우스 올리는 옵션
		action.moveToElement(tab2).build().perform();
		
		Thread.sleep(1000); // 브라우저 로딩될때까지 잠시 기다린다.

		// 값을 가져올 xpath 경로 
		List<WebElement> elements  = driver.findElements(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz[1]/div/div[2]/div[2]/div[4]/div/div/div[2]/div/div[2]"));
		// 경로에서 WebElement로 가져온 값을 text만 가져옴
		String element = elements.get(0).getText();
		System.out.println(element);
		
		// \은 인식하지 못하기때문에 \\를 사용해야한다. '\\'을 가져오고싶다면 \\\\ 을 써야함
		String[] change_target = element.split("\\n");
		
		// 반복문으로 국가와 국가정보를 하나의 리스트에 담아준다
		for(int i = 7 ; i < change_target.length ; i+=2) {
			vaccin.add(change_target[i].replaceAll(" ", "")+" "+change_target[i+1].replace("데이터 없음", "데이터없음"));
		}
		
		// 반복문으로 String[] change_target에 (" ")로 값을 나눠 담아준다 ( 변수 재사용 )
		for(int i = 0 ; i < vaccin.size();i++) {
			change_target = vaccin.get(i).split(" ");
			
			// (" ")로 나눠준 배열을 String[]타입의 리스트에 담아준다.
			vaccins.add(change_target);
//			change_targe에 값 들어갔는지 확인
//			for(int j = 0 ; j < change_target.length ; j++) {
//				System.out.println(change_target[0] + " :" + change_target[j]); 
//				System.out.println("배열길이  : " + change_target.length );
//			}
		}
		for(int i = 0 ; i < vaccins.size() ; i++) {
			vaccinDTO dto = new vaccinDTO();
			dto.setCountry(vaccins.get(i)[0]);
			dto.setTotalInjectionCount(vaccins.get(i)[1]);
			dto.setNewInjectionCount1(vaccins.get(i)[2]);
			dto.setNewInjectionCount60(vaccins.get(i)[2]);
			dto.setInjectionCompleteCount(vaccins.get(i)[3]);
			dto.setInjectionCompletePercent(vaccins.get(i)[4]);
			vaccinDTO.add(dto);
		}
		return vaccinDTO;
	}
}