package ua.com.bpgdev.onlineshop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetLoader {
    public InputStream loadFile(String assetFileName)
    {
        final File assetFile = new File(assetFileName);
        try  {

            final InputStream inputStream = assetFile.isFile() ?
                    new FileInputStream(assetFile) :
                    this.getClass().getResourceAsStream(assetFileName);

            if (inputStream != null) {
                return inputStream;
            }
            else {
                throw new IllegalArgumentException("Cannot find asset file: " + assetFileName);
            }
        }
        catch (IOException io) {
            throw new RuntimeException("Failed to read property file", io);
        }
    }
}
