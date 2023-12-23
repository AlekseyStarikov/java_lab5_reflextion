package expressionsolve.lab;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
/**
 Класс Injector отвечает за внедрение зависимостей в объект на основе
 *  аннотации AutoInjectable и файла properties.
 * @author Алексей Стариков
 */
public class Injector {
    /**
     * Объект Properties предназначен для хранения пар ключ-значение из файла конфигурации
     */
    private Properties properties;

    /**
     * Конструктор для класса Injector
     */
    public Injector() {}

    /**
     Внедряет зависимости в данный объект, используя файл конфигурации, указанный путем.
     *
     * @param object Объект, в который будут внедрены зависимости.
     * @param path Путь к файлу конфигурации, содержащему сопоставления зависимостей.
     * @param <T> Тип объекта.
     * @return объект с введенными зависимостями.
     * @throws IOException , если при загрузке файла конфигурации возникает ошибка.
     */
    public <T> T inject(T object, String path) throws IOException {
        loadProperties(path);
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String fieldClassName = field.getType().getName();
                String injectedClassName = properties.getProperty(fieldClassName);

                if (injectedClassName != null) {
                    field.setAccessible(true);

                    try {
                        Class<?> injectedClass = Class.forName(injectedClassName);
                        Object classObject = injectedClass.getDeclaredConstructor().newInstance();
                        field.set(object, classObject);
                    } catch (ClassNotFoundException | InstantiationException |
                             IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }

    /**
     * Загружает свойства из указанного файла конфигурации.
     *
     * @param path - путь к файлу конфигурации.
     * @throws IOException, если при загрузке файла конфигурации возникает ошибка.
     */
    private void loadProperties(String path) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IOException("Error loading properties from " + path, e);
        }
    }
}
