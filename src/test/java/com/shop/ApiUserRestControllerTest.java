package com.shop;

import com.shop.module.account.domain.Account;
import com.shop.module.account.domain.AccountResponse;
import com.shop.module.account.exception.AccountNotFoundException;
import com.shop.module.account.service.AccountService;
import com.shop.container.MainEntry;
import com.shop.module.apiuser.domain.ApiUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static com.shop.TestUtil.contentType;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by meg on 8/14/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEntry.class)
@WebAppConfiguration
@Slf4j
public class ApiUserRestControllerTest {

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

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


    //Test: Create ApiUser returns 201

    //Test: findall ApiUser returns 200
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/apiusers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.contentType));
    }

    //Test: ApiUser Found
    @Test
    public void findApiUserByUsername() throws Exception {
        //Execute a GET request to url ‘/accounts’.
        mockMvc.perform(get("/apiusers/find/" + "test"))
                //Verify that the HTTP status code 200 is returned.
                .andExpect(status().isOk())
                .andDo(print())
                //Verify that the content type of the response is ‘application/json’ and its character set is ‘UTF-8’.
                .andExpect(content().contentType(TestUtil.contentType));


    }

    //Test: ApiUser Not Found
    @Test
    public void accountNotFound() throws Exception {
        //Execute a GET request to url ‘/accounts/736363353’.
        mockMvc.perform(get("/apiusers/find/" + "notfound"))
                //Verify that the HTTP status code 404 is returned.
                .andExpect(status().isNotFound());

    }







}
