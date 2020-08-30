package info.akahori_s.keycloakgw.gateway

import com.auth0.jwt.JWT
import com.typesafe.config.Config
import info.akahori_s.keycloak.utility.KeycloakAuthzGateway
import info.akahori_s.keycloak.utility.model.Token
import org.keycloak.authorization.client.{AuthzClient, Configuration}

case class KeycloakAuthzGatewayImpl(config: Config)
    extends KeycloakAuthzGateway
    with Credential {

  import collection.JavaConverters._
  def getAccessToken(userName: String, password: String) = {
    val clientId = "sample_application"
    val configuration: Configuration =
      new Configuration(
        "http://localhost:8080/auth",
        "sample_service",
        clientId,
        Map[String, Object]("secret" -> secret(clientId)).asJava,
        null
      )
    val authzClient = AuthzClient.create(configuration)
    Token(authzClient.obtainAccessToken(userName, password).getToken)
  }

  def introspect(token: String): String = {
    val clientId = "sample_api_gateway"
    val configuration: Configuration =
      new Configuration(
        "http://localhost:8080/auth",
        "sample_service",
        clientId,
        Map[String, Object](
          "secret" -> secret(clientId)
        ).asJava,
        null
      )
    val authzClient = AuthzClient.create(configuration)
    val response =
      authzClient.protection(token).introspectRequestingPartyToken(token)
    println(response.getActive)
    val jwt = JWT.decode(token)

    jwt.getSubject
  }

}
