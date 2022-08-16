#   How to run the project

    Pre-requisition：
        pls complete CIC Day1 exercise!

    Use local postgresql:
        I recommend you to install postgresql locally. Note that if you use local postgresql, you need to create 
        DB "nomination" manually and setup your postgresql user name and password properly, refer to 
        nomination.core-> resource/application.properties

    Use local docker container:
        Also you can use the local docker container to access the DB, to activate postgresql in docker:
        In mock/local, setup docker environment:
        > docker-compose up -d
    
    Click bootRun to run the application

#   Endpoint:
    
    Import bundle data:
        Find InputData.json and POST it into DB via: http://localhost:8080/api/data
    
    CURD Entity:
        Role: http://localhost:8080/api/role
        Team: http://localhost:8080/api/team
        User: http://localhost:8080/api/user; http://localhost:8080/api/userview
        Period: http://localhost:8080/api/period
        Prize: http://localhost:8080/api/prize
        Assignment: http://localhost:8080/api/assignment; http://localhost:8080/api/assignmentview
        Nomination: http://localhost:8080/api/nomination; http://localhost:8080/api/nominationview
        Vote: http://localhost:8080/api/vote; http://localhost:8080/api/voteview

#   Task in progress
    
    1.  Adopt pojo into CIC BO layer
    2.  Adopt maven dependency by CIC artifacts
    3.  How to consume Kafka service?
    4.  UT coverage
    5.  Deploy this project into K8S