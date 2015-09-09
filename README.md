# MultipleUIApplication-Gateway
multiple ui and gateway section of tutorial: https://spring.io/guides/tutorials/spring-security-and-angular-js

Instructions:

1.**DOWNLOAD AND IMPORT**
  a. Clone or download the repository as zip. 
  b. If downloaded as zip, extract the zip to a directory. You will then have three subdirectories:
    - ui
    - resource
    - gateway 
  c. Open your IDE and import the 3 subdirectories as Maven Projects and wait for each of them to build.

2. **SETUP REDIS CONTAINER**
  - Open docker and execute the command below:
      $docker run -d --name redis -p 6379:6379 redis
        *this will setup a redis container with name "redis and port(public and private) = 6379
        *-d means to run container in background
3. **RUN THE SERVERS**
  a. Check first the properties in each of the following subdirectories
    - ui/src/main/resources
    - resource/src/main/resources
    - gateway/src/main/resources
    * check the redis host and ports
  b. Find UiApplication.java, GatewayApplication.java and ResourceApplication.java and run them as java applications
  c. Open a browser and go to http://localhost:8080/ui
