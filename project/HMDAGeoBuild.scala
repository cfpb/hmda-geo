import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import spray.revolver.RevolverPlugin._
import sbtassembly.AssemblyPlugin.autoImport._

object BuildSettings {
  val buildOrganization = "cfpb"
  val buildVersion      = "1.0.0"
  val buildScalaVersion = "2.11.6"

  val buildSettings = Defaults.coreDefaultSettings ++
    Defaults.itSettings ++
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

  val slickDeps = Seq(slick, slickPG, hikariCP, jts, scale)

  val deps = akkaDeps ++ Seq(akkaHttp, akkaHttpTestkit, akkaHttpJson, logback, scalaLogging) ++ slickDeps

  lazy val hmdaGeo = (project in file("."))
    .configs( IntegrationTest )
    .settings(buildSettings: _*)
    .settings(
      Revolver.settings ++
      Seq(
        assemblyJarName in assembly := {s"${name.value}"},
        libraryDependencies ++= deps,
        resolvers ++= repos
      )
    )
  

}
