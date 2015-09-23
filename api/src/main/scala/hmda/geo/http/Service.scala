package hmda.geo.http

import akka.http.scaladsl.server.Directives._
import hmda.geo.http.routes._
import hmda.geo.http.routes.census._

trait Service extends StatusRoute with TractRoute {
  val routes = statusRoute ~ tractRoute
}
