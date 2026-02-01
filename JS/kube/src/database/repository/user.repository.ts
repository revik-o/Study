import { Injectable } from "@nestjs/common";
import { InjectRepository } from "@nestjs/typeorm";
import { User } from "../entity/user.entity";
import { FindOptionsWhere, Repository } from "typeorm";
import { Nullable } from "src/type";

@Injectable()
export class UserRepository {

    public constructor(@InjectRepository(User) private readonly model: Repository<User>) {
        this.init();
    }

    private async init(): Promise<void> {
        const size = await this.count();
        if (size === 0) await this.save({ name: "Init User", posts: [] });
    }

    public async save(entity: User): Promise<Nullable<User>> {
        return await this.model.save(entity);
    }

    public async update(entity: User): Promise<void> {
        await this.model.update(entity.id!, entity);
    }

    public async delete(id: number): Promise<void> {
        await this.model.delete(id);
    }

    public async findById(id: number): Promise<Nullable<User>> {
        return await this.model.findOne({ where: { id } });
    }

    public async findAll(): Promise<User[]> {
        return await this.model.find();
    }

    public async findBy(where: FindOptionsWhere<User>): Promise<Nullable<User>> {
        return await this.model.findOneBy(where);
    }

    public async count(): Promise<number> {
        return await this.model.count();
    }
}