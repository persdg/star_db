package control;

import persistence.FileImporter;

import java.io.FileNotFoundException;

public class FileImporterController {

    public void ImportFile(String file){

        FileImporter FI = new FileImporter();
        FI.fileImport(file);

    }
}
