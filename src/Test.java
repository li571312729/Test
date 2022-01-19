import java.io.PrintStream;
import java.lang.invoke.MethodType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lxq
 * @date 2021年09月01日 17:47
 */
public class Test {

    private int age;

    public static void main(String[] args) throws ParseException {
        System.out.println("{'[depart]':'#{depart}','[arrive]':'#{arrive}','[orderID]':'#{orderID}'}".replace("#{depart}", "仙女座")
                .replace("#{arrive}", "太阳系")
                .replace("#{orderID}", "16895899108"));
    }

    private static void testss(Test tet) {
        tet = new Test(14);
        tet.setAge(11);
    }

    public Test(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "age=" + age +
                '}';
    }
}
