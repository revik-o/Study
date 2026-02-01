import { Column, Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { User } from "./user.entity";

@Entity()
export class Post {
    @PrimaryGeneratedColumn()
    id?: number;
    @Column()
    content: string;
    @Column()
    authorId: number;
    @ManyToOne(() => User, (user) => user.posts)
    @JoinColumn({ name: 'authorId' })
    author: User;
}