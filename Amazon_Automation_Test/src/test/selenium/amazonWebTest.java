import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;


public class amazonWebTest {
    static WebDriver driver=null ;

    //public static void main(String[] args) throws InterruptedException {
            @BeforeTest
            public static void openBrowser() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://www.amazon.eg/-/en");
            driver.manage().window().maximize();
        }
        @Test (priority = 1)
        public void leftSideBar() throws InterruptedException {
        // Left Side Bar
        Thread.sleep(5000);
        driver.findElement(By.className("hm-icon-label")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[14]/a[1]/i")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/ul/li[11]/a")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[16]/li[3]/a")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[2]/ul/li/span/a/div[1]/label/i")).click();
        driver.findElement(By.className("a-list-item")).click();

        // Filter
        driver.findElement(By.className("a-dropdown-label")).click();
        driver.findElement(By.id("s-result-sort-select_2")).click();
    }
    @Test (priority = 2)
    public void selectProduct() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.eg/s?i=videogames&bbn=18022560031&rh=n%3A18022560031%2Cp_n_free_shipping_eligible%3A21909080031%2Cp_n_condition-type%3A28071525031&s=price-desc-rank&dc&language=en&qid=1682040512&rnid=28071523031&ref=sr_st_price-desc-rank&ds=v1%3A%2BRMhb6rraWFPT%2BccDZi7cvq0l1YrWvikykzxOS%2FQ0u8");
        driver.manage().window().maximize();

        // Choose products
        WebElement prodSpan = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]"));
        WebElement res = prodSpan.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]"));

        List<WebElement> tes = res.findElements(By.className("a-price-whole"));

        for (int i = 0; i < tes.size(); i++) {
            try {
                WebElement prod = tes.get(i);
                System.out.println("i: " + i + " , text: " + prod.getText());
                double price = Double.parseDouble(prod.getText().replace(",", ""));

                if (price < 15000) {
                    prod.click();
                    driver.findElement(By.id("add-to-cart-button")).click();
                    driver.navigate().back();
                    driver.navigate().back();
                }

            } catch (StaleElementReferenceException e) {
                prodSpan = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]"));
                res = prodSpan.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]"));
                tes = res.findElements(By.className("a-price-whole"));
                WebElement prod = tes.get(i);
                System.out.println("i2: " + i + " , text2: " + prod.getText());
                prod.click();
                driver.findElement(By.id("add-to-cart-button")).click();
                driver.navigate().back();
                driver.navigate().back();
            }
        }
    }
    @Test (priority = 3)
    public void cartPayment() throws InterruptedException {

            // Cart
            Thread.sleep(2000);
            driver.findElement(By.id("nav-cart-count")).click();
            Thread.sleep(4000);

            // Payment
            driver.findElement(By.className("a-button-input")).click();

            // login
            driver.findElement(By.id("ap_email")).sendKeys("abdelrahman.elmahdy94@gmail.com");
            driver.findElement(By.id("continue")).click();
            driver.findElement(By.id("ap_password")).sendKeys("Boda@94#23$Hik");
            driver.findElement(By.id("signInSubmit")).click();

            // Add address
            driver.findElement(By.id("address-ui-widgets-enterAddressFullName")).sendKeys("Abdulrahman Mahdy");
            driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber")).sendKeys("01010272322");
            driver.findElement(By.name("address-ui-widgets-enterAddressLine1")).sendKeys("Talaat Harb St");
            driver.findElement(By.id("address-ui-widgets-enter-building-name-or-number")).sendKeys("Princess Tower");
            driver.findElement(By.id("address-ui-widgets-enterAddressCity")).sendKeys("New Cairo City");
            driver.findElement(By.id("address-ui-widgets-enterAddressDistrictOrCounty")).sendKeys("1 (1st Settlement)");
            driver.findElement(By.id("address-ui-widgets-landmark")).sendKeys("Cairo Festival");

            driver.findElement(By.className("a-button-input")).click();

        }

        @AfterTest
    public void closeBrowser() {
                driver.quit();
    }
    }



