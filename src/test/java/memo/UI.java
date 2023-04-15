package memo;

import com.library.pages.DataPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class UI {
    WebDriver wd;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        wd = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        wd.get(DataPage.urlWeb);
        wd.findElement(By.cssSelector("input#inputEmail")).sendKeys(DataPage.librarin);
        wd.findElement(By.cssSelector("input#inputPassword")).sendKeys(DataPage.pass);
        wd.findElement(By.tagName("button")).click();
    }

    String email;

    @DisplayName("creating user")
    @Test
    void create_user() throws InterruptedException {
        email="abc1@abc.com";
        Thread.sleep(3000);
        wd.findElement(By.xpath("(//ul/li//a)[2]")).click();
        Thread.sleep(1500);
        wd.findElement(By.cssSelector("a.btn.btn-lg.btn-outline.btn-primary.btn-sm")).click();
        Thread.sleep(1500);
        wd.findElement(By.xpath("//input[@name='password']")).sendKeys("pass123");
        wd.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        wd.findElement(By.xpath("//input[@name='full_name']")).sendKeys("hattori hanzo");
    }

}
