import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import spray.revolver.RevolverPlugin._

object BuildSettings {
  val buildOrganization = "cfpb"
  val buildVersion      = "1.0.0"
  val buildScalaVersion = "2.11.6"

  val buildSettings = Defaults.coreDefaultSettings ++
    scalariformSettings ++
    Seq(
      organization  := buildOrganization,
      version       := buildVersion,
      scalaVersion  := buildScalaVersion,
      scalacOptions := Seq("-deprecation", "-unchecked", "-feature")
    )
}

object PipBuild extends Build {
  import Dependencies._
  import BuildSettings._

  val testDeps = Seq(scalaTest)

  val akkaDeps = testDeps ++ Seq(akkaHttp)

  val akkaHttpDeps = akkaDeps ++ Seq(akkaHttp, akkaHttpTestkit, akkaHttpJson, logback, scalaLogging)

  lazy val microservice = Project(
    "microservice-template",
    file("."),
    settings = buildSettings ++ Revolver.settings ++ Seq(libraryDependencies ++= akkaHttpDeps, resolvers ++= repos)
  )

}
