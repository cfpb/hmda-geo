package hmda.geo.client.api.model.census

case class HMDAGeoTractResult(
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
  intptlon: String)

