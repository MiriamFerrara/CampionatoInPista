DROP DATABASE campionatoinpista;
CREATE SCHEMA CampionatoInPista;
USE CampionatoInPista;

#Creazione della Tabella Circuto (ID, NomeC, PaeseResidenza, Lunghezza, #Curve)
CREATE TABLE circuito (
    ID_Circuito INT AUTO_INCREMENT NOT NULL,
    NomeC VARCHAR(200) NOT NULL,
    PaeseResidenza VARCHAR(200) NOT NULL,
    Lunghezza FLOAT NOT NULL,
    NumCurve INT NOT NULL,
    PRIMARY KEY(ID_Circuito)
);


#Creazione della Tabella Gara (ID, Nome, DataEvento, DurataOre, TipoGara(asciutta o bagnata)) 
CREATE TABLE gara (
    ID_Gara INT AUTO_INCREMENT NOT NULL,
    NomeG VARCHAR(200) NOT NULL,
    DataEvento DATE NOT NULL,
    DurataOre TIME NOT NULL,
    TipoGara ENUM ("Asciutta", "Bagnata") NOT NULL,
    PRIMARY KEY(ID_Gara),
    id_Circuito INT,
    FOREIGN KEY (id_Circuito) REFERENCES circuito(ID_Circuito) ON UPDATE CASCADE ON DELETE SET NULL
);


#Creazione della Tabella Vettura (Targa, #Gara, Modello)
CREATE TABLE vettura (
    Targa VARCHAR(10) NOT NULL,
    NumGara INT,
    Modello VARCHAR(200) NOT NULL,
    PRIMARY KEY(Targa)
);


#Creazione della Tabella Costruttore (Nome, RagioneSociale, SedeFabbrica, #Componenti)
CREATE TABLE costruttore ( 
Nome VARCHAR(200) NOT NULL,
RagioneSociale VARCHAR(200) NOT NULL,
SedeFabbrica VARCHAR(200) NOT NULL,
NumComponenti INT NOT NULL, 
PRIMARY KEY(Nome)
);


#Creazione della Tabella Componente (Codice, DataIstallazione, Costo, TipoComponente, TipoMateriale, Peso, #Marce(7 o 8), Cilindrata, TipoMotore (turbo o aspirato), #cilindri, NOME_COSTRUTTORE, TARGA_VETTURA)
CREATE TABLE componente (
	Codice INT  AUTO_INCREMENT NOT NULL,
    DataInstallazione DATE NOT NULL,
    Costo FLOAT NOT NULL,
    TipoComponente ENUM ("Telaio", "Cambio", "Motore") NOT NULL,
    TipoMateriale VARCHAR(200) NULL,
    Peso FLOAT NULL,
    NumMarce ENUM ("7", "8") NULL,
    Cilindrata VARCHAR(200) NULL,
    TipoMotore ENUM ("Turbo", "Aspirato") NULL,
    NumCilindri INT NULL,
    PRIMARY KEY (Codice, DataInstallazione),
    NomeCostruttore VARCHAR(200),
    TargaVettura VARCHAR(10),
	FOREIGN KEY (NomeCostruttore) REFERENCES costruttore(Nome) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (TargaVettura) REFERENCES vettura(Targa) ON UPDATE CASCADE ON DELETE SET NULL
);


#Creazione della Tabella Pilota (ID, Nome, Cognome, DataNascita, Nazionalità, TipoPilota, #LicenzePossedute, DataLicenza, FinanziatoreGD)
CREATE TABLE pilota (
	ID INT NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Cognome VARCHAR(100) NOT NULL,
    DataNascita DATE NOT NULL,
    Nazionalita VARCHAR(100) NOT NULL,
    TipoPilota VARCHAR(100) NOT NULL,
    LicenzePossedute INT,
    DataLicenza DATE,
    FinanziatoreGD ENUM ("SI", "NO"),
    PRIMARY KEY(ID)
    );
    
    
#Creazione della Tabella Scuderia(Nome, Sede, Paese, #Finanziamenti, TARGA_VETTURA)
    CREATE TABLE scuderia (
    Nome VARCHAR(200) NOT NULL,
    Sede VARCHAR(200) NOT NULL,
    Paese VARCHAR(200) NOT NULL,
    NumFinanziamenti INT, 
	PRIMARY KEY(Nome),
	TargaVettura VARCHAR(10),
	FOREIGN KEY (TargaVettura) REFERENCES vettura(Targa) ON UPDATE CASCADE ON DELETE SET NULL
);


#Creazione della Tabella  Partecipa(id_gara, Targa_vettura,  MotivoRitiro (può essere incidente, guasto, squalifica), Punti)
CREATE TABLE partecipa (
    id_Gara INT,
    targa_Vettura VARCHAR(10),
    MotivoRitiro ENUM ("Incidente", "Guasto", "Squalifica"),
    Punti INT,
    FOREIGN KEY (id_Gara) REFERENCES gara(ID_Gara) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (targa_Vettura) REFERENCES vettura(Targa) ON UPDATE CASCADE ON DELETE SET NULL
);


#Creazione della Tabella Guidare(Targa_vettura, id_Pilota, NumComponentiPiloti)
CREATE TABLE guidare (
	targa_vettura VARCHAR(10),
    id_Pilota INT,
    NumComponentiPiloti INT, 
    FOREIGN KEY (targa_Vettura) REFERENCES vettura(Targa) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (id_Pilota) REFERENCES pilota(ID) ON UPDATE CASCADE ON DELETE SET NULL
);


#Creazione della Tabella Finanziare(QuantitaDenaro, data, id_Pilota, NomeScuderia)
CREATE TABLE Finanziare (
    QuantitaDenaro FLOAT,
    Data DATE,
	id_Pilota INT,
    NomeScuderia VARCHAR(200),
    FOREIGN KEY (id_Pilota) REFERENCES pilota(ID) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (NomeScuderia) REFERENCES scuderia(Nome) ON UPDATE CASCADE ON DELETE SET NULL
);
