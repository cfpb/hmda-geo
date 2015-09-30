package hmda.geo.pg

import com.github.tminglei.slickpg._

trait PostgisDriver extends ExPostgresDriver
    with PgArraySupport
    with PgDate2Support
    with PgEnumSupport
    with PgRangeSupport
    with PgHStoreSupport
    with PgSearchSupport
    with PgPostGISSupport {

  override val api = new MyAPI {}

  trait MyAPI extends API
    with ArrayImplicits
    with DateTimeImplicits
    with RangeImplicits
    with HStoreImplicits
    with SearchImplicits
    with PostGISImplicits
    with SearchAssistants
}

object PostgisDriver extends PostgisDriver

