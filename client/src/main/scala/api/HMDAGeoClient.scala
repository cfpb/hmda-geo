package hmda.geo.client.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.unmarshalling.Unmarshal
import com.typesafe.config.ConfigFactory
import hmda.geo.client.HMDAGeoServiceClient
import hmda.geo.client.api.model.HMDAGeoStatus
import hmda.geo.client.api.model.census.HMDAGeoTractResult
import hmda.geo.client.model.ResponseError
import hmda.geo.client.api.protocol.HMDAGeoJsonProtocol
import hmda.geo.client.api.protocol.census.HMDAGeoCensusJsonProtocol
import scala.concurrent.{ ExecutionContext, Future }
import geometry.Point

object HMDAGeoClient extends HMDAGeoServiceClient with HMDAGeoJsonProtocol with HMDAGeoCensusJsonProtocol {
  override val config = ConfigFactory.load()

  lazy val host = config.getString("hmda.geo.api.host")
  lazy val port = config.getString("hmda.geo.api.port")

  def status: Future[Either[ResponseError, HMDAGeoStatus]] = {
    implicit val ec: ExecutionContext = system.dispatcher
    sendGetRequest(Uri("/")).flatMap { response =>
      response.status match {
        case OK => Unmarshal(response.entity).to[HMDAGeoStatus].map(Right(_))
        case _ => sendResponseError(response)
      }
    }
  }

  def findTractByPoint(p: Point): Future[Either[ResponseError, HMDAGeoTractResult]] = {
    implicit val ec: ExecutionContext = system.dispatcher
    val lat = p.y
    val lon = p.x
    sendGetRequest(Uri(s"/tracts?latitude=${lat}&longitude=${lon}")).flatMap { response =>
      response.status match {
        case OK => Unmarshal(response.entity).to[HMDAGeoTractResult].map(Right(_))
        case _ => sendResponseError(response)
      }
    }
  }

}
