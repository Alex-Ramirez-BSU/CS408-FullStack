-- Inserts sample trail data into the "trails" table
-- Used for initial testing and development

INSERT INTO trails (name, date, city, state, difficulty, distance, rating, notes) VALUES
-- Easy difficulty example
('Demo Trail 1', '2026-01-01', 'Boise', 'ID', 'Easy', 1.0, 1, 'This trail is a demo with EASY difficulty'),

-- Medium difficulty example
('Demo Trail 2', '2026-02-02', 'Boise', 'ID', 'Medium', 2.0, 2, 'This trail is a demo with MEDIUM difficulty'),

-- Hard difficulty example
('Demo Trail 3', '2026-03-02', 'Boise', 'ID', 'Hard', 3.0, 3, 'This trail is a demo with HARD difficulty');