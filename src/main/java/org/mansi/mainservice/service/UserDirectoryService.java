package org.mansi.mainservice.service;

import org.mansi.mainservice.models.Contact;
import org.mansi.mainservice.models.UserDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserDirectoryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpHeaders httpHeaders;

    @Value("${directory.service.url}")
    String baseUrl;


    public UserDirectory getUserInfo(String userName){

        UserDirectory userDirectory = restTemplate.getForObject(baseUrl+"/directory/users/"+ userName, UserDirectory.class);
        return userDirectory;

    }


    public List<Contact> getContactInfo(String userName, String contactName){

        List<Contact> contactList = restTemplate.getForObject(baseUrl+"/directory/users/"+ userName+"/contacts/"+contactName, List.class);
        return contactList;

    }


    public void deleteUser(String userName){

        restTemplate.delete(baseUrl+"/directory/users/"+userName);
    }


    public void deleteContact(String userName, String contactName){

        restTemplate.delete(baseUrl+"/directory/users/"+userName+"/contacts/"+contactName);
    }


    public String addUser(UserDirectory userDirectory){

        HttpEntity<UserDirectory> entity = new HttpEntity<>(userDirectory,httpHeaders);
        return restTemplate.postForObject(baseUrl+"/directory/users",entity, String.class);
    }


    public UserDirectory addContact(String userName, Contact contact){

        HttpEntity<Contact> entity = new HttpEntity<Contact>(contact,httpHeaders);

        return restTemplate.postForObject(baseUrl+"/directory/users/"+userName, entity, UserDirectory.class);
    }


    public void updateUser(String userName, UserDirectory userDirectory){
        HttpEntity<UserDirectory> entity = new HttpEntity<UserDirectory>(userDirectory,httpHeaders);
        restTemplate.put(baseUrl+"/directory/users/"+userName,entity,Void.class);
    }


    public void updateContact(String userName, String contactName, Contact contact){

        HttpEntity<Contact> entity = new HttpEntity<Contact>(contact,httpHeaders);
        restTemplate.put(baseUrl+"/directory/users/"+userName+"/contacts/"+contactName,entity,Void.class);
    }



    public List<UserDirectory> getAllContacts(){

        List<UserDirectory> userDirectoryList = restTemplate.getForObject(baseUrl+"/directory/users", List.class);
        return userDirectoryList;

    }

}
