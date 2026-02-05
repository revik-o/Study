function getOrThrow(name: string) {
    if (!process.env[name]) {
        throw new Error(`${name} is not defined`)
    }

    return process.env[name]
}

export default function getEnv(ctx: any) {
    const config = {
        mongoPort: getOrThrow('MONGO_PORT'),
        mongoHost: getOrThrow('MONGO_HOST'),
        mongoAppDbName: getOrThrow('MONGO_APP_DB_NAME'),
        mongoAppDbUser: getOrThrow('MONGO_APP_DB_USER'),
        mongoAppDbPass: getOrThrow('MONGO_APP_DB_PASS'),
    }

    if (!config.mongoPort) {
        throw new Error('MONGO_PORT is not defined')
    }

    if (!config.mongoHost) {
        throw new Error('MONGO_HOST is not defined')
    }

    if (!config.mongoAppDbName) {
        throw new Error('MONGO_APP_DB_NAME is not defined')
    }

    if (!config.mongoAppDbUser) {
        throw new Error('MONGO_APP_DB_USER is not defined')
    }

    if (!config.mongoAppDbPass) {
        throw new Error('MONGO_APP_DB_PASS is not defined')
    }

    return {
        dbPort: config.mongoPort,
        dbHost: config.mongoHost,
        dbName: config.mongoAppDbName,
        dbUser: config.mongoAppDbUser,
        dbPassword: config.mongoAppDbPass,
    }
}