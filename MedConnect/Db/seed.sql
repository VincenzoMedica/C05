use medconnect;

-- Popolamento della tabella UtenteRegistrato
-- Password:
--      Pazienti: password1
--      Medici: medico1pass
--      Admin: adminpass1
INSERT INTO UtenteRegistrato (ID, email, pass, nome, cognome, cf, genere, biografia, data_nascita, luogo_nascita, num_cellulare)
VALUES
(1, 'paziente1@example.com', 'e38ad214943daad1d64c102faec29de4afe9da3d', 'Marco', 'Rossi', 'RSSMRC85A01H501N', 'M', 'Paziente con malattia cronica', '1985-01-01', 'Roma', '3331112233'),
(2, 'paziente2@example.com', 'e38ad214943daad1d64c102faec29de4afe9da3d', 'Luca', 'Verdi', 'VRDLCA78B09G700Y', 'M', 'Paziente regolare', '1978-02-15', 'Milano', '3342223344'),
(3, 'paziente3@example.com', 'e38ad214943daad1d64c102faec29de4afe9da3d', 'Giulia', 'Bianchi', 'BNCGLA92F45L219K', 'F', 'Paziente con follow-up post-operatorio', '1992-03-03', 'Napoli', '3353334455'),
(4, 'paziente4@example.com', 'e38ad214943daad1d64c102faec29de4afe9da3d', 'Laura', 'Neri', 'NRILRA85D04H501X', 'F', 'Paziente in cura', '1985-04-04', 'Bologna', '3364445566'),
(5, 'medico1@example.com', '61c44a005a554bec2efca398edc005855c189d99', 'Francesco', 'Savi', 'SVIFNC75E08A839T', 'M', 'Specialista in cardiologia', '1975-08-09', 'Roma', '3391112233'),
(6, 'medico2@example.com', '61c44a005a554bec2efca398edc005855c189d99', 'Sara', 'Galli', 'GLLSRA82T40B063T', 'F', 'Specialista in ortopedia', '1982-12-11', 'Milano', '3392223344'),
(7, 'medico3@example.com', '61c44a005a554bec2efca398edc005855c189d99', 'Giorgio', 'Martini', 'MRTGRG79R01F257X', 'M', 'Specialista in dermatologia', '1979-07-01', 'Firenze', '3393334455'),
(8, 'medico4@example.com', '61c44a005a554bec2efca398edc005855c189d99', 'Elena', 'Piazza', 'PZZLNA90A55F258M', 'F', 'Specialista in neurologia', '1990-06-22', 'Napoli', '3394445566'),
(9, 'admin1@example.com', '8a0fea2766c91cddee2cd04b5aebdd52cae5dd1b', 'Alessandro', 'Giorgi', 'GRGLSN82C10H501Z', 'M', 'Amministratore principale', '1982-03-10', 'Roma', '3401112233'),
(10, 'admin2@example.com', '8a0fea2766c91cddee2cd04b5aebdd52cae5dd1b', 'Francesca', 'Bianchi', 'BNCHFRC90L70H219', 'F', 'Amministratore gestione utenti', '1990-11-15', 'Milano', '3402223344'),
(11, 'medico5@example.com', '61c44a005a554bec2efca398edc005855c189d99', 'Paolo', 'Languilla', 'LNGPLA89C27F839V', 'M', 'Specialista in chirugia', '1989-03-27', 'Napoli', '3394445577');

-- Popolamento della tabella Admin
INSERT INTO Admin (ID, Ruolo)
VALUES 
(9, 'SuperAdmin'),
(10, 'Gestione Utenti');

-- Popolamento della tabella Medico
INSERT INTO Medico (ID, Ruolo, specializzazione, via, civico, citta)
VALUES 
(5, 'Medico Specialista', 'Cardiologia', 'Via Roma', '123', 'Milano'),
(6, 'Medico Generico', 'Medicina Generale', 'Via Napoli', '45', 'Roma'),
(7, 'Chirurgo', 'Chirurgia Generale', 'Via Firenze', '78', 'Firenze'),
(8, 'Oculista', 'Chirurgia Generale', 'Via Lungomare Trieste', '50', 'Salerno'),
(11, 'Chirurgo', 'Chirurgia Generale', 'Via Emilia', '33', 'Roma');

-- Popolamento della tabella Paziente
INSERT INTO Paziente (ID)
VALUES 
(1),
(2),
(3),
(4);

-- Popolamento della tabella Disponibilita
INSERT INTO Disponibilita (ID_disponibilita, data, ora_in, ora_fi, ID_medico)
VALUES 
(1, '2024-06-01', '09:00:00', '12:00:00', 5),
(2, '2024-06-02', '14:00:00', '18:00:00', 6),
(3, '2024-06-03', '08:00:00', '09:00:00', 7),
(4, '2024-06-03', '10:00:00', '11:00:00', 7),
(5, '2024-06-04', '09:00:00', '10:30:00', 7),
(6, '2024-06-02', '08:00:00', '09:00:00', 11),
(7, '2024-06-02', '09:00:00', '10:00:00', 11),
(8, '2024-06-02', '10:00:00', '11:00:00', 11);

-- Popolamento della tabella Prenotazione
INSERT INTO Prenotazione (ID_prenotazione, stato, nota, ID_paziente, ID_disponibilita)
VALUES 
(1, 'Da Completare', 'Visita di controllo annuale', 2, 1),
(2, 'Completata', 'Paziente impossibilitato', 3, 2),
(3, 'Da Completare', 'Prima visita di controllo', 4, 3);

-- Popolamento della tabella Recensione
INSERT INTO Recensione (ID_recensione, ID_prenotazione, ID_medico, ID_paziente, nota, stelle)
VALUES 
(1, 1, 5, 2, 'Medico molto competente e disponibile.', 5),
(2, 2, 6, 3, 'Visita soddisfacente, tutto ok.', 4),
(3, 3, 7, 4, 'Ci sono medici migliori', 3);

INSERT INTO Cartelle (Path, Nome, Data, ID_paz)
VALUES
('/cartelle/marco_rossi.pdf', 'Risonanza magnetica', '2024-05-01', 1),
('/cartelle/luca_verdi.pdf', 'Esami del sangue', '2024-05-15', 2);
