insert into users (cost_per_minute, enabled, created_by_id, created_date, last_modified_by_id,
                   last_modified_date, password, role, username)
values (null,true,null,'2023-11-15 20:52:07.000000', 1, '2023-11-15 20:52:07.000000',
        '$2a$12$dQ8kszwtHNeJdayDbzDdUucRpKuYE1dYQEqZMlrePBA.K9R8XRYLK','ADMIN', 'admin');

INSERT INTO public.client (discount_percentage, created_by_id, created_date, last_modified_by_id,
                           last_modified_date, city, company_type, country, cp, name, number, street)
VALUES (10, 1, '2023-11-15 21:22:48.618517', 1, '2023-11-15 21:22:48.618517', 'X city', 'ltd', 'Belgium', '9999',
        'CompanyX', '18b', 'X street');

INSERT INTO public.product_family (created_by_id, created_date, last_modified_by_id, last_modified_date, name)
VALUES (1, '2023-11-15 21:24:38.693409', 1, '2023-11-15 21:24:38.693409', 'Table');

INSERT INTO public.component (length, price, thickness, width, created_by_id, created_date, last_modified_by_id,
                              last_modified_date, material, name, type)
VALUES (1000, 8.5, 8, 800, 1, '2023-11-15 21:26:09.281940', 1, '2023-11-15 21:26:09.281940', 'T304', 'Top', 'PLATE');

INSERT INTO public.product_variant (height, length, price, width, created_by_id, created_date, last_modified_by_id,
                                    last_modified_date, product_family_id, code, description, material,
                                    variant_identifier)
VALUES (850, 1000, 60, 700, 1, '2023-11-15 21:27:33.455303', 1, '2023-11-15 21:27:33.455303', 1,
        'TABT304[L100xW70xH85]C', 'A table', 'T304', 'c');


