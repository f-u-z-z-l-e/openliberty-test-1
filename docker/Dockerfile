FROM openliberty/open-liberty:full-java13-openj9-ubi

LABEL maintainer="Andreas Lingenhag" vendor="fuzzle" github="https://github.com/f-u-z-z-l-e"

ADD --chown=1001:0 https://jdbc.postgresql.org/download/postgresql-42.2.9.jar /opt/ol/wlp/lib

ARG ARTIFACT_FILE
COPY --chown=1001:0 config/ /config/
COPY --chown=1001:0 "${ARTIFACT_FILE}" /config/apps/
