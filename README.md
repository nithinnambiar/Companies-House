# Companies-House

This is a spring boot web application using REST apis to manage companies

## build

`mvn clean install`

## Run the app

`java -jar ./target/Companies-House-0.0.1-SNAPSHOT.jar`

# REST API

The REST API to access the web app listed below.

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
`POST  /companies/addowners

    curl -H 'Content-Type: application/json' -X POST --data '{"name":"xyz", "beneficial_owners":["Stephey Fry"]}' http://localhost:8080/companies/addowners
### Response
    {"id":"3c566447-487d-44bb-8bb3-7ca576ae3c89","name":"xyz","address":"manchester","city":"manchester","country":"UK","beneficial_owners":["Tim Cook","Stephey Fry"]}