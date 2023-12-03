-- noinspection SpellCheckingInspectionForFile

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


INSERT INTO public.material_type (base_price, has_length, has_thickness, has_width, created_by_id, created_date, last_modified_by_id, last_modified_date, material, name, pricing_method)
VALUES
(750, true, true, true, 2, '2023-12-03 14:17:03.301227', 2, '2023-12-03 14:17:03.301227', 'T304', 'Plate', 'CUBE_METER'),
(380, true, true, true, 2, '2023-12-03 14:17:58.813905', 2, '2023-12-03 14:17:58.813905', 'T430', 'Plate', 'CUBE_METER'),
(11.5, true, false, false, 2, '2023-12-03 14:18:55.497786', 2, '2023-12-03 14:18:55.497786', 'T304', 'Tube(40x40)', 'METER'),
(5.5, true, false, false, 2, '2023-12-03 14:20:03.734989', 2, '2023-12-03 14:20:03.734989', 'T430', 'Tube(40x40)', 'METER'),
(9.5, true, false, false, 2, '2023-12-03 14:21:48.997359', 2, '2023-12-03 14:22:01.012862', 'T304', 'Tube(25x25)', 'METER'),
(4.5, false, false, false, 2, '2023-12-03 14:22:29.293800', 2, '2023-12-03 14:22:37.498143', 'T430', 'Tube(25x25)', 'METER'),
(14, true, false, true, 2, '2023-12-03 14:23:26.781062', 2, '2023-12-03 14:23:26.781062', 'OTHER', 'MDF Board', 'SQUARE_METER'),
(13.2, true, false, false, 2, '2023-12-03 14:25:05.505486', 2, '2023-12-03 14:25:05.505486', 'T304', 'Omega(150)', 'METER');

insert into public.component (length, price, requires_bending, requires_cutting, thickness, width, created_by_id, created_date, last_modified_by_id, last_modified_date, type_id, name)
values  (800, 38.4, true, true, 8, 800, 2, '2023-12-03 14:38:13.412737', 2, '2023-12-03 14:38:13.412737', 1, 'Tabletop'),
        (800, 9.200000000000001, false, false, null, null, 2, '2023-12-03 14:39:11.878708', 2, '2023-12-03 14:39:11.878708', 3, 'Foot'),
        (690, 6.6654, false, false, null, 690, 2, '2023-12-03 14:40:29.974795', 2, '2023-12-03 14:40:29.974795', 7, 'Underboard'),
        (825, 40.8375, true, true, 8, 825, 2, '2023-12-03 14:42:37.718020', 2, '2023-12-03 14:42:37.718020', 1, 'Subtablet'),
        (620, 8.184, false, false, null, null, 2, '2023-12-03 14:43:33.845715', 2, '2023-12-03 14:43:33.845715', 8, 'Tableside');

insert into public.product_family (created_by_id, created_date, last_modified_by_id, last_modified_date, name)
values  (2, '2023-12-03 14:49:43.739461', 2, '2023-12-03 14:49:43.739461', 'Table');

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

insert into public.product_variant (height, length, price, width, created_by_id, created_date, last_modified_by_id, last_modified_date, product_family_id, code, description, material, variant_identifier)
values  (850, 700, 175, 700, 2, '2023-12-03 14:52:51.942404', 2, '2023-12-03 14:52:51.942404', 1, 'TABT304[L700xW700xH850]C1ST', 'Table 70cm x 70cm x 85cm with one sub tablet.', 'T304', 'C1ST');

insert into public.product_variant_components (components_id, product_variant_id)
values  (1, 1),
        (3, 1),
        (5, 1),
        (5, 1),
        (5, 1),
        (5, 1),
        (2, 1),
        (2, 1),
        (2, 1),
        (2, 1),
        (4, 1);

INSERT INTO public.client (discount_percentage, created_by_id, created_date, last_modified_by_id,
                           last_modified_date, city, company_type, country, cp, name, number, street)
VALUES (10, 1, '2023-11-15 21:22:48.618517', 1, '2023-11-15 21:22:48.618517', 'X city', 'ltd', 'Belgium', '9999',
        'CompanyX', '18b', 'X street');


