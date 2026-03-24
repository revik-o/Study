package com.sql.service

import com.sql.dao.request.RegisterRequestDTO
import com.sql.database.model.UserModel
import com.sql.database.repository.RoleRepository
import com.sql.database.repository.UserRepository
import com.sql.utils.EncryptionUtils.hash
import com.sql.utils.inject

class UserService {

    val _userRepository: UserRepository by inject()
    val _roleRepository: RoleRepository by inject()

    fun register(request: RegisterRequestDTO) {
        _userRepository.save(UserModel(
            email = request.email,
            username = request.firstName,
            userLastName = request.lastName,
            role = _roleRepository.getRegularUserRole()!!,
            passwordHash = hash(request.passwordVerification),
        ))
    }
}