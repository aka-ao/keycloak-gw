package info.akahori_s.keycloakgw.entrypoint

import akka.actor.ActorSystem
import com.typesafe.config.Config
import wvlet.airframe._
import _root_.info.akahori_s.keycloak.utility.{
  KeycloakAdminGateway,
  KeycloakAuthzGateway
}
import _root_.info.akahori_s.keycloakgw.gateway.{
  KeycloakAdminGatewayImpl,
  KeycloakAuthzGatewayImpl
}

object Main extends App {

  val design = newDesign
    .bind[ActorSystem]
    .toInstance(ActorSystem("Keycloak-GW"))
    .bind[Config]
    .toProvider((system: ActorSystem) => system.settings.config)
    .bind[KeycloakAdminGateway]
    .to[KeycloakAdminGatewayImpl]
    .bind[KeycloakAuthzGateway]
    .to[KeycloakAuthzGatewayImpl]

  val session = design.newSessionBuilder.noShutdownHook.create
  session.start

  session.build[KeycloakGw].startServer()

}
