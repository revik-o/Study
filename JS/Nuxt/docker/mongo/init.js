db = db.getSiblingDB('admin');
db.auth(
    process.env.MONGO_INITDB_ROOT_USERNAME,
    process.env.MONGO_INITDB_ROOT_PASSWORD,
);

const appDbName = process.env.MONGO_INITDB_APP_DB_NAME;
const appDbPass = process.env.MONGO_INITDB_APP_DB_PASS;
const appDbUser = process.env.MONGO_INITDB_APP_DB_USER;

if (!appDbName || !appDbPass || !appDbUser) {
    throw new Error('Missing environment variables for app database');
}

db = db.getSiblingDB(appDbName);
db.createUser({
    user: appDbUser,
    pwd: appDbPass,
    roles: [
        { role: 'readWrite', db: appDbName },
    ],
});

db.createCollection('init_app_collection');

print('init.js status: DONE');
