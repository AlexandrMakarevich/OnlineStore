package com;

import com.client.Order;
import com.date.JsonDateDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import java.io.IOException;

public class TestTime {

    @Test
    public void testTime() throws JsonProcessingException {
        Order order = new Order();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        System.out.println(objectMapper.writeValueAsString(order));
    }

    @Test
    public void testDeserializationTime() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        String time = "{\"id\":null,\"orderItems\":[],\"status\":null,\"dateOfOrder\":\"2017-10-18T00:25:21.397+03:00[Europe/Simferopol]\"}";
        Order order = objectMapper.readValue(time, Order.class);
        System.out.println(order.getDateOfOrder());
    }

    @Test
    public void testTimeInstant() throws JsonProcessingException {
        Order order = new Order();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        System.out.println(objectMapper.writeValueAsString(order));
    }

    @Test
    public void testDeserializationTimeInstant() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Object.class, new JsonDateDeserializer());
        objectMapper.registerModule(simpleModule);
        String time = "{\"id\":null,\"orderItems\":[],\"status\":null,\"dateOfOrder\":{\"epochSecond\":1508272640,\"nano\":961000000}}";
        Order order = objectMapper.readValue(time, Order.class);
        System.out.println(order.getDateOfOrder());
    }
}
