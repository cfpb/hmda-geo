package hmda.geo.api

import akka.http.scaladsl.server.Directives._
import hmda.geo.api.routes._
import hmda.geo.api.routes.census._

trait Service extends StatusRoute with TractRoute {
  val routes = statusRoute ~ tractRoute
}
