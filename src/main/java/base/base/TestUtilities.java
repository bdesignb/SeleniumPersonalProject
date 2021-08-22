package base.base;

public class TestUtilities extends BaseTest{

    protected void sleep(long millis) {
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
