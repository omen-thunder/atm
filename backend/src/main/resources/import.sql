insert into account (id, balance) values (1000, 1500);

insert into card (id, card_status, expiry_date, pin, account_id) values (2000, 1, to_date('01/01/2025', 'DD/MM/YYYY'), '$2a$10$z5rqaBAezlGP.9F.gRvFS.79GcnZgRte9o5PVm.gGknDtUFBC8xx.', 1000);