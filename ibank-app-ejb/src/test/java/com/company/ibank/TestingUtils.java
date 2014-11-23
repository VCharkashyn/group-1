package com.company.ibank;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class TestingUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestingUtils.class);

    private TestingUtils() {
    }

    public static void setPrivateField(final Object instance, final String fieldName, final Object newValue) throws NoSuchFieldException,
            IllegalAccessException {
        Field privateFieldDefinition = getPrivateFieldDefinition(instance, fieldName);

        if (privateFieldDefinition == null) {
            throw new NoSuchFieldException(fieldName + " not found in any of the declared classes: "
                    + ClassUtils.convertClassesToClassNames(ClassUtils.getAllSuperclasses(instance.getClass())).toString());
        }


        privateFieldDefinition.set(instance, newValue);
    }

    public static boolean doesPrivateFieldExist(final Object instance, final String fieldName) {
        return getPrivateFieldDefinition(instance, fieldName) != null;
    }

    @SuppressWarnings("unchecked")
    public static Field getPrivateFieldDefinition(final Object instance, final String fieldName) {
        Field field = null;
        List<Class<?>> declaredClasses = new ArrayList<Class<?>>();
        declaredClasses.add(instance.getClass());
        declaredClasses.addAll(ClassUtils.getAllSuperclasses(instance.getClass()));

        for (Class<?> c : declaredClasses) {
            try {
                field = c.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }

        return field;
    }
}
