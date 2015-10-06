package hmda.geo.client.api.protocol

import spray.json.DefaultJsonProtocol
import hmda.geo.client.api.model.HMDAGeoStatus

trait HMDAGeoJsonProtocol extends DefaultJsonProtocol {
  implicit val hmdaGeoStatusFormat = jsonFormat4(HMDAGeoStatus.apply)
}
