package com.gustavoboliveira.cloud_native.temafinal01.services;

import com.gustavoboliveira.cloud_native.temafinal01.exceptions.DivisionByZeroException;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.NoHistoryFoundException;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.OperationNotAvailableException;
import com.gustavoboliveira.cloud_native.temafinal01.interfaces.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CalculatorService {

    @Autowired
    private Map<String, Operation> operationMap;
    @Autowired
    private List<String> history;

    private boolean verifyOperation(String operation, double y){
        if(operation.equalsIgnoreCase("dividir") && y == 0)
            throw new DivisionByZeroException();
        return true;
    }

    private void addHistory(String operation, double x, double y, double result){
        char op = ' ';
        switch (operation.toLowerCase()){
            case "somar":
                op = '+';
                break;
            case "subtrair":
                op = '-';
                break;
            case "multiplicar":
                op = '*';
                break;
            case "dividir":
                op = '/';
                break;
            case "potencia":
                op = '^';
                break;
        }
        history.add(String.format("%.2f %c %.2f = %.2f", x, op, y, result));
    }

    public double calcule(String operation, double x, double y){
        if(operationMap.containsKey(operation) && verifyOperation(operation, y)){
            double result = operationMap.get(operation).execute(x, y);
            addHistory(operation, x, y, result);
            return result;
        } else {
            throw new OperationNotAvailableException();
        }
    }

    public List<String> getHistory(){
        if(history.isEmpty())
            throw new NoHistoryFoundException();
        return history;
    }
}
