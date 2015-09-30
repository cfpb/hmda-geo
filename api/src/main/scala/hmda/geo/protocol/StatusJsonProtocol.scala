package hmda.geo.protocol

import hmda.geo.model.Status
import spray.json.DefaultJsonProtocol

trait StatusJsonProtocol extends DefaultJsonProtocol {
  implicit val statusFormat = jsonFormat4(Status.apply)
}
