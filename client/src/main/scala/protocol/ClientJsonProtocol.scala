package hmda.geo.client.protocol

import spray.json.DefaultJsonProtocol
import hmda.geo.client.model.ResponseError

trait ClientJsonProtocol extends DefaultJsonProtocol {
  implicit val responseErrorFormat = jsonFormat1(ResponseError.apply)
}
