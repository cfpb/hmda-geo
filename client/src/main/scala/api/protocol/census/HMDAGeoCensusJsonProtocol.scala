package hmda.geo.client.api.protocol.census

import spray.json.DefaultJsonProtocol
import hmda.geo.client.api.model.census.HMDAGeoTractResult

trait HMDAGeoCensusJsonProtocol extends DefaultJsonProtocol {
  implicit val tractFormat = jsonFormat11(HMDAGeoTractResult.apply)
}

