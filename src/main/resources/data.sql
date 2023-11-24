-- noinspection SpellCheckingInspectionForFile

insert into users (cost_per_minute, enabled, created_by_id, created_date, last_modified_by_id,
                   last_modified_date, password, role, username)
values (null,true,null,'2023-11-15 20:52:07.000000', 1, '2023-11-15 20:52:07.000000',
        '$2a$12$XTo..ZJDKz2FJMicdzZe6.Mp.ilmYBL4ajfZlwX/V5xTaQmZlGzO2','ADMIN', 'admin');

INSERT INTO public.client (discount_percentage, created_by_id, created_date, last_modified_by_id,
                           last_modified_date, city, company_type, country, cp, name, number, street)
VALUES (10, 1, '2023-11-15 21:22:48.618517', 1, '2023-11-15 21:22:48.618517', 'X city', 'ltd', 'Belgium', '9999',
        'CompanyX', '18b', 'X street');

INSERT INTO public.component (length, price, thickness, width, created_by_id, created_date, last_modified_by_id,
                              last_modified_date, material, name, type)
VALUES (1000, 8.5, 8, 800, 1, '2023-11-15 21:26:09.281940', 1, '2023-11-15 21:26:09.281940', 'T304', 'Top', 'PLATE');

INSERT INTO public.product_family (created_by_id, created_date, last_modified_by_id, last_modified_date, name)
VALUES (1, '2023-11-17 17:13:20.459258', 1, '2023-11-17 17:13:20.459258', 'Table');

INSERT INTO public.product_family_steps (step_order, product_family_id, production_path)
VALUES
    (0, 1, 'ENCODED'),
    (1, 1, 'PRODUCTION'),
    (2, 1, 'CUT'),
    (3, 1, 'BENT'),
    (4, 1, 'COMBINED'),
    (5, 1, 'WELDED'),
    (6, 1, 'FINISHED'),
    (7, 1, 'PACKED'),
    (8, 1, 'SENT');

INSERT INTO public.product_variant (height, length, price, width, created_by_id, created_date, last_modified_by_id,
                                    last_modified_date, product_family_id, code, description, material,
                                    variant_identifier)
VALUES (850, 1000, 60, 700, 1, '2023-11-17 17:21:18.591831', 1, '2023-11-17 17:21:18.591831', 1,
        'TABT304[L1000xW700xH850]C', 'A table', 'T304', 'c');
INSERT INTO public.product_variant_components (components_id, product_variant_id)
VALUES (1, 1);

