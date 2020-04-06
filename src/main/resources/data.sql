USE `truck-trans`;

INSERT INTO `company` (`id`, `name`, `nip`, street, street_number, post_code, place, iban, bank)
VALUES (1, 'Truck Giant', '7629736580', 'Wojska Polskiego', '49a', '59-870', 'Leśna', '42150013313706439256596152',
        'Santander');


INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (1, 'Truck Tech', '7629736580', 'Wojska Polskieg', '49a', '59-870', 'Leśna');
INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (2, 'Grand Logistics', '3923391981', 'Wiśniowa', '17', '34-287', 'Bielsko-Biała');
INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (3, 'Deltacar Spedition Ltd.', '1145439600', 'Widokowa', '11', '22-100', 'Warszawa');
INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (4, 'Usługi Transportowe, Jurga Stanisław', '9517462575', 'T. Kościuszki', '59', '50-009', 'Wrocław');
INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (5, 'Majewski Krzysztof, Usługi transportowe', '1258071082', 'Al. Jana Pawła II', '39b', '58-560',
        'Jelenia Góra');
INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (6, 'Transport Towarowy Tranz-Silesia', '4966959182', 'Bolesława Chrobrego', '2a/13', '58-300', 'Wałbrzych');



INSERT INTO `orders` (`id`, `date`, `order_number`, `direction_from`, `direction_to`, `goods`, `quantity`, `unit`,`value`, comment)
VALUES (1, '28-08-2019', '2019/08/01R', 'Bolesławiec', 'Żary Meldrosan', 'żwir', '26.30', 'kurs', '536.00', '');
INSERT INTO `orders` (`id`, `date`, `order_number`, `direction_from`, `direction_to`, `goods`, `quantity`, `unit`,`value`, comment)
VALUES (2,'2019-08-28', '2019/08/02R', 'Goczałków', 'Nowogród', 'piasek granitowy','3', 'kurs', '120.00', '' );

INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(1, 'DAF ', 'CF', 'DLB3421', '98076543212345678', '2020-07-12', '2020-08-11');
INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(2, 'MAN', 'TGX', 'DLU 3245', '3D7KS29C67G763440', '2028-06-20', '2020-09-19');
INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(3, 'SCANIA', 'XT', 'FZ 465FT', '5XYKT3A66DG370762', '2020-02-29', '2020-02-29');
INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(4, 'DAF', 'CF', 'DLB 45TG', '1FVHCYDJ85HV14123', '2020-02-22', '2022-04-08');
INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(5, 'DAF', 'XF', 'DLU 45654', '4S3BK6354S6355265', '2021-12-20', '2021-06-23');
INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(6, 'MAN', 'TGS', 'DLU 35PO', '1G8DC18D0CF150367',  '2020-04-14', '2020-07-06');


INSERT INTO trailers (`id`,make, body, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(1, 'WIELTON ', 'steel', 'DLU8865', '98086754212345678', '2021-03-19', '2022-08-13');
INSERT INTO trailers (`id`,make, body, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(2, 'SCHMITZ', 'aluminium', 'DLB 44UG', 'JTDKB20U983336905', '2034-06-13', '2020-12-20');
INSERT INTO trailers (`id`,make, body, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(3, 'SCHMITZ', 'steel', 'FZ PO678', '1HGFA16807L092733', '2020-02-22', '2020-02-29');
INSERT INTO trailers (`id`,make, body, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(4, 'Wielton', 'aluminium', 'DLB F633', '1GNKRGKD8DJ145866', '2020-07-25', '2021-05-10' );
INSERT INTO trailers (`id`,make, body, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(5, 'Wielton', 'aluminium', 'DLB 437P', 'JNKBY31AXYM379859', '2020-07-18', '2023-08-18');


INSERT INTO `drivers` VALUES
(1,'1980-08-14','2027-03-03','Jan','Kowalski','2019-07-16','2020-08-16','27040937840','560123432','1',1);
INSERT INTO `drivers` VALUES
(5, '1976-02-13', '2030-02-19', 'Krzysztof', 'Litwiniuk', '2020-02-13', '2020-02-20', '55101566360', '3324234', 2, 2 );
INSERT INTO `drivers` VALUES
(7, '1983-02-04', '2029-02-20',  'Jarosław', 'Stempel', '2020-02-03', '2020-02-27', '24062743417', '9874239', 3,3);



INSERT INTO `invoices` (`id`, `invoice_number`, `invoice_date`, `service_date`, `place`, days, payment_method, annotations, is_paid, contractor_id, company_id ) VALUES
(1, '1/2019', '2019-07-12', '2019-07-12', 'Leśna', 30, 'cash', '', 'false', '1', '1' );
INSERT INTO `invoices` (`id`, `invoice_number`, `invoice_date`, `service_date`, `place`, days, payment_method, annotations, is_paid, contractor_id, company_id ) VALUES
(2, '2/2019', '2019-07-14', '2019-07-14', 'Leśna', 30, 'cash', '', 'true', '2', '1');
INSERT INTO `invoices` (`id`, `invoice_number`, `invoice_date`, `service_date`, `place`, days, payment_method, annotations, is_paid, contractor_id, company_id ) VALUES
(3, '3/2019', '2019-07-16', '2019-07-16', 'Leśna', 45, 'cash ', ' ', 'false ', '2', '1');
INSERT INTO `invoices` (`id`, `invoice_number`, `invoice_date`, `service_date`, `place`, days, payment_method, annotations, is_paid, contractor_id, company_id ) VALUES
(4, '4/2019', '2019-07-16', '2019-07-16', 'Leśna', 30, 'cash', ' ', 'false',  '3', '1');
INSERT INTO `invoices` (`id`, `invoice_number`, `invoice_date`, `service_date`, `place`, days, payment_method, annotations, is_paid, contractor_id, company_id ) VALUES
(5, '5/2019', '2019-07-19', '2019-07-19', 'Leśna', 60, 'cash', ' ', 'true',  '4', '1');


INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(9,123,  'Sprzedaż piasku', 'tona', 24.50,23,1);
INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(23,4, 'Transport żwiru', 'kurs', 120, 23, 2);
INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(25, 3, 'Transport żwiru', 'kurs', 125, 23, 2);
INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(27, 5, 'Transport żwiru', 'kurs', 120, 23, 2);
INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(28, 6, 'Transport kruszywa Sulików-Myślibórz', 'kurs', 1239.39, 23, 3);
INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(29, 2,'Transport kruszywa granitowego', 'kurs', 676, 23, 4);
INSERT INTO 'items' (id, quantity, service_description, unit, unit_price, vat_rate, invoice_id) VALUES
(30, 23, 'Transport kruszywa Okmiany-Goczałków', 'kurs', 374.09, 23,5);
