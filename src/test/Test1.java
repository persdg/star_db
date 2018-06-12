package test;

import control.LogInController;
import persistence.FileImporter;
import persistence.REQs;

public class Test1{

    public static void main(String[] args) {
        String path = "C:\\Users\\Pers\\Desktop\\contorni_filamenti_Herschel.csv";
        FileImporter FI = new FileImporter();
        FI.fileImport(path);

        /*int id = 45;
        String name = "HiGALFil013.9201-1.2385";

        REQs req1 = new REQs();
        req1.searchFilaments(id);
        */

        /*LogInController LGC = new LogInController();
        if(LGC.LogIn("Peppo","culonero")) {
            System.out.println("Utente presente");
        } else {
            System.out.println("Utente non presente");
        }*/

        System.out.println("Ok!");
    }
}
