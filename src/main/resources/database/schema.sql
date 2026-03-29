CREATE TABLE trails (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    city VARCHAR(255),
    state VARCHAR(255),
    difficulty VARCHAR(50),
    distance DOUBLE,
    rating INTEGER CHECK (rating >= 0 AND raring <= 5),
    notes VARCHAR(1000),
)