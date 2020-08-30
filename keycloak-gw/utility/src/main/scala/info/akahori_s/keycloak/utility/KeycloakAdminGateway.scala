package info.akahori_s.keycloak.utility

trait KeycloakAdminGateway {
  def createUser(
      firstName: String,
      lastName: String,
      email: String,
      userName: String,
      password: String
  ): Unit
}
