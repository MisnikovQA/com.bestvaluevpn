import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;

    public class MainClass {

        static WebDriver driver;
        static ArrayList<String> tabs;


        public static void main(String[] args) throws InterruptedException {

            System.setProperty("webdriver.chrome.driver","F:\\SeleniumWorkFile\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.get("https://bestvaluevpn.com/comparison-chart/");
            MainPage mainPage = new MainPage(driver,tabs);
            mainPage.setAcceptPrivacyPolicy();
            mainPage.BannerJumpingAfter_10_sec();
            mainPage.clickableBanner();
            mainPage.switchTabs();
            mainPage.setCloseBanner();
            mainPage.checkSizeBox();
            mainPage.checkClickableBlocks();
            mainPage.ScoresAndStarsGoingDown();
            mainPage.clickMakingPBCalls();
            mainPage.switchTabs();
            mainPage.currentSateDate();
            mainPage.quitFromTest();

        }
    }


