package expressionsolve.lab;
import java.io.IOException;
public class Main {
    private final static String firstPath = "src/main/resources/first.properties";
    private final static String secondPath = "src/main/resources/second.properties";
    public static void main(String[] args) {
        SomeBean someBean = null;
        try {
            someBean = (new Injector().inject(new SomeBean(), firstPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        someBean.foo();

        try {
            someBean = (new Injector().inject(new SomeBean(), secondPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        someBean.foo();
    }
}
