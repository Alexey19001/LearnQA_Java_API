import org.junit.BeforeClass;
import org.junit.Test;

public class TestManager extends Autorizat{

    private static MetodsClass metodsClass;

    @BeforeClass
    public static void setUp() {
        metodsClass = new MetodsClass();
        metodsClass.setUp();
        MetodsClass.autorization(devManager, loginDevManager, passwordDevManager);
        MetodsClass.selectAccount(account_id);
    }

    @Test
    public void closeAllOrders() {
        metodsClass.createOrderBuyManager();
        metodsClass.closeAllOrdersManager();
    }

    @Test
    public void createOrderBuy() {
        metodsClass.createOrderBuyManager();
    }

    @Test
    public void createOrderSell() {
        metodsClass.createOrderSellManager();
    }

    @Test
    public void createPandingOrderBuy()  {
        metodsClass.createPandingOrderBuyManager();
    }

    @Test
    public void createPandingOrderSell()  {
        metodsClass.createPandingOrderSellManager();
    }
}
