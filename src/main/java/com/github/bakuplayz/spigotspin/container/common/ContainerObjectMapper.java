package com.github.bakuplayz.spigotspin.container.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.util.Set;

public final class ContainerObjectMapper {

    private ContainerObjectMapper() {
    }


    @NotNull
    public static ObjectMapper createMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        Reflections reflections = new Reflections();
        Set<Class<?>> subtypes = reflections.getTypesAnnotatedWith(ContainerSubType.class);

        for (Class<?> subType : subtypes) {
            ContainerSubType annotation = subType.getAnnotation(ContainerSubType.class);
            if (annotation != null) {
                String typeName = annotation.value();
                objectMapper.registerSubtypes(new NamedType(subType, typeName));
            }
        }

        return objectMapper;
    }

}
