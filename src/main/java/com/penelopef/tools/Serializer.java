package com.penelopef.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.penelopef.models.Group;
import com.penelopef.models.Priority;
import com.penelopef.models.Project;
import com.penelopef.models.User;
import com.penelopef.test.TestData;

import java.io.File;
import java.io.IOException;

import static com.penelopef.tools.DateTools.now;

public class Serializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Serializer() {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    public <T> void serialize(T object, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), object);
    }

    public <T> T deserialize(String filePath, Class<T> type) throws IOException {
        return objectMapper.readValue(new File(filePath), type);
    }

    public <T> T deserialize(String filePath, TypeReference<T> typeRef) throws IOException {
        return objectMapper.readValue(new File(filePath), typeRef);
    }
}
