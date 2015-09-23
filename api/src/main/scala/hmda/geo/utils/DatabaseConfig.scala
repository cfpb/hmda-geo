package hmda.geo.utils

import hmda.geo.pg.PostgisDriver
import hmda.geo.pg.PostgisDriver.api._

trait DatabaseConfig {
  val slickProfile = PostgisDriver
  val db = slickProfile.api.Database.forConfig("database")
  implicit val session: Session = db.createSession()
}
