import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { GraphQLModule } from '@nestjs/graphql';
import { ApolloDriver } from '@nestjs/apollo';
import { join } from 'node:path';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AppConfigModule } from './app-config/app-config.module';
import { AppEnvService, Profile } from './app-config/app-env.service';
import { GraphQlModule } from './graph-ql/graph-ql.module';
import { DatabaseModule } from './database/database.module';

@Module({
  imports: [
    TypeOrmModule.forRootAsync({
      imports: [AppConfigModule],
      inject: [AppEnvService],
      useFactory: async (appEnv: AppEnvService) => {
        return {
          type: 'postgres',
          host: appEnv.dbHost,
          port: appEnv.dbPort,
          username: appEnv.dbUser,
          password: appEnv.dbPassword,
          database: appEnv.dbName,
          entities: [__dirname + '/**/*.entity.{js,ts}'],
          autoLoadEntities: appEnv.activeProfile === Profile.TEST,
          synchronize: appEnv.activeProfile === Profile.TEST,
        }
      }
    }),
    GraphQLModule.forRootAsync({
      driver: ApolloDriver,
      imports: [AppConfigModule],
      inject: [AppEnvService],
      useFactory: async (appEnv: AppEnvService) => ({
        autoSchemaFile: appEnv.activeProfile === Profile.PRODUCTION
          ? '/tmp/schema.gql' : join(process.cwd(), 'graphql/schema.gql'),
        sortSchema: true,
        introspection: appEnv.activeProfile !== Profile.PRODUCTION,
        playground: appEnv.activeProfile !== Profile.PRODUCTION,
        debug: appEnv.activeProfile !== Profile.PRODUCTION,
        formatError: (error) => ({
          message: error.message,
          code: error.extensions?.code || 'INTERNAL_SERVER_ERROR',
          extensions: appEnv.activeProfile === Profile.PRODUCTION ? undefined : error.extensions,
          location: appEnv.activeProfile === Profile.PRODUCTION ? undefined : error.locations,
          path: appEnv.activeProfile === Profile.PRODUCTION ? undefined : error.path,
        }),
      }),
    }),
    AppConfigModule,
    DatabaseModule,
    GraphQlModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
