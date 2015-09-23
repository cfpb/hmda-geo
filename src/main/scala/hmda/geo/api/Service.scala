package hmda.geo.api

import hmda.geo.api.routes._

trait Service extends StatusRoute {

  val routes = statusRoute

}
