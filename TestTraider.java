import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestTraider extends Autorizat {

    private static MetodsClass metodsClass;

    @BeforeClass
    public static void setUp() {
        metodsClass = new MetodsClass();
        metodsClass.setUp();
        MetodsClass.autorization(prod2, loginProd2, passwordProd2);
    }

    String var = "THETAUSDT";

    @Test
    public void createOrder() throws InterruptedException {
        metodsClass.createOrders(var);
    }

    @Test
    public void createPendingOrder() throws InterruptedException {
        metodsClass.createPendingOrder(var);
    }

    @Test
    public void createorderWithSlTp() throws InterruptedException {
        metodsClass.createorderWithSlTp(var);
    }

    @Test
    public void getElementsOrder() throws InterruptedException {
        metodsClass.createOrders(var);
        metodsClass.getElementsOrder(var);
    }

    @Test
    public void updateOrder() throws InterruptedException {
        metodsClass.createOrders(var);
        metodsClass.updateOrder();
    }

    @Test
    public void chancheActivationPrice() throws InterruptedException {
        metodsClass.chancheActivationPrice(var);
    }

    @Test
    public void closeAllOrders() throws InterruptedException {
        metodsClass.createOrders(var);
        metodsClass.closeAllOrders();
    }

  /* @Test
    public void selectSymbol1() throws InterruptedException {
        metodsClass.selectSymbol("USDCUSDT");
    }

    @Test
    public void selectSymbol3() throws InterruptedException {
        metodsClass.selectSymbol("EUR/USD");
    }
*/
}



