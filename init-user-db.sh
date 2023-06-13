#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER webblog;
	CREATE DATABASE webblog;
	GRANT ALL PRIVILEGES ON DATABASE webblog TO webblog;
EOSQL
