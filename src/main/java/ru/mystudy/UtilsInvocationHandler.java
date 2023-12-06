package ru.mystudy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UtilsInvocationHandler<T> implements InvocationHandler {
    private final T obj;
    private final Map<String, Object> cacheMap = new HashMap<>();

    public UtilsInvocationHandler(T obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method objMethod = getObjMethod(method);
        String methodName = method.getName();

        if (objMethod.isAnnotationPresent(Cache.class)) {
            System.out.println("Cache proxy invoke " + methodName);
            if (cacheMap.containsKey(methodName)) {
                return cacheMap.get(methodName);
            }

            Object objResult = method.invoke(obj, args);
            cacheMap.put(methodName, objResult);
            return objResult;
        }

        if (objMethod.isAnnotationPresent(Setter.class)) {
            System.out.println("Setter proxy invoke " + methodName);
            cacheMap.clear();
            return method.invoke(obj, args);
        }

        return method.invoke(obj, args);
    }

    private Method getObjMethod(Method method) throws NoSuchMethodException {
        Class<?> objClass = obj.getClass();
        return objClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
    }
}
