FROM eclipse-temurin:17.0.6_10-jdk-alpine

ADD ./app/build/distributions/app.tar /

ENTRYPOINT ["/app/bin/app"]
