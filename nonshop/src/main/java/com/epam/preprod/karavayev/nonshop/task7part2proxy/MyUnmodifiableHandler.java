package com.epam.preprod.karavayev.nonshop.task7part2proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyUnmodifiableHandler implements InvocationHandler {

    private Object object;

    public MyUnmodifiableHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            throw new UnsupportedOperationException();
        }
        return method.invoke(object, args);
    }
}
