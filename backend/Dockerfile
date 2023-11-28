ARG APP_INSIGHTS_AGENT_VERSION=3.4.18
FROM hmctspublic.azurecr.io/base/java:21-distroless

COPY build/libs/backend-0.0.1-SNAPSHOT /opt/app/

EXPOSE 4550
CMD [ "spring-boot-template.jar" ]