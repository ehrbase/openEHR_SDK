# openEHR SDK ![Maven Central](https://img.shields.io/maven-central/v/org.ehrbase.openehr.sdk/sdk-parent) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ehrbase_openEHR_SDK&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ehrbase_openEHR_SDK) [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)


## Features of the SDK

### client (mostly Beta)

Generic openEHR Client and Object-mapper:
* Define entity classes for openEHR-Templates (v1.4) in a jpa like way
* Map entity <-> Archie RM objekts.
* RestClient for openEHR Rest-API
* AQL-Query generator (Alpha)

### generator (Beta)

Autogenerate entity classes from template

### opt-1.4 (Beta)

Opt 1.4 xmlbeans

### response-dto (Beta)

DTO's representing the response for the ehrscape and openEHR Rest API

### terminology (Beta)

Mini openEHR terminology implementation

### validation (Beta)

Validation of Compositions against templates

### test-data (Beta)

Example templates and Composition for tests

### serialisation (Beta)

Map Compositions from and to JSON;XML

### aql (Beta)
* Map an Aql-String from and to an Aql-Dto-Model.
* Not supported right now:
    - XOR
    - functions
    - matches
    - like
    - compare path to path expressions 
    
## Release Notes

Please check the [CHANGELOG](CHANGELOG.md)

## Installation

### Build

Use one of the options below to build the project.

#### Option 1) -  Build w/o integration tests

This option skipps integration tests. Code coverage report is based on unit tests only.

```
mvn clean install
```
or any specific maven phase
```
mvn clean test
mvn clean verify
mvn clean package
...
```

#### Option 2) - Build with unit & integration tests
This option includes unit as well as integration tests. Three coverage reports are generated: unit test report, integration test report and overall coverage report. 

```
mvn clean install -DskipIntegrationTests=false
```

or any specific maven phase

```
mvn clean verify -DskipIntegrationTests=false
mvn clean package -DskipIntegrationTests=false
...
```

  :warning: EHRbase server + DB must be running to execute integration tests successfully

#### Option 3) - Execute tests via profiles

```
mvn clean -Pfast test    # will execute unit tests only
mvn clean -Pslow test    # will execute integration tests only
mvn clean -Pfull test    # will execute all test
```
NOTE: This option may not properly generate coverage reports because Jacoco is not configured in the profiles. Feel free to provide a PR to enhance this :wink:

## Usage

### Entity generation

To generate an entity class from a template use

```bash
 java  -jar generator-version.jar
 -h               show help
 -opt <arg>       path to opt file
 -out <arg>       path to output directory
 -package <arg>   package name
 -config <arg>    optional Path to config file
```

In the optional config file you can define

| Parameter                     | Default                          | Description                                                                                                                                                                                                                                                                                                                                                 |
|-------------------------------|----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| optimizerSetting              | SECTION                          | Defines if nodes which belong to are archetype but are single valued generate a new class: <ul><li>NONE: Always generate a class for nodes which belong to a archetype</li><li>SECTION: Do not generate a class for nodes which have rm-type section and are single valued</li><li>ALL: Do not generate a class for nodes which are single valued</li></ul> |
| addNullFlavor                 | true                             | Whether or not to generate null flavor fields for Elements.                                                                                                                                                                                                                                                                                                 |
| generateChoicesForSingleEvent | false                            | Whether or not to generate Choices (POINT_EVENT & INTERVAL_EVENT) fields for a single EVENT. If "false" only POINT_EVENT will be generated.                                                                                                                                                                                                                 |
| replaceChars                  | German and Norwegian Characters  | Map to define Characters in the Node name to be replaced.                                                                                                                                                                                                                                                                                                   |

see [generator/src/main/resources/DefaultConfig.yaml](generator/src/main/resources/DefaultConfig.yaml)

### Use The SDK in your project

You can include release versions of the SDK via [maven central](https://search.maven.org/search?q=g:org.ehrbase.openehr.sdk)

If you need specific development-versions, you can use [jitpack.io](https://jitpack.io/#ehrbase/openEHR_SDK)

#### Map entity <-> Archie RM objects

see FlattenerTest and UnflattenerTest

#### RestClient for openEHR Rest-API

- ehr : see DefaultRestEhrEndpointIT
- composition: see DefaultRestCompositionEndpointIT
- template : see DefaultRestTemplateEndpointIT
- directory : see DefaultRestDirectoryEndpointIT

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

### Codestyle/Formatting

openEHR-SDK java sourcecode is using [palantir-java-format](https://github.com/palantir/palantir-java-format) codestyle.
The formatting is checked and applied using the [spotless-maven-plugin](https://github.com/diffplug/spotless/tree/main/plugin-maven).
To apply the codestyle run the `com.diffplug.spotless:spotless-maven-plugin:apply` maven goal in the root directory of the project.
To check if the code conforms to the codestyle run the `com.diffplug.spotless:spotless-maven-plugin:check` maven goal in the root directory of the project.
These maven goals can also be run for a single module by running them in the modules' subdirectory.

To make sure all code conforms to the codestyle, the "check-codestyle" check is run on all pull requests.
Pull requests not passing this check shall not be merged.

If you wish to automatically apply the formatting on commit for *.java files, a simple pre-commit hook script "pre-commit.sh" is available in the root directory of this repository.
To enable the hook you can either copy the script to or create a symlink for it at `.git/hooks/pre-commit`.
The git hook will run the "apply" goal for the whole project, but formatting changes will only be staged for already staged files, to avoid including unrelated changes.

In case there is a section of code that you carefully formatted in a special way the formatting can be turned off for that section like this:
```
everything here will be reformatted..

// @formatter:off

    This is not affected by spotless-plugin reformatting...
            And will stay as is it is!

// @formatter:on

everything here will be reformatted..
```
Please be aware that `@formatter:off/on` should only be used on rare occasions to increase readability of complex code and shall be looked at critically when reviewing merge requests.

----

## License

openEHR SDK uses the Apache License, Version 2.0 (https://www.apache.org/licenses/LICENSE-2.0)

## Stargazers over time

[![Stargazers over time](https://starchart.cc/ehrbase/openEHR_SDK.svg)](https://starchart.cc/ehrbase/openEHR_SDK)
