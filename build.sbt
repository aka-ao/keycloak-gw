lazy val `keycloak-gw` = (project in file("."))
  .aggregate(
    `entrypoint`, `presentation`, `gateway`
  )
  .dependsOn(
    `entrypoint`, `presentation`, `gateway`
  )
  .settings(
    inThisBuild(
      List(
        name := "keycloak-gw",
        version := "0.1",
        scalaVersion := "2.12.4"
      )
    )
  )

lazy val `presentation` = (project in file("keycloak-gw/presentation"))
  .dependsOn(`utility`, `gateway`)
  .settings(
  name := "presentation",
  libraryDependencies ++= Seq(
    "org.wvlet.airframe" %% "airframe" % "19.5.0",
    "com.typesafe.akka" %% "akka-http" % "10.1.0",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.0",
    "com.typesafe.akka" %% "akka-stream" % "2.5.11",
  )
)

lazy val `gateway` = (project in file("keycloak-gw/gateway"))
  .dependsOn(`utility`)
  .settings(
    name := "gateway",
    libraryDependencies ++= Seq(
      "org.keycloak" % "keycloak-authz-client" % "11.0.1",
      "org.keycloak" % "keycloak-admin-client" % "11.0.1",
      "com.auth0" % "java-jwt" % "3.10.3"
    )
  )

lazy val `entrypoint` = (project in file("keycloak-gw/entrypoint"))
  .dependsOn(`utility`, `presentation`, `gateway`)
  .settings(
  name := "entrypoint",
  libraryDependencies ++= Seq(
    "org.wvlet.airframe" %% "airframe" % "19.5.0",
    "com.typesafe.akka" %% "akka-stream" % "2.5.11",
  )
)

lazy val `utility` = (project in file("keycloak-gw/utility"))
  .settings(
  name := "utility",
  libraryDependencies ++= Seq(
    "com.typesafe" % "config" % "1.4.0"
  )
)