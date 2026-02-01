import { Injectable } from "@nestjs/common";
import { InjectRepository } from "@nestjs/typeorm";
import { FindOptionsWhere, Repository } from "typeorm";
import { Nullable } from "src/type";
import { Post } from "../entity/post.entity";

@Injectable()
export class PostRepository {

    public constructor(@InjectRepository(Post) private readonly model: Repository<Post>) { }

    public async save(entity: Post): Promise<Nullable<Post>> {
        return await this.model.save(entity);
    }

    public async update(entity: Post): Promise<void> {
        await this.model.update(entity.id!, entity);
    }

    public async delete(id: number): Promise<void> {
        await this.model.delete(id);
    }

    public async findByUserId(id: number): Promise<Nullable<Post[]>> {
        return await this.model.find({ where: { authorId: id } });
    }

    public async findAll(): Promise<Post[]> {
        return await this.model.find();
    }

    public async findBy(where: FindOptionsWhere<Post>): Promise<Nullable<Post>> {
        return await this.model.findOneBy(where);
    }
}