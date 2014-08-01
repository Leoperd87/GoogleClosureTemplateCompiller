import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import ua.in.dej.CT;
import ua.in.dej.MX;
import ua.in.dej.MyExec;

import java.io.IOException;

/**
 * Created by fima on 01.08.14.
 */
public class Tests {

    @Test
    public void execTest() {

        MyExec test1 = new MyExec();
        test1.main("java -version");
    }

    @Test
    public void mxTest() {

        String testPath = getClass().getResource("").getPath();

        MX testMx = new MX();
        String resourcesPath = this.getClass().getResource("").getPath();
        testMx.setInputPath(resourcesPath);
        testMx.setOutputPath(resourcesPath);
        testMx.setSrcLocale("en");
        testMx.setTargetLocale("ru-out");
        testMx.exec();

        try {
            Assert.assertEquals(FileUtils.fileRead(testPath + "exp.xlf"), FileUtils.fileRead(testPath + "ru-out.xlf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ctTest() {

        String testPath = getClass().getResource("").getPath();

        CT testCt = new CT(testPath);
        testCt.setMessageFilePathFormat(testPath + "{LOCALE}.xlf");
        testCt.exec();

        try {
            Assert.assertEquals(FileUtils.fileRead(testPath + "test_exp.js"), FileUtils.fileRead(testPath + "test_ru.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(FileUtils.fileRead(testPath + "test2_exp.js"), FileUtils.fileRead(testPath + "test2_ru.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(FileUtils.fileRead(testPath + "recursive/test3_exp.js"), FileUtils.fileRead(testPath + "recursive/test3_ru.js"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
