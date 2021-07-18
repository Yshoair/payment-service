### :computer: How to execute

1. Start docker containers
   
    a. Through mvn -> mvn package -f pom.xml
    
    b. manually -> open bash in src/main/resources/dependency/docker-compose/ and run docker-compose up -d


2. run Spring boot main class com.wefox.payment.PaymentServiceApplication

    a. Please note The project is developed in Java 11 so make sure you have a compatible JDK.

3. The entry point to the code would be src/main/java/com/wefox/payment/infrastructure/kafka/consumer/KafkaPaymentConsumer.java

### :memo: Notes

1. docker-compose.yml
   
   a. Added extra listener to kafka-server to allow connection from localhost clients 
   so please use the modified docker-compose.yml
  
 
2. pom.xml 
   
   a. added plugin to start containers by maven
   
   b. clean life cycle would run docker-compose down
   
   c. compile life cycle would run docker-compose up -d 
   

3. Frameworks used
    
    a. Spring boot
    
    b. Hibernate

    c. Kafka

    d. Junit&Mockito

    e. Lombok


4. Programming paradigms applied
    
    a. Test Driven Development
    
    b. Interface-based architecture

    c. Solid architecture


5. Conventions
   
    a. Packages naming convention

        I.   Singular names

        II.  model refers to transfere objects 

        III. entity refers to db table objects

        IV.  contract refers to interfaces

        V.   service refers to bussiness services

        VI.  client refers to external endpoint e.g. rest/soap etc
    b. Classes naming convention
        
        I.  Singular Pascal case
        II. Interfaces are prefixed by I
    c. Code format -> google java format 
   
    d. Git Commits -> conventional commits format 


6. Project Structure

   a. Layered structure

        I.   data -> package for enums/models/entities + associated contracts

        II.  repository -> package for database repositories

        III. service -> package for business services + associated contracts 

        IV.  infrastructre -> package for configClasses/exceptions/listeners/loggers
   b. Configurations: in application.properites file managed by ConfigurationManager class

### :pushpin: Things to improve

1. Kafka consumer error handler for broker related issues and more customized kafka configurations
2. Using kafka mockProducer to develop unit test code for the consumer
3. Separate configuration properties in different properties files


