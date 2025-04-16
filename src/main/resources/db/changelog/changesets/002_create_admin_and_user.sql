insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (name, username, password)
values
('user', 'user@gmail.com', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
('admin', 'admin@gmail.com', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);