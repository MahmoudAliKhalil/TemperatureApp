FROM node:18-alpine as builder
WORKDIR application
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build -- -c production

FROM nginx:1.21-alpine
COPY --from=builder application/dist/temperature-front /usr/share/nginx/html
