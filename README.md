# Suburb Service


## Execute Tests
```
mvn clean test
```

## Start Application
```
mvn clean spring-boot:run
```

## Create Docker Image
```
docker build -t suburbService .
```

## Run Docker Container
```
docker run -p 8080:8080 suburbService 
```


## Create Operation
```
curl --location --request POST 'localhost:8080/v1/suburb' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "name":"A G C R",
        "postalCode": 110002
    },
    {
        "name":"Anand Parbat",
        "postalCode": 110005
    },
    {
        "name":"A P S Colony",
        "postalCode": 110010
    },
    {
        "name":"Ashram",
        "postalCode": 110016
    },
    {
        "name":"Ashok Nagar",
        "postalCode": 110018
    }
]'
```

## Read Operation
```
curl --location --request GET 'localhost:8080/v1/suburb/110002/110005'
```