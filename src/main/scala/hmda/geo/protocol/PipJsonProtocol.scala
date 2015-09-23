package hmda.geo.protocol

import hmda.geo.model.{ Status, PipResult }
import spray.json.DefaultJsonProtocol

trait PipJsonProtocol extends DefaultJsonProtocol {
  implicit val statusFormat = jsonFormat4(Status.apply)
  implicit val pipResultFormat = jsonFormat1(PipResult.apply)
}
