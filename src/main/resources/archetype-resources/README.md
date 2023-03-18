# ${artifactId}

#if (${githubAccount} != '_UNDEFINED_')
[![CI pipeline status](https://github.com/${githubAccount}/${artifactId}/actions/workflows/maven.yml/badge.svg)](https://github.com/${githubAccount}/${artifactId}/actions)
#end

# 👩‍💻🧾 How To Start
1. Compile the application: `mvn clean install`
2. Start up Docker compose: `docker-compose up`
3. Go to browser and check REST api documentation: http://localhost:8000/swagger-ui/index.html

# 🤖🧾 How to use GitHub actions
1. Commit and push your changes to your remote repository placed at GitHub
5. Automatically a new workflow execution is triggered