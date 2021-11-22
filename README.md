# Database

> This Repo educates about the usage of Data Access Objects and Repositories

The DAO (Data Access Object) and the Repository Pattern tend to be interchangeable in data centric applications, but there are differences especially if you focus on solving business use cases.

A DAO is an abstraction of the data persistence layer on a low level. A DAO often matches database tables and focus on a single Entity or Model.

A Repository on the other hand is also an abstraction of the data persistence layer but on a higher level. A Repository often matches multiple database tables and focus on the relation between multiple Entities or Models. 

## Start the DB
 
>**!!! NEVER COMMIT DATABASE CREDENTIALS TO A GIT REPOSITORY !!!**

Make sure to have Docker installed and running.

```sh
# --rm - delete the container when stopped
# --detach - start the db in a background process
# --name - name of the container
# -e - environment variables (we set user and pw)
# -p - port mapping hostport:containerport
# -v - cerate a volume to persist data between container starts and removing containers
# nameofthevolume:/path/in/the/container/we/want/persisted
# postgres is the image
$ docker run --rm --detach --name swe1db -e POSTGRES_USER=swe1user -e POSTGRES_PASSWORD=swe1pw -v data:/var/lib/postgresql/data -p 5431:5432 postgres
#
# open a terminal within a container
# exec - run a command in a container (e.g. bash)
# -i - interactive means to keep STDIN open
# -t - allocate a pseudo-TTY, add a terminal driver
# - it basically makes the container start look like a terminal connection session
# bash - the command that is executed inside the container  
$ docker exec -it swe1db bash
# inside the container connect to the postgresql
# -p - port, -h - host, -U - user
$ psql -p 5431 -h localhost -U swe1user
# inside postgresql
# list all databases
$ \l
# create new db
$ CREATE DATABASE swe1db;
# list all databases and confirm that new db exists
$ \l
# add privledges (in production you should really carefully select which privledges arre allowed) we simply add everything
$ GRANT ALL PRIVILEGES ON DATABASE swe1db TO swe1user;
# connect to db
$ \c swe1db
# create a table
# https://www.postgresql.org/docs/9.5/datatype.html
# https://www.postgresql.org/docs/9.4/ddl-constraints.html
$ CREATE TABLE users (
	id serial PRIMARY KEY,
	password VARCHAR (50) NOT NULL,
	first_name VARCHAR (255) NOT NULL,
	last_name VARCHAR (255) NOT NULL,
	user_name VARCHAR(255) UNIQUE NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
# list all tables in db
$ \dt
# create a table
# https://www.postgresql.org/docs/9.5/datatype.html
# https://www.postgresql.org/docs/9.4/ddl-constraints.html
$ CREATE TABLE tweets (
	id serial PRIMARY KEY,
	message VARCHAR (144) NOT NULL,
	user_id INT,
	created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_user
	  FOREIGN KEY(user_id)
	    REFERENCES users(id)
);
# list all tables in db
$ \dt
```