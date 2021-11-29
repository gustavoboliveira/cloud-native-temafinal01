package com.gustavoboliveira.cloud_native.temafinal01.configs;

import com.gustavoboliveira.cloud_native.temafinal01.interfaces.Operation;
import com.gustavoboliveira.cloud_native.temafinal01.models.*;
import com.gustavoboliveira.cloud_native.temafinal01.services.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ApplicationConfigs {

    @Bean
    public CalculatorService service(){
        return new CalculatorService();
    }

    @Bean
    public List<String> history(){
        return new ArrayList<>();
    }

    @Bean
    public Map<String, Operation> operations() {
        Map<String, Operation> operationMap = new HashMap<>();
        operationMap.put("somar", new Sum());
        operationMap.put("subtrair", new Subtraction());
        operationMap.put("multiplicar", new Multiplication());
        operationMap.put("dividir", new Division());
        operationMap.put("potencia", new Pow());

        return operationMap;
    }
}
