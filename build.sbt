import BuildSettings.{buildSettings, _}
import Dependencies._
import spray.revolver.RevolverPlugin.autoImport.Revolver
import sbtassembly.AssemblyPlugin.autoImport._


val testDeps = Seq(scalaTest)

val akkaDeps = testDeps ++ Seq(akkaHttp)

val slickDeps = Seq(slick, slickPG, slickPGGeom, slickHikariCP, jts, scale)

val deps = akkaDeps ++ Seq(akkaHttp, akkaHttpTestkit, akkaHttpJson, logback, scalaLogging) ++ slickDeps

lazy val hmdageo = (project in file("."))
  .settings(buildSettings: _*)
  .aggregate(api, client)

lazy val api = (project in file("api"))
  .configs( IntegrationTest )
  .settings(buildSettings: _*)
  .settings(
    Revolver.settings ++
      Seq(
        name := s"hmda-geo-${name.value}",
        assemblyJarName in assembly := "hmda-geo-api.jar",
        libraryDependencies ++= deps,
        resolvers ++= repos
      )
  )

lazy val client = (project in file("client"))
  .configs( IntegrationTest )
  .settings(buildSettings: _*)
  .settings(
    Seq(
      name := s"hmda-geo-${name.value}",
      assemblyJarName in assembly := "hmda-geo-client.jar",
      libraryDependencies ++= akkaDeps ++ Seq(akkaHttp, akkaHttpTestkit, akkaHttpJson, logback, jts, scale),
      resolvers ++= repos
    )
  )



