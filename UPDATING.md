# Updating the SDK

This file documents any backwards-incompatible changes in SDK and
assists users migrating to a new version.

## SDK 1.0.0
### Using the sdk with old generated classes
Classes generated with the old SDK can be used with the new version
### Migrating from old generated classes to new ones
It is recommended to update the generated classes. To do this:
* Update the sdk version
* Rename any Name with *defningcode to *defningCode
* replace the EnumValueSet in your local shareddefinition folder with the new from the sdk:
  * Language -> org.ehrbase.client.classgenerator.shareddefinition.Language
  * CategoryDefiningcode -> org.ehrbase.client.classgenerator.shareddefinition.Category
  * MathFunctionDefiningcode -> org.ehrbase.client.classgenerator.shareddefinition.MathFunction
  * SettingDefiningcode -> org.ehrbase.client.classgenerator.shareddefinition.Setting
  * Territory -> org.ehrbase.client.classgenerator.shareddefinition.Territory
  * TransitionDefiningcode ->  org.ehrbase.client.classgenerator.shareddefinition.Transition
    
* Generate your classes new with the generator using generator/src/main/resources/LegacyConfig.yaml as config
* Delete you old classes and replaces them with the new one
* There may be some more changes regrading the naming or the class structure which you have to change in your code.
