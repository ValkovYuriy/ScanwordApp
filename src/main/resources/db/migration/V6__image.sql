CREATE TABLE IF NOT EXISTS image(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    image bytea NOT NULL ,
    question VARCHAR(60) NOT NULL ,
    answer VARCHAR(10) UNIQUE NOT NULL
)