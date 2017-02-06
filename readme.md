**UNFINISHED -> NOT WORKING**

This is a Maven archetype to start with an a simple Angular 2 project 
that is directly integrated into a Spring Boot Server.

* It exposes the angular application on root ('/' and '/index.html')
* The REST api is exposed under /api/ 

**Preconditions**
* java (JDK) is installed
* maven ist installed 
* node.js and npm are installed
* git is installed (optional)

**Usage**
* Check out this project by calling 'git clone https://github.com/zebrajaeger/springboot-angular2ts-starter' 

There a two modes
* Ever step starts with the following steps
  * run 'mvn install' in the project root
  * execute the .jar under webapp/target (java -jar xxx.jar)
* Running the Spring webapp and the Angular app separate (Develop)
  * Go to frontend and execute npm run start (is watches file changes)
  * Open Browser at http://localhost:4201
* Together in one .jar (Production, Angular is embedded)
  * Open Browser at http://localhost:8080
