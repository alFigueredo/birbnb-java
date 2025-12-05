FROM node:lts AS build

WORKDIR /home/node/app
RUN chown -R node:node /home/node/app

COPY package.json .

ARG NODE_ENV
RUN npm install

COPY . ./
RUN npm run build


FROM nginx:stable
COPY --from=build /home/node/app/dist /usr/share/nginx/html

# Copiamos una configuración personalizada opcional
# (si no la agregás, igual funciona)
# COPY nginx.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]
