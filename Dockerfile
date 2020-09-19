FROM openjdk:11

ARG APP_HOME=/opt/pet-project
ARG APP_JAR=pet-project.jar

ENV TZ=Europe/Moscow\
    HOME=$APP_HOME \
    JAR=$APP_JAR

WORKDIR $HOME
COPY build/libs/pet-project-*.jar $HOME/$JAR
ENTRYPOINT java $JAVA_OPTS -jar $JAR