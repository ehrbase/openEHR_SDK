# generator-maven-plugin

Maven Plugin to generate code from otp files

```xml

  <build>
  ...
    <plugins>
      <plugin>
        <groupId>org.ehrbase.openehr.sdk</groupId>
        <artifactId>generator-maven-plugin</artifactId>
        <version>${project.version}</version>
        <configuration>
          <!-- default is 'true' -->
          <addNullFlavor>true</addNullFlavor>
          <!-- default is 'false' -->
          <choicesForSingleEvent>false</choicesForSingleEvent>
          <!-- ALL, NONE, SECTION, default is 'SECTION' -->
          <optimizerSetting>SECTION</optimizerSetting>
          <!-- optional, takes generators defaults if not set -->
          <replaceChars>
            <ö>oe</ö>
            <ä>ae</ä>
          </replaceChars>
          <!-- mandatory to have at least one template file configured -->
          <templateFiles>
            <templateFile>src/main/resources/some_definitions.vita.v0.opt</templateFile>
            <templateFile>src/main/resources/some_other_definitions.vita.v0.opt</templateFile>
          </templateFiles>
          <!-- mandatory -->
          <packageName>ag.vitagroup.example</packageName>
        </configuration>
      </plugin>
    </plugins>
  </build>

```