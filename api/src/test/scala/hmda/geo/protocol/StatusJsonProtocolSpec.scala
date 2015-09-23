package hmda.geo.protocol

import java.net.InetAddress
import java.util.Calendar
import org.scalatest._
import spray.json._
import hmda.geo.model.Status

class StatusJsonProtocolSpec extends FlatSpec with MustMatchers with StatusJsonProtocol {

  "Status" should "convert to and from JSON" in {
    val date = Calendar.getInstance().getTime().toString
    val status = Status("OK", "hmda-pip", date, InetAddress.getLocalHost.getHostName)
    status.toJson.convertTo[Status] mustBe status
  }

}
