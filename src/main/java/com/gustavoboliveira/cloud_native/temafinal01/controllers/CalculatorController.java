package com.gustavoboliveira.cloud_native.temafinal01.controllers;

import com.gustavoboliveira.cloud_native.temafinal01.configs.ApplicationConfigs;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.DivisionByZeroException;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.NoHistoryFoundException;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.OperationNotAvailableException;
import com.gustavoboliveira.cloud_native.temafinal01.services.CalculatorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/calcule")
public class CalculatorController extends HttpServlet {

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfigs.class);
    private final CalculatorService service = (CalculatorService) applicationContext.getBean("service");

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        try {
            String op = request.getParameter("operation");
            if(op.equalsIgnoreCase("history")){
                List<String> history = service.getHistory();
                for(String h : history){
                    pw.println(h);
                }
            } else {
                double num1 = Double.parseDouble(request.getParameter("num1"));
                double num2 = Double.parseDouble(request.getParameter("num2"));
                pw.println((String.format("%.2f", service.calcule(op, num1, num2))));
            }
        } catch (NullPointerException e){
            pw.println("Parametro nao preenchido");
        } catch (IllegalArgumentException e1){
            pw.println("Parametro fornecido de formato incorreto");
        } catch (OperationNotAvailableException e2){
            pw.println("Operacao solicitada nao disponivel");
        } catch (DivisionByZeroException e3){
            pw.println("Nao eh possivel fazer divisao por 0");
        } catch (NoHistoryFoundException e4){
            pw.println("No history recorded yet");
        }
        pw.close();
    }
}
