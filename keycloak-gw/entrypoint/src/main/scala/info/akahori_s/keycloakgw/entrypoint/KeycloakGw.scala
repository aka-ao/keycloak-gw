package info.akahori_s.keycloakgw.entrypoint

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import info.akahori_s.keycloakgw.presentation.RootRoute

class KeycloakGw(implicit val system: ActorSystem, route: RootRoute) {
  import system.dispatcher
  private implicit val materializer: Materializer = ActorMaterializer()

  def startServer() = {
    Http().bindAndHandle(route.route, "0.0.0.0", 18080)
  }
}
