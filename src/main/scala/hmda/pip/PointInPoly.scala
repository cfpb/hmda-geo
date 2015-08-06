package hmda.pip

import hmda.pip.api.Service
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

object PointInPoly extends App with Service {
  override implicit val system = ActorSystem("hmda-pip")
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)

  val http = Http(system).bindAndHandle(
    routes,
    config.getString("hmda.pip.http.interface"),
    config.getInt("hmda.pip.http.port")
  )

  sys.addShutdownHook {
    system.shutdown()
  }
}
