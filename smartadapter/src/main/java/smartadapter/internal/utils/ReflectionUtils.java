package smartadapter.internal.utils;

/*
 * Created by Manne Öhlund on 24/03/17.
 * Copyright © 2017 All rights reserved.
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import smartadapter.internal.exception.ConstructorNotFoundException;

public class ReflectionUtils {

    public static Constructor getConstructor(Class<?> clazz, Class<?>... validConstructorClasses) throws ConstructorNotFoundException {
        if (validConstructorClasses.length == 0) {
            throw new IllegalArgumentException("No validConstructorClasses passed");
        }
        for (Constructor<?> constructor : clazz.getConstructors()) {
            int targetParameterIndex = isNonStaticInnerClass(clazz) ? 1 : 0;
            if (constructor.getParameterTypes().length == 1 + targetParameterIndex) {
                Class<?> constructorParameter = constructor.getParameterTypes()[targetParameterIndex];
                for (Class<?> validConstructorClass : validConstructorClasses) {
                    if (validConstructorClass.isAssignableFrom(constructorParameter)) {
                        return constructor;
                    }
                }
            }
        }

        throw new ConstructorNotFoundException(clazz);
    }

    public static Object invokeConstructor(Constructor constructor, Object... args) throws Exception {
        return constructor.newInstance(args);
    }

    public static boolean isStatic(Class clazz) {
        return Modifier.isStatic(clazz.getModifiers());
    }

    public static boolean isNonStaticInnerClass(Class clazz) {
        return clazz.getDeclaringClass() != null && !isStatic(clazz);
    }
}
