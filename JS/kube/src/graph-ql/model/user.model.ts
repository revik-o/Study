import { Field, Int, ObjectType } from '@nestjs/graphql';

@ObjectType()
export class User {
    @Field(type => Int, { nullable: false })
    id: number;

    @Field({ nullable: false })
    name: string;
}