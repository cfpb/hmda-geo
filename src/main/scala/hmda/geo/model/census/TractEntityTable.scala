package hmda.geo.model.census

import com.vividsolutions.jts.geom.Geometry
import hmda.geo.model.GeometryTable
import hmda.geo.pg.PostgisDriver.api._
import hmda.geo.utils.DatabaseConfig

trait TractEntityTable extends DatabaseConfig {

  class Tracts(tag: Tag) extends GeometryTable[TractEntity](tag, "tl_2014_tract") {
    def statefp = column[String]("statefp")
    def countyfp = column[String]("countyfp")
    def tractce = column[String]("tractce")
    def geoid = column[String]("geoid")
    def namelsad = column[String]("namelsad")
    def mtfcc = column[String]("mtfcc")
    def funcstat = column[String]("funcstat")
    def aland = column[Double]("aland")
    def awater = column[Double]("awater")
    def intptlat = column[String]("intptlat")
    def intptlon = column[String]("intptlon")

    def * = (gid, geom, statefp, countyfp, tractce, geoid, namelsad, mtfcc, funcstat, aland, awater, intptlat, intptlon) <> (TractEntity.tupled, TractEntity.unapply)
  }

  protected val tracts = TableQuery[Tracts]

}

