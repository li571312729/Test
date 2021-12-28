import java.io.PrintStream;
import java.lang.invoke.MethodType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lxq
 * @date 2021年09月01日 17:47
 */
public class Test {

    private int age;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list = list.stream().filter(o -> o > 2).collect(Collectors.toList());
        Integer first = list.stream().distinct().findFirst().orElse(null);
        System.out.println(first);
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
}
