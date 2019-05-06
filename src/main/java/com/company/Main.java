package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

/**
 * program til at vedligeholdelse af administrative dele af databasen
 * @Author Alexander Kjeldsen
 **/

public class Main {

    Connection connection = new Connection();
    BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String brugernavn = "";
    String kodeord = "";


    public static void main(String[] args) throws RemoteException {
        new Main().run();
    }


    private void run() throws RemoteException {

        System.out.println("Velkommen til Administrationsmodulet til køreskolen");
        System.out.println("Skriv brugernavn og kode for at fortsætte ");

        while (true) {
            System.out.println("Brugernavn : ");
            String stringBruger = "";
            try {
                stringBruger = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Kode : ");
            String stringKode = "";
            try {
                stringKode = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection.connect();
            if (connection.ki.logIndAdmin(stringBruger, stringKode)) {
                this.brugernavn=stringBruger;
                this.kodeord=stringKode;
                loggetind();
                break;
            }


            System.out.println("forkert kode.");
        }
    }

    private void loggetind() {
        System.out.println("velkommen til Admin af køreskolepriser.");

        while (true){

            System.out.println("vælg følgende muligheder : ");
            System.out.println("0 for at lukke programmet");
            System.out.println("1 for at se alle køreskoler");
            System.out.println("2 for at se alle tilbud");
            System.out.println("3 efterfulgt at studienummer for at se tilbud fra en køreskole");
            System.out.println("'slet tilbud' efterfulgt af [tilbuds id] for at slette tilbud");
            System.out.println("'slet køreskole' efterfulgt af [køreskole id] for at slette køreskole og alle deres tilbud");

            String valg = "3 s165477";

            try {
                valg = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (valg.equals("0")||valg.equals("exit")){
                System.exit(0);
            } else if (valg.equals("1")){
                System.out.println(connection.alleKoreskoler());
            } else if (valg.equals("2")){
                System.out.println(connection.alleTilbud());
            } else if (valg.startsWith("3")){
                valg = valg.substring(2);
                valg = valg.trim();
                System.out.println(valg);
                System.out.println(connection.tilbudFraKøreskole(this.brugernavn, this.kodeord, valg));
            } else if (valg.startsWith("slet ")){
                try {
                    slet(valg);
                } catch (IOException e) {
                    System.out.println("fejl");
                }
            }

        }

    }

    private void slet(String valg) throws IOException {
        valg = valg.substring("slet".length());
        valg = valg.trim();
        if (valg.startsWith("tilbud")){
            valg = valg.substring("tilbud".length());
            System.out.println(valg +" slettes er du sikker ? J/N");
            if (bufferedReader.readLine().equals("j")){
                connection.sletTilbud(brugernavn, kodeord, valg);
            }
            System.out.println(connection.tilbudFraKøreskole(this.brugernavn, this.kodeord, valg));

        }
        if (valg.startsWith("køreskole")){
            valg = valg.substring("køreskole".length());
            valg = valg.trim();
            System.out.println(valg);
            System.out.println(valg +" slettes er du sikker ? J/N");
            if (bufferedReader.readLine().equals("j")) {
                connection.sletKøreskole(brugernavn, kodeord, valg);
            }
            System.out.println(connection.alleKoreskoler());

        }
    }

}
