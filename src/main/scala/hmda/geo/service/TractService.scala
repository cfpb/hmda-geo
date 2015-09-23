package hmda.geo.service

import hmda.geo.model.census.TractEntityTable
import hmda.geo.model.census.TractResult
import geometry.Point
import slick.lifted.TableQuery
import scala.concurrent.{ ExecutionContext, Future }
import hmda.geo.pg.PostgisDriver.api._

object TractService extends TractService

trait TractService extends TractEntityTable {

  def findByPoint(p: Point)(implicit ec: ExecutionContext): Future[TractResult] = {
    val q = tracts.filter(c => c.geom.contains(p.jtsGeometry))
    db.run(q.result).map { r =>
      if (r.size > 0) {
        val t = r.head
        TractResult(t.statefp, t.countyfp, t.tractce, t.geoid, t.namelsad, t.mtfcc, t.funcstat, t.aland, t.awater, t.intptlat, t.intptlon)
      } else {
        TractResult.empty
      }
    }
  }

}
