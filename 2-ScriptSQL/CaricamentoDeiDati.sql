#INSERIMENTO DEI DATI 


#Inserimento della Tabella Circuito(ID, Nome, PaeseResidenza, Lunghezza, #Curve) 
 INSERT INTO circuito
	VALUES (1, 'Circuito A', 'Italia', 2.5, 5),
		   (2, 'Circuito B', 'Monaco', 3.0, 6),
		   (3, 'Circuito C', 'Francia', 3.5, 3),
		   (4, 'Circuito D', 'Regno Unito', 4.8, 4),
		   (5, 'Circuito E', 'Spagna', 5.5, 9);   
           
#Inserimento della Tabella Gara (ID, Nome, DataEvento, DurataOre, TipoGara(asciutta o bagnata), ID_CIRCUITO) 
INSERT INTO gara
	VALUES (1, 'Gran Premio d''Italia', '2023-09-01', '02:30:00', 'Asciutta', 1),
		   (2, 'Gran Premio Monaco', '2024-09-02', '02:45:00', 'Bagnata', 2),
		   (3, 'Gran Premio Francia', '2023-09-03', '03:00:00', 'Asciutta', 3),
		   (4, 'Gran Premio Regno Unito', '2024-09-05', '01:45:00', 'Bagnata', 4),
		   (5, 'Gran Premio Spagna', '2024-09-06', '02:15:00', 'Asciutta', 5);
           
           
#Inserimento della Tabella Vettura (Targa, #Gara, Modello)   
INSERT INTO vettura
	VALUES ('JKL012', 4, 'Bugatti Chiron'),
		   ('MNO345', 5, 'Mercedes-AMG GT'),
		   ('PQR678', 6, 'Ferrari 488 GTB'),
		   ('STU901', 7, 'Lamborghini Aventador'),
		   ('VWX234', 8, 'McLaren 720S');
           
           
#Inserimento della Tabella Costruttore (Nome, RagioneSociale, SedeFabbrica, #Componenti)
INSERT INTO costruttore
	VALUES ('Ferrari','Ferrari S.p.A.', 'Maranello', 10),
		   ('Mercedes-Benz', 'Daimler AG', 'Stoccarda', 12),
		   ('Lamborghini', 'Lamborghini', 'Barcellona', 8),
           ('McLaren Racing', 'McLaren', 'Stoccarda', 3),
           ('Bugatti', 'Bugatti', 'Parigi', 15);


#Inserimento della Tabella Componente (Codice, DataIstallazione, Costo, TipoComponente, TipoMateriale, Peso, #Marce(7 o 8), Cilindrata, TipoMotore (turbo o aspirato), #cilindri, NOME_COSTRUTTORE, TARGA_VETTURA)
 INSERT INTO componente
	VALUES (1, '2023-05-20', 500.00, 'Telaio', 'Alluminio', 90.5, NULL, NULL, NULL, NULL, 'Bugatti', 'JKL012'),
		   (2, '2023-06-10', 1050.50, 'Motore', NULL, NULL, NULL, 1.8, 'Aspirato', 4, 'Mercedes-Benz', 'MNO345'),
		   (3, '2023-07-15', 750.00, 'Cambio', NULL, NULL, '7', NULL, NULL, NULL, 'Ferrari', 'PQR678'),
		   (4, '2023-08-05', 1500.50, 'Telaio', 'Fibra di carbonio', 85.0, NULL, NULL, NULL, NULL, 'Lamborghini', 'STU901'),
		   (5, '2023-09-12', 3000.80, 'Motore', NULL, NULL, NULL, 2.2, 'Turbo', 8, 'McLaren Racing', 'VWX234');		

		
#Inserimento della Tabella Pilota (ID, Nome, Cognome, DataNascita, Nazionalità, TipoPilota, #LicenzePossedute, DataLicenza, FinanziatoreGD)
INSERT INTO pilota
	VALUES (1, 'Lewis', 'Hamilton', '1985-01-07', 'Regno Unito', 'PRO', 5, '2010-03-15', 'NO'),
		   (2, 'Ralf', 'Schumacher', '1975-06-30', 'Monaco', 'AM', 6, '1997-08-18', 'SI'),
		   (3, 'Charles', 'Leclerc', '1997-10-16', 'Francia', 'AM', 3, '2016-08-10', 'SI'),
		   (4, 'Lando', 'Norris', '1999-11-13', 'Regno Unito', 'AM', 2, '2017-12-05', 'SI'),
		   (5, 'Mick', 'Schumacher', '1999-03-22', 'Spagna', 'PRO', 1, '2020-04-18', 'NO');
      
      
#Inserimento della Tabella Scuderia(Nome, Sede, Paese, #Finanziamenti, TARGA_VETTURA)
 INSERT INTO scuderia
	VALUES ('Scuderia Ferrari', 'Maranello', 'Italia', 3, 'PQR678'),
		   ('Mercedes Team', 'Stoccarda', 'Germania', 4, 'MNO345'),
		   ('Red Bull Racing', 'Barcellona', 'Spagna', 2, 'STU901'),
		   ('McLaren Racing', 'Londra', 'Regno Unito', 3, 'VWX234'),
		   ('Bugatti Motorsport', 'Parigi', 'Francia', 2, 'JKL012');    
           
           
 #Inserimento della Tabella Partecipa(id_gara, Targa_vettura,  MotivoRitiro (può essere incidente, guasto, squalifica), Punti)
INSERT INTO partecipa
	VALUES (1, 'JKL012', 'Guasto', 0),
		   (2, 'MNO345', null, 150),
		   (3, 'PQR678', 'Squalifica', 0),
		   (4, 'STU901', null, 100),
		   (5, 'VWX234', 'Incidente', 5);       
           
#Inserimento della Tabella Guidare(Targa_vettura, id_Pilota, NumComponentiPiloti)
INSERT INTO guidare
	VALUES ('JKL012', 1, 20),
		   ('MNO345', 2, 18),
		   ('PQR678', 3, 22),
		   ('STU901', 4, 21),
		   ('VWX234', 5, 19);        
           
 #Inserimento della Tabella Finanziare(QuantitaDenaro, data, id_Pilota, NomeScuderia)
INSERT INTO finanziare
	VALUES (50000, '2023-01-10', 1, 'Scuderia Ferrari'),
		   (60000, '2023-02-15', 2, 'Red Bull Racing'),
		   (45000, '2023-03-20', 3, 'McLaren Racing'),
		   (55000, '2023-04-25', 4, 'Mercedes Team'),
		   (40000, '2023-05-30', 5, 'Bugatti Motorsport');        
           
          