insert into currencies (code, name) VALUES ('KZT', 'Tenge'), ('USD', 'Dollar'), ('EUR', 'Euro');

insert into exchange_rates (exchange_date, exchange_rate, currency_id_from, currency_id_to)
values ('2023-12-27 14:44:16.000000', 462.42, 2, 1),
       ('2023-12-27 14:46:16.000000', 464.42, 2, 1),
       ('2023-12-27 14:48:16.000000', 0.0022, 1, 2),
       ('2023-12-27 14:50:16.000000', 0.0021, 1, 2),
       ('2023-12-27 14:50:16.000000', 0.0020, 1, 3),
       ('2023-12-27 14:55:16.000000', 0.0020, 1, 3);