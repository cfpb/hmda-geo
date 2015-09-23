# HMDA-geo

Introduction
------------
HMDA-geo is a collection of services that allow the user to query geographic entities by latitude and longitude, and extract data. 

## Dependencies

### Scala
The HMDA service layer is written in [Scala](http://www.scala-lang.org/).  To build it, you will need to
[download](http://www.scala-lang.org/download/) and [install](http://www.scala-lang.org/download/install.html)
Scala 2.11.x

In addition, you'll need Scala's interactive build tool [sbt](http://www.scala-sbt.org/0.13/tutorial/index.html).
Please refer to the [installation instructions](http://www.scala-sbt.org/0.13/tutorial/Setup.html) to get going.

### Postgres & Postgis
HMDA-pip uses Postgis to perform spatial queries on data. Postgres 9.x with Postgis extension is required. 

### Docker
All HMDA services and apps can be built as [Docker](https://docs.docker.com/) images.
[Docker Compose](https://docs.docker.com/compose/) is also used to simplify local development.

**Note:** Docker is a Linux-only tool.  If you are running on Mac or Windows, you will need
[boot2docker](http://boot2docker.io/) or a similar Docker VM setup.


## Getting involved

For details on how to get involved, please first read our [CONTRIBUTING](CONTRIBUTING.md) guidelines.

## Open source licensing info
1. [TERMS](TERMS.md)
2. [LICENSE](LICENSE)
3. [CFPB Source Code Policy](https://github.com/cfpb/source-code-policy/)


