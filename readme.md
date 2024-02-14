Social media blog API

## Background 

When building a full-stack application, we're typically concerned with both a front end, that displays information to the user and takes in input, and a backend, that manages persisted information.

This project will be a backend for a hypothetical social media app, where we must manage our users’ accounts as well as any messages that they submit to the application. The application will function as a micro-blogging or messaging app. In our hypothetical application, any user should be able to see all of the messages posted to the site, or they can see the messages posted by a particular user. In either case, we require a backend which is able to deliver the data needed to display this information as well as process actions like logins, registrations, message creations, message updates, and message deletions.

## Database Tables 

These will be provided in a sql script, and a ConnectionUtil class that will run the sql script is provided:

### Account
```
account_id integer primary key auto_increment,
username varchar(255),
password varchar(255)
```

### Message
```
message_id integer primary key auto_increment,
posted_by integer,
message_text varchar(255),    
time_posted_epoch long,
foreign key (posted_by) references Account(account_id)
```

# Requirements

## 1: Our API should be able to process new User registrations.

## 2: Our API should be able to process User logins.

## 3: Our API should be able to process the creation of new messages.

## 4: Our API should be able to retrieve all messages.

## 5: Our API should be able to retrieve a message by its ID.

## 6: Our API should be able to delete a message identified by a message ID.

## 7: Our API should be able to update a message text identified by a message ID.

## 8: Our API should be able to retrieve all messages written by a particular user.


Author: Joseph Kim @justjohykim

