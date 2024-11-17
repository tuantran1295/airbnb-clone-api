INSERT INTO "user" (username, password, email, fullname, type, status)
SELECT
    'tuantran_' || i,
	'a.123456',
	('user_' || i || '@gmail.com' ),
	'John Doe',
	1,
	1
    FROM
    generate_series(1, 10) as i;

INSERT INTO homestay (name, description, type, host_id, status, 
phone_number, address, longitude, latitude, geom, images, guests,
bedrooms, bathrooms)
SELECT
    'Autumn at Homie - a romantic sea-view room ' || i,
	'An ideal place to relax with a sea view. ' || i,
	1, 1, 1,
	to_char(random() * 10000000000, 'FM(000) 000-0000'),
	i || ' Hamingway Street, London, England ' || i,
	random() * 100,
	random() * 100,
	to_char(random() * 10000000000, '99999999999'),
	'{"https://picsum.photos/200"}',
	floor(random()*4)+1,
	floor(random()*4)+1,
	floor(random()*4)+1
    FROM
    generate_series(1, 50) as i;

INSERT INTO homestay_availability(homestay_id, date, price, status)
SELECT
    i,
    days,
    floor(random()*(951))+50,
    0
FROM generate_series(1, 50) as i,
     generate_series(CURRENT_DATE::date, (CURRENT_DATE + 50)::date, '1 day'::interval) as days;