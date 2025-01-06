use medconnect;

-- Popolamento della tabella UtenteRegistrato
-- Password:
--      Pazienti: password1
--      Medici: medico1pass
--      Admin: adminpass1

INSERT INTO UtenteRegistrato (ID, email, pass, nome, cognome, cf, genere, biografia, data_nascita, luogo_nascita, num_cellulare)
VALUES
    (1, 'paziente1@example.com', SHA1('Ma1985Ro@'), 'Marco', 'Rossi', 'RSSMRC85A01H501N', 'M', 'Mi chiamo Marco Rossi, sono nato a Roma il 1° gennaio 1985. Sono un paziente con malattia cronica, e mi impegno a gestire la mia salute con costanza e determinazione.', '1985-01-01', 'Roma', '3331112233'),
    (2, 'paziente2@example.com', SHA1('Lu1978Mi@'), 'Luca', 'Verdi', 'VRDLCA78B09G700Y', 'M', 'Mi chiamo Luca Verdi, nato a Milano il 15 febbraio 1978. Sono un paziente regolare, attento alla mia salute e impegnato nel mantenere uno stile di vita sano e equilibrato.', '1978-02-15', 'Milano', '3342223344'),
    (3, 'paziente3@example.com', SHA1('Gi1992Na@'), 'Giulia', 'Bianchi', 'BNCGLA92F45L219K', 'F', 'Sono Giulia Bianchi, nata a Napoli il 3 marzo 1992. Attualmente sto seguendo un follow-up post-operatorio e mi impegno a recuperare la mia salute con il supporto dei medici.', '1992-03-03', 'Napoli', '3353334455'),
    (4, 'paziente4@example.com', SHA1('La1985Bo@'), 'Laura', 'Neri', 'NRILRA85D04H501X', 'F', 'Mi chiamo Laura Neri, sono nata a Bologna il 4 aprile 1985. Attualmente sono in cura e sto lavorando per migliorare la mia salute con l''aiuto dei miei medici.', '1985-04-04', 'Bologna', '3364445566'),
    (5, 'medico1@example.com', SHA1('Fr1975Ro@'), 'Francesco', 'Rossi', 'RSSFNC75E08A839T', 'M', 'Sono Francesco Rossi, nato a Roma il 9 agosto 1975. Sono uno specialista in cardiologia e mi dedico con passione alla cura del cuore dei miei pazienti, aiutandoli a mantenere una buona salute cardiovascolare.', '1975-08-09', 'Roma', '3391112233'),
    (6, 'medico2@example.com', SHA1('Sa1982Mi@'), 'Sara', 'Persico', 'PRSSRA82T40B063T', 'F', 'Sono Sara Persico, nata a Milano l''11 dicembre 1982. Sono specialista in ortopedia e mi impegno a migliorare la qualità della vita dei miei pazienti, trattando e prevenendo i disturbi muscolo-scheletrici.', '1982-12-11', 'Milano', '3392223344'),
    (7, 'medico3@example.com', SHA1('Gi1979Fi@'), 'Giorgio', 'Salvemini', 'SLVGRG79R01F257X', 'M', 'Sono Giorgio Salvemini, nato a Firenze il 1° luglio 1979. Specializzato in dermatologia, mi dedico alla cura e prevenzione delle malattie della pelle, aiutando i miei pazienti a mantenere una pelle sana.', '1979-07-01', 'Firenze', '3393334455'),
    (8, 'medico4@example.com', SHA1('El1990Na@'), 'Elena', 'Piazza', 'PZZLNA90A55F258M', 'F', 'Sono Elena Piazza, nata a Napoli il 22 giugno 1990. Specialista in neurologia, mi dedico alla diagnosi e al trattamento delle malattie neurologiche, con l''obiettivo di migliorare la qualità della vita dei miei pazienti.', '1990-06-22', 'Napoli', '3394445566'),
    (9, 'admin1@example.com', SHA1('Al1982Ro@'), 'Alessandro', 'Giorgi', 'GRGLSN82C10H501Z', 'M', 'Sono Alessandro Giorgi, nato a Roma il 10 marzo 1982. Come amministratore principale, mi occupo della gestione e del coordinamento delle risorse, garantendo l''efficienza e il buon funzionamento dei sistemi.', '1982-03-10', 'Roma', '3401112233'),
    (10, 'admin2@example.com', SHA1('Fr1990Mi@'), 'Francesca', 'Bianchi', 'BNCHFRC90L70H219', 'F', 'Sono Francesca Bianchi, nata a Milano il 15 novembre 1990. Mi occupo della gestione utenti, garantendo un''esperienza sicura ed efficiente per gli utenti del sistema.', '1990-11-15', 'Milano', '3402223344'),
    (11, 'medico5@example.com', SHA1('Pa1989Na@'), 'Paolo', 'Languilla', 'LNGPLA89C27F839V', 'M', 'Sono Paolo Languilla, nato a Napoli il 27 marzo 1989. Specialista in chirurgia, mi dedico a trattamenti chirurgici avanzati per migliorare la salute e il benessere dei miei pazienti.', '1989-03-27', 'Napoli', '3394445577');

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
    (1, '2024-06-01', '09:00:00', '12:00:00', 6),
    (2, '2024-06-02', '14:00:00', '18:00:00', 8),
    (3, '2024-06-03', '08:00:00', '09:00:00', 6),
    (4, '2024-06-03', '10:00:00', '11:00:00', 6),
    (5, '2024-06-04', '09:00:00', '10:30:00', 7),
    (6, '2024-06-05', '08:00:00', '09:00:00', 7),
    (7, '2024-06-06', '09:00:00', '10:00:00', 8),
    (8, '2024-06-07', '10:00:00', '11:00:00', 11),
    (9, '2024-06-08', '08:00:00', '09:00:00', 11),
    (10, '2024-06-09', '09:00:00', '10:00:00', 11),
    (11, '2024-06-10', '10:00:00', '11:00:00', 11),
    (12, '2024-06-11', '11:00:00', '12:00:00', 5),
    (13, '2024-06-11', '12:00:00', '13:00:00', 5),
    (14, '2024-06-11', '15:00:00', '16:00:00', 6),
    (15, '2024-06-11', '16:00:00', '17:00:00', 6);

-- Popolamento della tabella Prenotazione
INSERT INTO Prenotazione (ID_prenotazione, stato, nota, ID_paziente, ID_disponibilita)
VALUES
    (1, 'Da Completare', 'Visita di controllo annuale', 3, 1),
    (2, 'Completata', 'Visita di controllo periodica', 4, 3),
    (3, 'Completata', 'Forte dolore al torace e pressione bassa', 3, 2),
    (4, 'Da Completare', 'Prima visita di controllo', 2, 4),
    (5, 'Completata', 'analisi e tac', 2, 7),
    (6, 'Completata', 'Paziente impossibilitato', 4, 5),
    (7, 'Completata', 'Prima visita di controllo', 4, 6);

-- Popolamento della tabella Recensione
INSERT INTO Recensione (ID_recensione, ID_prenotazione, ID_medico, ID_paziente, nota, stelle)
VALUES
    (1, 1, 5, 1, 'Medico molto competente e disponibile.', 5),
    (2, 2, 6, 2, 'Visita soddisfacente, tutto ok.', 4),
    (3, 3, 7, 3, 'Ci sono medici migliori', 3);

INSERT INTO Cartelle (Path, Nome, Data, ID_paz)
VALUES
    ('/cartelle/marco_rossi.pdf', 'Risonanza magnetica', '2024-05-01', 1),
    ('/cartelle/luca_verdi.pdf', 'Esami del sangue', '2024-05-15', 2);
