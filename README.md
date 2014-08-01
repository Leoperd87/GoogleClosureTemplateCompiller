ClosureTemplateCompiller
===================

Generate vocabulary and translate soy files.

### Public src

git clone http://git.dejin.pp.ua/fima/googleclosuretemplatecompiller.git

### Usage
Generate vocabulary example:

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    ...
    <build>
        ...
        <plugins>
            <plugin>
                <groupId>ua.in.dej</groupId>
                <artifactId>ClosureTemplateCompiller</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>generate-vocabulary</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <workDir>
                        ${project.build.directory}/${project.build.finalName}/
                    </workDir>
                    <outputPath>
                        ${project.build.directory}/${project.build.finalName}/
                    </outputPath>
                    <targetLocale>
                        ru
                    </targetLocale>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Usage example:

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    ...
    <build>
        ...
        <plugins>
            <plugin>
                <groupId>ua.in.dej</groupId>
                <artifactId>ClosureTemplateCompiller</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>make-translate</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <workDir>
                        ${project.build.directory}/${project.build.finalName}/
                    </workDir>
                    <locales>
                        ru
                    </locales>
                    <messageFilePathFormat>
                        ${project.build.directory}/${project.build.finalName}/{LOCALE}.xlf
                    </messageFilePathFormat>
                    <outputPathFormat>
                        {INPUT_DIRECTORY}/{INPUT_FILE_NAME_NO_EXT}.js
                    </outputPathFormat>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
