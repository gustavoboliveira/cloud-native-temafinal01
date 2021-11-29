import com.gustavoboliveira.cloud_native.temafinal01.configs.ApplicationConfigs;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.DivisionByZeroException;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.NoHistoryFoundException;
import com.gustavoboliveira.cloud_native.temafinal01.exceptions.OperationNotAvailableException;
import com.gustavoboliveira.cloud_native.temafinal01.services.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceTests {

    private ApplicationContext applicationContext;
    private CalculatorService service;

    @BeforeEach
    void init() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfigs.class);
        service = (CalculatorService) applicationContext.getBean("service");
    }

    @Test
    void shouldReturnResultExpectedWhenSumTest() {
        assertEquals(5.0, service.calcule("somar", 2, 3));
    }

    @Test
    void shouldReturnResultExpectedWhenSubtractionTest(){
        assertEquals(5.0, service.calcule("subtrair", 8, 3));
    }

    @Test
    void shouldReturnResultExpectedWhenMultiplicationTest(){
        assertEquals(6.0, service.calcule("multiplicar", 2, 3));
    }

    @Test
    void shouldReturnResultExpectedWhenDivisionTest(){
        assertEquals(5.0, service.calcule("dividir", 10, 2));
    }

    @Test
    void shouldReturnResultExpectedWhenPowTest(){
        assertEquals(8.0, service.calcule("potencia", 2, 3));
    }

    @Test
    void shouldReturnNotFoundOperationTest() {
        assertThrows(OperationNotAvailableException.class, () -> service.calcule("exponencial", 2, 3));
    }


    @Test
    void shouldReturnCannotDivideByZeroTest(){
        assertThrows(DivisionByZeroException.class, () -> service.calcule("dividir", 2, 0));
    }

    @Test
    void shouldReturnNoOperationWhenNoHistoryTest(){
        assertThrows(NoHistoryFoundException.class, () -> service.getHistory());
    }

    @Test
    void shouldReturnHistoryTest(){
        service.calcule("somar", 2, 3);
        service.calcule("subtrair", 8, 3);
        assertEquals("[2.00 + 3.00 = 5.00, 8.00 - 3.00 = 5.00]", service.getHistory().toString());
    }

}
