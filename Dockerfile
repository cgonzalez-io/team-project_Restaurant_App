FROM mysql:latest
LABEL authors="abaddon"
COPY ./src/repository/createFinal.sql /docker-entrypoint-initdb.d/createFinal.sql
EXPOSE 3306
RUN createFinal.sql
ENTRYPOINT ["top", "-b","run"]