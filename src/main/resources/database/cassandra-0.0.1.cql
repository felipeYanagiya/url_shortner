CREATE KEYSPACE url
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE url (
  id text PRIMARY KEY,
  shortUrl text,
  longUrl text
);
