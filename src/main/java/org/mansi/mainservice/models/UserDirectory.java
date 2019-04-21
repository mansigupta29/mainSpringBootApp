package org.mansi.mainservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDirectory {

@Id
    private String id;

    private String name;
    private String phoneNumber;
    private String emailAddress;

    private List<Contact> userContacts;

    public UserDirectory(){

    }

    public UserDirectory(String name, String phoneNumber, String emailAddress, List<Contact> userContacts) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userContacts = userContacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Contact> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(List<Contact> userContacts) {
        this.userContacts = userContacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public static class Builder{
        private String name;
        private String phoneNumber;
        private String emailAddress;

        private List<Contact> userContacts;


        public Builder name(String name) {
            this.name = name;

            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;

            return this;
        }

        public Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;

            return this;
        }

        public Builder contacts(List<Contact> userContacts) {
            this.userContacts = userContacts;

            return this;
        }

        public UserDirectory build(){
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            UserDirectory userDirectory = new UserDirectory();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
            userDirectory.name = this.name;
            userDirectory.phoneNumber = this.phoneNumber;
            userDirectory.emailAddress = this.emailAddress;
            userDirectory.userContacts = this.userContacts;

            return userDirectory;
        }

    }

}
