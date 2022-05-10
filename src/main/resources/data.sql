INSERT INTO Person (id, name) VALUES (1, 'Jan Kowalski');
INSERT INTO Person (id, name) VALUES (2, 'Magdalena Nowak');
INSERT INTO Person (id, name) VALUES (3, 'Robert Mak');
INSERT INTO Person (id, name) VALUES (4, 'Ania Kowalska');

INSERT INTO Place (id, name, unit_price, place_area, description) VALUES (1, 'Hilton', 400, 80, 'Opis');
INSERT INTO Place (id, name, unit_price, place_area, description) VALUES (2, 'Amber', 200, 30, 'Opis');
INSERT INTO Place (id, name, unit_price, place_area, description) VALUES (3, 'Hampton', 300, 50, 'Opis');
INSERT INTO Place (id, name, unit_price, place_area, description) VALUES (4, 'Sheraton', 450, 50, 'Opis');

INSERT INTO Reservation (id, tenant_id, landlord_id, place_id, date_from, date_to, price)
    VALUES (1, 1, 4, 1, '2022-05-01', '2022-05-03', 800);
INSERT INTO Reservation (id, tenant_id, landlord_id, place_id, date_from, date_to, price)
    VALUES (2, 2, 3, 2, '2022-04-20', '2022-04-24', 600);
INSERT INTO Reservation (id, tenant_id, landlord_id, place_id, date_from, date_to, price)
    VALUES (3, 3, 2, 3, '2022-04-22', '2022-04-30', 2100);
INSERT INTO Reservation (id, tenant_id, landlord_id, place_id, date_from, date_to, price)
    VALUES (4, 4, 1, 4, '2022-05-01', '2022-05-08', 2700);
INSERT INTO Reservation (id, tenant_id, landlord_id, place_id, date_from, date_to, price)
    VALUES (5, 2, 1, 1, '2022-03-21', '2022-03-28', 2400);
