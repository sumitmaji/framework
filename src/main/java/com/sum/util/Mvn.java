package com.sum.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Mvn {
    private static transient Logger log = Logger.getLogger(Mvn.class);

    public static void run(String command) throws Exception {
        // Create a list for storing output.
        ArrayList<String> list = new ArrayList<String>();
        // Execute a command and get its process handle
        Process proc = Runtime.getRuntime().exec(command);
        // Get the handle for the processes InputStream
        InputStream istr = proc.getInputStream();
        // Create a BufferedReader and specify it reads
        // from an input stream.
        BufferedReader br = new BufferedReader(new InputStreamReader(istr));
        String str; // Temporary String variable
        // Read to Temp Variable, Check for null then
        // add to (ArrayList)list
        while ((str = br.readLine()) != null)
            list.add(str+"\n");
        // Wait for process to terminate and catch any Exceptions.
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            log.error("Process was interrupted");
        }
        br.close(); // Done.
        log.info("Command Output: " + list.toString());
    }


    public static void hbm2Ddl() {
        try {
            run("mvn hibernate3:hbm2java -DoutputDir=src/ -DrevengFile=model.reveng.xml -DhibernateFile=hibernate.cfg.xml -f /projects/framework/poc");
        } catch (Exception e) {
            log.error(e, e);
        }

    }


}
