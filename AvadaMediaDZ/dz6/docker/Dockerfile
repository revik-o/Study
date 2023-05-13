FROM mysql:latest

RUN mkdir -p /proj/mysql/

ENV TZ=europe/kiev MYSQL_ROOT_PASSWORD=12345678

EXPOSE 3306

VOLUME [ "/home/oleg/git/AvadaMediaDZ/docker/data" ]

# CMD ["mysql", "-u root -p12345678"]