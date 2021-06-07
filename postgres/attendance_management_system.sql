DROP SCHEMA IF EXISTS attendance_management_system CASCADE;

CREATE SCHEMA attendance_management_system;
SET search_path TO 'attendance_management_system';
SET schema 'attendance_management_system';

CREATE TABLE user_account
(
    user_id   VARCHAR(6)  NOT NULL CHECK (user_id ~ '^[0-9]{6}$'),
    full_name VARCHAR(200) NOT NULL,
    password  VARCHAR(50) NOT NULL,
    access    VARCHAR(30) NOT NULL CHECK (access in ('student', 'teacher', 'admin')),
    PRIMARY KEY (user_id)
);

CREATE TABLE classes
(
    class_name VARCHAR NOT NULL,
    PRIMARY KEY (class_name)
);

CREATE TABLE student_list
(
    class_name VARCHAR REFERENCES classes (class_name) ON delete cascade,
    user_id  VARCHAR REFERENCES user_account (user_id) ON delete cascade,
    PRIMARY KEY (class_name, user_id)
);

CREATE TABLE lesson
(
    lesson_id   INT GENERATED ALWAYS AS IDENTITY UNIQUE,
    subject     VARCHAR(50) NOT NULL,
    topic       VARCHAR(150),
    homework    text,
    description text,
    PRIMARY KEY (lesson_id)
);

CREATE TABLE schedule_lessons
(
    class_name VARCHAR REFERENCES classes (class_name) ON delete cascade,
    lesson_id INT REFERENCES lesson (lesson_id) ON delete cascade,
    PRIMARY KEY (class_name, lesson_id)
);

CREATE TABLE time_of_conduct
(
    lesson_id   INT REFERENCES lesson (lesson_id) ON delete cascade,
    lesson_date date    NOT NULL,
    time_from   time    NOT NULL,
    time_to     time    NOT NULL,
    classroom VARCHAR not null,
    PRIMARY KEY (lesson_id, lesson_date)
);

CREATE TABLE taught_by
(
    teacher_id VARCHAR(6) references user_account (user_id) ON delete cascade,
    lesson_id  INT REFERENCES Lesson (lesson_id) ON delete cascade,
    PRIMARY KEY (teacher_id, lesson_id)
);

CREATE TABLE lesson_data
(
    user_id        VARCHAR REFERENCES user_account (user_id) ON delete cascade,
    lesson_id      INT REFERENCES lesson (lesson_id) ON delete cascade,
    grade          INT CHECK (grade in (-3,-1, 0, 2, 4, 7, 10, 12)),
    comment        VARCHAR,
    absence_status bool,
    absence_motive VARCHAR,
    PRIMARY KEY (user_id, lesson_id)
);

-- admin account
INSERT INTO user_account(user_id, full_name, password, access)
VALUES ('111111', 'Administration', 'admin', 'admin') on conflict do nothing;
