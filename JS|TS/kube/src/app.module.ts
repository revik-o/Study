import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AppConfigModule } from './app-config/app-config.module';
import { AppEnvService } from './app-config/app-env.service';

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
          autoLoadEntities: false,
          synchronize: false,
        }
      }
    }),
    AppConfigModule
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule { }
