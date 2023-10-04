ALTER TABLE student ADD CONSTRAINT age_constraint CHECK ( age >= 16 ),
    ALTER COLUMN name SET NOT NULL,
    ADD CONSTRAINT name_constraint UNIQUE (name),
    ALTER COLUMN age SET DEFAULT 20;
ALTER TABLE faculty ADD CONSTRAINT name_and_color_constraint UNIQUE (name, color);