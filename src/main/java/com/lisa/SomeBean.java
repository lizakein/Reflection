package com.lisa;

/**
 * Класс, использующий внедрение зависимостей для полей field1 и field2.
 */
public class SomeBean {
    /**
     * Внедренная зависимость, реализующая SomeInterface.
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     * Внедренная зависимость, реализующая SomeOtherInterface.
     */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Выполняет некоторые действия, используя внедренные зависимости field1 и field2.
     * Метод вызывает метод doSomething() объекта, реализующего интерфейс SomeInterface,
     * который был внедрен в поле field1. Затем вызывает метод doSomeOther() объекта,
     * реализующего интерфейс SomeOtherInterface, внедренного в поле field2.
     *
     * Примечание: Перед вызовом этого метода убедитесь, что зависимости field1 и field2
     * были успешно внедрены, в противном случае могут возникнуть NullPointerException.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}