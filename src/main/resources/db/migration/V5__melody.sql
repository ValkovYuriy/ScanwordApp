CREATE TABLE IF NOT EXISTS melody(
     id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
     melody bytea NOT NULL ,
     question VARCHAR(60) NOT NULL ,
     answer VARCHAR(10) UNIQUE NOT NULL,
     name VARCHAR(60)
)