# Companies-House

This is a spring boot web application using REST APIs to manage companies

  ## Prerequistes
  Requires java 8 or higher version.
## build

`mvn clean install`

## Run the app

`java -jar ./target/Companies-House-0.0.1-SNAPSHOT.jar`

# REST API

The REST API to access the web app listed below.
note: Replace `localhost:8080` with `nithinnambiar.us-east-2.elasticbeanstalk.com` in the url to access the remote server

## Create new company
### Request
`POST  /companies/create`

    curl -H 'Content-Type: application/json' -X POST --data '{"name":"xyz", "address":"colchester", "city":"london", "country":"UK", "beneficial_owners":["Tim Cook"]}' http://localhost:8080/companies/create
### Response
    {"id":"3c566447-487d-44bb-8bb3-7ca576ae3c89","name":"xyz","address":"colchester","city":"london","country":"UK","beneficial_owners":["Tim Cook"]}

## Get a list of all companies
### Request
`GET  /companies/list`

    curl -H 'Content-Type: application/json' -X GET http://localhost:8080/companies/list
### Response
    {"id":"3c566447-487d-44bb-8bb3-7ca576ae3c89","name":"xyz","address":"colchester","city":"london","country":"UK","beneficial_owners":["Tim Cook"]}
## Get details about a company
### Request
`GET  /companies/details/{name}`

    curl -H 'Content-Type: application/json' -X GET http://localhost:8080/companies/details/xyz
### Response
    {"id":"3c566447-487d-44bb-8bb3-7ca576ae3c89","name":"xyz","address":"colchester","city":"london","country":"UK","beneficial_owners":["Tim Cook"]}
## Update a company
### Request
`POST  /companies/update`

    curl -H 'Content-Type: application/json' -X POST --data '{"name":"xyz", "address":"manchester", "city":"manchester", "country":"UK", "beneficial_owners":["Tim Cook"]}' http://localhost:8080/companies/update
### Response
    {"id":"3c566447-487d-44bb-8bb3-7ca576ae3c89","name":"xyz","address":"manchester","city":"manchester","country":"UK","beneficial_owners":["Tim Cook"]}
## Add beneficial owner(s) of the company
### Request
`POST  /companies/addowners`

    curl -H 'Content-Type: application/json' -X POST --data '{"name":"xyz", "beneficial_owners":["Stephey Fry"]}' http://localhost:8080/companies/addowners
### Response
    {"id":"3c566447-487d-44bb-8bb3-7ca576ae3c89","name":"xyz","address":"manchester","city":"manchester","country":"UK","beneficial_owners":["Tim Cook","Stephey Fry"]}

# Consideratons
## Authentication
Authentication can be added to this application to make it secure. This could be done either via HTTP Basic Authentication or using OAUTH2 tokens.
HTTP basic Authentication can implemented using Spring-security module.  The following code changes would be required.
1. Implement a `AuthenticationEntryPoint` to specify the entry-point-ref.
2. Implement spring security config with` @EnableWebSecurity` annotation.

## Service Redundancy
Multiple instance could be deployed in active-active topology behind a load balancer. This services can be deployed in multiple availability zones(aws cloud) which is spread across different geographic locations. This would ensure that the service is available even if site goes down in one geographic solution.