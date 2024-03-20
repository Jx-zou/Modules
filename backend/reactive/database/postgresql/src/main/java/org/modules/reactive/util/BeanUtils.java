package org.modules.reactive.util;

import java.lang.reflect.Field;

/**
 * The type BeanUtils.
 *
 * @author Jx-zou
 */
public class BeanUtils {

    public static <T> T merge(T sourceBean, T targetBean) {

        Class<?> sourceBeanClass = sourceBean.getClass();
        Class<?> targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();

        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                Object value = sourceField.get(sourceBean);
                if (value != null) {
                    targetField.set(targetBean, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return targetBean;
    }
}
