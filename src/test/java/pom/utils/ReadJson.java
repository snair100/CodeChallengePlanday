package pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pom.Objects.Data;

import java.io.IOException;
import java.io.InputStream;

public class ReadJson {
    public static Data deserializeJson(InputStream is, Data data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, data.getClass());
    }
}
