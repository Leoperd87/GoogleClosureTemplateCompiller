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

import java.io.File;

/**
 * Goal which touches a timestamp file.
 *
 * @goal make-translate
 * 
 * @phase prepare-package
 */
public class MakeTranslate
    extends AbstractMojo
{
    /**
     * Work location
     * @parameter expression="${project.build.directory}/${project.build.finalName}"
     * @required
     */
    private File workDir;

    /**
     * Should generate jsdoc
     * @parameter default-value=true
     */
    private boolean shouldGenerateJsdoc;

    /**
     * Should provide require soy namespaces
     * @parameter default-value=true
     */
    private boolean shouldProvideRequireSoyNamespaces;

    /**
     * Code style
     * @parameter default-value="concat"
     */
    private String codeStyle;

    /**
     * Css handling scheme
     * @parameter default-value="goog"
     */
    private String cssHandlingScheme;

    /**
     * Locales
     * @parameter default-value="ru"
     */
    private String locales;

    /**
     * Message file path format
     * @parameter default-value="./{LOCALE}.xlf"
     */
    private String messageFilePathFormat;

    /**
     * Output path format
     * @parameter default-value="{INPUT_DIRECTORY}/{INPUT_FILE_NAME_NO_EXT}_{LOCALE}.js"
     */
    private String outputPathFormat;

    public void execute() throws MojoExecutionException
    {
        CT testMx = new CT(workDir.getPath());
        testMx.setCodeStyle(codeStyle);
        testMx.setCssHandlingScheme(cssHandlingScheme);
        testMx.setLocales(locales);
        testMx.setMessageFilePathFormat(messageFilePathFormat);
        testMx.setShouldGenerateJsdoc(shouldGenerateJsdoc);
        testMx.setShouldProvideRequireSoyNamespaces(shouldProvideRequireSoyNamespaces);
        testMx.setOutputPathFormat(outputPathFormat);
        testMx.exec();
    }
}
