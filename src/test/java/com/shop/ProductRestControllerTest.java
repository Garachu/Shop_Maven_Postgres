package com.shop;

import com.shop.container.MainEntry;
import com.shop.module.product.exception.ProductNotFoundException;
import com.shop.module.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by meg on 7/21/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEntry.class)
@WebAppConfiguration
@Slf4j
public class ProductRestControllerTest {

    private MockMvc mockMvc;

    private int producId = 99999;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private ProductService productServiceMock = org.mockito.Mockito.mock(ProductService.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    //Test read Product Records returns 200Ok
    @Test
    public void readProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.contentType));
    }

    //Test create single Product
    @Test
    public void createProduct() throws Exception {
        String json = String.format("{\"label\":\"%s\", \"description\":\"%s\", \"bp\":\"%s\",\"price\":\"%s\", \"category\":\"%s\"}",
                "test label", "test description", "500", "600", "test");
        log.info("createProduct() json: " + json);

        this.mockMvc.perform(post("/products/add/one")
                .contentType(TestUtil.contentType)
                .content(json))
                .andExpect(status().isCreated());
    }

    //Test: Product Not Found
    @Test
    public void productNotFound() throws Exception {

        //Configure our mock object to throw a AccountNotFoundException when its findAccountByAccountId() method is called.
        when(productServiceMock.findByProductId(999)).thenThrow(new ProductNotFoundException());

        //Execute a GET request to url ‘/accounts/736363353’.
        mockMvc.perform(get("/accounts/" + "736363353"))
                //Verify that the HTTP status code 404 is returned.
                .andExpect(status().isNotFound());

        //Ensure that the findAccountByAccountId() method is called only once by using the correct method parameter (1L).
        // verify(accountServiceMock, times(1)).findAccountByAccountId("736363353");

        //Verify that no other methods of the accountServiceMock interface are called during this test.
        // verifyNoMoreInteractions(accountServiceMock);
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
