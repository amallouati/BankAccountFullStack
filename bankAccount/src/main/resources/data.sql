INSERT INTO BANK_ACCOUNT (balance,libelle) values (3000,'Compte epargne'),(1000,'Current account'),(4500,'salary account');

INSERT INTO OPERATION (amount, date, type, account_id) values
    (200,'2020-01-01','DEPOSIT',1),
     (200,'2020-01-01','WITHDRAWAL',1),
      (400,'2020-01-01','DEPOSIT',1),
       (200,'2020-01-01','DEPOSIT',1),
        (200,'2020-01-01','WITHDRAWAL',1),
    (100,'2020-01-01','WITHDRAWAL',2),
    (2000,'2020-01-01','DEPOSIT',3);