package com.gustavoboliveira.cloud_native.temafinal01;

import com.gustavoboliveira.cloud_native.temafinal01.configs.ApplicationConfigs;
import com.gustavoboliveira.cloud_native.temafinal01.services.CalculatorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfigs.class);
        CalculatorService service = (CalculatorService) applicationContext.getBean("service");
    }
}
