insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (name, username, password)
values
('user', 'user@gmail.com', '$2a$10$8SSCtzB5JXiahzeCw/Z5EOPza.EvuLe560jdhsStP.iRMyqmiAbgy'),
('admin', 'admin@gmail.com', '$2a$10$8SSCtzB5JXiahzeCw/Z5EOPza.EvuLe560jdhsStP.iRMyqmiAbgy');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);