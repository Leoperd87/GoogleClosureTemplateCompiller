package ua.in.dej;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import sun.misc.IOUtils;

import java.io.*;

/**
 * Goal which touches a timestamp file.
 *
 * @goal generate-vocabulary
 * @phase prepare-package
 */
public class GenerateVocabulary
        extends AbstractMojo {
    /**
     * Work location
     *
     * @parameter expression="${project.build.directory}/${project.build.finalName}"
     * @required
     */
    private File workDir;

    /**
     * Output path
     *
     * @parameter expression="${project.build.directory}/${project.build.finalName}"
     * @required
     */
    private File outputPath;

    /**
     * Src locale
     *
     * @parameter default-value="en"
     */
    private String srcLocale;

    /**
     * Target locale
     *
     * @parameter default-value="ru"
     */
    private String targetLocale;

    public void execute() throws MojoExecutionException {
        MX testMx = new MX();
        testMx.setInputPath(workDir.getPath());
        testMx.setOutputPath(outputPath.getPath());
        testMx.setSrcLocale(srcLocale);
        testMx.setTargetLocale(targetLocale);
        testMx.exec();
    }
}
