package com.lisa;

/**
 * Другая реализация интерфейса SomeInterface, которая выводит "B", когда вызывается doSomething().
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}