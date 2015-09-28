# HMDA-geo API

Service that allows the user to query geographic entities and extract data.

## Building

From the HMDA-geo root directory, run `sbt` and then `project api`. This will set the current sbt project to `api`.
From this prompt run `test` to compile the project and run the automated unit tests.

## Running

For development, the project can be run from the `sbt` by typing `re-start`. This will fork a JVM and start the service, which by default requires an Postgis server running on the same machine.

The project can also be run on a Docker container with the following commands (assumes boot2docker running on a Mac, change IP as appropriate)

From the root project, build the project and package it in a single jar:

```
$ sbt
> test assembly
````

From the api directory, build the docker image:

`docker build --rm -t=hmda/geo-api .`

And then run the service linking to a running Postgis container on the same host (in this example, boot2docker):

`docker run --rm --name hmda-geo-api -e PG_HOST=192.168.59.103 -p 8084:8084 --link postgis:postgis hmda/geo-api`

The Postgis host and port are configurable, passing them as environment variables to the docker container. If not specified, the defaults are _localhost_ and _5432_, respectively.
The database name, user and password are configurable as well (defaults are: _gisdb_, _postgres_, _postgres_ respectively).

For more information about the configuration of the Postgis connection see the configuration file at `src/main/resources/application.conf`

