-- V1__create_project_task_shift.sql
CREATE TABLE shift (
                       id BIGSERIAL PRIMARY KEY,
                       shift_name VARCHAR(50) NOT NULL,
                       shift_description VARCHAR(100),
                       start_time TIME NOT NULL,
                       end_time TIME NOT NULL,
                       duration INTERVAL NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE project (
                         id BIGSERIAL PRIMARY KEY,
                         project_name VARCHAR(100) NOT NULL,
                         project_description VARCHAR(255),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE task (
                      id BIGSERIAL PRIMARY KEY,
                      task_name VARCHAR(100) NOT NULL,
                      task_description VARCHAR(255),
                      status VARCHAR(50) DEFAULT 'PENDING',
                      start_time TIMESTAMP,
                      end_time TIMESTAMP,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                      project_id BIGINT,
                      shift_id BIGINT,

                      CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES project(id),
                      CONSTRAINT fk_task_shift FOREIGN KEY (shift_id) REFERENCES shift(id)
);

CREATE INDEX idx_task_project ON task(project_id);
CREATE INDEX idx_task_shift ON task(shift_id);