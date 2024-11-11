CREATE TABLE IF NOT EXISTS scanword(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    width INT NOT NULL ,
    height INT NOT NULL,
    preview bytea NOT NULL,
    dictionary_id UUID REFERENCES dictionary(id),
    content jsonb,
    is_created boolean NOT NULL,
    number_of_hints INT NOT NULL
)