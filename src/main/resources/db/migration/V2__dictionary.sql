CREATE TABLE IF NOT EXISTS dictionary(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    word VARCHAR(20) NOT NULL,
    question VARCHAR NOT NULL
)