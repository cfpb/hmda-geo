#!/usr/bin/env bash
# Census Tracts files can be downloaded from the Census Bureau FTP server at ftp://ftp2.census.gov (directory /geo/tiger/TIGER2014/TRACT)

wget -r ftp://ftp2.census.gov/geo/tiger/TIGER2014/TRACT/

db=gisdb
schema=public
table=tl_2014_tract

psql -p 5432 $db -c 'DROP TABLE '"$schema"'.'"$table"''

psql -p 5432 $db -c 'CREATE TABLE tl_2014_tract
(
  gid serial NOT NULL,
  statefp character varying(2),
  countyfp character varying(3),
  tractce character varying(6),
  geoid character varying(11),
  name character varying(7),
  namelsad character varying(20),
  mtfcc character varying(5),
  funcstat character varying(1),
  aland double precision,
  awater double precision,
  intptlat character varying(11),
  intptlon character varying(12),
  geom geometry(MultiPolygon,4326),
  CONSTRAINT tl_2014_tract_pkey PRIMARY KEY (gid )
)'


for i in *.zip
  do
    x=${i%.zip}  
    echo 'Loading' $i 
    unzip -o $i
    ogr2ogr -f "ESRI Shapefile" proj_$x.shp $x.shp -t_srs "EPSG:4326"
    shp2pgsql -a -s 4326 -W latin1 -g geom proj_$x.shp $schema.$table $db | psql -p 5432 $db
    rm proj*
  done

psql -p 5432 $db -c 'CREATE INDEX '"$schema"'_gix ON '"$schema"'.'"$table"' USING GIST (geom);'



