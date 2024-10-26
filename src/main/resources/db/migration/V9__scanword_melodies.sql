CREATE TABLE IF NOT EXISTS scanword_melodies(
       melody_id UUID,
       scanword_id UUID,
       CONSTRAINT fk_users FOREIGN KEY (melody_id) REFERENCES melody(id),
       CONSTRAINT fk_scanwords FOREIGN KEY (scanword_id) REFERENCES scanword(id)
)