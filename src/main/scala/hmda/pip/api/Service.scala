package hmda.pip.api

import java.net.InetAddress
import java.time.Instant
import akka.actor.ActorSystem
import com.typesafe.config.Config
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContextExecutor
import akka.event.LoggingAdapter
import akka.http.scaladsl.coding.{ Deflate, Gzip, NoCoding }
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import spray.json._
import hmda.pip.model.Status
import hmda.pip.protocol.PipProtocol

trait Service extends PipProtocol {
  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: ActorMaterializer

  def config: Config

  val logger: LoggingAdapter

  lazy val log = Logger(LoggerFactory.getLogger("hmda-pip"))

  val routes = {
    path("status") {
      get {
        encodeResponseWith(NoCoding, Gzip, Deflate) {
          complete {
            val now = Instant.now.toString
            val host = InetAddress.getLocalHost.getHostName
            val status = Status("OK", "hmda-pip", now, host)
            log.debug(status.toJson.toString())
            ToResponseMarshallable(status)
          }
        }
      }
    }

  }

}
