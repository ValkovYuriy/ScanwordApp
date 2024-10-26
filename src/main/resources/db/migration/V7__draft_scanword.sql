CREATE TABLE IF NOT EXISTS draft_scanword(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    scanword_id UUID REFERENCES scanword(id),
    content jsonb
)