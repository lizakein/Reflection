package com.lisa;

/**
 * Реализация интерфейса SomeOtherInterface, которая выводит "C", когда вызывается doSomeOther().
 */
public class SODoer implements SomeOtherInterface {
    @Override
    public void doSomething() {
        System.out.println("C");
    }
}