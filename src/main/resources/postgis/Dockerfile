FROM mdillon/postgis:9.4

ENV TZ=Europe/Moscow \
    POSTGRES_DB=pet \
    POSTGRES_USER=pet \
    POSTGRES_PASS=pet

COPY pet_init.sql /docker-entrypoint-initdb.d/pet_init.sql