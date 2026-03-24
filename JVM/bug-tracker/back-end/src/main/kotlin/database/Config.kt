package com.sql.database

import com.sql.database.repository.RoleRepository
import com.sql.database.repository.UserRepository
import com.sql.utils.DependencyInjectionContainer.register
import com.sql.utils.inject
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.util.logging.Logger
import org.flywaydb.core.Flyway
import java.sql.Connection
import java.sql.PreparedStatement
import javax.sql.DataSource

enum class Databases(name: String) {
    PostgreSQL("postgresql");

    val databaseStrName = name
}

/**
 * Makes a connection to a Postgres database.
 *
 * In order to connect to your running Postgres process,
 * please specify the following parameters in your configuration file:
 * - postgres.url -- Url of your running database process.
 * - postgres.user -- Username for database connection
 * - postgres.password -- Password for database connection
 *
 * If you don't have a database process running yet, you may need to [download]((https://www.postgresql.org/download/))
 * and install Postgres and follow the instructions [here](https://postgresapp.com/).
 * Then, you would be able to edit your url,  which is usually "jdbc:postgresql://host:port/database", as well as
 * user and password values.
 *
 *
 * @param embedded -- if [true] defaults to an embedded database for tests that runs locally in the same process.
 * In this case you don't have to provide any parameters in configuration file, and you don't have to run a process.
 *
 * @return [Connection] that represent connection to the database. Please, don't forget to close this connection when
 * your application shuts down by calling [Connection.close]
 * */
fun Application.connectToDb(embedded: Boolean): DataSource {
//    if (embedded && !environment.config.keys().any { it == "postgres.url" }) {
//        log.info("Using embedded H2 database for testing; replace this flag to use postgres")
//        val url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
//        val user = "root"
//        val password = ""
//        Class.forName("org.h2.Driver")
//        migration(Databases.H2, url, user, password)
//        return initDatabaseConnectionPool(url, user, password, "org.h2.Driver")
//    } else {
        val url = environment.config.property("postgres.url").getString()
        log.info("Connecting to postgres database at $url")
        val user = environment.config.property("postgres.user").getString()
        val password = environment.config.property("postgres.password").getString()
        Class.forName("org.postgresql.Driver")
        migration(Databases.PostgreSQL, url, user, password)
        return initDatabaseConnectionPool(url, user, password, "org.postgresql.Driver")
//    }
}

fun migration(
    database: Databases,
    url: String,
    user: String,
    password: String
) {
    Flyway.configure().dataSource(url, user, password)
        .locations("classpath:/database/${database.databaseStrName}/migration")
        .load().also {
            it.migrate()
        }
}

fun initDatabaseConnectionPool(
    url: String,
    user: String,
    password: String,
    driver: String
): DataSource {
    val config = HikariConfig()
    config.jdbcUrl = url
    config.username = user
    config.password = password
    config.driverClassName = driver
    config.maximumPoolSize = 100
    config.minimumIdle = 50
    config.maxLifetime = 300
    config.connectionTimeout = 1000
    config.validationTimeout = 1000
    config.isAutoCommit = false

    return HikariDataSource(config)
}

class DatabaseConfiguration(app: Application) {

    private val _dataSource: DataSource = app.connectToDb(embedded = true)
    private val _log: Logger by inject()

    fun createTransaction(): Connection = _dataSource.connection

    fun prepareRollbackableTransaction(than: (Connection) -> Unit) = createTransaction().use {
        try {
            than(it)
        } catch (exception: Exception) {
            _log.error("transaction exception: ", exception)
            it.rollback()
        } finally {
            it.autoCommit = true
        }
    }

    fun <T> prepareRollbackableTransaction(than: (Connection) -> T?): T? = createTransaction().use {
        try {
            than(it)
        } catch (exception: Exception) {
            _log.error("transaction exception: ", exception)
            it.rollback()
            null
        } finally {
            it.autoCommit = true
        }
    }

    fun <T> prepareQuery(sql: String, than: (PreparedStatement) -> T?): T? = createTransaction().use { connection ->
        connection.autoCommit = true
        _log.info("Preparing SQL:\n$sql")
        connection.prepareStatement(sql).use { than(it) }
    }
}

fun Application.configureDatabase() {
    register(DatabaseConfiguration(this))
    register(RoleRepository())
    register(UserRepository())
}
