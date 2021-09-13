FROM gradle:7-jdk16
WORKDIR /home/gradle/project
COPY . ./
RUN gradle build -x test --stacktrace
CMD gradle bootRun
