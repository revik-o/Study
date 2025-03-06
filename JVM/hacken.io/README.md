# hacken.io
This is the implementation of the Hacken task (check out “Middle Strong Java Test Task.pdf”)

# Hello!
Before starting this task, you should use this portal https://portal.grove.city:
* Create an application
* Remember your Application ID (Keys tab)
* Remember your Chain prefix (Networks tab)

After that, clone this repo `git clone https://github.com/Olehandro767/hacken.io.git`, and open `hacken.io` folder.

# How to compile a project?
Unix like OS: 
* (Optional) You can check the `/src/main/resources/application.yaml` configuration and modify if you wish. You can set credentials in this file into `grove-city-chain-prefix` and `grove-city-application-id` params or simply declare global variables like `export GROVE_CITY_CHAIN_PREFIX="<prefix>"` or `export GROVE_CITY_APPLICATION_ID="<appid>"`
* Open a terminal in the project root and just run the following command for compile the project `./gradlew clean && ./gradlew web_application:buildAngularProject && ./gradlew copyFrontendResources && ./gradlew bootJar`
* After compilation you need to run the PostgreSQL database. If you are using Doker, you are lucky, because you can run the following command `./run-local-dependencies.sh --rm-db` to start DB and Apache Kafka
* After that, you finally can run the application `java -jar build/libs/hacken_task-0.0.1.jar` and open this default URL (if you haven't changed the server port) http://localhost:8080

Happy **hacking**!!! :D

Windows: (in progress)

# Questions in this tasks
* Start function ✓
* Stop function ✓
* Wait function... I actually didn't understand this part of the task a bit **BUT** I've implemented two solutions, Trotting and Pause. I hope I implemented something correctly (statuc ?)
* DB ✓
* Seach function ✓
* Kubernetes feature [X] // FIXME!!!

Video tutor: https://github.com/Olehandro767/hacken.io/blob/main/tutor/WATCH_ME.mp4