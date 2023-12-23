package expressionsolve.lab;
import java.lang.annotation.*;

/**
 *Аннотация для пометки полей для автоматического ввода
 *@author Алексей Стариков
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {
}
