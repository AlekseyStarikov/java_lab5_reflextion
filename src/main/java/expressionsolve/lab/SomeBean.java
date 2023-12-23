package expressionsolve.lab;
/**
 * @author Алексей Стариков
 */
public class SomeBean {
    /**
     * Конструктор для класса SomeBean
     */
    public SomeBean() {}

    /**
     * Автоматически вводимое поле, представляющее SomeInterface
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     *  Автоматически вводимое поле, представляющее OtherInterface
     */
    @AutoInjectable
    private OtherInterface field2;

    /**
     * Выполняет действие, связанное с введенными полями
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}
