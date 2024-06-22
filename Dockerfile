FROM mysql:latest
LABEL authors="abaddon"
COPY ./src/repository/final.sql /docker-entrypoint-initdb.d/final.sql
EXPOSE 3306
RUN final.sql
ENTRYPOINT ["top", "-b","run"]