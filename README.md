# EHRBase Client (Pre-Alpha) 

Generic openEHR Client and Objekt-mapper:
* Define entity classes for openEHR-Templates (v1.4) in a jpa like way
* Autogenerate  entity classes from template
* Map entity <-> Archie RM objekts.
* RestClient for openEHR Rest-API 
* AQL-Query generator (TODO)


## Installation

### Prerequisite
You need to have EHRbase jars for v0.8.0 in your maven repo 
### Build
```bash
mvn clean install
```

## Usage
###  Entity generation
To generate a entity class from a template use
```bash
 java -jar client-library-version.jar
 -h               show help
 -opt <arg>       path to opt file
 -out <arg>       path to output directory
 -package <arg>   package name
```

###  Map entity <-> Archie RM objekts

see FlattenerTest and UnflattenerTest

### RestClient for openEHR Rest-API 

- ehr : see DefaultRestEhrEndpointIT
- composition: see DefaultRestCompositionEndpointIT
- template : see DefaultRestTemplateEndpointIT
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[APACHE 2.0](https://www.apache.org/licenses/LICENSE-2.0)
