package info.akahori_s.keycloakgw.presentation.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import info.akahori_s.keycloak.utility.model.Token
import spray.json._

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{

  implicit object TokenJsonFormat extends JsonFormat[Token] {
    override def read(json: JsValue): Token = {
      json match {
        case JsString(str) => Token(str)
        case _ => throw new Exception("token変換エラー")
      }
    }

    override def write(obj: Token): JsValue = JsString(obj.value)
  }

  implicit val tokenResponseJsonFormat: RootJsonFormat[TokenResponse] = jsonFormat1(TokenResponse)
  implicit val introspectRequestJsonFormat: RootJsonFormat[IntrospectRequest] = jsonFormat1(IntrospectRequest)
  implicit val tokenRequestJsonFormat: RootJsonFormat[TokenRequest] = jsonFormat2(TokenRequest)
  implicit val createRequestJsonFormat: RootJsonFormat[CreateRequest] = jsonFormat5(CreateRequest)
  implicit val introspectResponseJsonFormat: RootJsonFormat[IntrospectResponse] = jsonFormat1(IntrospectResponse)
}