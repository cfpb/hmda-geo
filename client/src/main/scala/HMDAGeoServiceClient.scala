package hmda.geo.client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ ActorMaterializer, StreamTcpException }
import scala.concurrent.duration._
import akka.util.Timeout
import com.typesafe.config.Config
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model._
import akka.stream.scaladsl.{ Sink, Source }
import scala.concurrent.{ ExecutionContext, Future }
import hmda.geo.client.model.ResponseError
import hmda.geo.client.protocol.ClientJsonProtocol

trait HMDAGeoServiceClient extends ClientJsonProtocol {

  implicit val askTimeout: Timeout = 1000.millis
  implicit val system: ActorSystem = ActorSystem("hmda-geo-client")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  def host: String
  def port: String

  val config: Config

  def sendGetRequest(path: Uri): Future[HttpResponse] = {
    implicit val ec: ExecutionContext = system.dispatcher
    val connectionFlow = Http().outgoingConnection(host, port.toInt)
    val request = HttpRequest(GET, path)
    Source.single(request).via(connectionFlow).runWith(Sink.head).recover {
      case e: StreamTcpException =>
        HttpResponse(
          ServiceUnavailable,
          Nil,
          HttpEntity.empty(ContentTypes.NoContentType),
          HttpProtocols.`HTTP/1.1`
        )
    }
  }

  def sendResponseError(response: HttpResponse) = {
    Future.successful {
      Left(ResponseError(response.status.toString()))
    }
  }
}
