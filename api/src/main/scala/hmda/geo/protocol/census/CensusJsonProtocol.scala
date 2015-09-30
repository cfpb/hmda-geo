package hmda.geo.protocol.census

import hmda.geo.model.census.TractResult
import spray.json.DefaultJsonProtocol

trait CensusJsonProtocol extends DefaultJsonProtocol {
  implicit val tractFormat = jsonFormat11(TractResult.apply)
}

