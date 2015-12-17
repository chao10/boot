curl -X POST -vu modeapp:whatsmode http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=password&scope=read%20write"
