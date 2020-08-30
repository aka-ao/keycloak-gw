package info.akahori_s.keycloakgw.presentation.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import info.akahori_s.keycloak.utility.model.Token
import spray.json.{DefaultJsonProtocol, JsString, JsValue, RootJsonFormat}

final case class TokenResponse(token: Token)

