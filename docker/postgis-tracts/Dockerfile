FROM centos:7
MAINTAINER Juan Marin Otero <juan.marin.otero@gmail.com>

# Update base image
RUN yum -y update; yum clean all
RUN yum -y install wget
RUN yum -y install unzip
RUN wget http://dl.fedoraproject.org/pub/epel/7/x86_64/e/epel-release-7-5.noarch.rpm
RUN rpm -ivh epel-release-7-5.noarch.rpm

RUN yum -y install python-setuptools; yum clean all
RUN easy_install pip
RUN pip install supervisor
RUN mkdir -p /var/log/supervisor
RUN mkdir -p /etc/supervisor/conf.d

# Add supervisor base configuration
ADD supervisor.conf /etc/supervisor/supervisor.conf

ADD OpenGeo.repo /etc/yum.repos.d/OpenGeo.repo

RUN yum -y install postgis21-postgresql93; yum clean all

# Install GDAL
RUN yum -y install gdal

RUN service postgresql-9.3 initdb
RUN service postgresql-9.3 start && /bin/su postgres -c "createuser -d -s -r -l docker" && /bin/su postgres -c "psql postgres -c \"ALTER USER docker WITH ENCRYPTED PASSWORD 'docker'\"" && service postgresql-9.3 stop
RUN service postgresql-9.3 start && /bin/su postgres -c "createdb --owner docker docker" && service postgresql-9.3 stop

RUN echo "host all  all    0.0.0.0/0  md5" >> /var/lib/pgsql/9.3/data/pg_hba.conf
RUN echo "listen_addresses = '*'" >> /var/lib/pgsql/9.3/data/postgresql.conf
RUN echo "port = 5432" >> /var/lib/pgsql/9.3/data/postgresql.conf

ENV PGDATA /var/lib/pgsql/9.3/data

EXPOSE 5432

ADD postgis.sv.conf /etc/supervisor/conf.d/

RUN wget -r ftp://ftp.census.gov/geo/tiger/TIGER2014/TRACT/

WORKDIR /ftp.census.gov/geo/tiger/TIGER2014/TRACT

COPY load_tract.sh /var/lib/pgsql/load_tract.sh

RUN /var/lib/pgsql/load_tract.sh 

WORKDIR /

RUN rm -rf ftp.census.gov

CMD ["supervisord", "-c", "/etc/supervisor/supervisor.conf"]

