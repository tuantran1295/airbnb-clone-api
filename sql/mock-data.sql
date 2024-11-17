INSERT INTO "user" (username, password, email, fullname, type, status)
-- VALUES ('tuantran', 'a.123456', 'tuan@gmail.com', 'John Doe', 1, 1)
SELECT
    'tuantran_' || i,
	'a.123456',
	('user_' || i || '@gmail.com' ),
	'John Doe',
	1,
	1
    FROM
    generate_series(1, 10) as i

-- INSERT INTO homestay (name, description, type, host_id, status,
-- phone_number, address, longtitude, latitude, geom, images, guests,
-- bedrooms, bathrooms)
-- VALUES
-- ('Autumn at Homie - a romantic sea-view room',
-- 'An ideal place to relax with a sea view.', 1, 1, 1,
-- '0326405056',
-- 'Ngũ Hành Sơn, Đà Nẵng, Vietnam',
-- '34.05223',
-- '41.85003',
-- '2720546',
-- '{"https://picsum.photos/200"}',
-- 3, 3, 2)

INSERT INTO homestay (name, description, type, host_id, status, 
phone_number, address, longtitude, latitude, geom, images, guests,
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
    generate_series(1, 50) as i