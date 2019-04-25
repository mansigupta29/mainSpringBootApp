package org.mansi.mainservice.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mansi.mainservice.models.Contact;
import org.mansi.mainservice.models.UserDirectory;
import org.mansi.mainservice.service.UserDirectoryService;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserDirectoryController.class, secure = false)
public class UserDirectoryControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDirectoryService userDirectoryService;

    UserDirectory mockUserDirectory = new UserDirectory("mansi", "123456789", "i@gmail.com",
            Arrays.asList(new Contact("joha","123476589","ij@gmail.com")));

     //Contact mockContact = new Contact("tony", "987654321", "io@gmail.com");

     String contactJson = "{\"name\":\"tony\",\"phoneNumber\":\"987654321\",\"emailAddress\":\"io@gmail.com\"}";


    @Test
    public void getUserInfo() throws Exception {

        Mockito.when(
                userDirectoryService.getUserInfo(Mockito.anyString())).thenReturn(mockUserDirectory);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/mansi29").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"userName\":\"mansi\",\"phoneNumber\":\"123456789\",\"emailAddress\":\"i@gmail.com\",\"userContacts\":[{\"name\":\"joha\",\"phoneNumber\":\"123476589\",\"emailAddress\":\"ij@gmail.com\"}]}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void addContact() throws Exception {


        Mockito.when(
                userDirectoryService.addContact(Mockito.anyString(),
                        Mockito.any(Contact.class))).thenReturn(mockUserDirectory);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mansi")
                .accept(MediaType.APPLICATION_JSON).content(contactJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }


}
