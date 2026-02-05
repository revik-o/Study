# https://hub.docker.com/_/node

#------------------------------------------------------------------------
# Builder Stage
#------------------------------------------------------------------------
FROM node:25-alpine AS builder

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

#------------------------------------------------------------------------
# Runner Stage
#------------------------------------------------------------------------
FROM node:25-alpine AS runner

WORKDIR /app

COPY --from=builder /app/.output/ ./

ENV PORT=80
ENV HOST=0.0.0.0

CMD ["node", "/app/server/index.mjs"]