package info.akahori_s.keycloak.utility

import info.akahori_s.keycloak.utility.model.Token

trait KeycloakAuthzGateway {
  def getAccessToken(userName: String, password: String): Token
  def introspect(token: String): Option[String]
}
