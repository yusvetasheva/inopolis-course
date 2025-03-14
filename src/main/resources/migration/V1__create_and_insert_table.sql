-- Создание таблицы course_flux
CREATE TABLE IF NOT EXISTS course_flux (
    id SERIAL PRIMARY KEY,          -- Идентификатор, автоинкремент
    name VARCHAR(255) NOT NULL,     -- Название курса
    date_begin DATE,                -- Дата начала курса
    is_active BOOLEAN,              -- Активен ли курс
    comments TEXT[]                 -- Список комментариев (массив строк)
);

-- Вставка тестовых данных в таблицу course_flux
INSERT INTO course_flux (name, date_begin, is_active, comments)
VALUES
    ('Java Programming', '2025-04-01', TRUE, ARRAY['Great course', 'Highly recommended']),
    ('Spring Boot Mastery', '2025-05-01', TRUE, ARRAY['Essential for backend developers']),
    ('Database Fundamentals', '2025-06-01', FALSE, ARRAY['Outdated material', 'Needs an update']),
    ('Cloud Computing Basics', '2025-07-01', TRUE, ARRAY['Important for future trends', 'Great introduction to cloud technologies']),
    ('Advanced Java', '2025-08-01', TRUE, ARRAY['For experienced developers', 'Difficult but rewarding']);
