DROP DATABASE IF EXISTS medconnect;
CREATE DATABASE medconnect;
USE medconnect;

-- Tabella principale: UtenteRegistrato
CREATE TABLE UtenteRegistrato (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cognome VARCHAR(100) NOT NULL,
    cf VARCHAR(16) UNIQUE NOT NULL,
    genere VARCHAR(10),
    biografia VARCHAR(255),
    data_nascita DATE,
    luogo_nascita VARCHAR(50),
    num_cellulare VARCHAR(10)
);

-- Tabella Admin (specializzazione di UtenteRegistrato)
CREATE TABLE Admin (
    ID INT PRIMARY KEY, 
    Ruolo VARCHAR(50),
    FOREIGN KEY (ID) REFERENCES UtenteRegistrato(ID) ON DELETE CASCADE
);

-- Tabella Medico (specializzazione di UtenteRegistrato)
CREATE TABLE Medico (
    ID INT PRIMARY KEY,
    Ruolo VARCHAR(50),
    specializzazione VARCHAR(100),
    via VARCHAR(100),
    civico VARCHAR(100),
    citta VARCHAR(100),
    FOREIGN KEY (ID) REFERENCES UtenteRegistrato(ID) ON DELETE CASCADE
);

-- Tabella Paziente (specializzazione di UtenteRegistrato)
CREATE TABLE Paziente (
    ID INT PRIMARY KEY,
    FOREIGN KEY (ID) REFERENCES UtenteRegistrato(ID) ON DELETE CASCADE
);

-- Tabella Disponibilita
CREATE TABLE Disponibilita (
    ID_disponibilita INT PRIMARY KEY AUTO_INCREMENT,
    data DATE NOT NULL,
    ora_in TIME NOT NULL,
    ora_fi TIME NOT NULL,
    ID_medico INT NOT NULL,
    FOREIGN KEY (ID_medico) REFERENCES Medico(ID) ON DELETE CASCADE
);

-- Tabella Prenotazione
CREATE TABLE Prenotazione (
    ID_prenotazione INT PRIMARY KEY AUTO_INCREMENT,
    stato VARCHAR(50) DEFAULT 'Da Completare',
    nota VARCHAR(255),
    ID_paziente INT NOT NULL,
    ID_disponibilita INT NOT NULL,
    FOREIGN KEY (ID_paziente) REFERENCES Paziente(ID) ON DELETE CASCADE,
    FOREIGN KEY (ID_disponibilita) REFERENCES Disponibilita(ID_disponibilita) ON DELETE CASCADE
);

-- Tabella Recensione
CREATE TABLE Recensione (
    ID_recensione INT PRIMARY KEY AUTO_INCREMENT,
    ID_prenotazione INT NOT NULL,
    ID_medico INT NOT NULL,
    ID_paziente INT NOT NULL,
    nota VARCHAR(255),
    stelle INT CHECK (stelle BETWEEN 1 AND 5),
    FOREIGN KEY (ID_prenotazione) REFERENCES Prenotazione(ID_prenotazione) ON DELETE CASCADE,
    FOREIGN KEY (ID_medico) REFERENCES Medico(ID) ON DELETE CASCADE,
    FOREIGN KEY (ID_paziente) REFERENCES Paziente(ID) ON DELETE CASCADE
);

CREATE TABLE Cartelle (
    Path VARCHAR(255) PRIMARY KEY,       -- Identificativo unico per la cartella
    Nome VARCHAR(100) NOT NULL,          -- Nome della cartella
    Data DATE NOT NULL,                  -- Data di creazione o aggiornamento
    ID_paz INT NOT NULL,                 -- Chiave esterna che si riferisce alla tabella Paziente
    FOREIGN KEY (ID_paz) REFERENCES Paziente(ID) ON DELETE CASCADE
);

