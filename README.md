## --------APPLICATION FOR PROJECT MANAGEMENT ----------  ## 
## User Requirements Mini-Specification  
This is for REST APIs communication for simple Web-based application  for project management

## User Stories The high level functional requirements are as follows. 
System Requirements
JDK: 1.8
SpringBoot: 2.7.1
MybatisPlus: 3.4.1
MySQL: 5.7

## Use Cases
The use cases just helps to comprehend the requirements.
1. Project managers can log in to the system using the administrator and a preset password.
2. Project managers can record project info into the system.
A project must contains name, introduction, owner, start date time, end date time, current status.
3. Project managers can make projects prepare/start/end.
4. Project managers can explore all projects

## DEVELOPMENT Requirement
Technology used:
JDK: 1.8
SpringBoot: 2.7.1
MybatisPlus: 3.4.1
MySQL: 5.7
Swagger: 1.9.1

## Serve the following implementation :
Implement 3 Tasks in 2 days
1. Implement the log in API
 HTTP Request Method: POST
 Should return the JWT, nickname, accountId of current userAccount as a object property each.

2. Implement the create a project API
HTTP Request Method: POST

3. Implement the list of projects(pagination) API
    HTTP Request Method: GET
    Should provide parameters:
    current: current page index
    size: item count of per page
   
    **Should return:**
    state: API response status, 1: success; 0: failure
    msg: API response message. e.g: "Get project list successful"data: API response body data
    total: Count of projects
    list:: List of projects, each item is a object
    item: Information of a project
    id: Project unique identifier
    name: Project name
    intro: Project introduce
    owner: Project owner name
    status: Project status 0: pre; 1: start; 3: end
    startDateTime: Project start date time, format: yyyy-MM-dd HH:mm:ss , if
    project status is pre , it should be null
    endDateTime: Project end date time, format: yyyy-MM-dd HH:mm:ss , if
    project status is not end , it should be null

## Database Setup:
1. Please create a database as ‘pm_db’ and update the database username and password in **'application.properties'** file before run the application.

2. No need to run any database DDL script for this application.
3. Run the following Query insert script for create userAccount before login to the system [Here system username/password: userAccount/1234]

``CREATE TABLE `project` (
   `id` 	  bigint NOT NULL AUTO_INCREMENT,
   `name`    varchar(255) NOT NULL,
   `owner`   varchar(255) NOT NULL,
   `intro`   text NOT NULL,
   `status`  int NOT NULL,
   `start_date_time` datetime,
   `end_date_time` datetime,
   `created` datetime  DEFAULT CURRENT_TIMESTAMP,
   `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE INDEX `uidx_project` (`name`)
   ) AUTO_INCREMENT = 100;
``

``CREATE TABLE `user_account` (
`id`       bigint NOT NULL AUTO_INCREMENT,
`password` varchar(255) DEFAULT NULL,
`username` varchar(255) DEFAULT NULL,
`nickname` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
);
``

## User instruction
1. For swagger use the URL: http://localhost:8080/swagger-ui.html
2. Register a user before login (http://localhost:8080/api/register)
3. Use [Bearer jwt-token-XXXXXX] for other request

