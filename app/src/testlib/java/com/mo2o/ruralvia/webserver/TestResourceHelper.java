package com.mo2o.ruralvia.webserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestResourceHelper {

    private static final String BASE_PATH = "./src/testlib/resources/raw/";

    private TestResourceHelper() {
        //no instances
    }

    /**
     * Reads file content and returns string.
     * @throws IOException
     */
    public static String readFile(final String path) throws IOException {
        final StringBuilder sb = new StringBuilder();
        String strLine;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(BASE_PATH + path), "UTF-8"));
            while ((strLine = reader.readLine()) != null) {
                sb.append(strLine);
            }

        } catch (final IOException ignore) {
            //ignore
        } finally {
            if (reader != null)
                reader.close();
        }
        return sb.toString();
    }

}
