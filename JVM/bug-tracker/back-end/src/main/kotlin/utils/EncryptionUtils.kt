package com.sql.utils

import at.favre.lib.crypto.bcrypt.BCrypt

object EncryptionUtils {

    fun hash(password: String): String =
        BCrypt.withDefaults().hashToString(15, password.toCharArray())

    fun verify(password: String, hash: String): Boolean =
        BCrypt.verifyer().verify(password.toCharArray(), hash).verified
}