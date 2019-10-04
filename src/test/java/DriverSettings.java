import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class DriverSettings {
    ChromeDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.sberbank.ru/ru/person");
        wait = new WebDriverWait(driver,10);
    }

    @After
    public void close() {
        driver.close();
    }

    WebElement waitForClickable(String xpath) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    WebElement findAndClick(String xpath) {
        WebElement element = waitForClickable(xpath);
        element.click();
        return element;
    }
    void sendElementKeys(String xpath, String text){
        WebElement webElement = findAndClick(xpath);
        webElement.sendKeys(text);
        System.out.println("Введен параметр: " + webElement.getAttribute("value"));

    }
    void sendElementKeysPass(String xpath, String text){
        WebElement webElement =  waitForClickable(xpath);
        webElement.sendKeys(text);
        System.out.println("Введен параметр: " + webElement.getAttribute("value"));

    }
    void findAndPrintText(String xpath){
        WebElement element = waitForClickable(xpath);
        System.out.println("Элемент на странице: " + element.getText());

    }
    void forvardWindowForClick(String xpath){
        Set<String> oldWindowsSet = driver.getWindowHandles();
        findAndClick(xpath);
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        String newWindowHandle = newWindowsSet.iterator().next();
        driver.close();
        driver.switchTo().window(newWindowHandle);
        System.out.println("New window title: " + driver.getTitle());
    }
    void setText(ArrayList<String> names, ArrayList<String> values){//для текста
        if(names.size() <= values.size()){
            for (int i = 0; i < names.size(); i++){
                sendElementKeys(String.format("//input[@name= '%s']", names.get(i)), values.get(i));
            }
        }
    }
    void setTextPassport(ArrayList<String> names, ArrayList<String> values){//для пасспорта без клика
        if(names.size() <= values.size()){
            for (int i = 0; i < names.size(); i++){
                sendElementKeysPass(String.format("//input[@name= '%s']", names.get(i)), values.get(i));
            }
        }
    }

}
