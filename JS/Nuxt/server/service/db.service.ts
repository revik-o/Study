import mongoose from "mongoose"
import getEnv from "./env.service"

export async function connectToDB(ctx: any) {
    const env = getEnv(ctx)

    if (mongoose.connection.readyState === 0) {
        const uri = `mongodb://${env.dbUser}:${env.dbPassword}@${env.dbHost}:${env.dbPort}/${env.dbName}`
        console.log('Connecting to MongoDB at', uri)
        await mongoose.connect(uri)
        console.log('Connected to MongoDB')
    }
}