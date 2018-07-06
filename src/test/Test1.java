package test;

import control.LogInController;
import entity.Instrument;
import entity.Satellite;
import exception.AlreadyInDBException;
import persistence.FileImporter;
import persistence.InstrumentRepository;
import persistence.FilamentRepository;
import persistence.SatelliteRepository;

import java.util.ArrayList;
import java.util.List;

public class Test1{

    public static void main(String[] args) {

        initSatellites();
        initInstruments();

        String path = "C:\\Users\\Pers\\Desktop\\filamenti_Herschel.csv";
        FileImporter FI = new FileImporter();
        FI.fileImport(path);

        System.out.println("Ok!");
    }

    private static void initSatellites() {
        List<Satellite> S_List = new ArrayList<>();

        S_List.add(new Satellite("Herschel","2009-07-10","2013-06-17"));
        S_List.add(new Satellite("Spitzer","2003-12-18","2009-05-15"));

        S_List.get(0).addAgency("ESA");
        S_List.get(0).addAgency("MEME");
        S_List.get(1).addAgency("NASA");

        SatelliteRepository SR = new SatelliteRepository();
        for(Satellite satellite : S_List) {
            SR.addSatellite(satellite);
            for (String agency : satellite.getAgencies()) {
                try {
                    SR.addAgency(agency, satellite.getName());
                } catch (AlreadyInDBException e) {
                    System.out.println(agency + " is already an agency of " + satellite.getName() + ".");
                }
            }
        }
    }

    private static void initInstruments() {
        List<Instrument> I_List = new ArrayList<>();

        I_List.add(new Instrument("PACS","Herschel"));
            I_List.get(0).addBand(70.0);
            I_List.get(0).addBand(160.0);
        I_List.add(new Instrument("SPIRE","Herschel"));
            I_List.get(1).addBand(250.0);
            I_List.get(1).addBand(350.0);
            I_List.get(1).addBand(500.0);
        I_List.add(new Instrument("IRAC","Spitzer"));
            I_List.get(2).addBand(3.6);
            I_List.get(2).addBand(4.5);
            I_List.get(2).addBand(5.8);
            I_List.get(2).addBand(8.0);
        I_List.add(new Instrument("MIPS","Spitzer"));
            I_List.get(3).addBand(24.0);

        InstrumentRepository IR = new InstrumentRepository();
        for (Instrument instrument : I_List) {
            try {
                IR.addInstrument(instrument);
            } catch (AlreadyInDBException e) {
                System.out.println(instrument.getName() + "." + instrument.getSatellite() + " is already memorized.");
            }
            for (Double band : instrument.getBands()) {
                try {
                    IR.addBand(band, instrument.getName());
                } catch(AlreadyInDBException e) {
                    System.out.println(instrument.getName() + " has already " + band + " memorized.");
                }
            }
        }
    }
}
