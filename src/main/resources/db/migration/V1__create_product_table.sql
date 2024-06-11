CREATE EXTENSION  IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS product(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(200) NOT NULL,
    description VARCHAR(1500) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    image_url VARCHAR(1000),
    category VARCHAR(70) NOT NULL

)

