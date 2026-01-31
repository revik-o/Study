import { Module } from '@nestjs/common';
import { AppEnvService } from './app-env.service';

@Module({
    providers: [AppEnvService],
    exports: [AppEnvService]
})
export class AppConfigModule { }
