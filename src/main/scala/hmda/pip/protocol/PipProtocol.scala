package hmda.pip.protocol

import hmda.pip.model.Status
import spray.json.DefaultJsonProtocol

trait PipProtocol extends DefaultJsonProtocol {
  implicit val statusFormat = jsonFormat4(Status.apply)
}
