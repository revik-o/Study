import { Module } from '@nestjs/common';
import { UserResolver } from './resolver/user.resolver';
import { DatabaseModule } from 'src/database/database.module';
import { PostResolver } from './resolver/post.resolver';

@Module({ imports: [DatabaseModule], providers: [UserResolver, PostResolver] })
export class GraphQlModule { }
