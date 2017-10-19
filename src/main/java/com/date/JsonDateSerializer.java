package com.date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Repository
public class JsonDateSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        String formattedDate = formatter.format(instant);
        jsonGenerator.writeString(formattedDate);
    }
}
