package hmda.geo.pg

import com.github.tminglei.slickpg._

trait PostgisProfile extends ExPostgresProfile
    with PgArraySupport
    with PgDate2Support
    with PgRangeSupport
    with PgHStoreSupport
    with PgSearchSupport
    with PgPostGISSupport
    with PgNetSupport
    with PgLTreeSupport {

  override val api = MyAPI

  object MyAPI extends API with ArrayImplicits
    with DateTimeImplicits
    with NetImplicits
    with LTreeImplicits
    with RangeImplicits
    with HStoreImplicits
    with SearchImplicits
    with PostGISImplicits
    with SearchAssistants

}

object PostgisDriver extends PostgisProfile

