FROM openjdk:11


# Set timezone
RUN ln -sf /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
ENV TZ=America/Los_Angeles

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# TODO: Come up with consistent process for how we do ENTRYPOINT

ENTRYPOINT exec java $JAVA_OPTS -cp app:app/lib/* com.carta.llc.Application

EXPOSE 8080
