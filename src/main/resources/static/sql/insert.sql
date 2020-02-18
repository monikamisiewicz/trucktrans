INSERT INTO `company` (`id`, `name`, `nip`, street, street_number, post_code, place, iban, bank)
VALUES (1, 'Truck Giant', '7629736580', 'Wojska Polskiego', '49a', '59-870', 'Leśna', '42150013313706439256596152',
        'Santander');


INSERT INTO `contractors` (`id`, `name`, `nip`, street, street_number, post_code, place)
VALUES (1, 'Truck Tech', '7629736580', 'Wojska Polskieg', '49a', '59-870', 'Leśna'),
       (2, 'Grand Logistics', '3923391981', 'Wiśniowa', '17', '34-287', 'Bielsko-Biała'),
       (3, 'Deltacar Spedition Ltd.', '1145439600', 'Widokowa', '11', '22-100', 'Warszawa'),
       (4, 'Usługi Transportowe, Jurga Stanisław', '9517462575', 'T. Kościuszki', '59', '50-009', 'Wrocław'),
       (5, 'Majewski Krzysztof, Usługi transportowe', '1258071082', 'Al. Jana Pawła II', '39b', '58-560',
        'Jelenia Góra'),
       (6, 'Transport Towarowy Tranz-Silesia', '4966959182', 'Bolesława Chrobrego', '2a/13', '58-300', 'Wałbrzych');



INSERT INTO `orders` (`id`, `date`, `order_number`, `direction_from`, `direction_to`, `goods`, `quantity`, `unit`,
                      `value`, comment)
VALUES (1, '28-08-2019', '2019/08/01R', 'Bolesławiec', 'Żary Meldrosan', 'żwir', '26.30', 'kurs', '536.00', ''),
       (2,'2019-08-28', '2019/08/02R', 'Goczałków', 'Nowogród', 'piasek granitowy','3', 'kurs', '120.00', '' )
;

INSERT INTO tractors (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(1, 'DAF ', 'CF', 'DLB3421', '98076543212345678', '2020-07-12', '2020-08-11');

INSERT INTO trailers (`id`,make, model, registration_number, vin, insurance_expires, next_technical_inspection ) VALUES
(1, 'WIELTON ', '', 'DLU8865', '98086754212345678', '2021-03-19', '2022-08-13');


INSERT INTO `drivers` VALUES (5,'2020-02-10','Jan','Kowalski','79081301616','9872359874',1,'2020-02-21','2020-02-04','2020-02-21',1)
;


INSERT INTO `invoices` (`id`, `invoice_number`, `invoice_date`, `service_date`, `place`, days, payment_method, service_description, unit, quantity,unit_price, vat_rate, annotations, contractor_id, company_id) VALUES
(1, '1/2019', '2019-07-12', '2019-07-12', 'Leśna', 30, '', 'Transport piasku granitowego Okmiany-Goczałków', 'kurs', 2, 19.00, 23, '63 tony', '1', '1' ),
(2, '2/2019', '2019-07-14', '2019-07-14', 'Leśna', 30, '', 'Transport kruszywa Bolesławiec-Sulików', 'kurs', 10, 25 , 23, '63 tony', '2', '1'),
(3, '3/2019', '2019-07-16', '2019-07-16', 'Leśna', 45, '', 'Transport wapna ', 'kurs', 4, 19.00, 23, '63 tony', '2', '1'),
(4, '4/2019', '2019-07-16', '2019-07-16', 'Leśna', 30, '', 'Transport piasku granitowego Okmiany-Goczałków', 'kurs', 2, 19.00, 23, '63 tony', '3', '1'),
(5, '5/2019', '2019-07-19', '2019-07-19', 'Leśna', 60, '', 'Transport piasku granitowego Okmiany-Goczałków', 'kurs', 2, 19.00, 23, '63 tony', '4', '1')
;
