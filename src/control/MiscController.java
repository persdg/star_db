package control;

import entity.Instrument;
import entity.Satellite;
import persistence.InstrumentRepository;
import persistence.SatelliteRepository;
import exception.AlreadyInDBException;

public class MiscController {

    public boolean addRepository(Satellite satellite) {
        SatelliteRepository SR = new SatelliteRepository();
        return SR.addSatellite(satellite);
    }

    public boolean addAgency(String agency, String satellite) throws AlreadyInDBException {
        SatelliteRepository SR = new SatelliteRepository();
        return SR.addAgency(agency, satellite);
    }

    public boolean addInstrument(Instrument instrument) throws AlreadyInDBException {
        InstrumentRepository IR = new InstrumentRepository();
        return IR.addInstrument(instrument);
    }

    public boolean addBand(Double band, String instrument) throws AlreadyInDBException {
        InstrumentRepository IR = new InstrumentRepository();
        return IR.addBand(band, instrument);
    }
}
