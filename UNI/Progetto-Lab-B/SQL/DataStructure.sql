/* CentroVaccini v1.0 */
/* -------------------------- */
/* Written by: Andrea Ferrario, Loschiavo Christian, Ivan Giubilei, Nicolo' Rossi */
/* -------------------------- */
/* DATA DEFINITION */

/* Enum Definition */
CREATE TYPE QualificatoreIndirizzo AS ENUM ('VIA', 'VIALE', 'PIAZZA');
CREATE TYPE TipologiaCentroVaccinale AS ENUM ('OSPEDALIERO', 'AZIENDALE', 'HUB');
CREATE TYPE TipoEvento AS ENUM ('MAL_DI_TESTA', 'FEBBRE', 'DOLORI_MUSCOLARI_ARTICOLARI', 'LINFOADENOPATIA', 'TACHICARDIA', 'CRISI_IPERTENSIVA');
CREATE TYPE TipoVaccino AS ENUM ('PFIZER', 'ASTRAZENECA', 'MODERNA', 'J_AND_J');

/* Table Definition */
CREATE TABLE CentriVaccinali (
    id CHAR(16) PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    qualificatoreIndirizzo QualificatoreIndirizzo NOT NULL,
    nomeIndirizzo VARCHAR(50) NOT NULL,
    numeroCivico VARCHAR(5) NOT NULL,
    comune VARCHAR(50) NOT NULL,
    provincia CHAR(2) NOT NULL,
    CAP CHAR(5) NOT NULL,
    tipologia TipologiaCentroVaccinale NOT NULL
);

/*Questa definizione sarà soltanto un modello da utilizzare per la creazione dinamica
   della tabella vaccinati relativa ad un centro vaccinale particolare */
CREATE TABLE Vaccinati (
    id CHAR(16) PRIMARY KEY,
    nomeCittadino VARCHAR(40) NOT NULL,
    cognomeCittadino VARCHAR(40) NOT NULL,
    codiceFiscale CHAR(16) NOT NULL UNIQUE,
    dataSomministrazione DATE NOT NULL,
    tipoVaccino TipoVaccino NOT NULL,
    idCentro CHAR(16),
    
    FOREIGN KEY (idCentro) REFERENCES CentriVaccinali(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Cittadini_Registrati(
    idVaccinazione CHAR(16) PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    cognome VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL UNIQUE,
    codiceFiscale CHAR(16) NOT NULL UNIQUE,
    passwd CHAR(60) NOT NULL,

    /* Vaccinati_NomeCentro da sostituire con il nome del centro in cui si è vaccinato */
    FOREIGN KEY (idVaccinazione) REFERENCES Vaccinati(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Eventi_Avversi (
    idEvento CHAR(16) NOT NULL PRIMARY KEY,
    tipoEvento TipoEvento NOT NULL,
    tipoVaccino TipoVaccino NOT NULL,
    gradoSeverita SMALLINT CHECK (gradoSeverita < 5 AND gradoSeverita > 1) NOT NULL,
    dataSomministrazione DATE CHECK (dataAvvenimento > dataSomministrazione) NOT NULL,
    dataAvvenimento DATE CHECK (dataAvvenimento > dataSomministrazione) NOT NULL,
    note VARCHAR(256),
    idCentro CHAR(16) NOT NULL,

    FOREIGN KEY (idCentro) REFERENCES CentriVaccinali(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);






