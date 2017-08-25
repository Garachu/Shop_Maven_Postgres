package com.shop;

import com.shop.container.MainEntry;
import com.shop.module.sale.domain.Sale;
import com.shop.module.sale.domain.SaleRequest;


import com.shop.module.sale.service.SaleService;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by meg on 7/22/17.
 */

@RunWith(SpringRunner.class)
//@SpringBootTest tell the SpringJUnit4ClassRunner where it should get information about the Spring application under test
@SpringBootTest(classes = MainEntry.class)
//@WebAppConfiguration tells JUnit that this is a unit test for Spring MVC web components and should thus run under a WebApplicationContext variety, not a standard ApplicationContext implementation.
@WebAppConfiguration //Enables web context testing
public class SaleRestControllerTest {

    //The MockMvc is the center piece: all tests will invariably go through the MockMvc type to mock HTTP requests against the service.
    private MockMvc mockMvc;

    @Autowired //injects WebApplicationContext
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private SaleService saleService;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    //JUnit’s @Before annotation, indicates that it should be executed before any test methods.
    @Before //Sets up MockMvc
    public void setup() throws Exception {
        //instantiate a MockMvc which requires a reference to the application’s WebApplicationContext.
        //webAppContextSetup() takes a WebApplicationContext as an argument.
        //We can use the static imports on org.springframework.test.web.servlet.request.MockMvcRequestBuilders.* to chain together HTTP requests and verify the responses.
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }



    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/sales"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.contentType));
    }

    @Test
    public void saleNotFound() throws Exception {
        mockMvc.perform(get("/sales/" + 9999999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createSaleWithInvalidProduct() throws Exception {

        this.mockMvc.perform(post("/sales")
                .contentType(TestUtil.contentType)
                .content(json(new SaleRequest(900, 2, "2017-07-24", 99999, 9999))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createSaleWithInvalidUser() throws Exception {
        SaleRequest saleInModel = new SaleRequest();
        saleInModel.setSp(900);
        saleInModel.setQuantity(2);
        saleInModel.setDate("2017-07-24");
        saleInModel.setProductId(9);
        saleInModel.setUserId(999);

        this.mockMvc.perform(post("/sales")
                .contentType(TestUtil.contentType)
                .content(json(new SaleRequest(900, 2, "2017-07-24", 9, 999))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createSaleWithValidData() throws Exception {
        SaleRequest saleInModel = new SaleRequest(900, 2, "2017-07-24", 1, 1);
        if(saleInModel == null){
            System.err.println("There is an error");
        }

        this.mockMvc.perform(post("/sales")
                .contentType(TestUtil.contentType)
                .content(json(saleInModel)))
                .andExpect(status().isCreated());
    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Test
    public void testService() {
        Sale sale = saleService.findOne(1);
        assertEquals("P", sale.getProduct().getId());
        assertEquals("Sherman", sale.getSp());
        assertEquals("42 Wallaby Way", sale.getQuantity());
        assertEquals("Sydney", sale.getProfit());
        assertEquals("New South Wales", sale.getTimestamp());
        assertEquals("2000", sale.getUser());
    }



}
