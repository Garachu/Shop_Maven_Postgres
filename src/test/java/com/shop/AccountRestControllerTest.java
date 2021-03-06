package com.shop;

import com.shop.module.account.domain.AccountResponse;
import com.shop.module.account.exception.AccountNotFoundException;
import com.shop.module.account.service.AccountService;
import com.shop.container.MainEntry;
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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
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
public class AccountRestControllerTest {

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private AccountService accountServiceMock = org.mockito.Mockito.mock(AccountService.class);

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

    //Test: Account Not Found


    //Test: Account Found
    @Test
    public void accountFound() throws Exception {
        //Execute a GET request to url ‘/accounts’.
        mockMvc.perform(get("/accounts/" + "29380231"))
                //Verify that the HTTP status code 200 is returned.
                .andExpect(status().isOk())
                .andDo(print())
                //Verify that the content type of the response is ‘application/json’ and its character set is ‘UTF-8’.
                .andExpect(content().contentType(TestUtil.contentType));

        //.json("{\"accountId\": \"11160011\",\"accountName\": \"Richard Muinami\", \"accountContact\": \"0700242450\",\"accountType\": \"Creditor\",\"accountNarration\": \"Supplier\",\"firstModifiedDate\": \"0015-02-07\",\"lastModifiedDate\": \"0015-02-07\",\"initialBalance\": 104560,\"balance\": 104560}"));

    }


    @Test
    public void validRequestReturns201Created() throws Exception {
        String json = String.format("{\"accountId\":\"%s\", \"accountName\":\"%s\", \"accountContact\":\"%s\",\"accountType\":\"%s\", \"accountNarration\":\"%s\", \"date\": \"%s\", \"balance\": \"%s\"}",
                TestUtil.generateRandomStr(), "Richard Muinami", "0700242450", "Creditor", "Supplier", "2017-05-08", "104560");
        log.error(json);
        mockMvc.perform(post("/accounts/add/one")
                .contentType(TestUtil.contentType)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    //Test: Accounts are found
    @Test
    public void accountsAreFound() throws Exception {
        //Create the test data which is returned when the findLastWithLimit(20) method is called. We create the test data by using a test data builder class.
        List<AccountResponse> accounts = accountServiceMock.findLastWithLimit(20);
        log.info("Test DAta: ===========" + Arrays.toString(accounts.toArray()));

        //Configure our mock object to return the created test data when its findLastWithLimit(20) method is invoked.
        when(accountServiceMock.findLastWithLimit(20)).thenReturn(accounts);

        //Execute a GET request to url ‘/api/todo’.
        mockMvc.perform(get("/accounts"))
                .andDo(print())

                //Verify that the HTTP status code 200 is returned.
                .andExpect(status().isOk())

                //Verify that the content type of the response is ‘application/json’ and its character set is ‘UTF-8’.
                .andExpect(content().contentType(TestUtil.contentType));

        //Verify that the findLastWithLimit(20) method of the TodoService interface is called only once.
        verify(accountServiceMock, times(1)).findLastWithLimit(20);

        //Ensure that no other methods of our mock object are called during the test
        //verifyNoMoreInteractions(accountServiceMock);

    }

}
