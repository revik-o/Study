package com.sql.database.repository

import com.sql.database.DatabaseConfiguration
import com.sql.database.model.RoleModel
import com.sql.database.model.RoleModel.Companion.DefaultRoleType.ADMIN
import com.sql.database.model.UserModel
import com.sql.database.repository.RoleRepository.Companion.ROLE_TABLE_NAME
import com.sql.utils.EncryptionUtils.verify
import com.sql.utils.EncryptionUtils.hash
import com.sql.utils.inject
import io.ktor.util.logging.*
import java.sql.PreparedStatement
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

class UserRepository : RepositoryI<UUID, UserModel> {

    private val _databaseConfiguration: DatabaseConfiguration by inject()
    private val _roleRepository: RoleRepository by inject()
    private val _log: Logger by inject()

    init {
        if (count() <= 0L) {
            val adminUser = save(
                UserModel(
                    email = "admin@admin.com",
                    username = "admin",
                    userLastName = "admin",
                    passwordHash = hash("admin"),
                    role = _roleRepository.getByRoleName(ADMIN.name)
                        ?: throw IllegalStateException("ADMIN role is missing")
                )
            )!!

            assert(verify("admin", adminUser.passwordHash))
        }
    }

    companion object {
        val USER_TABLE_NAME = "users"

        private val GET_USER_BY_ID_SQL = """
            SELECT u_table.*, r_table.id as r_id, r_table.role_name as r_name
            FROM $USER_TABLE_NAME u_table
            INNER JOIN $ROLE_TABLE_NAME r_table ON u_table.id = r_id
            WHERE u_table.id = ?;
        """.trimIndent()
        private val GET_USER_BY_EMAIL_SQL = """
            SELECT u_table.*, r_table.id as r_id, r_table.role_name as r_name
            FROM $USER_TABLE_NAME u_table
            INNER JOIN $ROLE_TABLE_NAME r_table ON u_table.id = r_id
            WHERE u_table.email = ?;
        """.trimIndent()
        private val COUNT_USERS_SQL = """
            SELECT COUNT(*) FROM $USER_TABLE_NAME;
        """.trimIndent()
        private val DELETE_USER_BY_ID_SQL = """
            DELETE FROM $USER_TABLE_NAME u_table WHERE u_table.id = ?;
        """.trimIndent()
        private val INSERT_USER_MODEL_SQL = """
            INSERT INTO $USER_TABLE_NAME (%s) VALUES (%s)
        """.trimIndent()
    }

    private fun initNewEntity(prepareStatement: PreparedStatement, entity: UserModel) {
        prepareStatement.setString(1, entity.email)
        prepareStatement.setString(2, entity.username)
        prepareStatement.setString(3, entity.userLastName)
        prepareStatement.setString(4, entity.passwordHash)
        prepareStatement.setBoolean(5, entity.deactivated)
        prepareStatement.setInt(6, entity.role.id!!)
    }

    override fun getById(id: UUID): UserModel? = _databaseConfiguration.prepareQuery(GET_USER_BY_ID_SQL) {
        it.setObject(1, id)
        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                UserModel(
                    id = resultSet.getObject("id", UUID::class.java),
                    email = resultSet.getString("email"),
                    username = resultSet.getString("username"),
                    userLastName = resultSet.getString("user_last_name"),
                    passwordHash = resultSet.getString("password_hash"),
                    deactivated = resultSet.getBoolean("deactivated"),
                    registrationDate = ZonedDateTime.ofInstant(resultSet.getTimestamp("registration_date").toInstant(), ZoneOffset.systemDefault()),
                    role = RoleModel(
                        id = resultSet.getInt("r_id"),
                        name = resultSet.getString("r_name")
                    )
                )
            } else {
                null
            }
        }
    }

    fun getByEmail(email: String): UserModel? = _databaseConfiguration.prepareQuery(GET_USER_BY_EMAIL_SQL) {
        it.setString(1, email)
        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                UserModel(
                    id = resultSet.getObject("id", UUID::class.java),
                    email = resultSet.getString("email"),
                    username = resultSet.getString("username"),
                    userLastName = resultSet.getString("user_last_name"),
                    passwordHash = resultSet.getString("password_hash"),
                    deactivated = resultSet.getBoolean("deactivated"),
                    registrationDate = ZonedDateTime.ofInstant(resultSet.getTimestamp("registration_date").toInstant(), ZoneOffset.systemDefault()),
                    role = RoleModel(
                        id = resultSet.getInt("r_id"),
                        name = resultSet.getString("r_name")
                    )
                )
            } else {
                null
            }
        }
    }

    override fun count(): Long = _databaseConfiguration.prepareQuery(COUNT_USERS_SQL) {
        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                resultSet.getLong(1)
            } else {
                -1
            }
        }
    }!!

    override fun save(entity: UserModel): UserModel? = _databaseConfiguration.prepareQuery(
        "$INSERT_USER_MODEL_SQL RETURNING *;".format(
            "email, username, user_last_name, password_hash, deactivated, role_id",
            "?, ?, ?, ?, ?, ?"
        )
    ) {
        initNewEntity(it, entity)

        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                _log.info("Saved new user: ${entity.email}")

                val roleId = resultSet.getInt("role_id")
                val role = _roleRepository.getById(roleId)

                UserModel(
                    id = resultSet.getObject("id", UUID::class.java),
                    email = resultSet.getString("email"),
                    username = resultSet.getString("username"),
                    userLastName = resultSet.getString("user_last_name"),
                    passwordHash = resultSet.getString("password_hash"),
                    deactivated = resultSet.getBoolean("deactivated"),
                    registrationDate = ZonedDateTime.ofInstant(resultSet.getTimestamp("registration_date").toInstant(), ZoneOffset.systemDefault()),
                    role = role!!
                )
            } else {
                null
            }
        }
    }

    override fun saveAll(entities: Collection<UserModel>) =
        _databaseConfiguration.prepareRollbackableTransaction { transaction ->
            transaction.autoCommit = false

            transaction.prepareStatement(
                INSERT_USER_MODEL_SQL.format(
                    "email, username, user_last_name, password_hash, deactivated, role_id",
                    "?, ?, ?, ?, ?, ?"
                )
            ).use { statement ->
                for (user in entities) {
                    initNewEntity(statement, user)
                    statement.addBatch()
                }

                statement.executeBatch()

                transaction.commit()
                _log.info("Saved new users: ${entities.map { it.email }}")
            }
        }

    override fun delete(id: UUID): UserModel? {
        val entity = getById(id)

        if (entity != null) {
            _databaseConfiguration.prepareQuery(DELETE_USER_BY_ID_SQL) {
                it.setObject(1, id)
                it.executeUpdate()
            }
        }

        return entity
    }
}