package io.vekzz_dev.task_tracker.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonConfig {

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Register the Java 8 Date/Time module
        mapper.registerModule(new JavaTimeModule());

        // So that LocalDateTime serializes as "yyyy-MM-dd'T'HH:mm:ss"
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}
