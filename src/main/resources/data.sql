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









INSERT INTO public.client (discount_percentage, created_by_id, created_date, last_modified_by_id,
                           last_modified_date, city, company_type, country, cp, name, number, street)
VALUES (10, 1, '2023-11-15 21:22:48.618517', 1, '2023-11-15 21:22:48.618517', 'X city', 'ltd', 'Belgium', '9999',
        'CompanyX', '18b', 'X street');


