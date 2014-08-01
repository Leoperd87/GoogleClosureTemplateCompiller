package ua.in.dej;

import org.codehaus.plexus.util.DirectoryScanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by fima on 01.08.14.
 */
public class MX {
    private String inputPath = "/**/*";
    private String outputPath;
    private String srcLocale = "en";
    private String targetLocale = "ru";

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setSrcLocale(String srcLocale) {
        this.srcLocale = srcLocale;
    }

    public void setTargetLocale(String targetLocale) {
        this.targetLocale = targetLocale;
    }

    public void exec() {


        InputStream is = this.getClass().getResourceAsStream("/mx/SoyMsgExtractor.jar");
        try {
            OutputStream os = new FileOutputStream(inputPath + File.separator + "SoyMsgExtractor.jar");
            byte[] buffer = new byte[4096];
            for (int n; (n = is.read(buffer)) != -1; )
                os.write(buffer, 0, n);
            os.close();

            is.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setIncludes(new String[]{"**" + File.separator + "*.soy"});
        scanner.setBasedir(inputPath);
        scanner.setCaseSensitive(false);
        scanner.scan();
        String[] files = scanner.getIncludedFiles();

        String filesAsLongString = "";

        for (String f: files) {
            filesAsLongString = filesAsLongString + " " + inputPath + File.separator + f;
        }

        MyExec runner = new MyExec();
//        String mxPath = this.getClass().getResource("/mx/SoyMsgExtractor.jar").getPath();
        runner.main("java -jar " + inputPath + File.separator + "SoyMsgExtractor.jar" +
                " --outputFile " + outputPath + File.separator +  targetLocale + ".xlf" +
                " --targetLocaleString " + targetLocale +
                " --sourceLocaleString " + srcLocale +
                " " + filesAsLongString);

        File file = new File(inputPath + File.separator + "SoyMsgExtractor.jar");
        file.delete();
    }
}
