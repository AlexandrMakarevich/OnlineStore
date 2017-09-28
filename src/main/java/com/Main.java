package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    public static void main(String[] args) {
        System.out.println("hello");
        ApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("store_application.xml");
    }
}
