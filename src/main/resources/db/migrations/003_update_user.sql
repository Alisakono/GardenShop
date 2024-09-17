-- liquibase formatted sql
-- changeset Konovalov:003

ALTER TABLE users
    ADD COLUMN refresh_token VARCHAR(255);