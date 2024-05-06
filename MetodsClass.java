
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MetodsClass
{
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public static void autorization(String url,String login,String password)  {

        driver.navigate().to(url);
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@class, 'ant-btn')]")).click();
    }

    public void createOrders(String a) throws InterruptedException{

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//li[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'][.='Markets'])[1]")).click();
        driver.findElement(By.xpath("//article[.='" + a + "']")).click();
        driver.findElement(By.xpath("//div[@class='markets__top-btn-block']//span[.='Buy']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(@class, 'markets__modal_btn_buy')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Position'])[4]")).click();
        driver.findElement(By.xpath("//tr[@data-row-key='" + a + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        driver.findElement(By.xpath("//*[.='Markets']//button[@class='ant-tabs-tab-remove']")).click();
        driver.findElement(By.xpath("//*[.='Position']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();

    }

    public void getElementsOrder(String a)  {

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Position'])[1]")).click();
        driver.findElement(By.xpath("//tr[@data-row-key='" + a + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        List<WebElement> rows1 = driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr"));
        int lastIndex = rows1.size() - 1;
        WebElement lastRow = rows1.get(lastIndex);
        String str = lastRow.getText();
        String[] words = str.split(" ");
        String idOrder1 = words[0].substring(0, 4);
        String currency = words[0].substring(4, 15);
        String plOrder = words[8];
        System.out.println("Количество элементов - " + rows1.size());
        System.out.println(" Поле 2");
        System.out.println("Номер ордера - " + idOrder1);
        System.out.println("Символ ордера - " + currency);
        System.out.println("P/L ордера - " + plOrder);
        System.out.println("Остальные поля ордера. Реализация по необходимости. ");
        driver.findElement(By.xpath("//*[.='Position']//button[@class='ant-tabs-tab-remove']")).click();
        for(String d : words){
            System.out.println(d);
            }

        driver.navigate().refresh();
        }

    public void closeAllOrders()  {

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Position'])[1]")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//button[contains(@class, 'ant-dropdown-trigger')]/span[.='Close All']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        driver.findElement(By.xpath("//article[.='Close All']")).click();
        driver.findElement(By.xpath("//*[.='Position']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();

    }

    public void updateOrder() throws InterruptedException {

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[.='Position'])[1]")));
        element1.click();
        driver.findElement(By.xpath("(//*[.='Position'])[1]")).click();
        driver.findElement(By.xpath("(//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed'])[1]")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("(//span[@class='anticon anticon-setting'])[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[.='Update']")).click();
        driver.findElement(By.xpath("//*[.='Position']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();

    }

    public void createPendingOrder(String a) throws InterruptedException {

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//li[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'][.='Markets'])[1]")).click();
        driver.findElement(By.xpath("//article[.='" + a + "']")).click();
        driver.findElement(By.xpath("//div[@class='markets__top-btn-block']//span[.='Buy']")).click();
        driver.findElement(By.xpath("//div[@data-node-key='1'][.='Pending order']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@class, 'markets__modal_btn_buy')]")).click();
        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Pending Orders'])[4]")).click();
        driver.findElement(By.xpath("//tr[@data-row-key='" + a + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        driver.findElement(By.xpath("//*[.='Markets']//button[@class='ant-tabs-tab-remove']")).click();
        driver.findElement(By.xpath("//*[.='Pending Orders']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();
    }

    public void chancheActivationPrice(String a) throws InterruptedException {

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[1]")).click();
        WebElement marketsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "(//li[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'][.='Markets'])[1]")));
        marketsButton.click();
        driver.findElement(By.xpath("//article[.='" + a + "']")).click();
        driver.findElement(By.xpath("//span[.='Buy']")).click();
        driver.findElement(By.xpath("//div[.='Pending order']")).click();

        WebElement inputElement = driver.findElement(By.xpath("(//div[.='Activation price:']//input)[1]"));
        String value = inputElement.getAttribute("value");
        System.out.println("Цена активации - " + value);

        WebElement inputElement2 = driver.findElement(By.xpath("(//input[@class='ant-input-number-input'])[4]"));
        new Actions(driver).click(inputElement2).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        double value1 = Double.parseDouble(value);
        double result = value1 + 100;

        String resultAsString = String.valueOf(result);
        inputElement2.sendKeys(resultAsString);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[contains(@class, 'markets__modal_btn_buy')]")).click();
        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Pending Orders'])[4]")).click();
        driver.findElement(By.xpath("//tr[@data-row-key='" + a + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        List<WebElement> rows1 = driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr"));
        int lastIndex = rows1.size() - 1;
        WebElement lastRow = rows1.get(lastIndex);
        String str = lastRow.getText();
        String[] words = str.split(" ");
        String idOrder = words[0].substring(0, 4);
        String activationPrice = words[7];
        System.out.println("Номер ордера - " + idOrder);


        Assert.assertNotEquals("Значения равны - ",value,activationPrice);
        driver.findElement(By.xpath("//*[.='Markets']//button[@class='ant-tabs-tab-remove']")).click();
        driver.findElement(By.xpath("//*[.='Pending Orders']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();

    }

    public void createorderWithSlTp(String a) throws InterruptedException {

        driver.findElement(By.xpath("(//*[@data-icon='plus'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'][.='Markets'])[1]")).click();
        driver.findElement(By.xpath("//article[.='" + a + "']")).click();
        driver.findElement(By.xpath("//div[@class='markets__top-btn-block']//span[.='Buy']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@class='ant-checkbox-input'])[1]")).click();
        driver.findElement(By.xpath("(//input[@class='ant-checkbox-input'])[2]")).click();
        List<WebElement> numberInputs = driver.findElements(By.xpath("//input[@class='ant-input-number-input']"));
        String value1 = numberInputs.get(2).getAttribute("value");
        WebElement ee = numberInputs.get(2);
        new Actions(driver).click(ee).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        double value2 = Double.parseDouble(value1);
        double result = value2 - 20;
        String resultAsString = String.valueOf(result);
        numberInputs.get(2).sendKeys(resultAsString);
        String value6 = numberInputs.get(3).getAttribute("value");
        WebElement ee1 = numberInputs.get(3);
        new Actions(driver).click(ee1).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        double value3 = Double.parseDouble(value6);
        double result1 = value3 + 20;
        String resultAsString2 = String.valueOf(result1);
        numberInputs.get(3).sendKeys(resultAsString2);

        driver.findElement(By.xpath("//button[contains(@class, 'markets__modal_btn_buy')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Position'])[4]")).click();
        driver.findElement(By.xpath("//tr[@data-row-key='" + a + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        List<WebElement> rows1 = driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr"));
        int lastIndex = rows1.size() - 1;
        WebElement lastRow = rows1.get(lastIndex);
        String str = lastRow.getText();
        String[] words = str.split(" ");
        System.out.println("Сколько всего слов  - " + words.length);
        String plOrder = words[7];
        String plOrder1 = words[8];
        String plOrder2 = words[9];
        String plOrder3 = words[10];
        System.out.println("SL price  - " + plOrder +" "+ plOrder1);
        System.out.println("TP price  - " + plOrder2 + " " + plOrder3 );
        System.out.println("Old TP price  - " + value6 + " " + result1);
        System.out.println("Old SL price  - " + value1 + " " + result);
        driver.findElement(By.xpath("//*[.='Markets']//button[@class='ant-tabs-tab-remove']")).click();
        driver.findElement(By.xpath("//*[.='Position']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();

    }

    public void selectSymbol(String a) throws InterruptedException {
        driver.findElement(By.xpath("(//*[@data-icon='plus'])[1]")).click();
        driver.findElement(By.xpath("(//li[@class='ant-dropdown-menu-item ant-dropdown-menu-item-only-child'][.='Markets'])[1]")).click();
        driver.findElement(By.xpath("//article[.='" + a + "']")).click();
        driver.findElement(By.xpath("//div[@class='markets__top-btn-block']//span[.='Buy']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(@class, 'markets__modal_btn_buy')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[@data-icon='plus'])[2]")).click();
        driver.findElement(By.xpath("(//*[.='Position'])[4]")).click();
        String b;
        if(a.equals("EUR/USD")){
            b = a + ":AFX";
            driver.findElement(By.xpath("//tr[@data-row-key='" + b + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        } else {
            driver.findElement(By.xpath("//tr[@data-row-key='" + a + "']//button[@class='ant-table-row-expand-icon ant-table-row-expand-icon-collapsed']")).click();
        }
        List<WebElement> rows1 = driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr"));
        int lastIndex = rows1.size() - 1;
        WebElement lastRow = rows1.get(lastIndex);
        String str = lastRow.getText();
        String[] words = str.split(" ");
        String currency = words[0].substring(4);
        String currency2 = words[0].substring(5, currency.length() - 7);
        System.out.println("Валюта ордера 2 - " + currency2);
        System.out.println("Валюта ордера 1 - " + a);

        Assert.assertEquals(a,currency2);

        driver.findElement(By.xpath("//*[.='Markets']//button[@class='ant-tabs-tab-remove']")).click();
        driver.findElement(By.xpath("//*[.='Position']//button[@class='ant-tabs-tab-remove']")).click();
        driver.navigate().refresh();

    }

    public static void selectAccount(String a)  {
        driver.findElement(By.xpath("//a[.='" + a + "']")).click();
        String id = driver.findElement(By.xpath("//article[.='" + a + "']")).getText();
        Assert.assertEquals("Аккаунт не соответствует указанному",id,a);
    }

    public void closeAllOrdersManager(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//button[contains(@class, 'ant-dropdown-trigger')]/span[.='Close All']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        driver.findElement(By.xpath("//article[.='Close All']")).click();
        String closeData = driver.findElement(By.xpath("//div[@class='ant-empty-description']")).getText();
        Assert.assertEquals("Ордера не закрыты",closeData,"No data");

    }

    public void createOrderBuyManager(){
        driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Trading']")).click();
        driver.findElement(By.xpath(("(//button[@class='ant-btn css-82v7t6 ant-btn-primary ant-btn-lg'])[2]"))).click();
        driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Account']")).click();

    }

    public void createOrderSellManager(){
        driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Trading']")).click();
        driver.findElement(By.xpath(("(//button[@class='ant-btn css-82v7t6 ant-btn-primary ant-btn-lg'])[1]"))).click();
        driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Account']")).click();

    }

    public void createPandingOrderBuyManager() {
        driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Trading']")).click();
        driver.findElement(By.xpath("//span[@title='Instant execution']")).click();
        driver.findElement(By.xpath("(//div[.='Pending order'])[1]")).click();
        driver.findElement(By.xpath(("(//button[contains(@class, 'ant-btn-primary ant-btn-lg')])[2]"))).click();

        //driver.findElement(By.xpath(("(//button[@class='ant-btn css-82v7t6 ant-btn-primary ant-btn-lg'])[2]"))).click();
        //driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Account']")).click();

    }

    public void createPandingOrderSellManager() {
        driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Trading']")).click();
        driver.findElement(By.xpath("//span[@title='Instant execution']")).click();
        driver.findElement(By.xpath("(//div[.='Pending order'])[1]")).click();
        driver.findElement(By.xpath(("(//button[contains(@class, 'ant-btn-primary ant-btn-lg')])[1]"))).click();

        //driver.findElement(By.xpath(("(//button[@class='ant-btn css-82v7t6 ant-btn-primary ant-btn-lg'])[2]"))).click();
        //driver.findElement(By.xpath("//*[@class='ant-tabs-tab-btn'][.='Account']")).click();

    }

  }



