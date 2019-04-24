# mainSpringBootApp

Each call requires bearer token authorization
1.	To GET particular user’s contact
	http://localhost:8762/userDirectory/{ userName }
2.	To DELETE a user
http://localhost:8762/userDirectory/{ userName }
3.	To POST a new user 
http://localhost:8762/userDirectory
Body: {
"userName": "name",
    "phoneNumber": "123456789",
    "emailAddress": "email@gmail.com",
    "userContacts": [
        {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        } ]	
}

4.	To POST a new contact in particular user
http://localhost:8762/userDirectory/{ userName }
Body: {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        }
5.	To PUT (update) a user
http://localhost:8762/userDirectory/{ userName }
Body: {
“id”: “objectId”,
"userName": "name",
    "phoneNumber": "123456789",
    "emailAddress": "email@gmail.com"
}

6.	To GET a contact for a user
http://localhost:8762/userDirectory /{ userName }/contacts/{contactName}
7.	To DELETE a contact for a user
http://localhost:8762/userDirectory/{ userName }/contacts/{contactName}
8.	To PUT (update) a contact for a user
http://localhost:8762/userDirectory/{userName}/contacts/{contactName}

Body: {
            "name": "contactname",
            "phoneNumber": "123456789",
            "emailAddress": "email@gmail.com"
        }

