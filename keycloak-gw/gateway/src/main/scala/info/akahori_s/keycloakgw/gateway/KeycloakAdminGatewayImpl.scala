package info.akahori_s.keycloakgw.gateway

import com.typesafe.config.Config
import info.akahori_s.keycloak.utility.KeycloakAdminGateway
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.{
  CredentialRepresentation,
  UserRepresentation
}

class KeycloakAdminGatewayImpl(config: Config) extends KeycloakAdminGateway {
  import collection.JavaConverters._
  val secret =
    config.getString("info.akahori_s.keycloacgw.gateway.keycloak.secret")

  val realm = "master"

  val kc = Keycloak.getInstance(
    "http://localhost:8080/auth",
    realm,
    "admin",
    "Pa55w0rd",
    "admin-cli"
  )

  def createUser(
      firstName: String,
      lastName: String,
      email: String,
      userName: String,
      password: String
  ): Unit = {
    val user = new UserRepresentation()
    user.setFirstName(firstName)
    user.setLastName(lastName)
    user.setEmail(email)
    user.setUsername(userName)
    kc.realm("sample_service").users().create(user)

    val credential = new CredentialRepresentation()
    credential.setType(CredentialRepresentation.PASSWORD)
    credential.setValue(password)
    user.setCredentials(List(credential).asJava)
    // userNameはユニーク
    val res = kc.realm("sample_service").users().search(userName)

    val id = res.get(0).getId

    println(s"### id: ${id}")
    user.setEnabled(true)

    kc.realm("sample_service").users().get(id).update(user)
  }
}
