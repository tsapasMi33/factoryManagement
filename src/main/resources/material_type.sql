insert into public.material_type (base_price, has_length, has_thickness, has_width, created_by_id, created_date, id, last_modified_by_id, last_modified_date, material, name, pricing_method)
values  (750, true, true, true, 2, '2023-12-03 14:17:03.301227', 1, 2, '2023-12-03 14:17:03.301227', 'T304', 'Plate', 'CUBE_METER'),
        (380, true, true, true, 2, '2023-12-03 14:17:58.813905', 2, 2, '2023-12-03 14:17:58.813905', 'T430', 'Plate', 'CUBE_METER'),
        (11.5, true, false, false, 2, '2023-12-03 14:18:55.497786', 3, 2, '2023-12-03 14:18:55.497786', 'T304', 'Tube(40x40)', 'METER'),
        (5.5, true, false, false, 2, '2023-12-03 14:20:03.734989', 4, 2, '2023-12-03 14:20:03.734989', 'T430', 'Tube(40x40)', 'METER'),
        (9.5, true, false, false, 2, '2023-12-03 14:21:48.997359', 5, 2, '2023-12-03 14:22:01.012862', 'T304', 'Tube(25x25)', 'METER'),
        (14, true, false, true, 2, '2023-12-03 14:23:26.781062', 6, 2, '2023-12-03 14:23:26.781062', 'OTHER', 'MDF Board', 'SQUARE_METER'),
        (13.2, true, false, false, 2, '2023-12-03 14:25:05.505486', 7, 2, '2023-12-03 14:25:05.505486', 'T304', 'Omega(150)', 'METER'),
        (6.1, true, false, false, 2, '2023-12-04 10:41:58.793762', 8, 2, '2023-12-04 10:41:58.793762', 'T430', 'Omega(150)', 'METER'),
        (4.5, true, false, false, null, '2023-12-03 14:22:29.293800', 9, 2, '2023-12-04 10:50:46.576386', 'T430', 'Tube(25x25)', 'METER'),
        (null, false, false, false, 2, '2023-12-05 18:11:49.101810', 10, 2, '2023-12-05 18:11:49.101810', 'OTHER', 'TestMaterial', 'UNIT');