CREATE TABLE IF NOT EXISTS scanword_dictionaries(
       dictionary_id UUID,
       scanword_id UUID,
       CONSTRAINT fk_users FOREIGN KEY (dictionary_id) REFERENCES dictionary(id),
       CONSTRAINT fk_scanwords FOREIGN KEY (scanword_id) REFERENCES scanword(id)
)