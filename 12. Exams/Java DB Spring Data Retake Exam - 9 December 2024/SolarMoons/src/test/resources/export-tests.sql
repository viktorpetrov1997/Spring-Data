INSERT INTO planets (id, name, diameter,  distance_from_sun, orbital_period, type)
VALUES (1, 'Mercury', 4879, 57910000, 87.97, 'TERRESTRIAL'),
       (2, 'Venus', 12104, 108200000, 224.7, 'TERRESTRIAL'),
       (3, 'Earth', 12742, 149000000, 365.26, 'TERRESTRIAL'),
       (4, 'Mars', 6779, 227900000, 687, 'TERRESTRIAL'),
       (5, 'Jupiter', 139820, 778500000, 4331, 'GAS_GIANT'),
       (6, 'Saturn', 116460, 1434000000, 10747, 'GAS_GIANT'),
       (7, 'Uranus', 50724, 2871000000, 30589, 'ICE_GIANT'),
       (8, 'Neptune', 49244, 4495000000, 59800, 'ICE_GIANT'),
       (9, 'Pluto', 2376, 5906000000, 90560, 'DWARF_PLANET');

INSERT INTO discoverers (id, first_name, last_name, nationality, occupation)
VALUES (1, 'Galileo', 'Galilei', 'Italian', 'astronomer'),
       (2, 'Christiaan', 'Huygens', 'Dutch', 'mathematician'),
       (3, 'Giovanni', 'Cassini', 'Italian', 'astronomer'),
       (4, 'William', 'Herschel', 'German-British', 'astronomer'),
       (5, 'Gerard', 'Kuiper', 'Dutch-American', 'planetary scientist'),
       (6, 'Asaph', 'Hall', 'American', 'mathematician'),
       (7, 'William', 'Lassell', 'British', 'merchant'),
       (8, 'James', 'Christy', 'American', 'astronomer'),
       (9, 'Stephen', 'Synnott', 'American', 'scientist');


INSERT INTO moons (id, name, discovered, distance_from_planet, radius, discoverer_id, planet_id)
VALUES (1, 'Moon', '1609-11-30', 384400, 1737.4, 1, 3),
       (2, 'Phobos', '1877-08-18', 9377, 11.27, 6, 4),
       (3, 'Deimos', '1877-08-12', 23460, 6.2, 6, 4),
       (4, 'Io', '1610-01-07', 421800, 1821.6, 1, 5),
       (5, 'Europa', '1610-01-07', 670900, 1560.8, 1, 5),
       (6, 'Ganymede', '1610-01-07', 1070400, 2634.1, 1, 5),
       (7, 'Callisto', '1610-01-07', 1882700, 2410.3, 1, 5),
       (8, 'Titan', '1655-03-25', 1221830, 2574.73, 2, 6),
       (9, 'Rhea', '1672-12-23', 527040, 763.8, 3, 6),
       (10, 'Iapetus', '1671-10-25', 3560820, 734.5, 3, 6),
       (11, 'Tethys', '1684-03-21', 294619, 531.1, 3, 6),
       (12, 'Dione', '1684-03-21', 377396, 561.4, 3, 6),
       (13, 'Titania', '1787-01-11', 435910, 788.4, 4, 7),
       (14, 'Oberon', '1787-01-11', 583520, 761.4, 4, 7),
       (15, 'Ariel', '1851-10-24', 191020, 578.9, 7, 7),
       (16, 'Umbriel', '1851-10-24', 266000, 584.7, 7, 7),
       (17, 'Miranda', '1948-02-16', 129390, 235.8, 5, 7),
       (18, 'Triton', '1846-10-10', 354800, 1353.4, 7, 8),
       (19, 'Nereid', '1949-05-01', 5513800, 170, 5, 8),
       (20, 'Galatea', '1989-07-30', 37200, 87.4, 9, 8),
       (21, 'Charon', '1978-06-22', 17536, 606, 8, 9);
