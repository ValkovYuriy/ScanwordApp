CREATE TABLE IF NOT EXISTS dictionary(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    data jsonb
)