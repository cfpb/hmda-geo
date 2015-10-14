# Postgis Tracts Dockerfile

This Docker container configures a Postgis database with the Census Bureau Tracts loaded into the public schema as table tl_2014_tract

## Building

The Docker image can be built by issuing the following command from this directory:

`docker build -t hmda/postgis-tracts .`

The build will take a bit of time, since the image downloads and loads all Census Tracts into the Postgis database. 


## Running

To run the database, issue the following:

`docker run -p 5432:5432 hmda/postgis-tracts`
