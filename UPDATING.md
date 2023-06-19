# Updating the SDK

This file documents any backwards-incompatible changes in SDK and
assists users migrating to a new version.

## SDK 2.0.0

## Major overhaul of AQL DTO model and parser

The Model was changed quit significantly so code wich manipulates ot introspects them will be needed to be
reimplemented:

* AQL is parsed via `AqlQueryParser::parse`
* AQL is rendered via `AqlRenderer::render`
* parsing is now based on the official grammar
* `AqlDto` has been replaced by `AqlQuery`
* the underlying model is now more closely aligned with the grammar

## Changes to package paths and classes moved to other/new modules

The base package path of any SDK class is now `org.ehrbase.openehr.sdk` followed by the artifact-id of the containing module.
Since classes have also been relocated to different modules or replaced, simple string replacement will not suffice. A reimport of the used SDK classes is recommended.

Notable changes:
* new module generator-commons: `org.ehrbase.openehr.sdk.generator.commons`
    * shared classes used by multiple packages to be compatible with the DTO classes generated from templates
    * test-jar providing test data for features making use of generated DTOs
    * AQL model for generated DTOs (former `org.ehrbase.client.aql`)
    * most classes from former `org.ehrbase.client.classgenerator` package
* The class `org.ehrbase.client.openehrclient.VersionUid` has been removed and replaced by the archie class it was duplicating (`com.nedap.archie.rm.support.identification.ObjectVersionId`)
    * The respective change in any generated DTOs can be done by String replacement of `VersionUid` with `ObjectVersionId` and same for the import or by regenerating the DTOs, if they do not include modifications
    * The archie class `ObjectVersionId` provides the same basic values, but as strings using different methods
      * `VersionUid.getUuid()` (returns `java.util.UUID`) should be replaced by `ObjectVersionId.getObjectId().getValue()` (returns `java.lang.String`) and parsed as UUID if needed
      * `VersionUid.getSystem()` (returns `java.lang.String`) should be replaced by `ObjectVersionId.getCreatingSystemId().getValue()` (returns `java.lang.String`)
      * `VersionUid.getVersion()` (returns `long`) should be replaced by `ObjectVersionId.getVersionTreeId().getValue()` (returns `java.lang.String`) and parsed as long if needed (note that versions can follow the SemVer scheme and are therefore not necessarily numbers)
      * `VersionUid.toString()` (returns `java.lang.String`) should be replaced by `ObjectVersionId.getValue()` (returns `java.lang.String`) 
      * note that system and version parts are optional and that `ObjectVersionId.getObjectId()` and `ObjectVersionId.getVersionTreeId()` throw an exception if the respective part is not present

## SDK 1.0.0

### Using the sdk with old generated classes

Classes generated with the old SDK can be used with the new version

### Migrating from old generated classes to new ones

It is recommended to update the generated classes. To do this:

* Update the sdk version
* Rename any Name with *defningcode to *defningCode
* replace the EnumValueSet in your local shareddefinition folder with the new from the sdk:
    * Language -> org.ehrbase.client.generator.shareddefinition.Language
    * CategoryDefiningcode -> org.ehrbase.client.generator.shareddefinition.Category
    * MathFunctionDefiningcode -> org.ehrbase.client.generator.shareddefinition.MathFunction
    * SettingDefiningcode -> org.ehrbase.client.generator.shareddefinition.Setting
    * Territory -> org.ehrbase.client.generator.shareddefinition.Territory
    * TransitionDefiningcode ->  org.ehrbase.client.generator.shareddefinition.Transition

* Generate your classes new with the generator using generator/src/main/resources/LegacyConfig.yaml as config
* Delete you old classes and replaces them with the new one
* There may be some more changes regrading the naming or the class structure which you have to change in your code.
