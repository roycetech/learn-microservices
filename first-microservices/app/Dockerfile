FROM alpine
MAINTAINER roycetech
RUN apk update
RUN apk add nodejs
ADD app.js /
EXPOSE 3000
ENTRYPOINT ["node", "app.js"]

