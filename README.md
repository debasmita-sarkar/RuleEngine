# RuleEngine
This project is to build a Rule engine for order processing.
**Step for compiling:**
_mvn clean install_
**Steps for Execution:**
Right click and run **RuleEngineApplication.java** as "Java application" from your IDE.

**PFB the curl commands for supported payment types :**

Curl command for payment type PHYSICAL_PRODUCT 
curl --location --request POST 'http://localhost:9090/api/v1/payment' \
--header 'Content-Type: application/json' \
--data-raw '{ "paymentType":"PHYSICAL_PRODUCT",
   "to":"user1",
    "from":"user2",
    "agent":"agent"
}'

Curl command for payment type BOOK 
curl --location --request POST 'http://localhost:9090/api/v1/payment' \
--header 'Content-Type: application/json' \
--data-raw '{ "paymentType":"BOOK",
   "to":"user1",
    "from":"user2",
    "agent":"agent"
}'

Curl command for payment type MEMBERSHIP_UPGRADE 
curl --location --request POST 'http://localhost:9090/api/v1/payment' \
--header 'Content-Type: application/json' \
--data-raw '{ "paymentType":"MEMBERSHIP_UPGRADE",
   "to":"user2",
    "from":"user1"
    
}'

Curl command for payment type MEMBERSHIP_ACTIVATE 
curl --location --request POST 'http://localhost:9090/api/v1/payment' \
--header 'Content-Type: application/json' \
--data-raw '{ "paymentType":"MEMBERSHIP_ACTIVATE",
   "to":"user2",
    "from":"user1"
    
}'

curl --location --request POST 'http://localhost:9090/api/v1/payment' \
--header 'Content-Type: application/json' \
--data-raw '{ "paymentType":"VIDEO",
   "to":"user2",
    "from":"user1"
    
}'

**Tasks remaining:**
1.Actual implementation of all the actions
2.Exception handling



**Test Coverage Report**

<img width="402" alt="Coverage" src="https://user-images.githubusercontent.com/40517925/152660123-a6aaec04-a2dd-40b2-9485-7df4af614ceb.PNG">
