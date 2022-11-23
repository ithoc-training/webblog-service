ARG DOCKER_PROXY=${DOCKER_PROXY}

FROM ${DOCKER_PROXY}eclipse-temurin:17-jre-alpine as builder
WORKDIR application
COPY target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM ${DOCKER_PROXY}eclipse-temurin:17-jre-alpine

EXPOSE 8080

RUN adduser --uid 1000 --disabled-password --gecos "" appuser

# setup and configure folder before switching to user
RUN mkdir -p /var/webblog && chown appuser:appuser /var/webblog

WORKDIR /var/webblog

COPY --chown=appuser:appuser --from=builder application/dependencies/ ./
COPY --chown=appuser:appuser --from=builder application/spring-boot-loader/ ./
COPY --chown=appuser:appuser --from=builder application/snapshot-dependencies/ ./
COPY --chown=appuser:appuser --from=builder application/application/ ./

# -Dspring.profiles.active="dev"
ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE
ENTRYPOINT exec java ${JAVA_OPTS} -D${SPRING_PROFILES_ACTIVE} org.springframework.boot.loader.JarLauncher
