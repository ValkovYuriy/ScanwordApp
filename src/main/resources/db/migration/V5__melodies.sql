CREATE TABLE IF NOT EXISTS melody(
     id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
     melody bytea NOT NULL ,
     question VARCHAR(128) NOT NULL ,
     answer VARCHAR(128) UNIQUE NOT NULL,
     name VARCHAR
)