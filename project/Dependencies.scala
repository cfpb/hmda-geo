import sbt._

object Dependencies {
  val repos = Seq(
    "Local Maven Repo"  at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    "Typesafe Repo"     at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"
  )

    val slickPG         = "com.github.tminglei"         %% "slick-pg"                           % Version.slickPG
    val akkaHttp        = "com.typesafe.akka"           %% "akka-http-experimental"             % Version.akkaHttp
    val akkaHttpJson    = "com.typesafe.akka"           %% "akka-http-spray-json-experimental"  % Version.akkaHttp
    val akkaHttpTestkit = "com.typesafe.akka"           %% "akka-http-testkit-experimental"     % Version.akkaHttp % "test"

  val logback           = "ch.qos.logback"               % "logback-classic"                    % Version.logback

  val scalaLogging      = "com.typesafe.scala-logging"  %% "scala-logging"                      % Version.scalaLogging

  val scalaTest         = "org.scalatest"               %% "scalatest"                          % Version.scalaTest   % "test"
}
