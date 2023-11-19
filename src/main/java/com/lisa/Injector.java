package com.lisa;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс, ответственный за внедрение зависимостей на основе конфигурации в файле свойств.
 */
public class Injector {
    /**
     * Внедряет зависимости в поля объекта с использованием аннотаций AutoInjectable.
     *
     * @param object Объект, в который будут внедрены зависимости.
     * @param <T>    Тип объекта, для которого выполняется внедрение зависимостей.
     * @return Возвращает тот же самый объект после внедрения зависимостей.
     */
    public <T> T inject(T object) {
        // Получаем класс объекта
        Class<?> clazz = object.getClass();
        // Получаем все поля объекта
        Field[] fields = clazz.getDeclaredFields();

        // Загружаем конфигурацию из файла injector.properties
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("injector.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Итерируем по полям объекта
        for (Field field : fields) {
            // Проверяем наличие аннотации AutoInjectable
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                // Получаем тип поля
                Class<?> fieldType = field.getType();
                // Получаем имя класса для внедрения из файла конфигурации
                String implementationClassName = properties.getProperty(fieldType.getName());

                try {
                    // Загружаем класс для внедрения
                    Class<?> implementationClass = Class.forName(implementationClassName);
                    // Создаем экземпляр класса для внедрения
                    Object implementationInstance = implementationClass.newInstance();

                    // Устанавливаем доступность поля и устанавливаем внедренный экземпляр
                    field.setAccessible(true);
                    field.set(object, implementationInstance);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }
}