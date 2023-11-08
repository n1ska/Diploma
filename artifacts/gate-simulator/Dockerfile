FROM node:21-alpine3.18

RUN mkdir -p /usr/gate-simulator
WORKDIR /usr/gate-simulator

COPY . /usr/gate-simulator/
EXPOSE 9999

CMD [ "npm", "start" ]