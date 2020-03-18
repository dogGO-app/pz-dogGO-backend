package pl.put.poznan.pz.doggo.infrastructure.exceptions

class UserAlreadyExistsException(email: String) :
        RuntimeException("User with email: $email already exists.")