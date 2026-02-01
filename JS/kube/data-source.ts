import { MissingEnvironmentVariableException } from 'src/exception/MissingEnvironmentVariableException';
import { DataSource } from 'typeorm';

function getOrThrow<T>(signature: string): T {
    const value = process.env[signature];

    if (value) {
        return value as T;
    }

    throw new MissingEnvironmentVariableException(signature);
}

export default new DataSource({
    type: 'postgres',
    host: getOrThrow('DB_HOST'),
    port: Number.parseInt(getOrThrow('DB_PORT')),
    username: getOrThrow('DB_USER'),
    password: getOrThrow('DB_PASSWORD'),
    database: getOrThrow('DB_NAME'),
    entities: ['dist/src/database/**/*.entity.js'],
    migrations: ['dist/migrations/*.js'],
});