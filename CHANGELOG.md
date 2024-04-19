# Changelog

Note: version releases in the 0.x.y range may introduce breaking changes.

## [unreleased]
 ### Added 
 ### Fixed 
- migrated test to run against ehrbase v2  ([#588](https://github.com/ehrbase/openEHR_SDK/pull/588))
- contribution lifecycle creation  ([#588](https://github.com/ehrbase/openEHR_SDK/pull/588))
- removed client folder endpoint (use DirectoryCrudEndpoint instead)  ([#588](https://github.com/ehrbase/openEHR_SDK/pull/588))

## [2.9.1]
 ### Added 
 ### Fixed 

## [2.9.0]
 ### Added 
 ### Fixed 

## [2.8.0]
 ### Added 
 ### Fixed 

## [2.7.0]

### Added

- Added `QueryResponseData.meta` additional `fetch`, `offset` and `resultsize`
  properties ([#559](https://github.com/ehrbase/openEHR_SDK/pull/559))

### Changed

- Sealed AqlQuery
- AqlQuery: Support for VERSION without predicate

### Fixed

## [2.6.0]
 ### Changed
- Bumped libraries
 ### Added 
 ### Fixed 
- Parsing of string function CONTAINS ([5ef1eca](https://github.com/ehrbase/openEHR_SDK/commit/5ef1eca72289856fa94a04146aa876a4c5130423))
- Add missing aggregate function name to the parser ([8fd08bb](https://github.com/ehrbase/openEHR_SDK/commit/8fd08bbd1ce3b5a217cc8e2b701e32c2125bf0bf))

## [2.5.0]
 ### Added 
 ### Fixed 
- Flat: fix term mappings can not be saved in name via flat ([#519](https://github.com/ehrbase/openEHR_SDK/pull/519))
- Validation: fix DV_CODED_TEXT error messages ([#537](https://github.com/ehrbase/openEHR_SDK/pull/537))

## [2.4.0]
 ### Added 
 ### Fixed 

## [2.3.0]
 ### Added 
- AQL Dto model add terminology , and Versioning  ([#511](https://github.com/ehrbase/openEHR_SDK/pull/511))
 ### Fixed 

## [2.2.0]
 ### Added 
 ### Fixed 

## [2.1.0]
 ### Added 
- Added json Serialisation for the AQL-DTO model ([#496](https://github.com/ehrbase/openEHR_SDK/pull/496))  
 ### Fixed 

## [2.0.0]

### Added

- new module generator-commons ([#472](https://github.com/ehrbase/openEHR_SDK/pull/472))

### Changed

- switch to java 17 ([#464](https://github.com/ehrbase/openEHR_SDK/pull/464))
- Change the implementation of AqlDto ([#464](https://github.com/ehrbase/openEHR_SDK/pull/464)[#483](https://github.com/ehrbase/openEHR_SDK/pull/483))
- Change package paths to start with `org.ehrbase.openehr.sdk` ([#472](https://github.com/ehrbase/openEHR_SDK/pull/472))
- Moved classes to the modules they belong to semantically ([#472](https://github.com/ehrbase/openEHR_SDK/pull/472))
- Removed ehrbase specific package `org.ehrbase.serialisation.dbenconding` ([#472](https://github.com/ehrbase/openEHR_SDK/pull/472))
- Removed empty module "building" ([#472](https://github.com/ehrbase/openEHR_SDK/pull/472))

### Fixed

- Workaroud for ([an issue in java 17.0.7]())  ([#468] (https://github.com/ehrbase/openEHR_SDK/pull/468))

## [1.29.0]

### Changed

- Bump maven-plugin-annotations ([#466](https://github.com/ehrbase/openEHR_SDK/pull/466))

## [1.28.0]

### Added

- new DirectoryCrudEndpoint ([#465](https://github.com/ehrbase/openEHR_SDK/pull/465))

### Fixed

## [1.27.0]

### Changed

- Bump libraries

## [1.26.0]

### Added

- Added client support for managing folders trough
  contributions ([#427](https://github.com/ehrbase/openEHR_SDK/pull/427))
- update archie to 3.0.0 and antlr4 to 4.11.1 ([#428](https://github.com/ehrbase/openEHR_SDK/pull/428))

### Fixed

- Add workarounds for archie not handling date-time values according to spec when (de-)serializing (affects XML,JSON and
  Flat) ([#420](https://github.com/ehrbase/openEHR_SDK/pull/420))

## [1.25.0]

### Added

### Changed

- FlatPathDto is now immutable ([#425](https://github.com/ehrbase/openEHR_SDK/pull/425))

### Fixed

## [1.24.0]

### Added

- Added client support for managing compositions trough
  contributions ([#406](https://github.com/ehrbase/openEHR_SDK/pull/406))
- Added null verification and change gson to jackson ([#416](https://github.com/ehrbase/openEHR_SDK/pull/416))

### Changed

- Update libraries ([#422](https://github.com/ehrbase/openEHR_SDK/pull/422))

### Fixed

## [1.23.0]

### Added

- Added handling of stored AQL query requests ([#384](https://github.com/ehrbase/openEHR_SDK/pull/384))

### Fixed

- update archie to 2.1.0 ([#410](https://github.com/ehrbase/openEHR_SDK/pull/410))

## [1.22.0]

### Added

### Fixed

- Folder creation
- fix not unique rows in matrix formate ([#390](https://github.com/ehrbase/openEHR_SDK/pull/390))

## [1.21.0]

### Added

- Get items from current folder ([#385](https://github.com/ehrbase/openEHR_SDK/pull/385))

### Fixed

- Removed caching of root folders to prevent false conflicts and memory leaks
  ([#385](https://github.com/ehrbase/openEHR_SDK/pull/385))
- new Matrix serialisation use rm-model for index and fix section handling
  ([#386](https://github.com/ehrbase/openEHR_SDK/pull/386))

## [1.20.0]

### Added

- Add spotless plugin, Add codestyle check to workflows ([#368](https://github.com/ehrbase/openEHR_SDK/pull/368))
- Add new Tool to interpret AQL against a template ([#379](https://github.com/ehrbase/openEHR_SDK/pull/379))
- Add new Matrix serialisation format ([#381](https://github.com/ehrbase/openEHR_SDK/pull/381))

### Fixed

- Skip archetype slots not used by the template in example
  generator ([#369](https://github.com/ehrbase/openEHR_SDK/pull/369))
- enhance sdk aql parser to handle more cases ([#376](https://github.com/ehrbase/openEHR_SDK/pull/376))
- update update everit-json-schema to maven version ([#370](https://github.com/ehrbase/openEHR_SDK/pull/370))

## [1.19.0]

### Added

- Maven plugin to generate code from templates ([#347](https://github.com/ehrbase/openEHR_SDK/pull/347))
- Example
  Generator ([#349](https://github.com/ehrbase/openEHR_SDK/pull/349), [#351](https://github.com/ehrbase/openEHR_SDK/pull/351))
- Flatencoding parsing: automatically handle date/time/date_time
  precision [#352](https://github.com/ehrbase/openEHR_SDK/pull/352)
- Example Generator ([#349](https://github.com/ehrbase/openEHR_SDK/pull/349)
  , [#351](https://github.com/ehrbase/openEHR_SDK/pull/351))
- use bom for dependency management ([#358](https://github.com/ehrbase/openEHR_SDK/pull/358))
- created release action ([#362](https://github.com/ehrbase/openEHR_SDK/pull/362))

### Fixed

- fix wrong escape in names attribute of the aql path ([#364](https://github.com/ehrbase/openEHR_SDK/pull/364))

## [1.18.0]

### Added

- Update archie to version 2.0.1 ([#345](https://github.com/ehrbase/openEHR_SDK/pull/345))
- AQL: support `ORDER BY` and `LIMIT [OFFSET]` clauses in any
  order ([#344](https://github.com/ehrbase/openEHR_SDK/pull/344))

### Fixed

- Flat : corrected handling of PARTY_PROXY ( see https://github.com/ehrbase/openEHR_SDK/pull/320)
- Flat : corrected handling of history origin and ISM_TRANSITION (see https://github.com/ehrbase/openEHR_SDK/pull/329)
- Flat : handle fixed offset in template (see https://github.com/ehrbase/openEHR_SDK/pull/333)
- Validation: fix validation of DV_TEXT with listopen (see https://github.com/ehrbase/openEHR_SDK/pull/335)
- Walker, Flat, DTO, Validation  : enhance performance of path handling (
  see https://github.com/ehrbase/openEHR_SDK/pull/325, https://github.com/ehrbase/openEHR_SDK/pull/332 )
- dto: Fix handling of element wich contains a choice with one an interval (
  see https://github.com/ehrbase/openEHR_SDK/pull/334)
- Fixes AqlParseException while using boolean in where clause ([#338](https://github.com/ehrbase/openEHR_SDK/pull/338))
- Fixes default ASC value for ORDER BY clause ([#342](https://github.com/ehrbase/openEHR_SDK/pull/342))
- Fixes null nodeIds and annotations missing (https://github.com/ehrbase/openEHR_SDK/pull/343)
- Fixes duration validation ([#346](https://github.com/ehrbase/openEHR_SDK/pull/346)

## 1.17.0

### Added

- Flat : added more test and devise fixes ( see https://github.com/ehrbase/openEHR_SDK/pull/291)
- Upgrade to Archie 1.0.4 ([#292](https://github.com/ehrbase/openEHR_SDK/pull/292))
- cleanup created templates (https://github.com/ehrbase/openEHR_SDK/issues/298)
- WebTemplate based validation (see https://github.com/ehrbase/openEHR_SDK/pull/309)
- db-serialising: created conformance test (see https://github.com/ehrbase/openEHR_SDK/pull/311)
- flat: created conformance tests (see https://github.com/ehrbase/openEHR_SDK/pull/310
  ; https://github.com/ehrbase/openEHR_SDK/pull/305; https://github.com/ehrbase/openEHR_SDK/pull/304
  ; https://github.com/ehrbase/openEHR_SDK/pull/301)

### Fixed

- Fix NullPointerException when event has an empty state (https://github.com/ehrbase/openEHR_SDK/pull/294)
- Fix issue when template does not contain list of values for
  DV_ORDINAL (https://github.com/ehrbase/openEHR_SDK/pull/295)
- Fix issue in AQL regarding LIMIT and OFFSET (https://github.com/ehrbase/openEHR_SDK/pull/296)
- Fix issue while unmarshalling FLAT composition that contains ELEMENT with children DV_CODED_TEXT and
  DV_TEXT (https://github.com/ehrbase/openEHR_SDK/pull/300)
- Fix missing 'type' attribute in ExternalRef encoding (https://github.com/ehrbase/openEHR_SDK/pull/303)
- db-serialising: fix handling of dv_ehr_uri  (https://github.com/ehrbase/openEHR_SDK/pull/316)
- Fix db-serialising: date-time dv_order attributes (https://github.com/ehrbase/openEHR_SDK/pull/314)
- Fix db-serialising: element.null_reason (https://github.com/ehrbase/openEHR_SDK/pull/317)
- db-serialising: fix handing of guideline_id & work_flow_id &
  wf_definition (https://github.com/ehrbase/openEHR_SDK/pull/315)
- db-serialising: fix handling of locatable attributes  (https://github.com/ehrbase/openEHR_SDK/pull/318)

## 1.16.0

### Added

- Added WebTemplate based RmSkeleton builder. (see https://github.com/ehrbase/openEHR_SDK/pull/241)
- validate that all flat parts are consumed. (see https://github.com/ehrbase/openEHR_SDK/pull/264 )
- support Structured format. (see https://github.com/ehrbase/openEHR_SDK/pull/273)
- Allow Json Values in flat (see https://github.com/ehrbase/openEHR_SDK/pull/277)

### Fixed

- OptParser: fix parsing f snomed (see https://github.com/ehrbase/openEHR_SDK/pull/234)
- Flat: refactor code to walk to all nodes (see https://github.com/ehrbase/project_management/issues/541)
- DefaultRestClient: add VERSIONED_COMPOSITION endpoint (see https://github.com/ehrbase/openEHR_SDK/pull/237)
- Generated Dtos: Enable usage of Dots with inheritance  (see https://github.com/ehrbase/openEHR_SDK/pull/244)
- Fix validation of normal statuses (see https://github.com/ehrbase/openEHR_SDK/pull/249)
- Fix terminology validation issue (see https://github.com/ehrbase/openEHR_SDK/pull/263)
- Fix NullPointerException when `protocol.items` is `null` or empty (
  see https://github.com/ehrbase/openEHR_SDK/pull/262)
- FLAT: Fix missing items in ITEM_TREE (see https://github.com/ehrbase/openEHR_SDK/pull/265)
- Handle `expiry_time` in INSTRUCTION (see https://github.com/ehrbase/openEHR_SDK/pull/270)
- Fix ValidationException while parsing DvDuration with Period (see https://github.com/ehrbase/openEHR_SDK/pull/276)
- Support optional `ETag` return by the server when uploading a template (
  see https://github.com/ehrbase/openEHR_SDK/pull/275)
- FLat: Lots of small fixes (see https://github.com/ehrbase/openEHR_SDK/pull/280)
- OPt Parsing : enhance performance (see https://github.com/ehrbase/openEHR_SDK/pull/284
  , https://github.com/ehrbase/openEHR_SDK/pull/286)

## 1.5.0

### Added

- AqlParsing: Provide utility to remove parameters within AQL WHERE clauses (
  see https://github.com/ehrbase/openEHR_SDK/pull/231)

### Fixed

- Flat: Allow replacing DV_TEXT with DV_CODED_TEXT (see https://github.com/ehrbase/openEHR_SDK/pull/223)
- AqlParsing : parsing of wheres with mixed "and" and "or" (see https://github.com/ehrbase/openEHR_SDK/pull/231)

## 1.4.0

### Added

- Flat: support ctx variables (see https://github.com/ehrbase/openEHR_SDK/pull/206)
- Webtemplate: set inContext (see https://github.com/ehrbase/openEHR_SDK/pull/206)

### Fixed

- Lost of small fixes to flat (see https://githucb.com/ehrbase/openEHR_SDK/pull/204)
- Aql parsing : fix parsing of contains without archetypeId (see https://github.com/ehrbase/openEHR_SDK/pull/205)
- Aql building : fix building of aql with more from Dto with more than 2 where ANDs
- Several AQL fixes (see https://github.com/ehrbase/openEHR_SDK/pull/202
  and https://github.com/ehrbase/openEHR_SDK/pull/207)
- Feeder_audit other_details serialization and DB encoding (see https://github.com/ehrbase/openEHR_SDK/pull/208
  and https://github.com/ehrbase/openEHR_SDK/pull/209)

## 1.3.0

### Added

- Webtemplate: Generate cardinalities for Nodes (see https://github.com/ehrbase/openEHR_SDK/pull/198)
- ClientTemplateProvider: For dynamically retrieving templates from ehr server (
  see https://github.com/ehrbase/openEHR_SDK/pull/198)
- DefaultRestClient: remove the need to provide a template Provider (
  see https://github.com/ehrbase/openEHR_SDK/pull/197)

### Fixed

- Webtemplate: Fix erroneous skipping of Event. Happened when there were a EVENT and a EVENT Spezialisation as Children
  of the same Node (see https://github.com/ehrbase/openEHR_SDK/pull/197)

## 1.0.0

### Added

- Basic support for flat formats (see https://github.com/ehrbase/openEHR_SDK/pull/67)
- Web-Templates (see https://github.com/ehrbase/openEHR_SDK/pull/81)
- Allow Native Parts in Entity Queries  (see https://github.com/ehrbase/openEHR_SDK/pull/106)
- New endpoint for retrieving all templates (see https://github.com/ehrbase/openEHR_SDK/pull/153)
- New Dto -> RmClasses -> Dto mapper (see https://github.com/ehrbase/openEHR_SDK/pull/153)
- New ClassGenerator
    - Configurable via YAML
    - Optimized class generation
    - Optimized name Generation
    - Support Language specific Characters
    - Generate Interfaces for RM Attributes
    - Add null_flavor fields
    - Generate shared fields in Interfaces for Choice Elements
    - Support IsmTransition
    - Generate Javadoc
    - Add Generated Annotation

### Fixed

- Error when extracting name from a template where name has more than one child (
  see https://github.com/ehrbase/openEHR_SDK/pull/79)
- Enums where not correctly generated for value-sets (see https://github.com/ehrbase/openEHR_SDK/pull/92)

## 0.3.5

- patch: openEHR SDK containing the contents of EHRbase serialization, response DTOs, terminology and validation

[1.18.0]: https://github.com/ehrbase/openEHR_SDK/releases/tag/v1.18.0

[1.19.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.18.0...v1.19.0

[1.20.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.19.0...v1.20.0

[1.21.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.20.0...v1.21.0

[1.22.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.21.0...v1.22.0

[1.23.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.22.0...v1.23.0

[1.24.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.23.0...v1.24.0

[1.25.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.24.0...v1.25.0

[1.26.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.25.0...v1.26.0

[1.27.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.26.0...v1.27.0

[1.28.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.27.0...v1.28.0

[1.29.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.28.0...v1.29.0

[2.0.0]: https://github.com/ehrbase/openEHR_SDK/compare/v1.29.0...v2.0.0
[2.1.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.0.0...v2.1.0
[2.2.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.1.0...v2.2.0
[2.3.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.2.0...v2.3.0
[2.4.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.3.0...v2.4.0
[2.5.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.4.0...v2.5.0
[2.6.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.5.0...v2.6.0
[2.7.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.6.0...v2.7.0
[2.8.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.7.0...v2.8.0
[2.9.0]: https://github.com/ehrbase/openEHR_SDK/compare/v2.8.0...v2.9.0
[2.9.1]: https://github.com/ehrbase/openEHR_SDK/compare/v2.9.0...v2.9.1
[unreleased]: https://github.com/ehrbase/openEHR_SDK/compare/v2.9.1...HEAD
