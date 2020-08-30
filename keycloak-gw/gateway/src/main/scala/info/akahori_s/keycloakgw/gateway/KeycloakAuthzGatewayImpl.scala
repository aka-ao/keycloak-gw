package info.akahori_s.keycloakgw.gateway

import com.auth0.jwt.JWT
import com.typesafe.config.Config
import info.akahori_s.keycloak.utility.KeycloakAuthzGateway
import info.akahori_s.keycloak.utility.model.Token
import org.keycloak.authorization.client.{AuthzClient, Configuration}

case class KeycloakAuthzGatewayImpl(config: Config) extends KeycloakAuthzGateway {
  val secret = config.getString("info.akahori_s.keycloacgw.gateway.keycloak.secret")

  import collection.JavaConverters._
  def getAccessToken(userName: String, password: String) = {
    val configuration: Configuration =
      new Configuration(
        "http://localhost:8080/auth",
        "sample_service",
        "sample_application",
        Map[String, Object]("secret" -> secret).asJava,
        null)
    val authzClient = AuthzClient.create(configuration)
    Token(authzClient.obtainAccessToken(userName, password).getToken)
  }

  def introspect(token: String): String = {
    val configuration: Configuration =
      new Configuration(
        "http://localhost:8080/auth",
        "sample_service",
        "sample_api_gateway",
        Map[String, Object]("secret" -> "f3941464-9d8e-40b0-a145-272f8646d947").asJava,
        null)
    val authzClient = AuthzClient.create(configuration)
    val response = authzClient.protection(token).introspectRequestingPartyToken(token)
    println(response.getActive)
    val jwt = JWT.decode(token)

    jwt.getSubject
  }

}
