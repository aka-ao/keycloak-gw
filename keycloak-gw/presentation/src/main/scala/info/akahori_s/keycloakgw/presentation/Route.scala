package info.akahori_s.keycloakgw.presentation

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes.{Created, OK}
import akka.http.scaladsl.server.Directives
import info.akahori_s.keycloak.utility.{KeycloakAdminGateway, KeycloakAuthzGateway}
import info.akahori_s.keycloakgw.presentation.model.{CreateRequest, IntrospectResponse, JsonSupport, TokenRequest, TokenResponse}

class Route(keycloakAdminGateway: KeycloakAdminGateway, keycloakAuthzGateway: KeycloakAuthzGateway)(implicit system: ActorSystem) extends Directives with JsonSupport  {
  def route =concat(
    pathPrefix("create") {
      concat(
        pathEnd{
          post {
            entity(as[CreateRequest]) { req =>
              keycloakAdminGateway.createUser(
                req.firstName,
                req.lastName,
                req.email,
                req.userName,
                req.password
              )
              complete(
                Created
              )
            }
          }
        }
      )
    },
    pathPrefix("token") {
      concat(
        pathEnd{
          post {
            entity(as[TokenRequest]) { req =>
              val token = keycloakAuthzGateway.getAccessToken(req.userName, req.password)
              complete(OK -> TokenResponse(token))
            }
          }
        }
      )
    },
    pathPrefix("introspect"/".+".r) { token =>
      concat(
        pathEnd {
          get {
            val subject = keycloakAuthzGateway.introspect(token)
            complete(
              OK -> IntrospectResponse(
                subject
              )
            )
          }
        }
      )
    }
  )
}