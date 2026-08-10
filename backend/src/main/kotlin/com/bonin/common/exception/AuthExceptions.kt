package com.bonin.common.exception

class EmailAlreadyExistsException :
    RuntimeException("Email is already registered")

class InvalidCredentialsException :
    RuntimeException("Invalid email or password")

class UserNotFoundException :
    RuntimeException("User not found")