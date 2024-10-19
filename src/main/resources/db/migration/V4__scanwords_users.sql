CREATE TABLE IF NOT EXISTS scanwords_users(
       user_id UUID,
       scanword_id UUID,
       CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(id),
       CONSTRAINT fk_scanwords FOREIGN KEY (scanword_id) REFERENCES scanword(id)
)