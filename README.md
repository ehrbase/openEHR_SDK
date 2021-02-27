# EHRBase SDK

## client (mostly Beta)

Generic openEHR Client and Objekt-mapper:

* Define entity classes for openEHR-Templates (v1.4) in a jpa like way
* Map entity <-> Archie RM objekts.
* RestClient for openEHR Rest-API
* AQL-Query generator (Alpha)

## generator (Beta)

* Autogenerate entity classes from template

## opt-1.4 (Beta)

* Opt 1.4 xmlbeans

## response-dto (Beta)

* DTO's representing the response for the ehrsacpe and openEHR Rest API

## terminologie (Beta)

* Mini openEHR terminologie implementation

## validation (Beta)

* Validation of Compositions against templates

## test-data (Beta)

* Example templates and Composition for tests

## serialisation (Beta)

* map Compositions from and to JSON;XML

## aql (Beta)

* Map an Aql-String from and to an Aql-Dto-Model.
* Not supported right now:
    - XOR
    - functions
    - matches
    - like
    - compare path to path expressions 
    

## Release Notes (v1.0.0)

* Support web-templates and flat-format
* New Dto generator and Dto mapper


## Release Notes (v0.3.6)

* CircleCI pipeline w/ Jacoco code coverage and sonarcloud.io analysis
* semi automated version updating via [major] / [minor] / [patch] flags in merge commit title of Github PR
* updated test dependencies to use Junit5

## Release Notes (v0.3.0)

* RestClient for DIRECTORY endpoint
* Improved clean-up of empty elements
* Improved generation of EVENT classes
* Automated generation of PARTICIPATION classes
* Added support for PARTY_PROXY
* Moved to EHRbase 11

## Installation

### Build

Use one of the options below to build the project.

#### Option 1) -  Build w/o integration tets

This option skipps integration tests. Code coverage report is based on unit tests only.

```bash
mvn clean install
```
or any specific maven phase
```bash
mvn clean test
mvn clean verify
mvn clean package
...
```



#### Option 2) - Build with unit & integration tests
This option includes unit as well as integration tests. Three coverage reports are generated: unit test report, integration test report and overall coverage report. 

```bash
mvn clean install -DskipIntegrationTests=false
```

or any specific maven phase

```bash
mvn clean verify -DskipIntegrationTests=false
mvn clean package -DskipIntegrationTests=false
...
```

  :warning: EHRbase server + DB must be running to execute integration tests successfully

#### Option 3) - Execute tests via profiles

```bash
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

Parameter        | Default                         | Description
 -----------     | ---------                       | ------------- 
optimizerSetting | SECTION                         | Defines if nodes which belong to are archetype but are single valued generate a new class: <ul><li>NONE: Always generate a class for nodes which belong to a archetype</li><li>SECTION: Do not generate a class for nodes which have rm-type section and are single valued</li><li>ALL: Do not generate a class for nodes which are single valued</li></ul>
addNullFlavor    | true                            | Whether or not to generate null flavor fields for Elements.
generateChoicesForSingleEvent    | false                           | Whether or not to generate Choices (POINT_EVENT & INTERVAL_EVENT) fields for a single EVENT. If "false" only POINT_EVENT will be generated.
replaceChars     | German and Norwegian Characters | Map to define Characters in the Node name to be replaced. 

see generator/src/main/resources/DefaultConfig.yaml

### Use The SDK in your projekt

You can include the SDK via [jitpack.io](https://jitpack.io/#ehrbase/openEHR_SDK)

#### Map entity <-> Archie RM objekts

see FlattenerTest and UnflattenerTest

#### RestClient for openEHR Rest-API

- ehr : see DefaultRestEhrEndpointIT
- composition: see DefaultRestCompositionEndpointIT
- template : see DefaultRestTemplateEndpointIT
- directory : see DefaultRestDirectoryEndpointIT

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[APACHE 2.0](https://www.apache.org/licenses/LICENSE-2.0)
