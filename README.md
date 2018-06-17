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

`curl -H "Content-Type: application/json" -X POST -d '{"name":"abc","address":"colchester","city":"london","country":"UK","beneficial_owners":["Tim Cook"]}' http://localhost:8080/companies/create`


