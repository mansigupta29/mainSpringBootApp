package org.mansi.mainservice.controller;


import org.mansi.mainservice.models.Contact;
import org.mansi.mainservice.models.UserDirectory;
import org.mansi.mainservice.service.UserDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/")
public class UserDirectoryController {


    @Autowired
    UserDirectoryService directoryService;

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody UserDirectory userDirectory){

         directoryService.addUser(userDirectory);
    }

    @RequestMapping("/{userName}")
    public UserDirectory getUserInfo(@PathVariable("userName") String userName){

       return directoryService.getUserInfo(userName);

    }

    @RequestMapping("/{userName}/contacts/{contactName}")
    public List<Contact> getContactInfo(@PathVariable("userName") String userName, @PathVariable("contactName") String contactName){

       return directoryService.getContactInfo(userName,contactName);

    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("userName") String userName){

           directoryService.deleteUser(userName);
    }

    @RequestMapping(value = "/{userName}/contacts/{contactName}", method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable("userName") String userName, @PathVariable("contactName") String contactName){

       directoryService.deleteContact(userName,contactName);
    }



    @RequestMapping(value = "/{userName}", method = RequestMethod.POST)
    public void addContact(@PathVariable("userName") String userName, @RequestBody Contact contact){

       directoryService.addContact(userName,contact);
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("userName") String userName, @RequestBody UserDirectory userDirectory){

        directoryService.updateUser(userName,userDirectory);
    }

    @RequestMapping(value = "/{userName}/contacts/{contactName}", method = RequestMethod.PUT)
    public void updateContact(@PathVariable("userName") String userName, @PathVariable("contactName") String contactName, @RequestBody Contact contact){


       directoryService.updateContact(userName,contactName,contact);
    }

}
