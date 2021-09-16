FROM gradle:7-jdk16
WORKDIR /home/gradle/project
COPY . ./
RUN gradle build -x test --stacktrace
CMD gradle bootRun --stacktrace --args="--spring.datasource.url=jdbc:postgresql://db:5432/kingsatm"
