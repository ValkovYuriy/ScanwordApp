CREATE TABLE IF NOT EXISTS scanword(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(50) NOT NULL ,
    width INT NOT NULL ,
    height INT NOT NULL,
    preview bytea NOT NULL,
    content jsonb,
    is_created boolean NOT NULL
)