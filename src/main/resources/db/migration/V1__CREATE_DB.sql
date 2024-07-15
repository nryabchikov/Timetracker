CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE users
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('users_seq'),
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    email     VARCHAR(100) UNIQUE NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(20) NOT NULL
);

CREATE TABLE token
(
    id         SERIAL PRIMARY KEY,
    token      VARCHAR(500) UNIQUE NOT NULL,
    token_type VARCHAR(20) NOT NULL,
    revoked    BOOLEAN NOT NULL DEFAULT FALSE,
    expired    BOOLEAN NOT NULL DEFAULT FALSE,
    user_id    INTEGER NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);

CREATE TABLE projects
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE tasks
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    project_id  INTEGER NOT NULL,
    CONSTRAINT fk_project
        FOREIGN KEY (project_id)
            REFERENCES projects (id)
);

CREATE TABLE records
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER NOT NULL,
    task_id     INTEGER NOT NULL,
    start_time  TIMESTAMP NOT NULL,
    end_time    TIMESTAMP NOT NULL,
    description TEXT,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),
    CONSTRAINT fk_task
        FOREIGN KEY (task_id)
            REFERENCES tasks (id)
);
