package hmda.geo.protocol

import java.net.InetAddress
import java.util.Calendar
import org.scalatest._
import spray.json._
import hmda.geo.model.{ Status, PipResult }

class PipJsonProtocolSpec extends FlatSpec with MustMatchers with PipJsonProtocol {

  "Status" should "convert to and from JSON" in {
    val date = Calendar.getInstance().getTime().toString
    val status = Status("OK", "hmda-pip", date, InetAddress.getLocalHost.getHostName)
    status.toJson.convertTo[Status] mustBe status
  }

  "PipResult" should "convert to and from JSON" in {
    val result = PipResult(true)
    result.toJson.convertTo[PipResult] mustBe result
  }
}
