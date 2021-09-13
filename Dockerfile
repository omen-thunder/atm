FROM gradle:7-jdk16
WORKDIR /home/gradle/project
COPY . ./
RUN gradle build --stacktrace
CMD gradle bootRun
