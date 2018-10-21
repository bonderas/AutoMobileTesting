import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber() {
        int a = this.getLocalNumber();
        Assert.assertTrue( "Метод НЕ возвращает число 14",a == 14);
    }

    @Test
    public void testGetClassNumber() {
        int a = this.getClassNumber();
        Assert.assertTrue( "Метод НЕ возвращает число больше 45",a > 45);
    }
}
