package com.clone_coding.danggeon.utils;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetBoards {

    public void crawlingBoards(BoardsRepository boardsRepository) {
        Path path = Paths.get(System.getProperty("user.dir"), ("src/main/resources/chromedriver.exe"));

        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", path.toString());

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        //주의:팝업창 안뜨게 만들기 잊지말기!!! 중요함...


        // WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver(options);

        // 빈 탭 생성
        driver.executeScript("window.open('about:blank','_blank');");

        // 탭 목록 가져오기
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        //첫번째 탭으로 전환
        driver.switchTo().window(tabs.get(0));

        //웹페이지 요청
        driver.get("https://www.daangn.com/hot_articles");

        WebDriverWait wait = new WebDriverWait(driver, 1);


        //각 카드 href로 들어가기
        List<WebElement> webElements = driver.findElementsByXPath("//*[@id=\"content\"]/section[1]/article");
        List<String> detailsUrlList = new ArrayList<>();
        for (int i = 0; i < webElements.size(); i++) {
//            System.out.println(webElements.get(i).getText());
//            String url = webElements.get(i).getAttribute("href");
            String url = webElements.get(i).findElement(By.tagName("a")).getAttribute("href");
            detailsUrlList.add(url);

        }
        System.out.println(detailsUrlList);

        BoardsRequestDto boardsRequestDto = new BoardsRequestDto();

        //각 url에 들어가서 제목, 카테고리, 내용 가지고 오기
        for (int i = 0; i < detailsUrlList.size(); i++) {

            driver.get(detailsUrlList.get(i));
            try{
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"slick-slide00\"]/div/a/div/div/img")));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"article-title\"]")));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"article-detail\"]")));

                //*[@id="slick-slide00"]/div/a/div/div/img

                String title = "";
                String image = "";
                String detail = "";
                WebElement webElement;

                //제목가지고 오기
                webElement = driver.findElementByXPath("//*[@id=\"slick-slide00\"]/div/a/div/div/img");
                image = webElement.getAttribute("src");

                //카테고리 가지고 오기
                webElement = driver.findElementByXPath("//*[@id=\"article-title\"]");
                title = webElement.getText();

                //날짜 가지고 오기
                webElement = driver.findElementByXPath("//*[@id=\"article-detail\"]");
                detail = webElement.getText();

                System.out.println("image = " + image);
                System.out.println("title = " + title);
                System.out.println("detail = " + detail);

                boardsRequestDto.setImgFilePath(image);
                boardsRequestDto.setTitle(title);
                boardsRequestDto.setContents(detail);

                Boards boards = new Boards(boardsRequestDto);
                boardsRepository.save(boards);
            }
            catch (Exception e){
                System.out.println(e);
            }

        }
            driver.close();
            driver.quit();

    };
}

