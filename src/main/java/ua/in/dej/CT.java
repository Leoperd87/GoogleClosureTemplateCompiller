package ua.in.dej;

import org.codehaus.plexus.util.DirectoryScanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by fima on 01.08.14.
 */
public class CT {
    private String buildPath;
    private boolean shouldGenerateJsdoc = true;
    private boolean shouldProvideRequireSoyNamespaces = true;
    private String codeStyle = "concat";
    private String cssHandlingScheme = "goog";
    private String locales = "ru";
    private String messageFilePathFormat = "./{LOCALE}.xlf";
    private String outputPathFormat = "{INPUT_DIRECTORY}/{INPUT_FILE_NAME_NO_EXT}_{LOCALE}.js";

    public CT(String buildPath) {
        this.buildPath = buildPath;
    }

    public void setShouldGenerateJsdoc(boolean shouldGenerateJsdoc) {
        this.shouldGenerateJsdoc = shouldGenerateJsdoc;
    }

    public void setShouldProvideRequireSoyNamespaces(boolean shouldProvideRequireSoyNamespaces) {
        this.shouldProvideRequireSoyNamespaces = shouldProvideRequireSoyNamespaces;
    }

    public void setCssHandlingScheme(String cssHandlingScheme) {
        this.cssHandlingScheme = cssHandlingScheme;
    }

    public void setCodeStyle(String codeStyle) {
        this.codeStyle = codeStyle;
    }

    public void setOutputPathFormat(String outputPathFormat) {
        this.outputPathFormat = outputPathFormat;
    }

    public void setLocales(String locales) {
        this.locales = locales;
    }

    public void setMessageFilePathFormat(String messageFilePathFormat) {
        this.messageFilePathFormat = messageFilePathFormat;
    }

    public void exec() {


        InputStream is = this.getClass().getResourceAsStream("/ct/SoyToJsSrcCompiler.jar");
        try {
            OutputStream os = new FileOutputStream(buildPath + File.separator + "SoyToJsSrcCompiler.jar");
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
        scanner.setBasedir(buildPath);
        scanner.setCaseSensitive(false);
        scanner.scan();
        String[] files = scanner.getIncludedFiles();

        String filesAsLongString = "";

        for (String f: files) {
            filesAsLongString = filesAsLongString + " " + buildPath+File.separator +f;
        }

        MyExec runner = new MyExec();
//        String ctPath = this.getClass().getResource("/ct/SoyToJsSrcCompiler.jar").getPath();
        runner.main("java -jar " + buildPath + File.separator + "SoyToJsSrcCompiler.jar" +
                " --codeStyle  " + codeStyle +
                " --cssHandlingScheme  " + cssHandlingScheme +
                " --outputPathFormat  " + outputPathFormat +
                " --locales  " + locales +
                " --messageFilePathFormat  " + messageFilePathFormat +
                (shouldGenerateJsdoc ? " --shouldGenerateJsdoc  " : "") +
                (shouldProvideRequireSoyNamespaces ? " --shouldProvideRequireSoyNamespaces   " : "") +
                " " + filesAsLongString);
    }
}
