package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * klasse til at holde nøgler og printe json til skærmen
 * @Author Alexander Kjeldsen
 **/


public class Display {
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    private TableGenerator tableGenerator = new TableGenerator();

    private List<String> køreskolenøgler;
    private List<String> tilbudTilBrugereNøgler;
    private List<String> tilbudNøgler;
    private List<String> tilgangeligeDageNøgler;
    private List<String> tilbudTilBrugereTilbudObjektNøgler;
    //List<String> tilbudTilBrugereKøreskoleObjektNøgler;


    //-----------køreskole nøgler-----------//
    private final String køreskoleid = "id";
    private final String køreskolenavn = "navn";
    private final String køreskoleadresse = "adresse";
    private final String køreskolepostnummer = "postnummer";
    private final String køreskoletelefonnummer = "telefonnummer";
    private final String køreskolemail = "mail";

    //-----------tilbud til brugere nøgler-----------//
    private final String tilbudTilBrugereTilbud = "tilbud";
    private final String tilbudTilBrugereKøreskole = "koreskole";

    //-----------tilbud nøgler-----------//
    private final String tilbudkøreskoleid = "koreskole_id";
    private final String tilbudkøreskolepris = "pris";
    private final String tilbudkørekorttype = "korekort_type";
    private final String tilbudlynkursus = "lynkursus";
    private final String tilbudbilmarke = "bilmarke";
    private final String tilbudbilstorrelse = "bilstorrelse";
    private final String tilbudkon = "kon";
    private final String tilbudbeskrivelse = "beskrivelse";
    private final String tilbudtilgangeligeDage = "tilgangeligeDage";
    private final String tilbudid = "id";

    //---------------tilgængelige dage nøgler--------//
    private final String tilgængeligeDageMandag = "tilgangelig_mandag";
    private final String tilgængeligeDageTirsdag = "tilgangelig_tirsdag";
    private final String tilgængeligeDageOnsdag = "tilgangelig_onsdag";
    private final String tilgængeligeDageTorsdag = "tilgangelig_torsdag";
    private final String tilgængeligeDageFredag = "tilgangelig_fredag";
    private final String tilgængeligeDageLørdag = "tilgangelig_lordag";
    private final String tilgængeligeDageSøndag = "tilgangelig_sondag";


    public Display() {
        køreskolenøgler = new ArrayList<>();
        køreskolenøgler.add(køreskoleid);
        køreskolenøgler.add(køreskolenavn);
        køreskolenøgler.add(køreskoleadresse);
        køreskolenøgler.add(køreskolepostnummer);
        køreskolenøgler.add(køreskoletelefonnummer);
        køreskolenøgler.add(køreskolemail);

        tilbudNøgler = new ArrayList<>();
        tilbudNøgler.add(tilbudkøreskoleid);
        tilbudNøgler.add(tilbudid);
        tilbudNøgler.add(tilbudkøreskolepris);
        tilbudNøgler.add(tilbudkørekorttype);
        tilbudNøgler.add(tilbudlynkursus);
        tilbudNøgler.add(tilbudbilmarke);
//        tilbudNøgler.add(tilbudbilstorrelse);
        tilbudNøgler.add(tilbudkon);
        tilbudNøgler.add(tilbudbeskrivelse);
//        tilbudNøgler.add(tilbudtilgangeligeDage);

        tilgangeligeDageNøgler = new ArrayList<>();
        tilgangeligeDageNøgler.add(tilgængeligeDageMandag);
        tilgangeligeDageNøgler.add(tilgængeligeDageTirsdag);
        tilgangeligeDageNøgler.add(tilgængeligeDageOnsdag);
        tilgangeligeDageNøgler.add(tilgængeligeDageTorsdag);
        tilgangeligeDageNøgler.add(tilgængeligeDageFredag);
        tilgangeligeDageNøgler.add(tilgængeligeDageLørdag);
        tilgangeligeDageNøgler.add(tilgængeligeDageSøndag);

        tilbudTilBrugereTilbudObjektNøgler = new ArrayList<>();
        tilbudTilBrugereTilbudObjektNøgler.add(køreskolenavn);
        tilbudTilBrugereTilbudObjektNøgler.add(køreskolemail);
        tilbudTilBrugereTilbudObjektNøgler.add(tilbudkøreskolepris);
        tilbudTilBrugereTilbudObjektNøgler.add(tilbudkørekorttype);

        tilbudTilBrugereTilbudObjektNøgler.add(tilbudbilmarke);

        tilbudTilBrugereTilbudObjektNøgler.add(køreskolepostnummer);
        tilbudTilBrugereTilbudObjektNøgler.add(køreskoletelefonnummer);
        tilbudTilBrugereTilbudObjektNøgler.add(tilbudbeskrivelse);


    }


    public String koreskoler(String str) {

        List<List<String>> values = new ArrayList<>();

        JsonElement jsonElement = jsonParser.parse(str);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            values.add(new ArrayList<String>());
            for (int j = 0; j < køreskolenøgler.size(); j++) {
                values.get(i).add(jsonArray.get(i).getAsJsonObject().get(køreskolenøgler.get(j)).getAsString());
            }
        }
        return tableGenerator.generateTable(køreskolenøgler, values);
    }

    public String AlleTilbud(String str) {

        List<List<String>> values = new ArrayList<>();

        JsonElement jsonElement = jsonParser.parse(str);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            values.add(new ArrayList<String>());

            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereKøreskole).getAsJsonObject().get(køreskolenavn).getAsString());
            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereKøreskole).getAsJsonObject().get(køreskolemail).getAsString());
            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get(tilbudkøreskolepris).getAsString());
            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get(tilbudkørekorttype).getAsString());
            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get(tilbudbilmarke).getAsString());
            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereKøreskole).getAsJsonObject().get(køreskolepostnummer).getAsString());
            values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereKøreskole).getAsJsonObject().get(køreskoletelefonnummer).getAsString());
            if (!(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get("beskrivelse") ==null)){
                if (jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get("beskrivelse").getAsString().length()>25){
                    values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get("beskrivelse").getAsString().substring(0, 25));
                } else {
                    values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudTilBrugereTilbud).getAsJsonObject().get("beskrivelse").getAsString());
                }
            }

        }
        return tableGenerator.generateTable(tilbudTilBrugereTilbudObjektNøgler, values);
    }

    public String tilbudFraKøreskole(String str) {

        List<List<String>> values = new ArrayList<>();
        JsonElement jsonElement = jsonParser.parse(str);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            values.add(new ArrayList<String>());
            for (int j = 0; j < tilbudNøgler.size(); j++) {
                if (!(jsonArray.get(i).getAsJsonObject().get(tilbudNøgler.get(j)) ==null))
                    if (jsonArray.get(i).getAsJsonObject().get(tilbudNøgler.get(j)).getAsString().length()>30){
                        values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudNøgler.get(j)).getAsString().substring(0, 30));
                    } else {
                        values.get(i).add(jsonArray.get(i).getAsJsonObject().get(tilbudNøgler.get(j)).getAsString());
                    }
            }
        }
        return tableGenerator.generateTable(tilbudNøgler, values);

    }


}