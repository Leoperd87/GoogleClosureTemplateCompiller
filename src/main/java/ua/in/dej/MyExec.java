package ua.in.dej;

import java.io.*;

/**
 * Created by fima on 01.08.14.
 */
public class MyExec {

    private static void printLines(String name, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
        }
    }

    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines("[INFO] ", pro.getInputStream());
        printLines("[ERROR] ", pro.getErrorStream());
        pro.waitFor();
        System.out.println(" exitValue() " + pro.exitValue());
        if (pro.exitValue() > 0) {
            System.exit(pro.exitValue());
        }
    }

    public static void main(String cmd) {
        try {
            runProcess(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
