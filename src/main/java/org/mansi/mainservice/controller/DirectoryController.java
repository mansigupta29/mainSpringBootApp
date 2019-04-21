package org.mansi.mainservice.controller;

import org.apache.catalina.User;
import org.mansi.mainservice.models.Contact;
import org.mansi.mainservice.models.UserDirectory;
import org.mansi.mainservice.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/userDirectory")
public class DirectoryController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired HttpHeaders httpHeaders;

    @Value("${directory.service.url}")
    String baseUrl;


    @RequestMapping("/{userId}")
    public UserDirectory getContacts(@PathVariable("userId") String userId){

        UserDirectory contactList = restTemplate.getForObject(baseUrl+"/directory/users/"+ userId, UserDirectory.class);
        return contactList;

    }

    @RequestMapping("/{userId}/contacts/{contactName}")
    public List<Contact> getAContact(@PathVariable("userId") String userId, @PathVariable("contactName") String contactName){

        List<Contact> contactList = restTemplate.getForObject(baseUrl+"/directory/users/"+ userId+"/contacts/"+contactName, List.class);
        return contactList;

    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("userId") String userId){

            restTemplate.delete(baseUrl+"/directory/users/"+userId+"/delete");
    }

    @RequestMapping(value = "/{userId}/contacts/{contactName}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("userId") String userId, @PathVariable("contactName") String contactName){

        restTemplate.delete(baseUrl+"/directory/users/"+userId+"/contacts/"+contactName+"/delete");
    }


    @RequestMapping(method = RequestMethod.PUT)
    public void addUser(@RequestBody UserDirectory userDirectory){
        //List<Contact> contacts = Arrays.asList(new Contact("mansi","6475392678","imansigupta29@gmail.com"));
        //UserDirectory userDirectory = new UserDirectory.Builder().name("Riya").phoneNumber("675678987").emailAddress("riya.gupta27@gmail.com").contacts(contacts).build();

        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDirectory> entity = new HttpEntity<>(userDirectory,httpHeaders);
        restTemplate.put(baseUrl+"/directory/users/add",entity);
    }

    @RequestMapping(value = "/{userId}/addContact", method = RequestMethod.PUT)
    public void addContact(@PathVariable("userId") String userId, @RequestBody Contact contact){

        HttpEntity<Contact> entity = new HttpEntity<Contact>(contact,httpHeaders);

        restTemplate.put(baseUrl+"/directory/users/"+userId+"/addContact", entity);
    }

    @RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
    public void updateUser(@PathVariable("userId") String userId, @RequestBody UserDirectory userDirectory){

        HttpEntity<UserDirectory> entity = new HttpEntity<UserDirectory>(userDirectory,httpHeaders);

        restTemplate.postForObject(baseUrl+"/directory/users/"+userId+"/update",entity,Void.class);
    }

    @RequestMapping(value = "/{userId}/contacts/{contactName}/update", method = RequestMethod.POST)
    public void updateContact(@PathVariable("userId") String userId, @PathVariable("contactName") String contactName, @RequestBody Contact contact){


        HttpEntity<Contact> entity = new HttpEntity<Contact>(contact,httpHeaders);

         restTemplate.postForObject(baseUrl+"/directory/users/"+userId+"/contacts/"+contactName+"/update", entity,Void.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDirectory> getAllContacts(){

        List<UserDirectory> userDirectoryList = restTemplate.getForObject(baseUrl+"/directory/users/all", List.class);
        return userDirectoryList;

    }



}
