package pl.put.poznan.pz.doggo.infrastructure.exceptions

class UserNotFoundException(email: String) :
        RuntimeException("User with email: $email not exists.")