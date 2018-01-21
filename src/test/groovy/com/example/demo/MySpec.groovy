package com.example.demo

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = DemoApplication.class)
public abstract class MySpec extends Specification {
}
