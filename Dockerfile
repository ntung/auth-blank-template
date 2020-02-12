FROM openjdk:8
LABEL maintainer="nguyenvungoctung@gmail.com"

# install sdkman and Grails
#RUN apt-get update && apt-get install -y --no-install-recommends zip
#RUN curl -s "https://get.sdkman.io" | bash
#RUN ["/bin/bash", "-lc", "source $HOME/.sdkman/bin/sdkman-init.sh"]
#RUN ["/bin/bash", "-lc", "sdk install grails 3.3.2"]

# expected database port
EXPOSE 3306
EXPOSE 8080

COPY . /usr/src/ssrestdemo
WORKDIR /usr/src/ssrestdemo

RUN ./gradlew clean
#ENTRYPOINT ["/bin/bash", "-lc", "grails"]

ENTRYPOINT ["/bin/bash", "-lc", "./grailsw run-app"]
