# Dockerized Spring Boot archetype

## 🧰 Tech Stack
* Language: Java 17
* Framework: Spring Boot 2.7.9
* Testing: JUnit Jupiter, MockMVC, TestContainers, Cucumber
* Build tool: Maven
* Doc: Swagger
* Containers: Docker, Docker compose
* CI: GitHub Actions
* Libraries: Lombok
* Architecture: Hexagonal

## 👩‍💻🧾 How To Start

1. Compile the archetype project: `mvn -B -U clean install`
2. Generate project from the archetype: `mvn archetype:generate -DarchetypeGroupId=com.example -DarchetypeArtifactId=dockerized-springboot-archetype -DarchetypeVersion=0.0.1-SNAPSHOT`
   1. Do you want the GitHub Actions badge result as part of the project's README? Add `-DgithubAccount=your_usename` to the previous command
3. Fill the new project variables:
   ```
   Define value for property 'groupId': my.groupId
   Define value for property 'artifactId': my-project
   Define value for property 'version' 1.0-SNAPSHOT: 1.0.0-SNAPSHOT
   Define value for property 'package' my.groupId: my.groupId.domain
   ```
4. A new directory will be created: `my-project` 
5. Code 🚀
