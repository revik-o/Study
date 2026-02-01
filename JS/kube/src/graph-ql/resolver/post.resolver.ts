import { Args, Int, Mutation, Parent, Query, ResolveField, Resolver } from "@nestjs/graphql";
import { UserRepository } from "src/database/repository/user.repository";
import { Post } from "../model/post.model";
import { PostRepository } from "src/database/repository/post.repository";
import { User } from "../model/user.model";
import { Post as PostEntity } from "../../database/entity/post.entity";


@Resolver(() => Post)
export class PostResolver {
    constructor(private readonly userRepository: UserRepository, private readonly postRepository: PostRepository) { }

    @Query(() => [Post], { name: 'posts' })
    async getPosts(@Args('id', { type: () => Int }) id: number) {
        const user = await this.userRepository.findById(id);

        if (!user) {
            throw new Error("User not found");
        }

        return await this.postRepository.findByUserId(user.id!);
    }

    @Mutation(() => Post)
    async createPost(@Args('name') name: string, @Args('content') content: string) {
        const user = await this.userRepository.findBy({ name });

        if (!user) {
            throw new Error("User not found");
        }

        return await this.postRepository.save({ content, authorId: user.id!, author: user });
    }

    @ResolveField(() => User)
    async author(@Parent() post: Post) {
        return await this.userRepository.findById((post as PostEntity).authorId);
    }
}