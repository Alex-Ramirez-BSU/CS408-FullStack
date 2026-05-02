-- Drops the trails table if it already exists (ensures a clean reset)
DROP TABLE IF EXISTS trails;

-- Creates the trails table to store trail information
CREATE TABLE trails (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,                      -- Unique identifier for each trail (auto-incremented)
    name VARCHAR(255) NOT NULL,                                 -- Name of the trail (required)
    date DATE NOT NULL,                                         -- Date the trail was explored (required)
    city VARCHAR(255),                                          -- City where the trail is located
    state VARCHAR(255),                                         -- State where the trail is located
    difficulty VARCHAR(50),                                     -- Difficulty level (e.g., Easy, Medium, Hard)
    distance DOUBLE,                                            -- Distance of the trail (in miles)
    rating INTEGER CHECK (rating >= 0 AND rating <= 5),         -- Rating of the trail (must be between 0 and 5)
    notes VARCHAR(1000)                                         -- Additional notes about the trail
);