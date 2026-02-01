import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './entity/user.entity';
import { UserRepository } from './repository/user.repository';
import { Post } from './entity/post.entity';
import { PostRepository } from './repository/post.repository';

@Module({
    imports: [
        TypeOrmModule.forFeature([Post, User])
    ],
    providers: [UserRepository, PostRepository],
    exports: [UserRepository, PostRepository]
})
export class DatabaseModule { }
