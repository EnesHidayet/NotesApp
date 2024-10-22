
INSERT INTO public.tbl_user (username, password) VALUES ('user1', '$2a$10$V2UuPZLY7Pzu6ihGbA0Yc.lCHmZ7KCr0Ahdm5IPcYkES/HO0bD1NO');
INSERT INTO public.tbl_user (username, password) VALUES ('user2', '$2a$10$WDtUVEjZuuQ7YNOfweEOu.5BxWq/yRH/LIyU9jv0g.TYjP8DfiMfC');
INSERT INTO public.tbl_user (username, password) VALUES ('user3', '$2a$10$WDtUVEjZuuQ7YNOfweEOu.5BxWq/yRH/LIyU9jv0g.TYjP8DfiMfC');

INSERT INTO public.role (name) VALUES ('ROLE_USER');

INSERT INTO public.tbl_user_roles (user_username, roles_name) VALUES ('user1', 'ROLE_USER');
INSERT INTO public.tbl_user_roles (user_username, roles_name) VALUES ('user2', 'ROLE_USER');
INSERT INTO public.tbl_user_roles (user_username, roles_name) VALUES ('user3', 'ROLE_USER');