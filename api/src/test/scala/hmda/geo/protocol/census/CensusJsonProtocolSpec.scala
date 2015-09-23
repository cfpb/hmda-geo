package hmda.geo.protocol.census

import org.scalatest._
import spray.json._
import hmda.geo.model.census.TractResult

class CensusJsonProtocolSpec extends FlatSpec with MustMatchers with CensusJsonProtocol {

  "Tracts" should "convert to and from JSON" in {
    val tract = TractResult("32", "023", "960200", "32023960200", "Census Tract 9602", "G5020", "S", -2147483648, 4386983, "+38.1235057", "-116.6775170")
    tract.toJson.convertTo[TractResult] mustBe tract
  }

}
