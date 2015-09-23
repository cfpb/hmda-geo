package hmda.geo.http.routes

import akka.actor.ActorSystem
import com.typesafe.config.Config
import akka.stream.ActorMaterializer
import akka.event.LoggingAdapter
import scala.concurrent.ExecutionContextExecutor

trait BaseRoute {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: ActorMaterializer
  def config: Config
  val logger: LoggingAdapter
}
