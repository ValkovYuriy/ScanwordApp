CREATE TABLE IF NOT EXISTS dictionary(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    data jsonb
)