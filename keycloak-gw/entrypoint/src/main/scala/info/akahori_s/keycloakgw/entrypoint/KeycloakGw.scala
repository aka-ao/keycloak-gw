package info.akahori_s.keycloakgw.entrypoint

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import info.akahori_s.keycloakgw.presentation.Route

class KeycloakGw(implicit val system: ActorSystem, route: Route) {
  import system.dispatcher
  private implicit val materializer: Materializer = ActorMaterializer()

  def startServer() = {
    Http().bindAndHandle(route.route, "0.0.0.0", 18080)
  }
}
