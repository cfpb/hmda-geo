package hmda.geo.http.routes.census

import akka.http.scaladsl.coding.{ Deflate, Gzip, NoCoding }
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.typesafe.scalalogging.Logger
import geometry.Point
import hmda.geo.http.routes.BaseRoute
import hmda.geo.protocol.census.CensusJsonProtocol
import hmda.geo.service.TractService
import org.slf4j.LoggerFactory

trait TractRoute extends BaseRoute with CensusJsonProtocol {

  lazy val tractLog = Logger(LoggerFactory.getLogger("hmda-geo-census-tract"))

  val tractRoute = {
    pathPrefix("tracts") {
      parameters('latitude.as[Double], 'longitude.as[Double]) { (lat, lon) =>
        encodeResponseWith(NoCoding, Gzip, Deflate) {
          complete {
            val srid = 4326 // Spatial Reference ID, from EPSG code database (WGS 84)
            val p = Point(lon, lat, srid)
            TractService.findByPoint(p)
          }
        }
      }
    }
  }

}
