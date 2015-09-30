package hmda.geo.model.census

import com.vividsolutions.jts.geom.Geometry

case class TractEntity(
  gid: Long,
  geom: Geometry,
  statefp: String,
  countyfp: String,
  tractce: String,
  geoid: String,
  namelsad: String,
  mtfcc: String,
  funcstat: String,
  aland: Double,
  awater: Double,
  intptlat: String,
  intptlon: String
)

