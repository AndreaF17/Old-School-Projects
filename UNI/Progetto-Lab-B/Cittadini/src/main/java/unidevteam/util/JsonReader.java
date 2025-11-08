/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.util;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe per leggere e lavorare da file JSON
 * Usato per il file italia.json e per italia_comuni.json
 * Questi file ci permettono di ottenere informazioni sui comuni e province italiane 
 */
public class JsonReader {
    JSONParser parser;

    public JsonReader() {
        parser = new JSONParser();
    }

    /**
     * @return Una lista di tutte le province italiane già ordinate
     */
    public List<String> getProvince() {
        List<String> province = new ArrayList<String>();

        try
            {
                Object object;
                try{
                    
                    object = parser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("italia.json"))));
                } catch (Exception e) {
                    object = parser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("italia.json"))));
                }
                //convert Object to JSONObject
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    //Printing all the values
                    JSONObject regioneObject = (JSONObject)regione;
                    JSONArray provinceJSONArr = (JSONArray) regioneObject.get("province");
                    JSONArray capoluoghiJSONArr = (JSONArray) regioneObject.get("capoluoghi");
                    
                    for(int i = 0; i < provinceJSONArr.size(); i++) {
                        String record = String.format("%s (%s)", capoluoghiJSONArr.get(i), provinceJSONArr.get(i));
                        province.add(record);
                    }
                }
                
                java.util.Collections.sort(province);
                
            } catch(Exception e) {
                e.printStackTrace();
            }

            return province;
    }

    /**
     * Ottiene comuni tramite la sigla della provincia
     * @param siglaProvincia sigla della provincia 
     * @return una lista di comuni sotto forma di stringa
     */
    public List<String> getComuniByProvincia(String siglaProvincia) {
        List<String> comuni = new ArrayList<String>();

        try
            {
                Object object;
                try{
                    object = parser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("italia_comuni.json"))));
                } catch (FileNotFoundException e) {
                    object = parser.parse(new FileReader(new File(getClass().getClassLoader().getResource("italia_comuni.json").toString())));
                }
                //convert Object to JSONObject
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    //Printing all the values
                    JSONObject regioneObject = (JSONObject)regione;
                    JSONArray provinceJSONArr = (JSONArray) regioneObject.get("province");

                    for(Object provincia : provinceJSONArr) {
                        JSONObject provinciaJSONobj = (JSONObject)provincia;
                        if(provinciaJSONobj.get("code").toString().equals(siglaProvincia)) {
                            JSONArray comuniJSONArr = (JSONArray)provinciaJSONobj.get("comuni");

                            for(Object comune : comuniJSONArr) {
                                JSONObject comuneJSONobj = (JSONObject)comune;
                                comuni.add(comuneJSONobj.get("nome").toString());
                            }

                            java.util.Collections.sort(comuni);
                            return comuni;
                        }
                    }
                }
                
            } catch(Exception e) {
                e.printStackTrace();
            }

        return null;
    }

    /**
     * @param nomeComune nome del comune
     * @return il CAP sotto forma di stringa
     */
    public String getCAPfromComune(String nomeComune) {
        try {
            Object object;
            try{
                object = parser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("italia_comuni.json"))));
            } catch (FileNotFoundException e) {
                object = parser.parse(new FileReader(new File(getClass().getClassLoader().getResource("italia_comuni.json").toString())));
            }
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;
            
            JSONArray regioni = (JSONArray) jsonObject.get("regioni");

            for (Object regione : regioni) {
                //Printing all the values
                JSONObject regioneObject = (JSONObject)regione;
                JSONArray provinceJSONArr = (JSONArray) regioneObject.get("province");

                for(Object provincia : provinceJSONArr) {
                    JSONObject provinciaJSONobj = (JSONObject)provincia;
                    JSONArray comuniJSONArr = (JSONArray)provinciaJSONobj.get("comuni");

                    for(Object comune : comuniJSONArr) {
                        JSONObject comuneJSONobj = (JSONObject)comune;
                        if(comuneJSONobj.get("nome").toString().equals(nomeComune)) {
                            return comuneJSONobj.get("cap").toString();
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return una lista di comuni ordinati
     */
    public List<String> getAllComuni() {
        List<String> comuni = new ArrayList<String>();

        try {
                Object object;
                try{
                    object = parser.parse(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("italia_comuni.json"))));
                } catch (FileNotFoundException e) {
                    object = parser.parse(new FileReader(new File(getClass().getClassLoader().getResource("italia_comuni.json").toString())));
                }
                //convert Object to JSONObject
                JSONObject jsonObject = (JSONObject)object;
                
                JSONArray regioni = (JSONArray) jsonObject.get("regioni");

                for (Object regione : regioni) {
                    //Printing all the values
                    JSONObject regioneObject = (JSONObject)regione;
                    JSONArray provinceJSONArr = (JSONArray)regioneObject.get("province");

                    for(Object provincia : provinceJSONArr) {
                        JSONObject provinciaJSONobj = (JSONObject)provincia;
                        JSONArray comuniJSONArr = (JSONArray)provinciaJSONobj.get("comuni");

                        for(Object comune : comuniJSONArr) {
                            JSONObject comuneJSONobj = (JSONObject)comune;
                            comuni.add(comuneJSONobj.get("nome").toString());
                        }
                    }
                }

                java.util.Collections.sort(comuni);
                
            } catch(Exception e) {
                e.printStackTrace();
            }

        return comuni;
    }
}