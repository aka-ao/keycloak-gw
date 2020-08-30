package info.akahori_s.keycloakgw.presentation.model

final case class CreateRequest(
    firstName: String,
    lastName: String,
    email: String,
    userName: String,
    password: String
)
