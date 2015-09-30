package hmda.geo.client.api

import org.scalatest._
import scala.concurrent.Await
import scala.concurrent.duration._
import geometry.Point

class HMDAGeoClientSpec extends FlatSpec with MustMatchers {
  
  "A request to /status" must "return a status object" in {
    val maybeStatus = Await.result(HMDAGeoClient.status, 2.seconds)
    maybeStatus match {
      case Right(s) =>
        s.status mustBe "OK"
      case Left(b) =>
        b.desc mustBe "503 Service Unavailable"
        fail("SERVICE_UNAVAILABLE")
    }
  }

  "A requet to /tracts" must "find the correct tract by latitude,longitude" in {
    val p = Point(-117, 38)
    val maybeTract = Await.result(HMDAGeoClient.findTractByPoint(p), 2.seconds)
    maybeTract match {
      case Right(t) =>
        t.intptlon mustBe "-116.6775170"
        t.awater mustBe 4386983
        t.mtfcc mustBe "G5020"
        t.aland mustBe -2147483648
        t.namelsad mustBe "Census Tract 9602"
        t.tractce mustBe "960200"
        t.statefp mustBe "32"
        t.intptlat mustBe "+38.1235057"
        t.funcstat mustBe "S"
        t.countyfp mustBe "023"
        t.geoid mustBe "32023960200"
      case Left(b) =>
        b.desc mustBe "503 Service Unavailable"
        fail("SERVICE_UNAVAILABLE")
    }
  }
}

