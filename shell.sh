#!/bin/sh

DATABASE=`heroku config -a $1 | grep DATABASE_URL`

USERNAME=`echo ${DATABASE} | cut -d"/" -f3 | cut -d":" -f1`
PASSWORD=`echo ${DATABASE} | cut -d"/" -f3 | cut -d":" -f2 | cut -d"@" -f1`
HOST=`echo ${DATABASE} | cut -d"/" -f3 | cut -d":" -f2 | cut -d"@" -f2`
DATABASE=`echo ${DATABASE} | cut -d"/" -f4`

heroku config:set DB_CONNECTION=pgsql -a $1
heroku config:set DB_USERNAME=${USERNAME} -a $1
heroku config:set DB_PASSWORD=${PASSWORD} -a $1
heroku config:set DB_HOST=${HOST} -a $1
heroku config:set DB_PORT=5432 -a $1
heroku config:set DB_DATABASE=${DATABASE} -a $1
heroku config:set PLATFORM=org.hibernate.dialect.PostgreSQLDialect -a $1
heroku config:set DRIVER=org.postgresql.Driver -a $1
heroku config -a $1 | grep DB