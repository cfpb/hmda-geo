import sbt._
import sbt.Keys._
import spray.revolver.RevolverPlugin.autoImport.Revolver
import sbtassembly.AssemblyPlugin.autoImport._

object BuildSettings {
  val buildOrganization = "cfpb"
  val buildVersion      = "1.0.0"
  val buildScalaVersion = "2.12.3"

  val buildSettings = Defaults.coreDefaultSettings ++
    Defaults.itSettings ++
    Seq(
      organization  := buildOrganization,
      version       := buildVersion,
      scalaVersion  := buildScalaVersion,
      scalacOptions := Seq("-deprecation", "-unchecked", "-feature")
    )
}

object HMDAGeoBuild extends Build {
  import Dependencies._
  import BuildSettings._

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
  

}
