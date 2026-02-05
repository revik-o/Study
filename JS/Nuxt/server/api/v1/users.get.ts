import { getAllUsers } from "~~/server/service/db/repo/user.repository"

export default defineEventHandler(async (event) => {
    const users = await getAllUsers(event);

    return { status: 'success', count: users.length, users };
})