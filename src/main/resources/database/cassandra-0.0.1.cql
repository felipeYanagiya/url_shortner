CREATE KEYSPACE url
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE users (
  id text PRIMARY KEY,
  shortUrl text,
  longUrl text
);
