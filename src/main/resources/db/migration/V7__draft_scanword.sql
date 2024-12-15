CREATE TABLE IF NOT EXISTS draft_scanword(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    scanword_id UUID REFERENCES scanword(id),
    owner_id UUID REFERENCES users(id),
    content jsonb,
    is_solved boolean
)