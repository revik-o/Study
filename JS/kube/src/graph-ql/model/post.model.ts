import { Field, Int, ObjectType } from '@nestjs/graphql';
import { User } from './user.model';

@ObjectType()
export class Post {
    @Field(type => Int, { nullable: false })
    id: number;
    @Field({ nullable: false })
    content: string;
    @Field(() => User, { nullable: false })
    author: User
}