package info.akahori_s.keycloakgw.presentation

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.directives.Credentials
import info.akahori_s.keycloak.utility.KeycloakAuthzGateway
import akka.http.scaladsl.server.Directives.authenticateOAuth2

trait KeycloakAuthenticate {
  val keycloakAuthzGateway: KeycloakAuthzGateway

  def authorizeToken(realm: String): Directive1[String] =
    authenticateOAuth2(realm, authenticate)

  def authenticate(
      credentials: Credentials
  ): Option[String] = {
    credentials match {
      case Credentials.Provided(jwt) =>
        keycloakAuthzGateway.introspect(jwt)
      case _ => None
    }
  }
}
