# https://hub.docker.com/_/node

#------------------------------------------------------------------------
# Builder Stage
#------------------------------------------------------------------------
FROM node:25-alpine AS builder

WORKDIR /usr/src/app
COPY package.json ./
RUN npm --version && npm i
COPY . .
RUN npm run build

#------------------------------------------------------------------------
# Runner Stage
#------------------------------------------------------------------------
FROM node:25-alpine AS runner

WORKDIR /usr/src/app
COPY --chown=node:node --from=builder /usr/src/app/package-lock.json ./
COPY --chown=node:node --from=builder /usr/src/app/package.json ./
RUN npm --version && npm ci --omit=dev
COPY --chown=node:node --from=builder /usr/src/app/dist ./dist

CMD ["node", "dist/src/main.js"]