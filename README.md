<div id="top"></div>


<!--HEAD OF PROJECT -->
# QA Project DFESW7
>Pokemon REST API written in JAVA with SpringBoot framework


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Project-Specifications">Project Specifications</a></li>
    <li><a href="#MVP-Requirements"> MVP Requirements</a></li>
    <li><a href="#Built-with">Built With</a></li>
    <li><a href="#Why-are-we-doing-this">Why are we doing this?</a></li>
    <li><a href="#How-I-expected-the-challenge-to-go">How I expected the challenge to go</a></li>
    <li><a href="#what-went-well">What went well</a></li>
    <li><a href="#What-went-less-well">What went less well</a></li>
    <li><a href="#Possible-improvements-for-future-revisions">Possible improvements for future revisions</a></li>
    <li><a href="#Postman---API-Screenshots">Postman-API Screenshots</a></li>
    <li><a href="#Screenshots-to-show-Data-is-being-persisted in the database">Screenshots to show data is being persisted in the database</a></li>
    <li><a href="#Link-to-my-jira-board">Link to my Jira board</a></li>
  </ol>
</details>

<!-- About the project -->

## Project Specifications

Create a Spring Boot API, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training
<ul>
  <li> Back-end developed using Java</li>
  <li> Managed database</li>
  <li> A means of making API calls (Postman) and a means of checking persistence (Workbench/H2 console)</li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>
    
## MVP Requirements
<ul>
  <li>A completed project management board </li>
  <li>Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features</li>
  <li>A risk assessment which outlines the issues and risks faced during the project timeframe </li>
  <li>A relational database, which is used to persist data for the project. </li>
  <li>A functional application ‘back-end’, written in Java/Spring Boot, which meets the requirements set on your Jira board</li>
  <li>A build (.jar) of your application, including any dependencies it might need, produced using Maven. </li>
  <li>A series of API calls designed with postman, used for CRUD functionality </li>
  <li>Fully designed test suites for the application you are creating, including both unit and integration tests</li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>


## Built With
<ul>
  <li>Kanban Board: *Jira* </li>
  <li>Version Control System: *Git* </li>
  <li>Source Code Management: *Github* </li>
  <li>Database Management System: *H2 and MySQL* </li>
  <li>Coding Language: *Java* </li>
  <li>API Development Platform: *Spring Boot* </li>
  <li>Build Tool </li>
  <li>Testing: *JUnit* </li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>

## Why are we doing this
This project is the culmination of 9 weeks worth of learning with the QA Software Development Bootcamp. During this time we covered a variety of topics oncluding Java, Spring Boot, testing and MySQL along with many other things. To conclude this learning period we have been tasked with creating this project to put our knowledge and skills learnt to the test!
I have created a Pokemon app, that allows users to store Pokemon and their types within a database. The app has full CRUD functionality, two data entities with a one-to-many relationship, custom exceptions and fully designed test suites with coverage >80%.

<p align="right">(<a href="#top">back to top</a>)</p>

## How I expected the challenge to go
I was pretty pessimistic going into the project initially as I felt I had barely grasped the concepts related to Spring Boot. I expected to struggle at nearly every point of creating the app. To my pleasant suprise I found that the majority of the time it was smooth-sailing, only getting stuck with errors I had not encountered before and trying to implement concepts that had not been covered in the bootcamp.

<p align="right">(<a href="#top">back to top</a>)</p>

## What went well
First and foremost I have a fully functioning (basic) REST API with CRUD functionality. My integration and unit testing works as expected with >80% coverage (which took a very long time). I also was able to add two data entities to my project as a stretch goal, which have a one-to-many relationship.
Finally, and actually most importantly, I thoroughly enjoyed the process of getting lost in my project/code and look forward to expanding my knowledge and skillset to be able to create further and more complications projects.

<p align="right">(<a href="#top">back to top</a>)</p>


## What went less well
My SQL language recall was very rusty going into the project so it made the setup of MySQL databases a particularly slow and difficult process, for which I had to seek assistance for when MySQL refused to recognise Long or Boolean datatypes and I could only use BIGINTS and TINYINTS. I also attempted to use enums for the pokemon types however I also wanted to set this up as a data entity. I spent quite a long time on this and eventually had to abandon this task. I do now see how I could use enums in my project but time did not allow for me to implement this.

<p align="right">(<a href="#top">back to top</a>)</p>

## Possible improvements for future revisions
As mentioned above I would like to implement enums to restrict the pokemon types that can be inputted. I would also like to include further custom queries such as 'find by type' as I originally intended and to include the use of DTOs. Finally, a front end would be important to implement but this was outside the scope of this project.

<p align="right">(<a href="#top">back to top</a>)</p>

## Postman-API Acreenshots
#### Create Pokemon if Pokemon name doesn't already exist in the database
![create Pokemon](https://user-images.githubusercontent.com/94956101/152327662-514c799e-18c9-4efb-83c7-72039ac6840c.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Create pokemon when Pokemon name already exists in the database
![create invalid pokemon](https://user-images.githubusercontent.com/94956101/152328208-5df2f834-f381-486f-b7c6-cdc7024317b5.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Create type for Pokemon
![create type](https://user-images.githubusercontent.com/94956101/152328005-68fababa-097b-4849-b0b8-e181f4176e19.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Get all Pokemon
![get all pokemon](https://user-images.githubusercontent.com/94956101/152328707-ca0b0539-7d01-439d-9a18-d64de66b7bfe.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Get all types
![get all types](https://user-images.githubusercontent.com/94956101/152333620-293fe9f2-6b51-44e7-beef-6f3d0b55fde3.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Get Pokemon by ID
![get pokemon by id](https://user-images.githubusercontent.com/94956101/152329608-856f6958-cbc9-4858-9b18-6c0853f91566.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Get type by ID
![get type by id](https://user-images.githubusercontent.com/94956101/152329432-b8261c22-c809-455b-91e5-44e52df7781b.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Update Pokemon (changed Venasaur canEvolve field to false)
Before

![pokemon before update](https://user-images.githubusercontent.com/94956101/152333961-aa360e57-1a61-4a83-b766-d8313ed3f39a.png)


Update method

![update pokemon](https://user-images.githubusercontent.com/94956101/152334161-b5848381-fd08-4796-8e3a-ce44bd65e603.png)


After

![pokemon after update](https://user-images.githubusercontent.com/94956101/152334204-b1a060e8-ea5a-4d2b-8b29-220ada30694a.png)


<p align="right">(<a href="#top">back to top</a>)</p>

#### Update type (changed name of type and which Pokemon it relates to)
![update type](https://user-images.githubusercontent.com/94956101/152330254-65a7e154-7694-46ff-984f-900af538527d.png)

Changes persisted in Pokemon class with the types changes
![updated type in pokemon](https://user-images.githubusercontent.com/94956101/152330348-91f80c29-2f40-4613-88e9-8db601697b0b.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Delete pokemon
![delete pokemon](https://user-images.githubusercontent.com/94956101/152330914-0aaf08d6-509d-40f3-8407-6978ea318b13.png)


Get Pokemon by id displaying Pokemon no longer exists in database

![delete pokemon not appearing in get statement](https://user-images.githubusercontent.com/94956101/152331010-38b77dbb-1894-44eb-828d-002de906df9a.png)


Also deletes the associated type for that Pokemon

![deleted associated type](https://user-images.githubusercontent.com/94956101/152331418-a4f4b937-e32a-4525-84e0-b55f9a1474f6.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Delete type
![delete type](https://user-images.githubusercontent.com/94956101/152331543-34d8f087-0378-4508-bd95-b67d3ec3c3b1.png)

Get type by id displaying Pokemon no longer exists in database
![delete type not appearing in get statement](https://user-images.githubusercontent.com/94956101/152331620-c31ca58b-7fa2-4a59-8bcf-0f6b2b955faf.png)

The deleted type now does not appear in the Pokemon class
![deleted type in pokemon](https://user-images.githubusercontent.com/94956101/152331844-2fd08363-d129-41a1-b546-a494aa8dfec7.png)

<p align="right">(<a href="#top">back to top</a>)</p>

## Screenshots to show data is being persisted in the database
Screenshot to show the database on initialisation. It currently has one entry as I am running the production mode in validate so any previous entries are saved

![image](https://user-images.githubusercontent.com/94956101/152347520-c125eb63-c7dd-4ffb-a293-0e9841d0175c.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Create Pokemon
Post request in Postman

![image](https://user-images.githubusercontent.com/94956101/152348199-cf7228ce-e284-4b50-b8fe-a10af83dd468.png)

Data persisting within MySQL

![image](https://user-images.githubusercontent.com/94956101/152348436-308f9858-c964-469d-aa35-a2d69f969f8f.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Create type
Post request in Postman

![image](https://user-images.githubusercontent.com/94956101/152348705-72d4895a-e976-4c0a-be22-cbe691ac4978.png)

Data persisting within MySQL

![image](https://user-images.githubusercontent.com/94956101/152348844-c225bcdb-6cb6-4f94-9f45-3e87198bda9e.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Read all Pokemon
Added extra Pokemon to the database for the purpose of showing a Get request

![image](https://user-images.githubusercontent.com/94956101/152349844-f4f562e5-d106-4d7c-8352-4a9cd609c495.png)

Get all request in MySQL with corresponding data

![image](https://user-images.githubusercontent.com/94956101/152350364-406e3bc2-d83c-4dbc-ac4e-e248e750d712.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Read all types
Added extra types to the database for the purpise of showing a Get request

![image](https://user-images.githubusercontent.com/94956101/152350710-f63e86de-9cf8-4543-9dd8-04bc2c85cfa6.png)

Get all request in MySQL with corresponding data

![image](https://user-images.githubusercontent.com/94956101/152350828-f9ec218c-5b27-495f-a507-8ff06cf00a36.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Read by Pokemon id
Get by id in Postman

![image](https://user-images.githubusercontent.com/94956101/152351283-e0f37ebf-63fb-465c-a09a-407284da3c09.png)

Get by id request in MySQL with corresponding data

![image](https://user-images.githubusercontent.com/94956101/152351430-2712b4c8-bc16-4c26-bef5-40b0a408cd91.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Read by type id
Get by id in Postman

![image](https://user-images.githubusercontent.com/94956101/152351554-4bf4e8c1-939e-4b6d-a1a6-5e739705d538.png)

Get by id request in MySQL with corresponding data

![image](https://user-images.githubusercontent.com/94956101/152351616-313c2efd-ed9d-4ab2-9ad2-9d4d6238c0c6.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Update Pokemon
Put request in Postman, changing canEvolve field to false from true and pokedex number changed to 3 from 4

![image](https://user-images.githubusercontent.com/94956101/152352102-61089bea-b8c4-4ac1-b3c0-fe5ecde57ee9.png)

Data in MySQL with corresponding changes

![image](https://user-images.githubusercontent.com/94956101/152352375-22333766-da14-4a98-8cd9-b3604bdf208b.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Update type
Put request in Postman, changing name to "Fire" from "Poison" and Pokemon associated with type to null

![image](https://user-images.githubusercontent.com/94956101/152352695-5dba5dde-d3f2-40fb-a275-fb99d0c07edd.png)

Data in MySQL with corresponding changes

![image](https://user-images.githubusercontent.com/94956101/152352948-0916b0fd-8855-4480-b24b-5734b9a945ad.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Delete Pokemon
Delete request in Postman

![image](https://user-images.githubusercontent.com/94956101/152353111-3d9bda90-43a8-4b9c-964a-e5b5d323194c.png)

Data in MySQL with corresponding changes

![image](https://user-images.githubusercontent.com/94956101/152353156-63bff4fd-4c34-4618-8900-886f03013c84.png)

<p align="right">(<a href="#top">back to top</a>)</p>

#### Delete type
Delete request in Postman

![image](https://user-images.githubusercontent.com/94956101/152353330-f89fbe33-fd97-4b88-bcac-211f0b5f86ca.png)

Datain MySQL with corresponding changes

![image](https://user-images.githubusercontent.com/94956101/152353451-3a92d55c-0715-4cd0-8254-530e53718cb0.png)

<p align="right">(<a href="#top">back to top</a>)</p>


<p><b>o	Screenshot of your test results, including coverage report.</b></p>

<p></p>
<p><b>o	Link to Jira Board - You must add Morgan Walsh and Jordan Benbelaid as collaborators on your Jira board. </b></p>

