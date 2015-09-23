package hmda.geo.service

import hmda.geo.model.census.TractEntityTable
import geometry.Point
import slick.lifted.TableQuery
import scala.concurrent.{ ExecutionContext, Future }
import hmda.geo.model.PipResult
import hmda.geo.pg.PostgisDriver.api._

object TractService extends TractService

trait TractService extends TractEntityTable {

  def containsPoint(p: Point)(implicit ec: ExecutionContext): Future[PipResult] = {
    val q = tracts.filter(c => c.geom.contains(p.jtsGeometry)).map(c => c.gid)
    db.run(q.result).map(r => if (r.size > 0) PipResult(true) else PipResult(false))
  }

}
