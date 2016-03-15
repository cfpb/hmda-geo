package hmda.geo

import hmda.geo.http.Service
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

object HMDAGeo extends App with Service {
  override implicit val system = ActorSystem("hmda-geo")
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)

  val http = Http(system).bindAndHandle(
    routes,
    config.getString("hmda.geo.http.interface"),
    config.getInt("hmda.geo.http.port")
  )

  sys.addShutdownHook {
    system.terminate()
  }
}
