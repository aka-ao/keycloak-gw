package info.akahori_s.keycloakgw.gateway

import com.typesafe.config.Config

trait Credential {
  val config: Config

  def password(userName: String) =
    config.getString(
      s"info.akahori_s.keycloacgw.gateway.keycloak.$userName.password"
    )

  def secret(clientId: String) =
    config.getString(
      s"info.akahori_s.keycloacgw.gateway.keycloak.$clientId.secret"
    )

}
