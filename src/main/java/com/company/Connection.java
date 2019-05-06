package com.company;

import server.KøreskolePriserInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * klasse til at forbinde med databasen ved rmi kald. indeholder også display som styrer View
 * @Author Alexander Kjeldsen
 **/


public class Connection {

    KøreskolePriserInterface ki;

    Display display = new Display();

    String brugernavn = "s165477";
    String kodeord = "kodekode";


    public void connect(){
        try {
            //ki = (KøreskolePriserInterface) Naming.lookup("rmi://dist.saluton.dk:5478/koereskolepriser");
            ki = (KøreskolePriserInterface) Naming.lookup("rmi://localhost:5500/koereskolepriser");

        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    public String alleKoreskoler(){
        String str = "";
        try {
            str=ki.getAlleKøreskoler(brugernavn, kodeord);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return display.koreskoler(str);

    }

    public String alleTilbud(){
        String str = "";
        try {
            str=ki.getAlleTilbud();
        } catch (Exception e ){
            e.printStackTrace();
        }
        return display.AlleTilbud(str);
    }

    public String tilbudFraKøreskole(String adminBrugernavn, String adminKodeord, String køreskoleid) {
        String str ="";
        try {
            str = ki.getTilbudFraKøreskoler(adminBrugernavn, adminKodeord, køreskoleid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return display.tilbudFraKøreskole(str);
    }

    public void sletTilbud(String adminBrugernavn, String adminKodeord, String tilbudid) {
        String str = "";
        boolean b = false;

        try {
            int tilbud = Integer.parseInt(tilbudid.trim());
            b = ki.sletKøreskoleTilbud(adminBrugernavn, adminKodeord, tilbud);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("tilbud "+ tilbudid + " slettet "+ b);




    }

    public void sletKøreskole(String adminBrugernavn, String adminKodeord, String valg) {
        String str = "";
        boolean b = false;

        try {
            b = ki.sletKøreskole(adminBrugernavn, adminKodeord, valg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("køreskole "+ valg + " slettet "+ b);

    }

//    public String








}
