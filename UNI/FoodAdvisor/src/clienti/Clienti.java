package src.clienti;

/** 
 *  Università degli studi dell'Insubria
 *  
 *  Anno accademico 2019/2020 
 *  Sede Varese
 *  
 *  Progetto di Laboratorio Interdisciplinare A 
 * 
 *  Loschiavo Christian 739894 Varese
 *  Giubilei  Ivan      739892 Varese
 *  Rossi     Nicolò    742626 Varese
 *  Ferrario  Andrea    740485 Varese
 * 
 *  FoodAdvisor, applicazione Clienti
 */

import src.classes.User;
import src.classes.Restaurant;
import src.classes.Review;
import src.gui.components.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.gui.pages.C_Login;
import src.gui.pages.C_Register;
import src.gui.pages.C_Register2;
import src.gui.pages.C_Register3;
import src.gui.pages.C_RestaurantInfo;
import src.gui.pages.C_Search;
import src.gui.pages.C_ReviewInsertion;
import src.gui.pages.C_Reviews;
import src.util.*;

public class Clienti {
  private FWindow mainWindow;
  private C_Login loginPage;
  private C_Register registerPage;
  private C_Register2 registerPage2;
  private C_Register3 registerPage3;
  private C_Search searchPage;
  private C_RestaurantInfo restaurantInfoPage;
  private C_ReviewInsertion reviewInsertionPage;
  private C_Reviews reviewsPage;
  private boolean canChangePage;

  private static User user;
  private static List<Restaurant> restaurants;

  public static void main(final String[] args) {
    SwingUtilities.invokeLater(() -> new Clienti());
  }

  /** Costruttore della classe clienti */
  public Clienti() {
    // setup della window
    registerFonts();
    user = null;
    restaurants = FileManager.GetRestaurants();

    mainWindow = new FWindow("FoodAdvisor Clienti");
    loginPage = new C_Login();

    changePage(loginPage.getPage());
    addLoginPageListeners();

    mainWindow.setVisible(true);
    loginPage.getPage().requestFocusInWindow();
  }

  /**
   * Registra un nuovo utente inserendolo nel file "Utenti.dati"
   * 
   * @param user Oggetto User da registrare
   * @return Esito della registrazione (boolean) */
  public static boolean RegisterNewUser(User user) {
    return FileManager.SaveUser(user);
  }

  /**
   * Autentica l'account di un cliente
   * 
   * @param email    Email da autenticare
   * @param password Password da autenticare
   * @return Esito dell'autenticazione (boolean) */
  public static boolean AuthenticateUser(String email, String password) {
    // Array di stringhe contenente l'intero contenuto del file Utenti.dati
    String[] records = FileManager.GetFileRecords("Utenti.dati");
    // Se la stringa in posizione 0 ritornata dal metodo per leggere
    // dal file è "Error" ritorno (false), l'autenticazione non è
    // andata a buon fine, altrimenti continuo
    if (records.length == 0)
      return false;
    // Per ognuno dei record (utenti) controllo se l'email e
    // la password inseriti dall'utente che desidera accedere corrispondono
    for (String record : records) {
      // Splitto il record corrente per i suoi campi
      String[] fields = record.split("\\|");
      // Field 5 => Email, Field 6 => Hashed password
      String inputPasswordHash = Security.GetHash(password);
      if (fields[5].equals(email) && fields[6].equals(inputPasswordHash)) {
        // Accesso riuscito correttamente
        // nickname, nome, cognome, comune, provincia, email, password(hash)
        user = new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
        return true;
      }
    }
    return false;
  }

  /** Aggiunge i listeners della pagina di login */
  public void addLoginPageListeners() {
    loginPage.login_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        canChangePage = true;

        canChangePage &= validateField(loginPage.email_tf, "Email");
        canChangePage &= validateField(loginPage.password_pf, "Password");

        if (canChangePage) {
          String email = loginPage.email_tf.getText();
          String password = String.valueOf(loginPage.password_pf.getPassword());

          if (AuthenticateUser(email, password)) {
            emptyFields();
            searchPage = new C_Search(user);
            addSearchPageListeners();
            changePage(searchPage.getPage());
          } else {
            emptyField(loginPage.email_tf, "Email");
            emptyField(loginPage.password_pf, "Password");
            validateField(loginPage.email_tf, "Email");
            validateField(loginPage.password_pf, "Password");
          }
        }
      }
    });

    loginPage.register_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        emptyFields();
        registerPage = new C_Register();
        addRegisterPageListeners();
        changePage(registerPage.getPage());
      }
    });

    loginPage.guest_lb.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent e) {
        emptyFields();
        searchPage = new C_Search(user);
        addSearchPageListeners();
        changePage(searchPage.getPage());
      }
    });
  }

  /** Aggiunge i listeners della prima pagina di registrazione*/
  public void addRegisterPageListeners() {
    registerPage.back_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        changePage(loginPage.getPage());
      }
    });

    registerPage.continue_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        /* TO DO: Controllo nickname */
        canChangePage = true;

        if (registerPage.nick_tf.getText().length() > 20 || registerPage.nick_tf.getText().length() < 5)
          emptyField(registerPage.nick_tf, "Nickname");

        canChangePage &= validateField(registerPage.nick_tf, "Nickname");
        canChangePage &= validateField(registerPage.name_tf, "Nome");
        canChangePage &= validateField(registerPage.surname_tf, "Cognome");

        if (canChangePage) {
          registerPage2 = new C_Register2();
          addRegisterPage2Listeners();
          changePage(registerPage2.getPage());
        }
      }
    });
  }

  /** Aggiunge i listeners della seconda pagina di registrazione */
  public void addRegisterPage2Listeners() {
    registerPage2.back_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        changePage(registerPage.getPage());
      }
    });

    registerPage2.continue_btn.addMouseListener(new MouseInputAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        canChangePage = true;

        canChangePage &= validateField(registerPage2.town_tf, "Comune");
        canChangePage &= validateField(registerPage2.district_tf, "Provincia");

        if (canChangePage) {
          registerPage3 = new C_Register3();
          addRegisterPage3Listeners();
          changePage(registerPage3.getPage());
        }
      }
    });
  }

  /** Aggiunge i listeners della terza pagina di registrazione */
  public void addRegisterPage3Listeners() {
    registerPage3.back_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        changePage(registerPage2.getPage());
      }
    });

    registerPage3.continue_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        canChangePage = true;

        String pass1 = String.valueOf(registerPage3.password1_pf.getPassword());
        String pass2 = String.valueOf(registerPage3.password2_pf.getPassword());

        if (!pass1.equals(pass2)) {
          emptyField(registerPage3.password1_pf, "Password");
          emptyField(registerPage3.password2_pf, "Password");
        }

        canChangePage &= validateField(registerPage3.email_tf, "Email");
        canChangePage &= validateField(registerPage3.password1_pf, "Password");
        canChangePage &= validateField(registerPage3.password2_pf, "Password");

        if (canChangePage) {
          // Registra l'utente
          // nickname, nome, cognome, comune, provincia, email, password(hash)
          String nickname = registerPage.nick_tf.getText();
          String name = registerPage.name_tf.getText();
          String surname = registerPage.surname_tf.getText();

          String town = registerPage2.town_tf.getText();
          String district = registerPage2.district_tf.getText();

          String email = registerPage3.email_tf.getText();
          String hashedPsw = Security.GetHash(pass1);

          User user = new User(nickname, name, surname, town, district, email, hashedPsw);

          String result = RegisterNewUser(user) ? "Registrazione effettuata con successo"
              : "Registrazione fallita, ritenta.";

          JOptionPane.showMessageDialog(null, result, "Registrazione", JOptionPane.PLAIN_MESSAGE);

          emptyFields();
          changePage(loginPage.getPage());
        }
      }
    });
  }

  /** Aggiunge i listeners della pagina di ricerca di ristoranti */
  public void addSearchPageListeners() {
    searchPage.searchBar_tb.getDocument().addDocumentListener(new DocumentListener() {
      public void removeUpdate(DocumentEvent e) {
        updateRestaurantsList();
      }

      public void insertUpdate(DocumentEvent e) {
        updateRestaurantsList();
      }

      public void changedUpdate(DocumentEvent e) {
        updateRestaurantsList();
      }

      public void updateRestaurantsList() {
        String value = searchPage.searchBar_tb.getText().toLowerCase();
        List<Restaurant> filteredList = new ArrayList<Restaurant>();

        if (value.contains(",")) {
          String[] values = value.split(",");
          if (values.length == 2 && !values[0].equals("") && !values[1].equals("")) {
            if (values[1].charAt(0) == ' ' && values[1].length() > 1)
              values[1] = values[1].substring(1, values[1].length());

            filteredList = FilterListBy(restaurants, "Town&Typology", values);
          }
        } else {
          filteredList = FilterListBy(restaurants, "Name", new String[] { value });
          filteredList.addAll(FilterListBy(restaurants, "Town", new String[] { value }));
          filteredList.addAll(FilterListBy(restaurants, "Typology", new String[] { value }));
        }

        searchPage.listModel.clear();

        for (Restaurant r : filteredList)
          searchPage.listModel.addElement(r.GetName());
      }
    });

    searchPage.backIcon_lbl.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        // Logout
        user = null;
        changePage(loginPage.getPage());
      }
    });

    searchPage.restaurants_lst.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        FList<String> list = (FList<String>)arg0.getSource();
        if (list.getSelectedValue() != null) {
          String restName = list.getSelectedValue().toLowerCase();

          Restaurant selectedRestaurant = FilterListBy(restaurants, "Name", new String[] { restName }).get(0);
          restaurantInfoPage = new C_RestaurantInfo(user, selectedRestaurant);
          addRestaurantInfoPageListeners();
          changePage(restaurantInfoPage.getPage());
        }
      }
    });
  }

  /** Aggiunge i listeners della pagina di info del ristorante specificato */
  public void addRestaurantInfoPageListeners() {
    restaurantInfoPage.backIcon_lbl.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        searchPage = new C_Search(user);
        addSearchPageListeners();
        changePage(searchPage.getPage());
      }
    });

    restaurantInfoPage.ratingsText_lbl.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        if (user != null && restaurantInfoPage.action.equals("inserisci")) {
          reviewInsertionPage = new C_ReviewInsertion(user, restaurantInfoPage.getRestaurant());
          addReviewInsertionPageListeners();
          changePage(reviewInsertionPage.getPage());
        }

        if(restaurantInfoPage.action.equals("visualizza")) {
            reviewsPage = new C_Reviews(user, restaurantInfoPage.getRestaurant());
            addReviewsPageListeners();
            changePage(reviewsPage.getPage());
        }
        
        if(user == null && restaurantInfoPage.action.equals("inserisci")) {
          JOptionPane.showMessageDialog(null, "Devi essere loggato per lasciare una recensione", "Errore",
              JOptionPane.PLAIN_MESSAGE);
        }
      }
    });
  }

  /** Aggiunge i listeners della pagina di inserimento delle recensioni per un ristorante*/
  public void addReviewInsertionPageListeners() {
    reviewInsertionPage.backIcon_lbl.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        changePage(restaurantInfoPage.getPage());
      }
    });

    reviewInsertionPage.submit_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        canChangePage = true;

        String textFieldContent = reviewInsertionPage.textField.getText();
        if (textFieldContent.length() > 256 || textFieldContent.contains("|") || textFieldContent.contains("\n"))
          emptyField(reviewInsertionPage.textField, "Descrizione");

        canChangePage &= validateField(reviewInsertionPage.reviewTitle_tf, "Titolo recensione");
        canChangePage &= validateField(reviewInsertionPage.stars, "*");

        if (reviewInsertionPage.textField.getText().equals("Descrizione") || reviewInsertionPage.textField.getText().contains("|")) {
          reviewInsertionPage.scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
          canChangePage = false;
        } else {
          reviewInsertionPage.scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
          canChangePage = true;
        }

        if (canChangePage) {
          // Inserisci nuovo giudizio
          int stars = Integer.parseInt((String) reviewInsertionPage.stars.getSelectedItem());
          String title = reviewInsertionPage.reviewTitle_tf.getText();
          String description = reviewInsertionPage.textField.getText();
          Review review = new Review(restaurantInfoPage.getRestaurant().GetId(), user.GetNickname(), (byte) stars,
              title, description);

          if (FileManager.SaveRestaurantReview(review)) {
            JOptionPane.showMessageDialog(null, "Recensione inserita con successo!", "Successo",
                JOptionPane.PLAIN_MESSAGE);
            // Ristanzia pagina di info ristorante e cambia a quella pagina
            Restaurant selectedRestaurant = restaurantInfoPage.getRestaurant();
            restaurantInfoPage = new C_RestaurantInfo(user, selectedRestaurant);
            addRestaurantInfoPageListeners();
            changePage(restaurantInfoPage.getPage());
          } else {
            JOptionPane.showMessageDialog(null, "Non è stato possibile inserire la recensione", "Errore",
                JOptionPane.PLAIN_MESSAGE);
          }

          emptyField(reviewInsertionPage.reviewTitle_tf, "Titolo recensione");
          emptyField(reviewInsertionPage.stars, "*");
          emptyField(reviewInsertionPage.textField, "Descrizione");
        }
      }
    });
  }

  /** Aggiunge i listeners della pagina di visualizzazione delle recensioni relative ad un ristorante */
  public void addReviewsPageListeners() {
    reviewsPage.backIcon_lbl.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        changePage(restaurantInfoPage.getPage());
      }
    });

    reviewsPage.insert_btn.addMouseListener(new MouseAdapter() {
      public void mouseReleased(final MouseEvent arg0) {
        if(user != null) {
          reviewInsertionPage = new C_ReviewInsertion(user, restaurantInfoPage.getRestaurant());
          addReviewInsertionPageListeners();
          changePage(reviewInsertionPage.getPage());
        } else {
          JOptionPane.showMessageDialog(null, "Devi essere loggato per lasciare una recensione", "Errore",
              JOptionPane.PLAIN_MESSAGE);
        }
      }
    });
  }

  /**
   * Filtra una lista data in input in base ad un parametro
   * 
   * @param list       Lista di oggetti Restaurant originale da filtrare
   * @param filterType Stringa rappresentante l'attributo da filtrare: (Town, Typology, Name, Town&Typology)
   * @param values     Array di stringhe contenenti i valori per cui filtrare
   * @return Lista di ristoranti filtrata */
  public static List<Restaurant> FilterListBy(List<Restaurant> list, String filterType, String[] values) {
    if (list.isEmpty())
      return new ArrayList<Restaurant>();
    if (values[0].equals(""))
      return new ArrayList<Restaurant>();

    Predicate<Restaurant> filter;
    switch (filterType) {
      case "Town":
        filter = restaurant -> restaurant.GetAddress().GetTownName().toLowerCase().equals(values[0]);
        break;

      case "Typology":
        filter = restaurant -> restaurant.GetType().toString().toLowerCase().equals(values[0]);
        break;

      case "Name":
        filter = restaurant -> restaurant.GetName().toLowerCase().contains(values[0]);
        break;

      case "Town&Typology":
        list = FilterListBy(list, "Town", new String[] { values[0] });
        if (list.isEmpty())
          return new ArrayList<Restaurant>();
        filter = restaurant -> restaurant.GetType().toString().toLowerCase().equals(values[1]);
        break;

      default:
        filter = restaurant -> restaurant.toString() != null;
        break;
    }

    List<Restaurant> filteredList = list.stream().filter(filter).collect(Collectors.toList());

    return filteredList;
  }

  /**
   * Svuota il contenuto del campo desiderato e lo reinizializza con il suo
   * placeholder originale
   * 
   * @param field       Campo da svuotare
   * @param placeholder Placeholder default del campo */
  public void emptyField(Object field, String placeholder) {
    if (field instanceof FTextField) {
      ((FTextField) field).setText(placeholder);
      ((FTextField) field).setForeground(Color.GRAY);
    } else if (field instanceof FPasswordField) {
      ((FPasswordField) field).setText(placeholder);
      ((FPasswordField) field).setForeground(Color.GRAY);
    } else if (field instanceof FComboBox) {
      ((FComboBox) field).setSelectedItem(placeholder);
    } else if (field instanceof JTextArea) {
      ((JTextArea) field).setText(placeholder);
      ((JTextArea) field).setForeground(Color.GRAY);
    }
  }

  /** Svuota tutti i campi delle pagine di login e registrazione */
  public void emptyFields() {
    if (loginPage != null) {
      loginPage.email_tf.setText("Email");
      loginPage.email_tf.setForeground(Color.GRAY);
      loginPage.password_pf.setText("Password");
      loginPage.password_pf.setForeground(Color.GRAY);
    }

    if (registerPage != null) {
      registerPage.nick_tf.setText("Nickname");
      registerPage.nick_tf.setForeground(Color.GRAY);
      registerPage.name_tf.setText("Nome");
      registerPage.name_tf.setForeground(Color.GRAY);
      registerPage.surname_tf.setText("Cognome");
      registerPage.surname_tf.setForeground(Color.GRAY);
    }

    if (registerPage2 != null) {
      registerPage2.town_tf.setText("Comune");
      registerPage2.town_tf.setForeground(Color.GRAY);
      registerPage2.district_tf.setText("Provincia");
      registerPage2.district_tf.setForeground(Color.GRAY);
    }

    if (registerPage3 != null) {
      registerPage3.email_tf.setText("Email");
      registerPage3.email_tf.setForeground(Color.GRAY);
      registerPage3.password1_pf.setText("Password");
      registerPage3.password1_pf.setForeground(Color.GRAY);
      registerPage3.password2_pf.setText("Password");
      registerPage3.password2_pf.setForeground(Color.GRAY);
    }
  }

  /**
   * Verifica il contenuto di un campo, se il contenuto è uguale al valore del
   * placeholder, allora non è stato inserito nulla e quindi il contenuto di quel
   * campo non è valido.
   * 
   * @param field       Campo da verificare
   * @param placeholder Valore di default del campo (placeholder)
   * @return Esito della verifica (boolean) */
  public boolean validateField(Object field, String placeholder) {
    if (field instanceof FTextField) {
      String value = ((FTextField) field).getText();
      if (value.equals(placeholder) || value.contains("|") || value.contains("\n")) {
        ((FTextField) field).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        return false;
      } else {
        ((FTextField) field).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return true;
      }

    } else if (field instanceof FPasswordField) {
      String value = String.valueOf(((FPasswordField) field).getPassword());
      if (value.equals(placeholder)) {
        ((FPasswordField) field).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        return false;
      } else {
        ((FPasswordField) field).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return true;
      }
    } else if (field instanceof FComboBox) {
      String value = ((FComboBox) field).getSelectedItem().toString();
      if (value.equals(placeholder)) {
        ((FComboBox) field).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        return false;
      } else {
        ((FComboBox) field).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return true;
      }
    } else if (field instanceof JTextArea) {
      if (((JTextArea) field).getText().equals(placeholder)) {
        ((JTextArea) field).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        return false;
      } else {
        ((JTextArea) field).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        return true;
      }
    }

    return false;
  }

  /**
   * Svuota la mainWindow e inserisce la nuova pagina
   * 
   * @param newPage Nuova pagina da inserire */
  public void changePage(FPage newPage) {
    mainWindow.getContentPane().removeAll();
    mainWindow.getContentPane().add(newPage);
    mainWindow.repaint();
    mainWindow.revalidate();
  }

  /** Registra il font Manrope, utilizzato in tutte le pagine */
  public void registerFonts() {
    try {
      final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

      InputStream is = getClass().getResourceAsStream("/assets/Manrope/static/Manrope-Bold.ttf");
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
      is = getClass().getResourceAsStream("/assets/Manrope/static/Manrope-Light.ttf");
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
      is = getClass().getResourceAsStream("/assets/Manrope/static/Manrope-Medium.ttf");
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
      is = getClass().getResourceAsStream("/assets/Manrope/static/Manrope-Regular.ttf");
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
      is = getClass().getResourceAsStream("/assets/Manrope/static/Manrope-SemiBold.ttf");
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
    }
    catch(Exception e) {
      System.out.println("Errore Clienti :638");
      e.printStackTrace();
    }
  }
}
