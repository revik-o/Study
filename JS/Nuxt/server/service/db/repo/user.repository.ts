import mongoose from "mongoose";
import { connectToDB } from "../../db.service";

const userSchema = new mongoose.Schema({
    name: String,
    timestamp: Date
});

export const UserModel = mongoose.models.User || mongoose.model('User', userSchema);

async function initUsers(ctx: any) {
    await connectToDB(ctx)

    if (await UserModel.countDocuments() === 0) {
        await UserModel.insertMany([
            { name: 'Test User 1', timestamp: new Date() },
            { name: 'Test User 2', timestamp: new Date() },
            { name: 'Test User 3', timestamp: new Date() },
        ])
    }
}

export async function getAllUsers(ctx: any) {
    await initUsers(ctx)
    return await UserModel.find()
}
