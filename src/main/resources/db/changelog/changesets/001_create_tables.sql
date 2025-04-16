
CREATE TABLE users
(
    id          int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        varchar(30) NOT NULL,
    username    varchar(30) UNIQUE NOT NULL,
    password    varchar(80) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE roles (
    id          serial,
    name        varchar(50) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id     int         NOT NULL,
    role_id     int         NOT NULL,
    foreign key (user_id) REFERENCES users (id),
    foreign key (role_id) REFERENCES roles (id)
);

CREATE TABLE cards
(
    id          int         GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    number      varchar(16) unique NOT NULL,
    date        timestamp   WITHOUT TIME ZONE NOT NULL,
    balance     numeric     NOT NULL DEFAULT 0,
    user_id     int         NOT NULL,
    status      varchar(16) NOT NULL,
    CONSTRAINT cards_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT pk_card PRIMARY KEY (id)
);

CREATE TABLE transactions
(
    id          int         GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    from_id     int         NOT NULL,
    to_id       int         NOT NULL,
    amount      numeric     NOT NULL,
    CONSTRAINT transactions_cards_from FOREIGN KEY (from_id) REFERENCES cards (id),
    CONSTRAINT transactions_cards_to FOREIGN KEY (to_id) REFERENCES cards (id),
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

CREATE TABLE limits
(
    id          int         GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id     int         NOT NULL,
    start_limit timestamp   WITHOUT TIME ZONE NOT NULL,
    end_limit   timestamp   WITHOUT TIME ZONE NOT NULL,
    amount      numeric     NOT NULL,
    CONSTRAINT users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT pk_limit PRIMARY KEY (id)
);

CREATE TABLE blocking_requests
(
    id          int         GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id     int         NOT NULL,
    card_id     int         NOT NULL,
    created     timestamp   WITHOUT TIME ZONE NOT NULL,
    status      varchar(16) NOT NULL,
    CONSTRAINT bloking_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT bloking_cards FOREIGN KEY (card_id) REFERENCES cards (id),
    CONSTRAINT pk_blocking_request PRIMARY KEY (id)
);