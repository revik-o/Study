package com.company.CreationalDesignPatterns.Factory;

import java.lang.reflect.InvocationTargetException;

public class Factory {

    public Animal animalFactory(String string) throws ClassNotFoundException {
        if (string.equals("cat")) {
            return new Cat();
        }else if (string.equals("dog")) {
            return new Dog();
        }else {
            throw new ClassNotFoundException(string + " class not found");
        }
    }

    public static <T> T ObjectFactoryCreate(Class<T> type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return type.getDeclaredConstructor().newInstance();
    }

}