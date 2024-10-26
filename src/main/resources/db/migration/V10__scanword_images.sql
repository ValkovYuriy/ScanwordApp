CREATE TABLE IF NOT EXISTS scanword_images(
       image_id UUID,
       scanword_id UUID,
       CONSTRAINT fk_users FOREIGN KEY (image_id) REFERENCES image(id),
       CONSTRAINT fk_scanwords FOREIGN KEY (scanword_id) REFERENCES scanword(id)
)