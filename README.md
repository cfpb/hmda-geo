# HMDA-geo

Introduction
------------
HMDA-geo is a collection of services that allow the user to query geographic entities by latitude and longitude, and extract data. 

## Dependencies

### Java 8 JDK
The service layer runs on the Java Virtual Machine (JVM), and requires the Java 8 JDK to build and run the project.
This project is currenly being built and tested on [Oracle JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
See [Oracle's JDK Install Overview](http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
for install instructions.

This codebase _should_ also run on OpenJDK 8.

### Scala
The HMDA service layer is written in [Scala](http://www.scala-lang.org/).  To build it, you will need to
[download](http://www.scala-lang.org/download/) and [install](http://www.scala-lang.org/download/install.html)
Scala 2.11.x

In addition, you'll need Scala's interactive build tool [sbt](http://www.scala-sbt.org/0.13/tutorial/index.html).
Please refer to the [installation instructions](http://www.scala-sbt.org/0.13/tutorial/Setup.html) to get going.

### Postgres & Postgis
`HMDA-geo` uses Postgis to perform spatial queries on data. Postgres 9.x with Postgis extension is required.


The scripts in the `scripts` folder contain utilities to load data into Postgis.
They require the `shp2pgsql` command line tool included with and installation of Postgis as well as the `ogr2ogr` tool, usually installed with `GDAL`


## Building & Running

`HMDA-geo` uses [sbt's multi-project builds](http://www.scala-sbt.org/0.13/tutorial/Multi-Project.html),
each project representing a specific task and usually a [Microservice](http://en.wikipedia.org/wiki/Microservices).

### Interactive

1. Start `sbt`

        $ sbt

2. Select project to build and run

        > projects
        [info]       api
        [info]       client
        [info]     * hmdageo

        > project api
        [info] Set current project to hmda-geo-api (in build file: /path/to/hmda-geo/)

3. Start the service

    This will retrieve all necessary dependencies, compile Scala source, and
    start a local server.  It also listens for changes to underlying
    source code, and auto-deploys to local server.

        > ~re-start

4. Confirm service is up by browsing to `http://localhost:8084/status`.

In order to use the endpoints for geospatial operations, the following environment variables need to be set:

* `PG_HOST`: host running PostgreSQL. Defaults to `localhost`
* `PG_DATABASE` : name of the PostgreSQL database. Defaults to `gisdb`
* `PG_PORT`: port where PostgreSQL is running on. Defaults to 5432
* `PG_USER`: PostgreSQL user (needs to have relevant permissions on tables to be queried). Defaults to `postgres`
* `PG_PASSWORD`: Password for PostgreSQL user. Defaults to `postgres`

## Testing client

The API can be tested from the client project on a running system. First, load some data (i.e. census tracts) into a local Postgis database.
You can use the loading shell scripts in the `scripts` folder (i.e. `load_tract.sh`), adjust variables like database name and schema as appropriate.

From an sbt prompt, change into the client project:

     > project client

And run the integration tests:

     > it:test

## Docker

The software can also be run as a container. See the documentation for the [API](api/README.md) and for the [Postgis databases](docker) that can be used with this service.


## Getting involved

For details on how to get involved, please first read our [CONTRIBUTING](CONTRIBUTING.md) guidelines.

## Open source licensing info
1. [TERMS](TERMS.md)
2. [LICENSE](LICENSE)
3. [CFPB Source Code Policy](https://github.com/cfpb/source-code-policy/)


