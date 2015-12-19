# Spring boot application using oauth2 authentication

## To retrieve an access token via username/password (the password grant type):

curl -X POST -vu modeapp:whatsmode http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=password&scope=read%20write"

## To access the protected resource api using the access token:

curl -i http://localhost:8080/greeting -H "Authorization: Bearer <access_token>"

## To logout the user and remove the access token:

curl -i http://localhost:8080/oauth/logout -H "Authorization: Bearer <access_token>"
