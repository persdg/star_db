package control;

import entity.FilamentInfo;
import persistence.FilamentRepository;

public class FilamentController {

    private int id;
    private String name;

    public FilamentInfo filamentId(int id){  //parametro: id filamento, return: oggetto filamento con centroide (lat,long) estensione (height,width) e num segmenti
        FilamentRepository FR = new FilamentRepository();
        return FR.searchFilaments(id);
    }

    public FilamentInfo filamentName(String name){ //parametro: name filamento, return: oggetto filamento con centroide (lat,long) estensione (height,width) e num segmenti
        FilamentRepository FR = new FilamentRepository();
        return FR.searchFilaments(name);
    }

}
