# syntax=docker/dockerfile:experimental
FROM jeffback/meuolhar:latest
ENTRYPOINT ["java","-jar","/app.jar"]
