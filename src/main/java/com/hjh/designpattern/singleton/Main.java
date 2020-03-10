package com.hjh.designpattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: hjh
 * @description:
 */
public class Main {
  public static <T> T newInStance(Class<T> clazz) {
    Constructor[] constructor = clazz.getDeclaredConstructors();
    for (Constructor cons : constructor) {
      if (cons.getParameterCount() != 0) {
        continue;
      }
      // 私有的转公有
      cons.setAccessible(true);
      try {
        T obj = (T) cons.newInstance();
        return obj;
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
