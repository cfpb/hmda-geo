package hmda.geo.api.routes.census

import akka.http.scaladsl.coding.{ Deflate, Gzip, NoCoding }
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import hmda.geo.api.routes.BaseRoute
import hmda.geo.protocol.PipJsonProtocol
import hmda.geo.service.TractService
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import geometry.Point

trait TractRoute extends BaseRoute with PipJsonProtocol {

  lazy val tractLog = Logger(LoggerFactory.getLogger("hmda-geo-census-tract"))

  val tractRoute = {
    path("tract") {
      parameters('latitude.as[Double], 'longitude.as[Double]) { (lat, lon) =>
        encodeResponseWith(NoCoding, Gzip, Deflate) {
          complete {
            val p = Point(lon, lat, 4326)
            TractService.containsPoint(p)
          }
        }
      }
    }
  }

}
