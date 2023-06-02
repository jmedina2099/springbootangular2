FROM debian:bookworm

RUN apt update
RUN apt install gnupg2 -y

RUN echo "deb http://ppa.launchpad.net/linuxuprising/java/ubuntu focal main" | tee /etc/apt/sources.list.d/linuxuprising-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 73C3DB2A
RUN apt update

RUN apt install binutils binutils-common binutils-x86-64-linux-gnu ca-certificates fonts-urw-base35 gsfonts-x11 java-common libbinutils \ 
 libbrotli1 libc-l10n libctf-nobfd0 libctf0 libfontenc1 libfreetype6 libgprofng0 libjansson4 libpng16-16 libpsl5 locales openssl \
 publicsuffix wget x11-common xfonts-encodings xfonts-utils libc-bin libc6 -y

RUN echo oracle-java17-installer shared/accepted-oracle-license-v1-3 select true | /usr/bin/debconf-set-selections
RUN apt install oracle-java17-installer -y

RUN apt install tomcat10 -y

RUN echo "spring.profiles.active=container-prod" >> /var/lib/tomcat10/conf/catalina.properties

COPY lib/libtcnative-2.so /usr/lib
COPY target/springbootangulardev.war /var/lib/tomcat10/webapps/

ENV CATALINA_HOME /usr/share/tomcat10
ENV CATALINA_BASE /var/lib/tomcat10

ENV PATH $CATALINA_HOME/bin:$PATH

WORKDIR $CATALINA_HOME

EXPOSE 8080

CMD ["catalina.sh", "run"]