package com.bonin.user

import com.bonin.common.exception.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun getById(id: UUID): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException() }

        return UserResponse.from(user)
    }
}