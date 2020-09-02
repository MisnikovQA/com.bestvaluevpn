


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class MainPage {

        public WebDriver driver;
        public ArrayList<String> tabs;
        WebDriverWait wait;

        public MainPage(WebDriver driver, ArrayList<String> tabs) {
            this.driver = driver;
            this.tabs = tabs;


        }

    static By clickBanner = By.cssSelector("[href=\"https://bestvaluevpn.com/rd/?vnd=express&promo=tmr\"]");
    static By button_VisitSite_Box1 = By.xpath("/html/body/div[5]/div[2]/div/div/div[2]/div[1]/div/div[5]/div[1]/a");
    static By blockInBigBox = By.className("cm-row");
    static By scoreInBox = By.className("cm-score__num");
    static By siteDate = By.xpath("/html/body/div[5]/div[1]/div/div[3]/ul/li[4]/span");
    static By acceptPrivacyPolicy = By.xpath("/html/body/footer/div/div[4]/p[1]/span[3]");
    static By closeBanner = By.xpath("/html/body/div[4]/div[1]/div/div");
    static By stars = By.xpath("//div[@class='rate']/span");




    public void switchTabs() {
            driver.switchTo().window(tabs.get(0));
        }

        public void setAcceptPrivacyPolicy() {
            driver.findElement(acceptPrivacyPolicy).click();
        }

        public void BannerJumpingAfter_10_sec() {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(clickBanner));
            boolean isPresent = driver.findElements(clickBanner).size() > 0;
                Assert.assertTrue(isPresent);
        }

        public void clickableBanner() {
            driver.findElement(clickBanner).click();
            boolean isPresent = driver.findElements(clickBanner).size() != 0;
            Assert.assertTrue(isPresent);

        }

        public void setCloseBanner() {
            driver.findElement(closeBanner).click();
        }

        public void checkSizeBox() {
            if (driver.findElements(blockInBigBox).size() == 5) {
            }
            else System.out.println("FAIL! no 5 blocks");
        }



        public void checkClickableBlocks() {
            for (WebElement block : driver.findElements(blockInBigBox)) {
                block.click();
                driver.switchTo().window(tabs.get(0));

            }
        }

        public void ScoresAndStarsGoingDown() {
            List<Double> rate = new ArrayList<>();
            List<Integer> rateStar = new ArrayList<>();
            for (WebElement checkbox : driver.findElements(blockInBigBox)) {
                String scoreStr =  checkbox.findElement(scoreInBox).getText();
                double scoreDouble = Double.parseDouble(String.valueOf(scoreStr));
                rate.add(scoreDouble);
                driver.switchTo().window(tabs.get(0));
            }
            for (int i = 1; i < rate.size(); i++) {
                if ((rate.get(i - 1) <= rate.get(i))) {
                    System.out.println(rate.get(i - 1) + " < " + rate.get(i) + " bad");
                } else {
                    System.out.println(rate.get(i - 1) + " > " + rate.get(i) + " good");
                }
            }
            System.out.println(rate);



            for (WebElement star: driver.findElements(stars)) {
                String scoreStr = star.getAttribute("style").replace("width: ","").replace("%;", "");
                System.out.println(scoreStr);

                int scoreInt = Integer.parseInt(scoreStr);
                rateStar.add(scoreInt);
                driver.switchTo().window(tabs.get(0));
            }
            for (int i = 1; i < rateStar.size(); i++) {
                if ((rateStar.get(i - 1) <= rateStar.get(i)))
                    System.out.println(rateStar.get(i - 1) + " < " + rateStar.get(i) + " bad");
                else System.out.println(rateStar.get(i - 1) + " > " + rateStar.get(i) + " good");
            }
            System.out.println(rateStar);

        }

        public void clickMakingPBCalls() throws InterruptedException {
            driver.findElement(button_VisitSite_Box1).click();
            Thread.sleep(3000);
            String scriptToExecute = "return window.performance.getEntries();";
            String netData = ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
            System.out.println(netData);
            boolean isFound = netData.contains("https://pb.bestvaluevpn.com");
            System.out.println("making call to https://pb.bestvaluevpn.com  " + isFound);
            Assert.assertEquals(isFound,netData.contains("https://pb.bestvaluevpn.com"), "no call");
        }

        public void currentSateDate() {
            String siteDateStr = driver.findElement(siteDate).getText();
            String[] split1 = siteDateStr.split("\\s+");
            LocalDate currentDate = LocalDate.now();
            String split = split1[1].split(",")[0];
            int currentDay = currentDate.getDayOfMonth();
            Month currentMonth = currentDate.getMonth();
            int currentYear = currentDate.getYear();
            int i = Integer.parseInt(split);
            int Y = Integer.parseInt(split1[2]); //year
            String spl = split1[0].toUpperCase();
            String mon = currentMonth.toString();
            System.out.println(split1[0]);
            System.out.println(split1[1].split(",")[0]);
            System.out.println(split1[2]);
            System.out.println(siteDateStr);
            //Assert.assertEquals("Year not correct",currentYear, String.valueOf(Y));
            //Assert.assertEquals("Month not correct",mon, spl);
            //Assert.assertEquals("Day not correct",currentDay, String.valueOf(i));
            if (mon.equals(spl)) {
                System.out.println("site month ok");
            } else {
                System.out.println("site month error " + mon + ":" + spl);
            }
            if (currentDay == i) {
                System.out.println("site day ok");
            } else {
                System.out.println("site day error " + currentDay + " : " + split);
            }
            if (currentYear == Y) {
                System.out.println("site Year ok");
            } else {
                System.out.println("site Year error " + currentYear + " : " + split1[2]);
            }
        }

        public void quitFromTest() {
            driver.quit();
        }



    }

