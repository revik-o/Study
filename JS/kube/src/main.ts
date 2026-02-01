import { NestFactory } from '@nestjs/core';
import { ConsoleLogger, Logger } from '@nestjs/common';
import * as os from 'node:os';
import { AppModule } from './app.module';
import { AppEnvService } from './app-config/app-env.service';

async function bootstrap() {
  const app = await NestFactory.create(AppModule, {
    logger: new ConsoleLogger({
      prefix: `Logger <${os.hostname()}>`
    })
  });

  const appEnv = app.get(AppEnvService);
  Logger.log(`Listen ${appEnv.port} -> http://localhost:${appEnv.port}`);
  await app.listen(appEnv.port);
}

bootstrap();