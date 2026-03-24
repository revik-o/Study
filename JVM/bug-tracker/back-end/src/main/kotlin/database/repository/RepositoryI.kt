package com.sql.database.repository

interface RepositoryI<ID, T> {

    fun getById(id : ID) : T?

    fun count() : Long

    fun save(entity: T): T?

    fun saveAll(entities : Collection<T>)

    fun delete(id : ID) : T?
}