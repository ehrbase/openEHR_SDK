# EHRBase SDK  
## client (mostly Beta)
Generic openEHR Client and Objekt-mapper:
* Define entity classes for openEHR-Templates (v1.4) in a jpa like way
* Map entity <-> Archie RM objekts.
* RestClient for openEHR Rest-API 
* AQL-Query generator (Alpha)
## generator (Beta)
* Autogenerate  entity classes from template
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
```bash
mvn clean install
```

## Usage
###  Entity generation
To generate an entity class from a template use
```bash
 java  -jar generator-version.jar
 -h               show help
 -opt <arg>       path to opt file
 -out <arg>       path to output directory
 -package <arg>   package name
```
### Use The SDK in your projekt
You can include the SDK via [jitpack.io](https://jitpack.io/#ehrbase/openEHR_SDK)
####  Map entity <-> Archie RM objekts

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
