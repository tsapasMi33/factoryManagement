INSERT INTO public.users (cost_per_minute, enabled, created_by_id, created_date, last_modified_by_id,
                          last_modified_date, password, role, username)
VALUES
    (null, true, null, '1970-01-01 00:00:00.000000', 1, '1970-01-01 00:00:00.000000',
     '$2a$12$XTo..ZJDKz2FJMicdzZe6.Mp.ilmYBL4ajfZlwX/V5xTaQmZlGzO2', 'ADMIN', 'admin'),
    (0, true, 1, '2023-12-02 16:04:29.633833', 1, '2023-12-02 16:04:29.633833',
     '$2a$10$lbTH1WGbhGJPVkRhBHJD2.1EQGWJdDoWXCDNlWyU9m9ZjrDZ5FiUm', 'ADMIN', 'LukeManager'),
    (0.5, true, 1, '2023-12-02 16:05:12.948732', 2, '2023-12-02 16:05:12.948732',
     '$2a$10$GDoio9PvqWIZ9iog8q1zPuML0RGblPWNv31hBwXiG4Vqn3n0kIYHK', 'CUTTER', 'SergioCutter'),
    (0.62, true, 1, '2023-12-02 16:05:59.307402', 2, '2023-12-02 16:05:59.307402',
     '$2a$10$TOHf9gfTgUKYtz9gKvxxY.k0RXI2i1m1UpevgnWL.RULsvdk8MbZm', 'BENDER', 'JohnBender'),
    (0.55, true, 1, '2023-12-02 16:06:31.592642', 2, '2023-12-02 16:06:31.592642',
     '$2a$10$RzrLuIE4sOMT2Msri4nGr.oUKgWzct2rd2MuMjmc59HDnNxDwgf.e', 'COMBINER', 'EnriqueCombiner'),
    (0.95, true, 1, '2023-12-02 16:07:17.178038', 2, '2023-12-02 16:07:17.178038',
     '$2a$10$9Puo4aMnw6ieL0kdE1AMHuJ0mbwiLRhJqHU5RyKut1xyFxwW1S5qy', 'WELDER', 'PaulWelder'),
    (1, true, 1, '2023-12-02 16:07:54.115708', 2, '2023-12-02 16:07:54.115708',
     '$2a$10$XNM1lEd/3Nymt/hn4PoKMuMEzm6D48yicE12uzwNx8I25yW4ab9Cy', 'WELDER', 'SiliusWelder'),
    (0.55, true, 1, '2023-12-02 16:08:47.131836', 2, '2023-12-02 16:08:47.131836',
     '$2a$10$niqs.wX3DBc5ACb3P4jx2.U2yeLSaVCY8KN6DsUHxLUW3zc7EC46G', 'COMBINER', 'SamCombiner'),
    (0.9, true, 1, '2023-12-02 16:09:55.152774', 2, '2023-12-02 16:09:55.152774',
     '$2a$10$hBWtRcOFFntyJAW4rC6vEebOnsl7k2iKKj/rXiL0f8.jhepgDZwDO', 'WELDER', 'SebastianWelder'),
    (0.62, true, 1, '2023-12-02 16:10:30.513739', 2, '2023-12-02 16:10:30.513739',
     '$2a$10$pGDpZm9wZrXUhA0iRzgRmeG.uMwaMDsAClUaGCLPuOKqsBxlNPMk6', 'FINISHER', 'GeorgesFinisher'),
    (0.69, true, 1, '2023-12-02 16:11:22.309842', 2, '2023-12-02 16:11:22.309842',
     '$2a$10$8htNeLSiB6b3W.Vq8/i7d.beg3RqIzD15kUeb4AT9dW3Y8cm9ekdG', 'FINISHER', 'NunoFinisher'),
    (0, true, 1, '2023-12-02 16:11:47.592033', 2, '2023-12-02 16:11:47.592033',
     '$2a$10$Hjf8jEaxShIHBU3APeRQ5ev4nib0R6mniHsXM.qSa5aH2VnFOhZOi', 'PACKER', 'AdrianPacker'),
    (1.2, true, 1, '2023-12-02 16:09:55.152774', 2, '2023-12-02 16:09:55.152774',
     '$2a$10$hBWtRcOFFntyJAW4rC6vEebOnsl7k2iKKj/rXiL0f8.jhepgDZwDO', 'ASSEMBLER', 'GuntherAssembler');


INSERT INTO public.client (discount_percentage, created_by_id, created_date, last_modified_by_id,
                           last_modified_date, city, company_type, country, cp, name, number, street)
VALUES (10, 1, '2023-11-15 21:22:48.618517', 1, '2023-11-15 21:22:48.618517', 'X city', 'ltd', 'Belgium', '9999',
        'CompanyX', '18b', 'X street');


insert into public.product_family (created_by_id, created_date, id, last_modified_by_id, last_modified_date, name)
values  (2, '2023-12-03 14:49:43.739461', 1, 2, '2023-12-03 14:49:43.739461', 'Table');

insert into public.product_family_steps (step_order, product_family_id, production_path)
values  (0, 1, 'ENCODED'),
        (1, 1, 'PRODUCTION'),
        (2, 1, 'CUT'),
        (3, 1, 'BENT'),
        (4, 1, 'COMBINED'),
        (5, 1, 'WELDED'),
        (6, 1, 'FINISHED'),
        (7, 1, 'PACKED'),
        (8, 1, 'SENT');

insert into public.material_type (base_price, has_length, has_thickness, has_width, created_by_id, created_date, id, last_modified_by_id, last_modified_date, material, name, pricing_method)
values  (750, true, true, true, 2, '2023-12-03 14:17:03.301227', 1, 2, '2023-12-03 14:17:03.301227', 'T304', 'Plate', 'CUBE_METER'),
        (380, true, true, true, 2, '2023-12-03 14:17:58.813905', 2, 2, '2023-12-03 14:17:58.813905', 'T430', 'Plate', 'CUBE_METER'),
        (11.5, true, false, false, 2, '2023-12-03 14:18:55.497786', 3, 2, '2023-12-03 14:18:55.497786', 'T304', 'Tube(40x40)', 'METER'),
        (5.5, true, false, false, 2, '2023-12-03 14:20:03.734989', 4, 2, '2023-12-03 14:20:03.734989', 'T430', 'Tube(40x40)', 'METER'),
        (9.5, true, false, false, 2, '2023-12-03 14:21:48.997359', 5, 2, '2023-12-03 14:22:01.012862', 'T304', 'Tube(25x25)', 'METER'),
        (14, true, false, true, 2, '2023-12-03 14:23:26.781062', 6, 2, '2023-12-03 14:23:26.781062', 'OTHER', 'MDF Board', 'SQUARE_METER'),
        (13.2, true, false, false, 2, '2023-12-03 14:25:05.505486', 7, 2, '2023-12-03 14:25:05.505486', 'T304', 'Omega(150)', 'METER'),
        (6.1, true, false, false, 2, '2023-12-04 10:41:58.793762', 8, 2, '2023-12-04 10:41:58.793762', 'T430', 'Omega(150)', 'METER'),
        (4.5, true, false, false, null, '2023-12-03 14:22:29.293800', 9, 2, '2023-12-04 10:50:46.576386', 'T430', 'Tube(25x25)', 'METER');


insert into public.component (length, price, requires_bending, requires_cutting, thickness, width, created_by_id, created_date, last_modified_by_id, last_modified_date, type_id, name)
values  (800, 38.4, true, true, 8, 800, 2, '2023-12-03 14:38:13.412737', 2, '2023-12-03 14:38:13.412737', 1, 'Tabletop'),
        (1100, 52.80000000000001, true, true, 8, 800, 2, '2023-12-04 10:27:38.672629', 2, '2023-12-04 10:27:38.672629', 1, 'Tabletop'),
        (1300, 62.4, true, true, 8, 800, 2, '2023-12-04 10:27:52.957426', 2, '2023-12-04 10:27:52.957426', 1, 'Tabletop'),
        (1500, 72, true, true, 8, 800, 2, '2023-12-04 10:28:22.674342', 2, '2023-12-04 10:28:22.674342', 1, 'Tabletop'),
        (1700, 81.60000000000001, true, true, 8, 800, 2, '2023-12-04 10:28:33.029231', 2, '2023-12-04 10:28:33.029231', 1, 'Tabletop'),
        (1900, 91.2, true, true, 8, 800, 2, '2023-12-04 10:28:44.925853', 2, '2023-12-04 10:28:44.925853', 1, 'Tabletop'),
        (2100, 100.8, true, true, 8, 800, 2, '2023-12-04 10:28:59.588696', 2, '2023-12-04 10:28:59.588696', 1, 'Tabletop'),
        (800, 19.456000000000003, true, true, 8, 800, 2, '2023-12-04 10:29:52.710559', 2, '2023-12-04 10:29:52.710559', 2, 'Tabletop'),
        (1100, 26.75200000000001, true, true, 8, 800, 2, '2023-12-04 10:30:03.687096', 2, '2023-12-04 10:30:03.687096', 2, 'Tabletop'),
        (1300, 31.616000000000003, true, true, 8, 800, 2, '2023-12-04 10:30:26.298917', 2, '2023-12-04 10:30:26.298917', 2, 'Tabletop'),
        (1500, 36.480000000000004, true, true, 8, 800, 2, '2023-12-04 10:30:37.732815', 2, '2023-12-04 10:30:37.732815', 2, 'Tabletop'),
        (1700, 41.34400000000001, true, true, 8, 800, 2, '2023-12-04 10:30:58.210589', 2, '2023-12-04 10:30:58.210589', 2, 'Tabletop'),
        (1900, 46.208000000000006, true, true, 8, 800, 2, '2023-12-04 10:31:08.303321', 2, '2023-12-04 10:31:08.303321', 2, 'Tabletop'),
        (2100, 51.07200000000001, true, true, 8, 800, 2, '2023-12-04 10:31:22.267310', 2, '2023-12-04 10:31:22.267310', 2, 'Tabletop'),
        (690, 6.6654, false, false, null, 690, 2, '2023-12-03 14:40:29.974795', 2, '2023-12-03 14:40:29.974795', 7, 'Underboard'),
        (990, 9.5634, false, false, null, 690, 2, '2023-12-04 10:33:34.708009', 2, '2023-12-04 10:33:34.708009', 7, 'Underboard'),
        (1190, 11.4954, false, false, null, 690, 2, '2023-12-04 10:33:49.857179', 2, '2023-12-04 10:33:49.857179', 7, 'Underboard'),
        (1390, 13.427399999999997, false, false, null, 690, 2, '2023-12-04 10:34:08.213485', 2, '2023-12-04 10:34:08.213485', 7, 'Underboard'),
        (1590, 15.359399999999999, false, false, null, 690, 2, '2023-12-04 10:34:20.499637', 2, '2023-12-04 10:34:20.499637', 7, 'Underboard'),
        (1790, 17.2914, false, false, null, 690, 2, '2023-12-04 10:34:32.876267', 2, '2023-12-04 10:34:32.876267', 7, 'Underboard'),
        (1990, 19.223399999999998, false, false, null, 690, 2, '2023-12-04 10:34:47.617408', 2, '2023-12-04 10:34:47.617408', 7, 'Underboard'),
        (620, 8.184, false, false, null, null, 2, '2023-12-03 14:43:33.845715', 2, '2023-12-03 14:43:33.845715', 8, 'Tableside'),
        (920, 12.144, false, false, null, null, 2, '2023-12-04 10:39:31.526522', 2, '2023-12-04 10:39:31.526522', 8, 'Tableside'),
        (1120, 14.784, false, false, null, null, 2, '2023-12-04 10:39:48.625877', 2, '2023-12-04 10:39:48.625877', 8, 'Tableside'),
        (1320, 17.424, false, false, null, null, 2, '2023-12-04 10:40:16.398986', 2, '2023-12-04 10:40:16.398986', 8, 'Tableside'),
        (1520, 20.064, false, false, null, null, 2, '2023-12-04 10:40:44.302328', 2, '2023-12-04 10:40:44.302328', 8, 'Tableside'),
        (1720, 22.703999999999997, false, false, null, null, 2, '2023-12-04 10:40:54.690588', 2, '2023-12-04 10:40:54.690588', 8, 'Tableside'),
        (1920, 25.343999999999998, false, false, null, null, 2, '2023-12-04 10:41:09.019850', 2, '2023-12-04 10:41:09.019850', 8, 'Tableside'),
        (620, 3.7819999999999996, false, false, null, null, 2, '2023-12-04 10:42:27.200290', 2, '2023-12-04 10:42:27.200290', 9, 'Tableside'),
        (920, 5.612, false, false, null, null, 2, '2023-12-04 10:42:46.905848', 2, '2023-12-04 10:42:46.905848', 9, 'Tableside'),
        (1120, 6.832, false, false, null, null, 2, '2023-12-04 10:43:21.167170', 2, '2023-12-04 10:43:21.167170', 9, 'Tableside'),
        (1320, 8.052, false, false, null, null, 2, '2023-12-04 10:43:30.541297', 2, '2023-12-04 10:43:30.541297', 9, 'Tableside'),
        (1520, 9.272, false, false, null, null, 2, '2023-12-04 10:44:17.429891', 2, '2023-12-04 10:44:17.429891', 9, 'Tableside'),
        (1720, 10.491999999999999, false, false, null, null, 2, '2023-12-04 10:44:27.088496', 2, '2023-12-04 10:44:27.088496', 9, 'Tableside'),
        (1920, 11.712, false, false, null, null, 2, '2023-12-04 10:44:37.544703', 2, '2023-12-04 10:44:37.544703', 9, 'Tableside'),
        (800, 9.200000000000001, false, false, null, null, 2, '2023-12-03 14:39:11.878708', 2, '2023-12-03 14:39:11.878708', 3, 'Foot'),
        (850, 9.775, false, false, null, null, 2, '2023-12-04 10:32:07.277172', 2, '2023-12-04 10:32:07.277172', 3, 'Foot'),
        (800, 4.4, false, false, null, null, 2, '2023-12-04 10:32:20.308349', 2, '2023-12-04 10:32:20.308349', 4, 'Foot'),
        (850, 4.675, false, false, null, null, 2, '2023-12-04 10:32:30.991075', 2, '2023-12-04 10:32:30.991075', 4, 'Foot'),
        (825, 40.8375, true, true, 8, 825, 2, '2023-12-03 14:42:37.718020', 2, '2023-12-03 14:42:37.718020', 1, 'Subtablet'),
        (1125, 55.6875, true, true, 8, 825, 2, '2023-12-04 10:35:32.301737', 2, '2023-12-04 10:35:32.301737', 1, 'Subtablet'),
        (1325, 65.5875, true, true, 8, 825, 2, '2023-12-04 10:35:44.670353', 2, '2023-12-04 10:35:44.670353', 1, 'Subtablet'),
        (1525, 75.4875, true, true, 8, 825, 2, '2023-12-04 10:36:02.177835', 2, '2023-12-04 10:36:02.177835', 1, 'Subtablet'),
        (1725, 85.3875, true, true, 8, 825, 2, '2023-12-04 10:36:14.546752', 2, '2023-12-04 10:36:14.546752', 1, 'Subtablet'),
        (1925, 95.28750000000001, true, true, 8, 825, 2, '2023-12-04 10:36:28.298560', 2, '2023-12-04 10:36:28.298560', 1, 'Subtablet'),
        (2125, 105.1875, true, true, 8, 825, 2, '2023-12-04 10:36:42.677675', 2, '2023-12-04 10:36:42.677675', 1, 'Subtablet'),
        (825, 20.691, true, true, 8, 825, 2, '2023-12-04 10:37:00.623375', 2, '2023-12-04 10:37:00.623375', 2, 'Subtablet'),
        (1125, 28.215, true, true, 8, 825, 2, '2023-12-04 10:37:11.448556', 2, '2023-12-04 10:37:11.448556', 2, 'Subtablet'),
        (1325, 33.231, true, true, 8, 825, 2, '2023-12-04 10:37:27.868470', 2, '2023-12-04 10:37:27.868470', 2, 'Subtablet'),
        (1525, 38.247, true, true, 8, 825, 2, '2023-12-04 10:37:41.024073', 2, '2023-12-04 10:37:41.024073', 2, 'Subtablet'),
        (1725, 43.263000000000005, true, true, 8, 825, 2, '2023-12-04 10:37:51.439130', 2, '2023-12-04 10:37:51.439130', 2, 'Subtablet'),
        (1925, 48.278999999999996, true, true, 8, 825, 2, '2023-12-04 10:38:17.540784', 2, '2023-12-04 10:38:17.540784', 2, 'Subtablet'),
        (2125, 53.295, true, true, 8, 825, 2, '2023-12-04 10:38:27.706235', 2, '2023-12-04 10:38:27.706235', 2, 'Subtablet'),
        (920, 8.74, false, false, null, null, 2, '2023-12-04 10:47:45.017560', 2, '2023-12-04 10:47:45.017560', 5, 'Footjoint'),
        (620, 5.9375, false, false, null, null, 2, '2023-12-04 10:47:12.033613', 2, '2023-12-04 10:47:12.033613', 5, 'Footjoint'),
        (1120, 10.64, false, false, null, null, 2, '2023-12-04 10:48:40.940081', 2, '2023-12-04 10:48:40.940081', 5, 'Footjoint'),
        (1320, 12.540000000000001, false, false, null, null, 2, '2023-12-04 10:48:54.028012', 2, '2023-12-04 10:48:54.028012', 5, 'Footjoint'),
        (1520, 14.44, false, false, null, null, 2, '2023-12-04 10:49:43.435258', 2, '2023-12-04 10:49:43.435258', 5, 'Footjoint'),
        (1720, 16.34, false, false, null, null, 2, '2023-12-04 10:50:01.764617', 2, '2023-12-04 10:50:01.764617', 5, 'Footjoint'),
        (1920, 18.24, false, false, null, null, 2, '2023-12-04 10:50:16.646707', 2, '2023-12-04 10:50:16.646707', 5, 'Footjoint'),
        (620, 2.79, false, false, null, null, 2, '2023-12-04 10:51:21.743380', 2, '2023-12-04 10:51:21.743380', 6, 'Footjoint'),
        (920, 4.140000000000001, false, false, null, null, 2, '2023-12-04 10:51:44.771614', 2, '2023-12-04 10:51:44.771614', 6, 'Footjoint'),
        (1120, 5.040000000000001, false, false, null, null, 2, '2023-12-04 10:52:06.197541', 2, '2023-12-04 10:52:06.197541', 6, 'Footjoint'),
        (1320, 5.94, false, false, null, null, 2, '2023-12-04 10:52:19.441429', 2, '2023-12-04 10:52:19.441429', 6, 'Footjoint'),
        (1520, 6.84, false, false, null, null, 2, '2023-12-04 10:52:27.956734', 2, '2023-12-04 10:52:27.956734', 6, 'Footjoint'),
        (1720, 7.74, false, false, null, null, 2, '2023-12-04 10:52:37.900277', 2, '2023-12-04 10:52:37.900277', 6, 'Footjoint'),
        (1920, 8.64, false, false, null, null, 2, '2023-12-04 10:53:25.218957', 2, '2023-12-04 10:53:25.218957', 6, 'Footjoint'),
        (595, 3.6294999999999997, false, false, null, null, 2, '2023-12-04 10:55:14.344227', 2, '2023-12-04 10:55:14.344227', 9, 'Table Reinforcement');





insert into public.product_variant (height, length, price, width, created_by_id, created_date, id, last_modified_by_id, last_modified_date, product_family_id, code, description, material, variant_identifier)
values  (850, 700, 225, 700, 2, '2023-12-05 10:28:34.301419', 1, 2, '2023-12-05 10:28:34.301419', 1, 'TABT304[L700xW700xH850]C1ST', 'Central table 70cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 1000, 245, 700, 2, '2023-12-05 10:32:02.920254', 2, 2, '2023-12-05 10:32:02.920254', 1, 'TABT304[L1000xW700xH850]C1ST', 'Central table 100cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 1200, 275, 700, 2, '2023-12-05 10:40:18.058653', 3, 2, '2023-12-05 10:40:18.058653', 1, 'TABT304[L1200xW700xH850]C1ST', 'Central table 120cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 1400, 295, 700, 2, '2023-12-05 10:42:58.155236', 4, 2, '2023-12-05 10:42:58.155236', 1, 'TABT304[L1400xW700xH850]C1ST', 'Central table 140cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 1600, 325, 700, 2, '2023-12-05 10:46:51.973141', 5, 2, '2023-12-05 10:46:51.973141', 1, 'TABT304[L1600xW700xH850]C1ST', 'Central table 160cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 1800, 350, 700, 2, '2023-12-05 10:50:05.927164', 6, 2, '2023-12-05 10:50:05.927164', 1, 'TABT304[L1800xW700xH850]C1ST', 'Central table 180cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 2000, 380, 700, 2, '2023-12-05 10:52:24.288170', 7, 2, '2023-12-05 10:52:24.288170', 1, 'TABT304[L2000xW700xH850]C1ST', 'Central table 200cm x 70cm x 85cm with one subtablet.', 'T304', 'C1ST'),
        (850, 700, 130, 700, 2, '2023-12-05 11:00:03.127341', 8, 2, '2023-12-05 11:00:03.127341', 1, 'TABT430[L700xW700xH850]C1ST', 'Central table 70cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST'),
        (850, 1000, 150, 700, 2, '2023-12-05 11:01:33.986997', 9, 2, '2023-12-05 11:01:33.986997', 1, 'TABT430[L1000xW700xH850]C1ST', 'Central table 100cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST'),
        (850, 1200, 165, 700, 2, '2023-12-05 11:04:41.202518', 10, 2, '2023-12-05 11:04:41.202518', 1, 'TABT430[L1200xW700xH850]C1ST', 'Central table 120cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST'),
        (850, 1400, 185, 700, 2, '2023-12-05 11:07:23.717840', 11, 2, '2023-12-05 11:07:23.717840', 1, 'TABT430[L1400xW700xH850]C1ST', 'Central table 140cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST'),
        (850, 1600, 200, 700, 2, '2023-12-05 11:11:21.394171', 12, 2, '2023-12-05 11:11:21.394171', 1, 'TABT430[L1600xW700xH850]C1ST', 'Central table 160cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST'),
        (850, 1800, 215, 700, 2, '2023-12-05 11:14:32.428442', 13, 2, '2023-12-05 11:14:32.428442', 1, 'TABT430[L1800xW700xH850]C1ST', 'Central table 180cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST'),
        (850, 2000, 235, 700, 2, '2023-12-05 11:16:04.889442', 14, 2, '2023-12-05 11:16:04.889442', 1, 'TABT430[L2000xW700xH850]C1ST', 'Central table 200cm x 70cm x 85cm with one subtablet.', 'T430', 'C1ST');

insert into public.product_variant_components (components_id, product_variant_id)
values  (1, 1),
        (15, 1),
        (22, 1),
        (22, 1),
        (22, 1),
        (22, 1),
        (36, 1),
        (36, 1),
        (36, 1),
        (36, 1),
        (40, 1),
        (2, 2),
        (16, 2),
        (22, 2),
        (22, 2),
        (23, 2),
        (23, 2),
        (36, 2),
        (36, 2),
        (36, 2),
        (36, 2),
        (41, 2),
        (3, 3),
        (17, 3),
        (22, 3),
        (22, 3),
        (24, 3),
        (24, 3),
        (36, 3),
        (36, 3),
        (36, 3),
        (36, 3),
        (42, 3),
        (4, 4),
        (18, 4),
        (22, 4),
        (22, 4),
        (25, 4),
        (25, 4),
        (36, 4),
        (36, 4),
        (36, 4),
        (36, 4),
        (43, 4),
        (5, 5),
        (19, 5),
        (22, 5),
        (22, 5),
        (26, 5),
        (26, 5),
        (36, 5),
        (36, 5),
        (36, 5),
        (36, 5),
        (44, 5),
        (6, 6),
        (20, 6),
        (22, 6),
        (22, 6),
        (27, 6),
        (27, 6),
        (36, 6),
        (36, 6),
        (36, 6),
        (36, 6),
        (45, 6),
        (68, 6),
        (7, 7),
        (21, 7),
        (22, 7),
        (22, 7),
        (28, 7),
        (28, 7),
        (36, 7),
        (36, 7),
        (36, 7),
        (36, 7),
        (46, 7),
        (68, 7),
        (8, 8),
        (15, 8),
        (29, 8),
        (29, 8),
        (29, 8),
        (29, 8),
        (38, 8),
        (38, 8),
        (38, 8),
        (38, 8),
        (47, 8),
        (9, 9),
        (16, 9),
        (29, 9),
        (29, 9),
        (30, 9),
        (30, 9),
        (38, 9),
        (38, 9),
        (38, 9),
        (38, 9),
        (48, 9),
        (10, 10),
        (17, 10),
        (29, 10),
        (29, 10),
        (31, 10),
        (31, 10),
        (38, 10),
        (38, 10),
        (38, 10),
        (38, 10),
        (49, 10),
        (11, 11),
        (18, 11),
        (29, 11),
        (29, 11),
        (32, 11),
        (32, 11),
        (38, 11),
        (50, 11),
        (12, 12),
        (19, 12),
        (29, 12),
        (29, 12),
        (33, 12),
        (33, 12),
        (38, 12),
        (38, 12),
        (38, 12),
        (38, 12),
        (51, 12),
        (13, 13),
        (20, 13),
        (29, 13),
        (29, 13),
        (34, 13),
        (34, 13),
        (38, 13),
        (38, 13),
        (38, 13),
        (38, 13),
        (52, 13),
        (14, 14),
        (21, 14),
        (29, 14),
        (29, 14),
        (35, 14),
        (35, 14),
        (38, 14),
        (38, 14),
        (38, 14),
        (38, 14),
        (53, 14);
