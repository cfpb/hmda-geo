import sbt.Defaults
import sbt.Keys.{organization, scalaVersion, scalacOptions, version}

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