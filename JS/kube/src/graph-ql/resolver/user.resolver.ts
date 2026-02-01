import { Args, Int, Mutation, Query, Resolver } from "@nestjs/graphql";
import { UserRepository } from "src/database/repository/user.repository";
import { User } from "../model/user.model";

@Resolver(() => User)
export class UserResolver {
    constructor(private readonly userRepository: UserRepository) { }

    @Query(() => User, { name: 'user' })
    async getUser(@Args('id', { type: () => Int }) id: number) {
        const user = await this.userRepository.findById(id);

        if (!user) {
            throw new Error("User not found");
        }

        return user;
    }

    @Query(() => [User], { name: 'users' })
    async getUsers() {
        return await this.userRepository.findAll();
    }

    @Mutation(() => User)
    async createUser(@Args('name') name: string) {
        return await this.userRepository.save({ name, posts: [] });
    }
}