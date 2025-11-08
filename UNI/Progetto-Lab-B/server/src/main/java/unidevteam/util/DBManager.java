/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import unidevteam.classes.*;
import unidevteam.enumerators.*;

/**
 * Gestore del Database
 */
public class DBManager {
    private String url;
    private String user;
    private String password;

    private static DBManager instance = null;
    private Connection connection = null;

    /**
     * Design pattern singleton per l'istanza del manager
     * @return istanza del DB
     * @throws Exception
     */
    public static DBManager getInstance() throws Exception {
        if(instance != null) return instance;
        throw new Exception("Database manager is not initialized.");
    }

    /**
     * @param hostname hostname DB
     * @param dbName nome del DB
     * @param user utente del DB
     * @param password password del DB
     * @return istanza del DB
     */
    public static DBManager getInstance(String hostname, String dbName, String user, String password) {
        if(instance == null) instance = new DBManager(hostname, dbName, user, password);

        return instance;
    }

    private DBManager(String hostname, String dbName, String user, String password) {
        this.url = "jdbc:postgresql://"+ hostname +"/" + dbName;
        this.user = user;
        this.password = password;

        try {
            connection = connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Collegamento al database postgre
     *
     * @return una connessione
     * @throws java.sql.SQLException
     */
    public Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return DriverManager.getConnection(url, user, password);
    }

     /**
     * Ottiene un ID valido
     * @param columnName nome della colonna
     * @param tableName nome della tabella
     * @return id valido
     */
    public String getValidId(String columnName, String tableName) {
       String sql = "SELECT COUNT("+ columnName +") FROM "+ tableName + " WHERE " + columnName +"=?";
       String resl;
       int count = 0;
       ResultSet sqlResultSet;
       do {
        resl = Generator.getAlphaNumericString(16);
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, resl);
                sqlResultSet = statement.executeQuery();
                while (sqlResultSet.next()) {
                    count = sqlResultSet.getInt(1);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
       } while(count > 0);

       return resl;
    }

    /**
     * Inserisce nel DB un nuovo centro vaccinale
     * @param object centro vaccinale
     * @return id dell'oggetto aggiunto al DB
     * @throws java.sql.SQLException
     */
    public String addCentroVaccinale(CentroVaccinale object) {
        String sql = "INSERT INTO CentriVaccinali(id, nome, qualificatoreIndirizzo, nomeIndirizzo, numeroCivico, comune, provincia, CAP, tipologia)"
                + "VALUES(?,?,?::qualificatoreindirizzo,?,?,?,?,?,?::tipologiacentrovaccinale);";
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, object.getId());
                statement.setString(2, object.getNome());
                statement.setString(3, object.getQualificatoreIndirizzo().name());
                statement.setString(4, object.getNomeIndirizzo());
                statement.setString(5, object.getNumeroCivico());
                statement.setString(6, object.getComune());
                statement.setString(7, object.getProvincia());
                statement.setString(8, object.getCAP());
                statement.setString(9, object.getTipologiaCentroVaccinale().name());

                statement.executeUpdate();
                return object.getId();
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
                return null;
            }
    }

    /**
     * Inserisce nel DB un nuovo cittadino
     * @param object centro vaccinale
     * @return il risultato dell'operazione
     * @throws java.sql.SQLException
     */
    public Boolean addCittadino(Cittadino object) {
        String sql = "INSERT INTO Cittadini_Registrati(codiceFiscale, nome, cognome, email, idVaccinazione, passwd) VALUES (?,?,?,?,?,?)";

        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                
                statement.setString(1, object.getCodiceFiscale());
                statement.setString(2, object.getNome());
                statement.setString(3, object.getCognome());
                statement.setString(4, object.getEmail());
                statement.setString(5, object.getIdVaccinazione());
                statement.setString(6, object.getHashedPassword());

                statement.executeUpdate();
                return true;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return false;
        }   
    }

    /**
     * Inserisce nel DB un nuovo vaccinato
     * @param idVaccinazione id di vaccinazione
     * @param nomeCittadino nome del cittadino
     * @param cognomeCittadino cognome del cittadino
     * @param codiceFiscale codice fiscale del cittadino
     * @param dataSomministrazione data somministrazione del vaccino
     * @param typeVaccino tipo di vaccino
     * @param idCentro id del centro
     * @return id di vaccinazione del vaccinato aggiunto
     */
    public String addVaccinato(String idVaccinazione, String nomeCittadino, String cognomeCittadino, 
                                String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) {
        String sql = "INSERT INTO Vaccinati(id, nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, tipoVaccino, idCentro) VALUES (?,?,?,?,?,?::TipoVaccino,?)";
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, idVaccinazione);
                statement.setString(2, nomeCittadino);
                statement.setString(3, cognomeCittadino);
                statement.setString(4, codiceFiscale);
                statement.setDate(5, dataSomministrazione);
                statement.setString(6, typeVaccino.name().toUpperCase());
                statement.setString(7, idCentro);
                statement.executeUpdate();
                return idVaccinazione;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    /**
     * Elimina centro vaccinale
     * @param object centro vaccinale
     * @return il risultato dell'operazione
     * @throws java.sql.SQLException
     */
    public Boolean deleteCentroVaccinale(CentroVaccinale object) {
        String sql = "DELETE FROM CentriVaccinali WHERE nome = ?";
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, object.getNome());
                statement.executeUpdate();
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
                return false;
            }
        return true;
    }

    /**
     * Ottenimento di tutti i centri vaccinali
     * 
     * @return una lista di tutti i centri vaccinali
     * @throws java.sql.SQLException
     */
    public List<CentroVaccinale> getAllCentriVaccinali() {
        String sql = "SELECT * FROM CentriVaccinali";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(
                            rs.getString("id"),
                            rs.getString("nome"), 
                            QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                            rs.getString("nomeIndirizzo"),
                            rs.getString("numeroCivico"),
                            rs.getString("comune"),
                            rs.getString("provincia"),
                            rs.getString("cap"),
                            TipologiaCentroVaccinale.valueOf(rs.getString("tipologia"))
                        );
                        resl.add(centro);
                    }
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }

        return resl;
    }
    
    /**
     * Ottenimento dei centri vaccinali per nome
     * @param nomeCentro nome del centro che si sta cercando
     * @return una lista di centri vaccinali che matchano la string in input
     */
    public List<CentroVaccinale> getCentriVaccinaliByNome(String nomeCentro) {
        String sql = "SELECT * FROM CentriVaccinali WHERE nome ILIKE ?";
        List<CentroVaccinale> resl =  new ArrayList<CentroVaccinale>();
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%"+nomeCentro+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                CentroVaccinale centroVaccinale = new CentroVaccinale(
                    rs.getString("id"),
                    rs.getString("nome"), 
                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                    rs.getString("nomeIndirizzo"),
                    rs.getString("numeroCivico"),
                    rs.getString("comune"),
                    rs.getString("provincia"),
                    rs.getString("cap"),
                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia"))
                );

                resl.add(centroVaccinale);
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return resl;
    }

    /**
     * Ottenimento dei centri vaccinali per comune e tipologia del centro
     * @param comune nome del comune per filtrare i centri vaccinali
     * @param tipologiaCentroVaccinale nome della tipologia di centro per filtrare i centri vaccinali
     * @return una lista di centri vaccinali che matchano la string in input
     */
    public List<CentroVaccinale> getCentriVaccinaliByComuneETipologiaCentro(String comune, TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        String sql = "SELECT * FROM CentriVaccinali WHERE comune = ? AND tipologia = ?::TipologiaCentroVaccinale";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, comune);
                statement.setString(2, tipologiaCentroVaccinale.name().toUpperCase());
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(
                            rs.getString("id"),
                            rs.getString("nome"), 
                            QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                            rs.getString("nomeIndirizzo"),
                            rs.getString("numeroCivico"),
                            rs.getString("comune"),
                            rs.getString("provincia"),
                            rs.getString("cap"),
                            TipologiaCentroVaccinale.valueOf(rs.getString("tipologia"))
                        );
                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }

        return resl;
    }

    /**
     * Ottenimento dei centri vaccinali per ID
     * 
     * @return una lista di centri vaccinali che matchano la string in input
     * @throws java.sql.SQLException
     */
    public CentroVaccinale getCentroVaccinaleById(String id) {
        String sql = "SELECT * FROM CentriVaccinali WHERE id = ?";
        CentroVaccinale resl = null;
        try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        resl = new CentroVaccinale(
                            rs.getString("id"),
                            rs.getString("nome"), 
                            QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                            rs.getString("nomeIndirizzo"),
                            rs.getString("numeroCivico"),
                            rs.getString("comune"),
                            rs.getString("provincia"),
                            rs.getString("cap"),
                            TipologiaCentroVaccinale.valueOf(rs.getString("tipologia"))
                        );
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }

        return resl;
    }

    /**
     * Ottenimento del numero di centri vaccinali
     * 
     * @return numero dei centri vaccinali in long
     * @throws java.sql.SQLException
     */
    public long getCountCentriVaccinali() {
        String sql = "SELECT COUNT(nome) FROM CentriVaccinali";
         try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                        return rs.getLong(1);
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        return 0;
    }

    /**
     * Ottenimento del numero di cittadini
     * 
     * @return numero dei cittadini in long
     * @throws java.sql.SQLException
     */
    public long getCountCittadini() {
        String sql = "SELECT COUNT(codiceFiscale) FROM Cittadini_Registrati";
         try {
                if(connection == null && !connection.isClosed()) connection = connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                        return rs.getLong(1);
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        return 0;
    }

    /**
     * Inserisce nel DB l'evento avverso
     * @param eventoAvverso evento avverso
     * @param idVaccinazione id della vaccinazione
     * @param idCentro id del centro
     * @return il risultato dell'operazione
     */
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) {
        // Ottieni tutte le informazioni
        TipoVaccino tipoVaccino = null;
        Date dataSomministrazione = null;

        String sql = "SELECT dataSomministrazione, tipoVaccino FROM Vaccinati WHERE id = ?";

        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idVaccinazione);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                dataSomministrazione = rs.getDate(1);
                tipoVaccino = TipoVaccino.valueFromString(rs.getString(2));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        eventoAvverso.setDataSomministrazione(dataSomministrazione);
        eventoAvverso.setTipoVaccino(tipoVaccino);
        eventoAvverso.setDataAvventimento(new Date(Calendar.getInstance().getTime().getTime()));

        String adverseEventID = Generator.getAlphaNumericString(16);

        // Inserisci l'oggetto evento avverso
        sql = "INSERT INTO Eventi_Avversi (idEvento, tipoEvento, tipoVaccino, gradoSeverita, dataSomministrazione, dataAvvenimento, note, idCentro)"+
              "VALUES (?,?::TipoEvento,?::TipoVaccino,?,?,?,?,?)";

        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, adverseEventID);
            statement.setString(2, eventoAvverso.getTipoEvento().name().toUpperCase());
            statement.setString(3, eventoAvverso.getTipoVaccino().name().toUpperCase());
            statement.setInt(4, eventoAvverso.getGradoSeverita());
            statement.setDate(5, eventoAvverso.getDataSomministrazione());
            statement.setDate(6, eventoAvverso.getDataAvventimento());
            statement.setString(7, eventoAvverso.getNote());
            statement.setString(8, idCentro);
            statement.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Ottenimento del numero di eventi avversi
     * @return il numero degli eventi avversi
     */
    public long getCountEventiAvversi() {
        String sql = "SELECT COUNT(*) FROM Eventi_Avversi";
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return 0;
    }

    /**
     * Autenticazione utente
     * @param email email
     * @param plainPassword password non hashata dell'utente
     * @return il cittadino autenticato
     */
    public Cittadino autenticaUtente(String email, String plainPassword) {
        String sql = "SELECT * FROM Cittadini_Registrati WHERE email = ?";
        Cittadino res = null;
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String hashedPsw = rs.getString(6);
                if(BCrypt.checkpw(plainPassword, hashedPsw)) {
                    String _idVaccinazione = rs.getString(1);
                    String _nome = rs.getString(2);
                    String _cognome = rs.getString(3);
                    String _email = rs.getString(4);
                    String _codiceFiscale = rs.getString(5);

                    res = new Cittadino(_nome, _cognome, _codiceFiscale, _email, _idVaccinazione);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return res;
    }

    /**
     * Controllo se l'id vaccinazione dato e' presente tra quelli di un centro vaccinale
     * @param idVaccinazione id vaccinazione da controllare
     * @param idCentro id centro vaccinale in cui controllare
     * @return esito dell'operazione di controllo
     */
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) {
        String sql = "SELECT * FROM Vaccinati WHERE id = ? AND idCentro = ?";
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idVaccinazione);
            statement.setString(2, idCentro);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while(rs.next()) { count++; }
            return count > 0;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        
        return false;
    }

    /**
     * Ottenimento dei dati sugli eventi avversi di un dato centro vaccinale
     * @param idCentro id del centro vaccinale di cui si vogliono ottenere i dati riguardo gli eventi avversi
     * @return oggetto DatiExtraCentroVaccinale contenente le informazioni sugli eventi avversi registrati
     * @throws RemoteException
     */
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) {
        // Numero totale eventi avversi
        int numOfEventiAvversi = 0;
        String sql1 = "SELECT COUNT(*) FROM Eventi_Avversi WHERE idCentro = ?";
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.setString(1, idCentro);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                numOfEventiAvversi = rs.getInt(1);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }

        // Severita' media
        double gradoSeveritaMedio = 0.0;
        String sql2 = "SELECT AVG(gradoSeverita) FROM Eventi_Avversi WHERE idCentro = ?";
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql2);
            statement.setString(1, idCentro);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                gradoSeveritaMedio = rs.getDouble(1);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }

        // Tipologia evento piu' frequente
        TipoEvento tipoPiuFrequente = null;
        String sql3 = "SELECT tipoEvento, COUNT(tipoEvento) AS occorrenze " +
        "FROM (SELECT * FROM Eventi_Avversi WHERE idCentro = ?) AS sq GROUP BY tipoEvento ORDER BY occorrenze DESC LIMIT 1";
        try {
            if(connection == null && !connection.isClosed()) connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql3);
            statement.setString(1, idCentro);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                tipoPiuFrequente = TipoEvento.valueFromString(rs.getString(1));
                System.out.println(tipoPiuFrequente.getValue());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }

        return new DatiExtraCentroVaccinale(numOfEventiAvversi, gradoSeveritaMedio, tipoPiuFrequente);
    }
}
