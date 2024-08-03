package com.github.bakuplayz.spigotspin.container.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JacksonParser {

    private final static ObjectMapper MAPPER = ContainerObjectMapper.createMapper();

    private final static Logger LOGGER = Logger.getLogger(JacksonParser.class.getName());


    @Nullable
    public static <O> O deserializeFile(@NotNull File file) throws IOException {
        return MAPPER.readValue(file, new TypeReference<O>() {
        });
    }


    public static <O> Optional<O> deserialize(@NotNull String json) {
        try {
            return Optional.of(MAPPER.readValue(json, new TypeReference<O>() {
            }));
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Failed to deserialize json: {}", json);
        }
        return Optional.empty();
    }


    public static <O> void serializeFile(@NotNull String file, @NotNull O data) throws IOException {
        MAPPER.writeValue(new File(file), data);
    }


    public static <O> String serialize(@NotNull O data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to serialize object: {}", data);
        }
        return "{}";
    }

}
