#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER admin WITH PASSWORD 'admin';
    GRANT ALL PRIVILEGES ON DATABASE postgres TO admin;
EOSQL