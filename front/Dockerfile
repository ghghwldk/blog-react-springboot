FROM node:16.13.1-alpine

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

ADD package.json /app/package.json
RUN npm install --save --legacy-peer-deps
RUN npm install react-scripts@3.4.1 -g

EXPOSE 3000
CMD ["npm", "start"]