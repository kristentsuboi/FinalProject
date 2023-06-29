# Pawrents Place Application

### Final Team Project for Skill Distillery

### Team Members
- Braylin Pichardo (Developer, Database Administrator
- Kristen Tsuboi (Developer, RepoOwner)
- Kris Vesely (Developer)
- Tia Wright (Developer, Scrum Master)

## Overview
The purpose of the application is to demonstrate our ability to create a project that utilizes a SQL database, Java REST backend, and a front end built with Angular. This project includes non-authenticated and authenticated views of data, login authentication to access C.R.U.D. functionality, and a seperate privilege level that can perform C.R.U.D. on data a non-privileged user cannot.

## Description
Pawrents Place is an application for pet owners. This application shows their pets basic information, medial information, shot records, and discussion boards for communication amongs that owner and the PAWviders (providers) they choose such as vets, boarders, groomers, etc. The information is available to the PAWvider as well. No more asking for shot records and feeding information because Pawrents Place provides that data.

### Link to Deployed Application
- <a href="http://3.131.187.115:8080/PawrentsPlace/#/home">Pawrents Place Application </a>

### How to Login
To view functionality as a pet owner, you can create your own profile and upload your pets, or use the below account for demo
- username: owner
- password: wombat1

To view functionality as a PAWvider, use the below account.
- username: vet
- password: wombat1

## Expected Routes
<table>
    <tr>
        <td>Return Type</td>
        <td>Route</td>
        <td>Functionality</td>
    </tr>
    <tr>
        <td>User</td>
        <td>GET api/account/{userId}</td>
        <td>Gets one user by id</td>
    </tr>
    <tr>
        <td>User</td>
        <td>POST api/register</td>
        <td>Creates a new user</td>
    </tr>
    <tr>
        <td>User</td>
        <td>PUT api/account/{userId}</td>
        <td>Replaces an existing user by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/account/{userId}</td>
        <td>Deletes an existing user by id</td>
    </tr>
    <tr>
        <td>List&lt;User&gt;</td>
        <td>GET api/clients</td>
        <td>Gets all clients by business</td>
    </tr>
    <tr>
        <td>Pet</td>
        <td>GET api/pets/{petId}</td>
        <td>Gets one pet by id</td>
    </tr>
    <tr>
        <td>Pet</td>
        <td>POST api/pets</td>
        <td>Creates a new pet</td>
    </tr>
    <tr>
        <td>Pet</td>
        <td>PUT api/pets/{petId}</td>
        <td>Replaces an existing pet by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/pets/{petId}</td>
        <td>Deletes an existing pet by id</td>
    </tr>
    <tr>
        <td>List&lt;Pet&gt;</td>
        <td>GET api/pets</td>
        <td>Gets all pets by user</td>
    </tr>
    <tr>
        <td>Diet</td>
        <td>GET api/pets/{petId}/diets/{dietId}</td>
        <td>Gets one diet from pet</td>
    </tr>
    <tr>
        <td>Diet</td>
        <td>POST api/pets/{petId}/diets</td>
        <td>Creates new diet for pet</td>
    </tr>
    <tr>
        <td>Diet</td>
        <td>PUT api/pets/{petId}/diets/{dietId}</td>
        <td>Replaces an existing diet by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/pets/{petId}/diets/{dietId}</td>
        <td>Deletes existing diet by id</td>
    </tr>
    <tr>
        <td>List&lt;Diet&gt;</td>
        <td>GET api/pets/{petId}/diets</td>
        <td>Gets all diets for pet</td>
    </tr>
    <tr>
        <td>Medication</td>
        <td>GET api/pets/{petId}/medications/{medId}</td>
        <td>Gets one medication from pet</td>
    </tr>
    <tr>
        <td>Medication</td>
        <td>POST api/pets/{petId}/medications</td>
        <td>Creates new medication for pet</td>
    </tr>
    <tr>
        <td>Medication</td>
        <td>PUT api/pets/{petId}/medications/{medId}</td>
        <td>Replaces an existing medication by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/pets/{petId}/medications/{medId}</td>
        <td>Deletes existing medication by id</td>
    </tr>
    <tr>
        <td>List&lt;Medication&gt;</td>
        <td>GET api/pets/{petId}/medications</td>
        <td>Gets all medications for pet</td>
    </tr>
    <tr>
        <td>Shot</td>
        <td>GET api/pets/{petId}/shots/{shotId}</td>
        <td>Gets one shot from pet</td>
    </tr>
    <tr>
        <td>Shot</td>
        <td>POST api/pets/{petId}/shots</td>
        <td>Creates new shot for pet</td>
    </tr>
    <tr>
        <td>Shot</td>
        <td>PUT api/pets/{petId}/shots/{shotId}</td>
        <td>Replaces an existing shot by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/pets/{petId}/shots/{shotId}</td>
        <td>Deletes existing shot by id</td>
    </tr>
    <tr>
        <td>List&lt;Shot&gt;</td>
        <td>GET api/pets/{petId}/shots</td>
        <td>Gets all shots for pet</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>GET api/account/{userId}/address</td>
        <td>Gets address by user Id</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>POST api/account/{userId}/address</td>
        <td>Creates an address for a user</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>PUT api/account/{userId}/address/{addressId}</td>
        <td>Replaces address by user Id</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>DELETE api/account/{userId}/address/{addressId}</td>
        <td>Deletes address by addressId</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>POST api/business/{businessId}/address</td>
        <td>Creates an address for a business</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>PUT api/business/{businessId}/address/{addressId}</td>
        <td>Replaces address by business</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>DELETE api/business/{businessId}/address/{addressId}</td>
        <td>Deletes address by addressId</td>
    </tr>
    <tr>
        <td>Medical Note</td>
        <td>GET api/medicalnotes/{id}</td>
        <td>Gets medical note by provider Id</td>
    </tr>
    <tr>
        <td>Medical Note</td>
        <td>POST api/medicalnotes</td>
        <td>Creates a medical note</td>
    </tr>
    <tr>
        <td>Medical Note</td>
        <td>PUT api/medicalnotes/{id}</td>
        <td>Replaces medical note by provider Id</td>
    </tr>
    <tr>
        <td>Medical Note</td>
        <td>DELETE api/medicalnotes/{id}</td>
        <td>Deletes medical note by Id</td>
    </tr>
    <tr>
        <td>Medical Note</td>
        <td>POST api/pets/{petId}/medicalnotes</td>
        <td>Creates a medical note for pet</td>
    </tr>
    <tr>
        <td>Medical Note</td>
        <td>PUT api/pets/{petId}/medicalnotes/{noteId}</td>
        <td>Updates a medical note by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/pets/{petId}/medicalnotes/{noteId}</td>
        <td>Deletes a medical note by id</td>
    </tr>
    <tr>
        <td>Business</td>
        <td>GET api/business/{id}</td>
        <td>Get business by Id</td>
    </tr>
    <tr>
        <td>Business</td>
        <td>POST api/business/</td>
        <td>Creates a business</td>
    </tr>
    <tr>
        <td>Business</td>
        <td>PUT api/business/{id}</td>
        <td>Replaces business info by Id</td>
    </tr>
    <tr>
        <td>Business</td>
        <td>DELETE api/business/{id}</td>
        <td>Deletes business note by Id</td>
    </tr>
    <tr>
        <td>List&lt;Business&gt;</td>
        <td>business/serviceType/{serviceTypeId}</td>
        <td>Find a list of business by service type</td>
    </tr>
    <tr>
        <td>void</td>
        <td>GET api/business/{businessId}/addClient/{userId}</td>
        <td>Adds a business to a user&#39;s list of businesses used</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/business/{businessId}/removeClient/{userId}</td>
        <td>Removes a business from a user&#39;s list of businesses used</td>
    </tr>
    <tr>
        <td>void</td>
        <td>GET api/business/{businessId}/addEmployee/{userId}</td>
        <td>Adds a business to a user&#39;s list of businesses used</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/business/{businessId}/removeEmployee/{userId}</td>
        <td>Removes a business from a user&#39;s list of businesses used</td>
    </tr>
    <tr>
        <td>PetComment</td>
        <td>GET api/pets/{petId}/petcomments/{petCommentId}</td>
        <td>Gets one comment from pet</td>
    </tr>
    <tr>
        <td>PetComment</td>
        <td>POST api/pets/{petId}/petcomments</td>
        <td>Creates new comment for pet</td>
    </tr>
    <tr>
        <td>PetComment</td>
        <td>PUT api/pets/{petId}/petcomments/{petCommentId}</td>
        <td>Replaces an existing pet comment by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/pets/{petId}/petcomments/{petCommentId}</td>
        <td>Deletes existing comment by id</td>
    </tr>
    <tr>
        <td>List&lt;PetComment&gt;</td>
        <td>GET api/pets/{petId}/petcomments</td>
        <td>Gets all comments for pet</td>
    </tr>
    <tr>
        <td>PetComment</td>
        <td>POST api/pets/{petId}/petcomments/{petCommentId}</td>
        <td>Adds a reply to a pet comment</td>
    </tr>
    <tr>
        <td>Comment</td>
        <td>GET api/account/{userId}/comments/{commentId}</td>
        <td>View comment by id</td>
    </tr>
    <tr>
        <td>Comment</td>
        <td>POST api/account/{userId}/comments</td>
        <td>Create a comment</td>
    </tr>
    <tr>
        <td>Comment</td>
        <td>PUT api/account/{userId}/comments/{commentId}</td>
        <td>Replace comment by id</td>
    </tr>
    <tr>
        <td>void</td>
        <td>DELETE api/account/{userId}/comments/{commentId}</td>
        <td>Delete comment by id</td>
    </tr>
    <tr>
        <td>List&lt;Comments&gt;</td>
        <td>GET api/account/{userId}/comments</td>
        <td>Get all comments for user</td>
    </tr>
    <tr>
        <td>List&lt;ServiceType&gt;</td>
        <td>GET api/services</td>
        <td>Gets all service types</td>
    </tr>
</table>

## Implementation
Discuss overall project structure


## Entity Relationship Model
<img src="https://github.com/kristentsuboi/FinalProject/blob/main/DB/EER.png?raw=true">

## Technologies and Methodologies Used
- RESTful Services
- Java
- Spring
- Hibernate
- Spring Data JPA
- Spring Boot
- MySQL 
- Postman
- Angular
- DOM
- Bootstrap
- Agile

## Lessons Learned
blah

### Reference Links
- [Trello Board] (https://trello.com/b/yvDxicfI/angular-aurora)
- [Wireframing] (https://www.figma.com/file/JDQW1IJ1fn0mWgOihxLLNA/Pawrents-Place?type=design&node-id=0-1&mode=design)
