public class test {

    public static void main(String[] args) {
        System.out.println(getValue());
    }

    private static Personal getValue() {
        Personal personal = new Personal();
        try {
            personal.setName("123");
            return personal;
        } finally {
            personal.setName("234");;
            System.out.println(personal);
        }
    }

}

class Personal{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "name='" + name + '\'' +
                '}';
    }
}