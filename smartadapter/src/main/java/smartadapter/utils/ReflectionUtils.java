package smartadapter.utils;

/*
 * Created by Manne Öhlund on 24/03/17.
 * Copyright © 2017 All rights reserved.
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

    public static Constructor getConstructor(Class<?> clazz, Class<?>... parameterTypes) throws Exception {
        if (isNonStaticInnerClass(clazz)) {
            return clazz.getDeclaredConstructor(parameterTypes);
        } else {
            return clazz.getConstructor(parameterTypes);
        }
    }

    public static Object invokeConstructor(Class<?> clazz, Constructor constructor, Object... args) throws Exception {
        return constructor.newInstance(args);
    }

    public static boolean isStatic(Class clazz) {
        return Modifier.isStatic(clazz.getModifiers());
    }

    public static boolean isNonStaticInnerClass(Class clazz) {
        return clazz.getDeclaringClass() != null && !isStatic(clazz);
    }
}
