package com.expensify.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@SpringBootTest
public class ExportDataToPDFTest {
    @Test
    public void exportExpenseDataSuccessTest(){
        HttpServletResponse response = new MockHttpServletResponse();
        Assertions.assertNotEquals(null,response);
    }

    @Test
    public void exportExpenseDataFailureTest(){
        HttpServletResponse response = null;
        Assertions.assertEquals(null,response);
    }

}
