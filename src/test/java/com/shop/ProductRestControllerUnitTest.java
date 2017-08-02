package com.shop;

import com.shop.product.ProductController;
import com.shop.product.ProductService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by meg on 7/31/17.
 */

/*
    *When we are unit testing a rest service, we would want to launch only the specific controller and the related MVC Components
    *WebMvcTest annotation is used for unit testing Spring MVC application.
    *This can be used when a test focuses only Spring MVC components.
    *Using this annotation will disable full auto-configuration and only apply configuration relevant to MVC tests.
    *In this test, we want to launch only StudentController.
    * All other controllers and mappings will not be launched when this unit test is executed.
*/

//SpringRunner is short hand for SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductRestControllerUnitTest {

    /*
        *MockMvc is the main entry point for server-side Spring MVC test support.
        *It allows us to execute requests against the test context.
     */
    @Autowired
    private MockMvc mockMvc;


    /*
        *MockBean is used to add mocks to a Spring ApplicationContext.
        *A mock of ProductService is created and auto-wired into the ProductController.
    */
    @MockBean
    private ProductService productService;

}
