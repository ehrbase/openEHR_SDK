# Changelog

Note: version releases in the 0.x.y range may introduce breaking changes.

## [Unreleased]

### Added

- Added WebTemplate based RmSkeleton builder. (see https://github.com/ehrbase/openEHR_SDK/pull/241) 
- Generated Dtos: Add @inherited to annotation in generated classes for dtos so there can be inhered (see https://github.com/ehrbase/openEHR_SDK/pull/244) 
### Fixed

- OptParser: fix parsing f snomed (see https://github.com/ehrbase/openEHR_SDK/pull/234)
- Flat: refactor code to walk to all nodes (see https://github.com/ehrbase/project_management/issues/541)
- DefaultRestClient: add VERSIONED_COMPOSITION endpoint (see https://github.com/ehrbase/openEHR_SDK/pull/237)

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


