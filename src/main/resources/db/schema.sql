-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-01-27 19:31:35.405

CREATE
    DATABASE polxcommute;

-- tables
-- Table: Akumulator
CREATE TABLE Akumulator
(
    ID_Akumulatora           bigint    NOT NULL,
    Pojemnosc                mediumint NOT NULL,
    Znamionowa_Ilosc_Cykli   mediumint NOT NULL,
    Ilosc_Cykli_Rozladowania mediumint NOT NULL,
    Poziom_Naladowania       smallint  NOT NULL,
    CHECK (Poziom_Naladowania <= 100 AND Poziom_Naladowania >= 0),
    CONSTRAINT Akumulator_pk PRIMARY KEY (ID_Akumulatora)
);

-- Table: AkumulatoryAutobusu
CREATE TABLE Akumulatory_Autobusu
(
    ID_Autobusu_Elektryczny bigint NOT NULL,
    ID_Akumulatora          bigint NOT NULL,
    CONSTRAINT AkumulatoryAutobusu_pk PRIMARY KEY (ID_Autobusu_Elektryczny, ID_Akumulatora)
);

-- Table: AutobusModel
CREATE TABLE Autobus
(
    ID_Autobusu   bigint      NOT NULL,
    Przebieg      mediumint   NOT NULL,
    Rok_Produkcji mediumint   NOT NULL,
    Model         varchar(64) NOT NULL,
    Moc_Netto     mediumint   NOT NULL,
    Zasieg        mediumint   NOT NULL,
    Ilosc_Miejsc  mediumint   NOT NULL,
    CONSTRAINT Autobus_pk PRIMARY KEY (ID_Autobusu)
);

-- Table: Autobus_Elektryczny
CREATE TABLE Autobus_Elektryczny
(
    ID_Autobusu_Elektryczny     bigint    NOT NULL,
    Ilosc_Pakietow_Zasilajacych bigint    NOT NULL,
    Poziom_Naladowania          mediumint NOT NULL,
    CONSTRAINT Autobus_Elektryczny_pk PRIMARY KEY (ID_Autobusu_Elektryczny)
);

-- Table: Autobus_Hybrydowy
CREATE TABLE Autobus_Hybrydowy
(
    ID_Autobusu_Elektryczny bigint      NOT NULL,
    ID_Autobusu_Silnikowy   bigint      NOT NULL,
    Typ_Napedu_Hybrydowego  varchar(10) NOT NULL,
    Ladowanie_Plug_in       bool        NOT NULL,
    CHECK (Typ_Napedu_Hybrydowego = 'rownolegly' OR Typ_Napedu_Hybrydowego = 'szeregowy'),
    CONSTRAINT Autobus_Hybrydowy_pk PRIMARY KEY (ID_Autobusu_Elektryczny, ID_Autobusu_Silnikowy)
);

-- Table: Autobus_Miedzymiastowy
CREATE TABLE Autobus_Miedzymiastowy
(
    ID_Autobusu_Miedzymiastowy bigint    NOT NULL,
    Ilosc_Miast                mediumint NOT NULL,
    Max_Odlegosc_Od_zajezdni   mediumint NOT NULL,
    Czy_Poza_Kraj              bool      NOT NULL,
    CONSTRAINT Autobus_Miedzymiastowy_pk PRIMARY KEY (ID_Autobusu_Miedzymiastowy)
);

-- Table: Autobus_Miejski
CREATE TABLE Autobus_Miejski
(
    ID_Autobusu_Miejski bigint NOT NULL,
    ID_Miasta           bigint NOT NULL,
    Oznaczenie_Kursu    bool   NOT NULL,
    CONSTRAINT Autobus_Miejski_pk PRIMARY KEY (ID_Autobusu_Miejski)
);

-- Table: Autobus_Silnikowy
CREATE TABLE Autobus_Silnikowy
(
    ID_Autobusu_Silnikowy bigint      NOT NULL,
    Ilosc_Koni            mediumint   NOT NULL,
    Spalanie              smallint    NOT NULL,
    Pojemnosc_Zbiornika   smallint    NOT NULL,
    Typ_Paliwa            varchar(32) NOT NULL,
    Ilosc_Paliwa          smallint    NOT NULL,
    CHECK (Typ_Paliwa = 'benzyna' OR Typ_Paliwa = 'ropa' OR Typ_Paliwa = 'gaz'),
    CONSTRAINT Autobus_Silnikowy_pk PRIMARY KEY (ID_Autobusu_Silnikowy)
);

-- Table: Autobus_W_Miescie
CREATE TABLE Autobus_W_Miescie
(
    ID_Autobusu_Miedzymiastowy bigint NOT NULL,
    ID_Miasta                  bigint NOT NULL,
    CONSTRAINT Autobus_W_Miescie_pk PRIMARY KEY (ID_Autobusu_Miedzymiastowy, ID_Miasta)
);

-- Table: Dyspozytor
CREATE TABLE Dyspozytor
(
    ID_Dyspozytora bigint NOT NULL,
    CONSTRAINT Dyspozytor_pk PRIMARY KEY (ID_Dyspozytora)
);

-- Table: Dyspozytor_W_Autobusie
CREATE TABLE Dyspozytor_W_Autobusie
(
    ID_Dyspozytora bigint NOT NULL,
    ID_Autobusu    bigint NOT NULL,
    CONSTRAINT Dyspozytor_W_Autobusie_pk PRIMARY KEY (ID_Dyspozytora, ID_Autobusu)
);

-- Table: Kierowca
CREATE TABLE Kierowca
(
    ID_Kierowcy                   bigint       NOT NULL,
    ID_Autobusu                   bigint       NOT NULL,
    Lata_Doswiadczenia            smallint     NOT NULL,
    Typ_Prawa_Jazdy               varchar(2)   NOT NULL,
    Kara_Za_Opoznienia            smallint     NOT NULL,
    Dodatek_Za_Przydzielone_Kursy double(2, 2) NOT NULL,
    CONSTRAINT Kierowca_pk PRIMARY KEY (ID_Kierowcy)
);

-- Table: Kurs
CREATE TABLE Kurs
(
    ID_Kursu      bigint      NOT NULL,
    ID_Autobusu   bigint      NOT NULL,
    Nazwa_Kursu   varchar(32) NOT NULL,
    Dlugosc_Trasy mediumint   NOT NULL,
    CONSTRAINT Kurs_pk PRIMARY KEY (ID_Kursu)
);

-- Table: Mechanik
CREATE TABLE Mechanik
(
    ID_Mechanika                       bigint    NOT NULL,
    Stopien_Doswiadczenia              smallint  NOT NULL,
    Dodatek_Do_Premii_Za_Doswiadczenie smallint  NOT NULL,
    Premia                             mediumint NOT NULL,
    CONSTRAINT Mechanik_pk PRIMARY KEY (ID_Mechanika)
);

-- Table: Mechanik_W_Autobusie
CREATE TABLE Mechanik_W_Autobusie
(
    ID_Mechanika bigint NOT NULL,
    ID_Autobusu  bigint NOT NULL,
    CONSTRAINT Mechanik_W_Autobusie_pk PRIMARY KEY (ID_Mechanika, ID_Autobusu)
);

-- Table: Miasto
CREATE TABLE Miasto
(
    ID_Miasta bigint       NOT NULL,
    Nazwa     varchar(256) NOT NULL,
    CONSTRAINT Miasto_pk PRIMARY KEY (ID_Miasta)
);

-- Table: Pracownik
CREATE TABLE Pracownik
(
    ID_Pracownika        bigint       NOT NULL,
    Imie                 varchar(32)  NOT NULL,
    Nazwisko             varchar(64)  NOT NULL,
    PESEL                bigint       NOT NULL,
    Wiek                 tinyint      NOT NULL,
    Data_Urodzenia       date         NOT NULL,
    Dodatek_Od_Lat_Pracy int          NOT NULL,
    Numer_Telefonu       int          NULL,
    Data_Zatrudnienia    date         NOT NULL,
    Mnoznik_Wyplaty      double(2, 2) NOT NULL,
    Stawka_Bazowa        mediumint    NOT NULL,
    CONSTRAINT Pracownik_pk PRIMARY KEY (ID_Pracownika)
);

-- Table: Pracownik_W_Zajezdni
CREATE TABLE Pracownik_W_Zajezdni
(
    ID_Pracownika bigint  NOT NULL,
    ID_Zajezdni   bigint  NOT NULL,
    Data_Od       date NOT NULL,
    Data_Do       date NOT NULL,
    CONSTRAINT Pracownik_W_Zajezdni_pk PRIMARY KEY (ID_Pracownika, ID_Zajezdni)
);

-- Table: Przystanek
CREATE TABLE Przystanek
(
    ID_Przystanku bigint          NOT NULL,
    Nazwa         varchar(64)  NOT NULL,
    Numer         int          NOT NULL,
    Adres         varchar(128) NOT NULL,
    CONSTRAINT Przystanek_pk PRIMARY KEY (ID_Przystanku)
);

-- Table: Przystanek_W_Kursie
CREATE TABLE Przystanek_W_Kursie
(
    ID_Przystanku bigint NOT NULL,
    ID_Kursu      bigint NOT NULL,
    CONSTRAINT Przystanek_W_Kursie_pk PRIMARY KEY (ID_Przystanku, ID_Kursu)
);

-- Table: Zajezdnia
CREATE TABLE Zajezdnia
(
    ID_Zajezdni     bigint         NOT NULL,
    Rodzaj_Zajezdni varchar(16) NOT NULL,
    CONSTRAINT Zajezdnia_pk PRIMARY KEY (ID_Zajezdni)
);

-- foreign keys
-- Reference: AkumulatoryAutobusu_Akumulator (table: AkumulatoryAutobusu)
ALTER TABLE Akumulatory_Autobusu
    ADD CONSTRAINT AkumulatoryAutobusu_Akumulator FOREIGN KEY AkumulatoryAutobusu_Akumulator (ID_Akumulatora)
        REFERENCES Akumulator (ID_Akumulatora);

-- Reference: AkumulatoryAutobusu_Autobus_Elektryczny (table: AkumulatoryAutobusu)
ALTER TABLE Akumulatory_Autobusu
    ADD CONSTRAINT AkumulatoryAutobusu_Autobus_Elektryczny FOREIGN KEY AkumulatoryAutobusu_Autobus_Elektryczny (ID_Autobusu_Elektryczny)
        REFERENCES Autobus_Elektryczny (ID_Autobusu_Elektryczny);

-- Reference: Autobus_Elektryczny_Autobus (table: Autobus_Elektryczny)
ALTER TABLE Autobus_Elektryczny
    ADD CONSTRAINT Autobus_Elektryczny_Autobus FOREIGN KEY Autobus_Elektryczny_Autobus (ID_Autobusu_Elektryczny)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Autobus_Hybrydowy_Autobus_Elektryczny (table: Autobus_Hybrydowy)
ALTER TABLE Autobus_Hybrydowy
    ADD CONSTRAINT Autobus_Hybrydowy_Autobus_Elektryczny FOREIGN KEY Autobus_Hybrydowy_Autobus_Elektryczny (ID_Autobusu_Elektryczny)
        REFERENCES Autobus_Elektryczny (ID_Autobusu_Elektryczny);

-- Reference: Autobus_Hybrydowy_Autobus_Silnikowy (table: Autobus_Hybrydowy)
ALTER TABLE Autobus_Hybrydowy
    ADD CONSTRAINT Autobus_Hybrydowy_Autobus_Silnikowy FOREIGN KEY Autobus_Hybrydowy_Autobus_Silnikowy (ID_Autobusu_Silnikowy)
        REFERENCES Autobus_Silnikowy (ID_Autobusu_Silnikowy);

-- Reference: Autobus_Miedzymiastowy_Autobus (table: Autobus_Miedzymiastowy)
ALTER TABLE Autobus_Miedzymiastowy
    ADD CONSTRAINT Autobus_Miedzymiastowy_Autobus FOREIGN KEY Autobus_Miedzymiastowy_Autobus (ID_Autobusu_Miedzymiastowy)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Autobus_Miejski_Autobus (table: Autobus_Miejski)
ALTER TABLE Autobus_Miejski
    ADD CONSTRAINT Autobus_Miejski_Autobus FOREIGN KEY Autobus_Miejski_Autobus (ID_Autobusu_Miejski)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Autobus_Miejski_Miasto (table: Autobus_Miejski)
ALTER TABLE Autobus_Miejski
    ADD CONSTRAINT Autobus_Miejski_Miasto FOREIGN KEY Autobus_Miejski_Miasto (ID_Miasta)
        REFERENCES Miasto (ID_Miasta);

-- Reference: Autobus_Silnikowy_Autobus (table: Autobus_Silnikowy)
ALTER TABLE Autobus_Silnikowy
    ADD CONSTRAINT Autobus_Silnikowy_Autobus FOREIGN KEY Autobus_Silnikowy_Autobus (ID_Autobusu_Silnikowy)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Autobus_W_Miescie_Autobus_Miedzymiastowy (table: Autobus_W_Miescie)
ALTER TABLE Autobus_W_Miescie
    ADD CONSTRAINT Autobus_W_Miescie_Autobus_Miedzymiastowy FOREIGN KEY Autobus_W_Miescie_Autobus_Miedzymiastowy (ID_Autobusu_Miedzymiastowy)
        REFERENCES Autobus_Miedzymiastowy (ID_Autobusu_Miedzymiastowy);

-- Reference: Autobus_W_Miescie_Miasto (table: Autobus_W_Miescie)
ALTER TABLE Autobus_W_Miescie
    ADD CONSTRAINT Autobus_W_Miescie_Miasto FOREIGN KEY Autobus_W_Miescie_Miasto (ID_Miasta)
        REFERENCES Miasto (ID_Miasta);

-- Reference: Dyspozytor_Pracownik (table: Dyspozytor)
ALTER TABLE Dyspozytor
    ADD CONSTRAINT Dyspozytor_Pracownik FOREIGN KEY Dyspozytor_Pracownik (ID_Dyspozytora)
        REFERENCES Pracownik (ID_Pracownika);

-- Reference: Dyspozytor_W_Autobusie_Autobus (table: Dyspozytor_W_Autobusie)
ALTER TABLE Dyspozytor_W_Autobusie
    ADD CONSTRAINT Dyspozytor_W_Autobusie_Autobus FOREIGN KEY Dyspozytor_W_Autobusie_Autobus (ID_Autobusu)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Dyspozytor_W_Autobusie_Dyspozytor (table: Dyspozytor_W_Autobusie)
ALTER TABLE Dyspozytor_W_Autobusie
    ADD CONSTRAINT Dyspozytor_W_Autobusie_Dyspozytor FOREIGN KEY Dyspozytor_W_Autobusie_Dyspozytor (ID_Dyspozytora)
        REFERENCES Dyspozytor (ID_Dyspozytora);

-- Reference: Kierowca_Autobus (table: Kierowca)
ALTER TABLE Kierowca
    ADD CONSTRAINT Kierowca_Autobus FOREIGN KEY Kierowca_Autobus (ID_Autobusu)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Kierowca_Pracownik (table: Kierowca)
ALTER TABLE Kierowca
    ADD CONSTRAINT Kierowca_Pracownik FOREIGN KEY Kierowca_Pracownik (ID_Kierowcy)
        REFERENCES Pracownik (ID_Pracownika);

-- Reference: Kurs_Autobus (table: Kurs)
ALTER TABLE Kurs
    ADD CONSTRAINT Kurs_Autobus FOREIGN KEY Kurs_Autobus (ID_Autobusu)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Mechanik_Pracownik (table: Mechanik)
ALTER TABLE Mechanik
    ADD CONSTRAINT Mechanik_Pracownik FOREIGN KEY Mechanik_Pracownik (ID_Mechanika)
        REFERENCES Pracownik (ID_Pracownika);

-- Reference: Mechanik_W_Autobusie_Autobus (table: Mechanik_W_Autobusie)
ALTER TABLE Mechanik_W_Autobusie
    ADD CONSTRAINT Mechanik_W_Autobusie_Autobus FOREIGN KEY Mechanik_W_Autobusie_Autobus (ID_Autobusu)
        REFERENCES Autobus (ID_Autobusu);

-- Reference: Mechanik_W_Autobusie_Mechanik (table: Mechanik_W_Autobusie)
ALTER TABLE Mechanik_W_Autobusie
    ADD CONSTRAINT Mechanik_W_Autobusie_Mechanik FOREIGN KEY Mechanik_W_Autobusie_Mechanik (ID_Mechanika)
        REFERENCES Mechanik (ID_Mechanika);

-- Reference: Pracownik_W_Zajezdni_Pracownik (table: Pracownik_W_Zajezdni)
ALTER TABLE Pracownik_W_Zajezdni
    ADD CONSTRAINT Pracownik_W_Zajezdni_Pracownik FOREIGN KEY Pracownik_W_Zajezdni_Pracownik (ID_Pracownika)
        REFERENCES Pracownik (ID_Pracownika);

-- Reference: Pracownik_W_Zajezdni_Zajezdnia (table: Pracownik_W_Zajezdni)
ALTER TABLE Pracownik_W_Zajezdni
    ADD CONSTRAINT Pracownik_W_Zajezdni_Zajezdnia FOREIGN KEY Pracownik_W_Zajezdni_Zajezdnia (ID_Zajezdni)
        REFERENCES Zajezdnia (ID_Zajezdni);

-- Reference: Przystanek_W_Kursie_Kurs (table: Przystanek_W_Kursie)
ALTER TABLE Przystanek_W_Kursie
    ADD CONSTRAINT Przystanek_W_Kursie_Kurs FOREIGN KEY Przystanek_W_Kursie_Kurs (ID_Kursu)
        REFERENCES Kurs (ID_Kursu);

-- Reference: Przystanek_W_Kursie_Przystanek (table: Przystanek_W_Kursie)
ALTER TABLE Przystanek_W_Kursie
    ADD CONSTRAINT Przystanek_W_Kursie_Przystanek FOREIGN KEY Przystanek_W_Kursie_Przystanek (ID_Przystanku)
        REFERENCES Przystanek (ID_Przystanku);

-- Reference: Zajezdnia_Przystanek (table: Zajezdnia)
ALTER TABLE Zajezdnia
    ADD CONSTRAINT Zajezdnia_Przystanek FOREIGN KEY Zajezdnia_Przystanek (ID_Zajezdni)
        REFERENCES Przystanek (ID_Przystanku);

-- End of file.




