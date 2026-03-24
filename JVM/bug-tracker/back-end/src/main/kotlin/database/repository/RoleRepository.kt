package com.sql.database.repository

import com.sql.database.DatabaseConfiguration
import com.sql.database.model.RoleModel
import com.sql.database.model.RoleModel.Companion.DefaultRoleType
import com.sql.database.model.RoleModel.Companion.DefaultRoleType.USER
import com.sql.utils.inject
import io.ktor.util.logging.Logger
import java.sql.PreparedStatement

class RoleRepository : RepositoryI<Int, RoleModel> {

    private val _databaseConfiguration: DatabaseConfiguration by inject()
    private val _log: Logger by inject()

    init {
        if (count() <= 0L) {
            saveAll(DefaultRoleType.entries.map { RoleModel(name = it.name) })
        }
    }

    companion object {
        val ROLE_TABLE_NAME = "roles"

        private val GET_ROLE_BY_ID_SQL = """
            SELECT r_table.*
            FROM $ROLE_TABLE_NAME r_table
            WHERE r_table.id = ?;
        """.trimIndent()
        private val GET_ROLE_BY_NAME_SQL = """
            SELECT r_table.*
            FROM $ROLE_TABLE_NAME r_table
            WHERE r_table.role_name = ?;
        """.trimIndent()
        private val COUNT_ROLES_SQL = """
            SELECT COUNT(*) FROM $ROLE_TABLE_NAME;
        """.trimIndent()
        private val DELETE_ROLE_BY_ID_SQL = """
            DELETE FROM $ROLE_TABLE_NAME r_table WHERE r_table.id = ?;
        """.trimIndent()
        private val INSERT_ROLE_MODEL_SQL = """
            INSERT INTO $ROLE_TABLE_NAME (%s) VALUES (%s)
        """.trimIndent()
    }

    private fun initNewEntity(prepareStatement: PreparedStatement, entity: RoleModel) {
        prepareStatement.setString(1, entity.name)
    }

    override fun getById(id: Int): RoleModel? = _databaseConfiguration.prepareQuery(GET_ROLE_BY_ID_SQL) {
        it.setInt(1, id)
        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                RoleModel(
                    id = resultSet.getInt("id"),
                    name = resultSet.getString("role_name")
                )
            } else {
                null
            }
        }
    }

    fun getByRoleName(name: String): RoleModel? = _databaseConfiguration.prepareQuery(GET_ROLE_BY_NAME_SQL) {
        it.setString(1, name)

        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                RoleModel(
                    id = resultSet.getInt("id"),
                    name = resultSet.getString("role_name")
                )
            } else {
                null
            }
        }
    }

    fun getRegularUserRole(): RoleModel? = getByRoleName(USER.name)

    override fun count(): Long = _databaseConfiguration.prepareQuery(COUNT_ROLES_SQL) {
        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                resultSet.getLong(1)
            } else {
                -1L
            }
        }
    }!!

    override fun save(entity: RoleModel): RoleModel? = _databaseConfiguration.prepareQuery(
        "$INSERT_ROLE_MODEL_SQL RETURNING *;".format("role_name", "?")
    ) {
        initNewEntity(it, entity)

        it.executeQuery().use { resultSet ->
            if (resultSet.next()) {
                _log.info("Saved new role: ${entity.name}")

                RoleModel(
                    id = resultSet.getInt("id"),
                    name = resultSet.getString("role_name")
                )
            } else {
                null
            }
        }
    }

    override fun saveAll(entities: Collection<RoleModel>) =
        _databaseConfiguration.prepareRollbackableTransaction { transaction ->
            transaction.autoCommit = false

            transaction.prepareStatement(INSERT_ROLE_MODEL_SQL.format("role_name", "?")).use { statement ->
                for (role in entities) {
                    initNewEntity(statement, role)
                    statement.addBatch()
                }

                statement.executeBatch()
                transaction.commit()

                _log.info("Saved new roles: ${entities.map { it.name }}")
            }
        }

    override fun delete(id: Int): RoleModel? {
        val entity = getById(id)

        if (entity != null) {
            _databaseConfiguration.prepareQuery(DELETE_ROLE_BY_ID_SQL) {
                it.setInt(1, id)
                it.executeUpdate()
            }
        }

        return entity
    }
}