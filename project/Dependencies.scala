import sbt._

object Dependencies {
  val repos = Seq(
    "Local Maven Repo"  at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    "Typesafe Repo"     at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"
  )

  val slick           = "com.typesafe.slick"          %% "slick"                              % Version.slick
  val slickHikariCP   = "com.typesafe.slick"          %% "slick-hikaricp"                     % Version.slick
  val slickPG         = "com.github.tminglei"         %% "slick-pg"                           % Version.slickPG
  val slickPGGeom     = "com.github.tminglei"         %% "slick-pg_jts"                       % Version.slickPG
  val akkaHttp        = "com.typesafe.akka"           %% "akka-http"                          % Version.akkaHttp
  val akkaHttpJson    = "com.typesafe.akka"           %% "akka-http-spray-json"               % Version.akkaHttp
  val akkaHttpTestkit = "com.typesafe.akka"           %% "akka-http-testkit"                  % Version.akkaHttp     % "it, test"

  val scale           = "com.github.jmarin"           %% "scale-geojson"                      % Version.scale
  val jts             = "com.vividsolutions"           % "jts"                                % Version.jts
  val logback         = "ch.qos.logback"               % "logback-classic"                    % Version.logback
  val scalaTest       = "org.scalatest"               %% "scalatest"                          % Version.scalaTest    % "it, test"
  val scalaLogging    = "com.typesafe.scala-logging"  %% "scala-logging"                      % Version.scalaLogging

}
