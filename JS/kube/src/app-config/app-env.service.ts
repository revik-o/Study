import { Injectable } from '@nestjs/common';
import { MissingEnvironmentVariableException } from 'src/exception/MissingEnvironmentVariableException';

export enum Profile {
    PRODUCTION = 'production',
    TEST = 'test',
}

@Injectable()
export class AppEnvService {

    private getOrThrow<T>(signature: string): T {
        const value = process.env[signature];

        if (value) {
            return value as T;
        }

        throw new MissingEnvironmentVariableException(signature);
    }

    public get port(): number {
        return this.getOrThrow<number>('PORT');
    }

    public get dbPort(): number {
        return this.getOrThrow<number>('DB_PORT');
    }

    public get dbHost(): string {
        return this.getOrThrow<string>('DB_HOST');
    }

    public get dbUser(): string {
        return this.getOrThrow<string>('DB_USER');
    }

    public get dbPassword(): string {
        return this.getOrThrow<string>('DB_PASSWORD');
    }

    public get dbName(): string {
        return this.getOrThrow<string>('DB_NAME');
    }

    public get activeProfile(): string {
        return process.env.NODE_ENV || Profile.PRODUCTION;
    }
}
