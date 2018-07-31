# Spring-Boot-Demo
This is a small Spring-Boot-Demo project

### 1 - Jira
+ https://hdo-projects.atlassian.net/projects/SBD

### 2 - How to run this application:
Jump to the root directory of source code which contains pom.xml in Terminal
+ Solution 1: Using Eclipse
  * Run command: mvn clean eclipse:eclipse
  * Run the project as Java Application with the main class: DemoApplication
  
+ Solution 2: Using Maven
  * mvn spring-boot:run
  
+ Solution 3: Using executable JAR
  * mvn clean package -Dmaven.test.skip=true && java -jar target/demo-0.0.1-SNAPSHOT.jar
