import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MainPageTest {


    static BufferedReader br;
    static File junitReport;
    static BufferedWriter junitWriter;
    static PrintWriter pw;
    static File report;
    static ArrayList<String> tabs;
    static Date date;
    static MainPage mainPage;
    static WebDriver driver;


    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                junitWriter.write(description.getDisplayName()+"- Good! \uD83D\uDC4C");
                pw.println(description.getDisplayName()+"- Good! \uD83D\uDC4C");
            } catch (Exception e1) {
                e1.getMessage();
                System.out.println(e1.getMessage());
            }

        }

        @Override
        protected void failed(Throwable e, Description description) {
            try {
                junitWriter.write(description.getDisplayName()+" "+e.getClass().getSimpleName());
                pw.println(description.getDisplayName()+" - "+e.getClass().getSimpleName());
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }

    };


    @BeforeClass
    public static void setUp1() throws IOException {
        String junitReportFile = System.getProperty("user.dir")+"/junitReportFile.txt";
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss ");

        date = new Date();
        junitReport = new File(junitReportFile);
        report = new File("report.txt");
        pw = new PrintWriter("report.txt");
        junitWriter = new BufferedWriter(new FileWriter(junitReport));
        junitWriter.write("       ---===START TEST===---       ");
        pw.println("---===START \uD83E\uDDD0 TEST===---");
        junitWriter.write("Test is (https://bestvaluevpn.com/comparison-chart/) ");
        pw.println("Test is (https://bestvaluevpn.com/comparison-chart/) ");
        junitWriter.write(dateFormat.format(date));
    }

    @AfterClass
    public static void tearDown() {

        pw.println("---===END \uD83E\uDDD0 TEST===--- ");

        pw.close();

        try {
            br = new BufferedReader(new FileReader("report.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                //Start Send to Telegram Bot Group
                System.out.println(line);
                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "1156988319:AAFzV5zNcaMbKHraLpZyiDjayxfWq-yZ_v8";
                String chatId = "-430318664";
                String text = line;
                urlString = String.format(urlString, apiToken, chatId, text);
                try {
                    URL url = new URL(urlString);
                    URLConnection conn = url.openConnection();
                    InputStream is = new BufferedInputStream(conn.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //End Send to Telegram Bot Group
            }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }

    @Test()
    public void Banner_jumping_after_10sec_and_clickable() {
        mainPage.BannerJumpingAfter_10_sec();
        mainPage.clickableBanner();
    }

    @Test
    public void Click_on_the_table_making_PB_calls() throws InterruptedException {
        mainPage.clickMakingPBCalls();
    }

    @Test
    public void Current_date_site() {
        mainPage.currentSateDate();
    }

    @Test
    public void Scores_and_Starts_are_going_down() {
        mainPage.ScoresAndStarsGoingDown();
    }

    @Test
    public void Table_contains_5_lines_and_clickable() {
        mainPage.checkSizeBox();
        mainPage.checkClickableBlocks();
    }

    @After
    public void close() {
        driver.quit();
    }

    @Before
    public void setUp2() {
        System.setProperty("webdriver.chrome.driver", "F:\\SeleniumWorkFile\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://bestvaluevpn.com/comparison-chart/");
        mainPage = new MainPage(driver, tabs);
    }


}
