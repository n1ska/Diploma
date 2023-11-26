FROM openjdk:11

RUN mkdir -p /usr/aqa-shop
WORKDIR /usr/aqa-shop

COPY . /usr/aqa-shop/

EXPOSE 8080

CMD [ "java", "-jar", "aqa-shop.jar"]