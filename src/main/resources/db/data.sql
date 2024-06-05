INSERT INTO Pracownik (ID_Pracownika, Imie, Nazwisko, PESEL, Wiek, Data_Urodzenia, Dodatek_Od_Lat_Pracy, Numer_Telefonu,
                       Data_Zatrudnienia, Mnoznik_Wyplaty, Stawka_Bazowa)
VALUES (1, 'Jan', 'Zieminski', '32032962976', 38, '2024-06-20', 229, '250489111', '2024-03-23', 0.3, 378),
       (2, 'Albert', 'Kos', '98657588440', 55, '2024-02-28', 104, '220710556', '2024-02-22', 0.9, 385),
       (3, 'Name', 'Surname', '73428371212', 42, '2024-10-12', 482, '144866186', '2024-04-15', 0.8, 437),
       (4, 'Tomasz', 'Problem', '51276966357', 23, '2024-03-05', 301, '626962033', '2024-02-08', 0.9, 258),
       (5, 'Andrzej', 'Fawor', '25482718853', 68, '2024-04-12', 177, '665879701', '2024-08-26', 0.5, 486);

INSERT INTO Dyspozytor (ID_Dyspozytora)
VALUES (1),
       (2),
       (3),
       (4);

INSERT INTO Autobus (ID_Autobusu, Przebieg, Rok_Produkcji, Model, Moc_Netto, Zasieg, Ilosc_Miejsc)
VALUES (1, 45345, 40, 'elektryczny', 35, 44, 45),
       (2, 74553, 19, 'elektryczny', 18, 33, 84),
       (3, 66332, 15, 'hybrydowy', 30, 50, 73),
       (4, 98245, 80, 'spalinowy', 46, 59, 27),
       (5, 99999, 30, 'spalinowy', 19, 60, 25),
       (6, 99999, 30, 'hybrydowy', 19, 60, 25),
       (7, 99999, 30, 'hybrydowy', 19, 60, 25);

INSERT INTO Kierowca (ID_Kierowcy, ID_Autobusu, Lata_Doswiadczenia, Typ_Prawa_Jazdy, Kara_Za_Opoznienia,
                      Dodatek_Za_Przydzielone_Kursy)
VALUES (1, 1, 0, 'B', 153, 0.63),
       (2, 2, 0, 'C', 483, 0.54),
       (3, 3, 1, 'C', 265, 0.76),
       (4, 4, 0, 'C', 208, 0.87),
       (5, 5, 1, 'CE', 374, 0.33);

INSERT INTO Autobus_Silnikowy (ID_Autobusu_Silnikowy, Ilosc_Koni, Spalanie, Pojemnosc_Zbiornika, Typ_Paliwa,
                               Ilosc_Paliwa)
VALUES (1, 4, 50, 1, 'benzyna', 56),
       (2, 31, 54, 1, 'gaz', 54),
       (3, 81, 56, 0, 'ropa', 76),
       (4, 55, 35, 1, 'ropa', 96),
       (5, 80, 34, 1, 'ropa', 70);



INSERT INTO Kurs (ID_Kursu, ID_Autobusu, Nazwa_Kursu, Dlugosc_Trasy)
VALUES (1, 1, 'Naz_25', 0),
       (2, 2, 'Naz_90', 1),
       (3, 3, 'Naz_25', 1),
       (4, 4, 'Naz_36', 1),
       (5, 5, 'Naz_71', 0);


INSERT INTO Akumulator (ID_Akumulatora, Pojemnosc, Znamionowa_Ilosc_Cykli, Ilosc_Cykli_Rozladowania, Poziom_Naladowania)
VALUES (1, 20000, 52, 29, 10),
       (2, 50000, 12, 96, 30),
       (3, 10000, 70, 11, 99),
       (4, 100, 37, 12, 1),
       (5, 5, 42, 40, 15),
       (6, 5, 42, 40, 15),
       (7, 5, 42, 40, 15),
       (8, 5, 42, 40, 15),
       (9, 5, 42, 40, 15);

INSERT INTO Autobus_Elektryczny(ID_Autobusu_Elektryczny, ilosc_pakietow_zasilajacych, poziom_naladowania)
VALUES (1, 5, 61),
       (2, 3, 0),
       (6, 5, 61),
       (7, 5, 61);


INSERT INTO Akumulatory_Autobusu(ID_Akumulatora, ID_Autobusu_Elektryczny)
VALUES (1, 1),
       (2, 2),
       (4, 6),
       (4, 7),
       (9, 7),
       (8, 7),
       (7, 7);

INSERT INTO Autobus_Hybrydowy (ID_Autobusu_Elektryczny, ID_Autobusu_Silnikowy, Typ_Napedu_Hybrydowego,
                               Ladowanie_Plug_in)
VALUES (6, 1, 'rownolegly', 0),
       (7, 1, 'rownolegly', 0);

INSERT INTO Autobus_Miedzymiastowy (ID_Autobusu_Miedzymiastowy, Ilosc_Miast, Max_Odlegosc_Od_zajezdni, Czy_Poza_Kraj)
VALUES (1, 89, 1, 1),
       (2, 23, 0, 1),
       (3, 70, 0, 0),
       (4, 34, 1, 0),
       (5, 55, 1, 1);

INSERT INTO Miasto (ID_Miasta, Nazwa)
VALUES (1, 'Naz_72'),
       (2, 'Naz_100'),
       (3, 'Naz_32'),
       (4, 'Naz_64'),
       (5, 'Naz_73');

INSERT INTO Autobus_Miejski (ID_Autobusu_Miejski, ID_Miasta, Oznaczenie_Kursu)
VALUES (1, 1, 1),
       (2, 2, 0),
       (3, 3, 0),
       (4, 4, 0),
       (5, 5, 0);

INSERT INTO polxcommute.Autobus_W_Miescie (ID_Autobusu_Miedzymiastowy, ID_Miasta)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);



INSERT INTO Dyspozytor_W_Autobusie (ID_Dyspozytora, ID_Autobusu)
VALUES (1, 1),
       (2, 2),
       (3, 3);


INSERT INTO Mechanik (ID_Mechanika, Stopien_Doswiadczenia, Dodatek_Do_Premii_Za_Doswiadczenie, Premia)
VALUES (1, 1, 135, 307),
       (2, 1, 283, 418),
       (3, 1, 212, 421),
       (4, 0, 157, 248),
       (5, 1, 358, 199);

INSERT INTO Mechanik_W_Autobusie (ID_Mechanika, ID_Autobusu)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (1, 6),
       (1, 7),
       (1, 2),
       (1, 3),
       (1, 4);



INSERT INTO Przystanek (ID_Przystanku, Nazwa, Numer, Adres)
VALUES (1, 'Naz_12', '629581888', 'Adr_36'),
       (2, 'Naz_71', '548408023', 'Adr_56'),
       (3, 'Naz_49', '538463649', 'Adr_99'),
       (4, 'Naz_97', '390725476', 'Adr_89'),
       (5, 'Naz_21', '562662573', 'Adr_78');

INSERT INTO Zajezdnia (ID_Zajezdni, Rodzaj_Zajezdni)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 0),
       (5, 0);

INSERT INTO Pracownik_W_Zajezdni (ID_Pracownika, ID_Zajezdni, Data_Od, Data_Do)
VALUES (1, 1, '2024-05-24', '2024-03-10'),
       (2, 2, '2024-08-16', '2024-09-03'),
       (3, 3, '2024-03-05', '2024-03-04'),
       (4, 4, '2024-04-01', '2024-08-15'),
       (5, 5, '2024-08-02', '2024-06-07');

INSERT INTO Przystanek_W_Kursie (ID_Przystanku, ID_Kursu)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);
