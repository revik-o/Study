import { Test, TestingModule } from '@nestjs/testing';
import { AppEnvService } from './app-env.service';

describe('AppEnvService', () => {
  let service: AppEnvService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [AppEnvService],
    }).compile();

    service = module.get<AppEnvService>(AppEnvService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
