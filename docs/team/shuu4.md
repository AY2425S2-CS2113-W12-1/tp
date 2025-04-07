---
layout: page
title: Zhong Shu
parent: About Us
---

### Project: ModMate

ModMate is a command-line tool that helps NUS students efficiently manage timetables and module information using the NUSMods API.

Given below are my contributions to the project.

* **New Feature**:  Added a `addmod` command that allows the user to add a mod to an timetable
  * What it does: allows the user to add single or multiple mods from NUSMods to an existing timetable based on the module codes or names.
  * Justification: Enables flexible timetable planning by letting users customise their schedules with specific modules they intend to take.  
  * Highlights: Leverages modular data structures (Mod, CondensedMod) for efficient data handling and future scalability.

* **New Feature**:  Added a `removemod` command that allows the user to remove a mod from an timetable
  * What it does: Allows users to remove one or more modules from an existing timetable using their module codes or names.
  * Justification: Provides users with control over their timetable by allowing the removal of unwanted or mistakenly added modules.
  * Highlights: Ensures safe removal with checks for module existence.

* **New Feature**: Added `addbreak` command that allows the user to add a break to an timetable
  * What it does: Allows users to insert a labelled break period (e.g. Lunch, Study Time) into a specific timetable.
  * Justification: Improves usability and realism of the timetable by allowing users to block out non-academic periods for better time management and schedule planning.
  * Highlights: Enforces time format validation to avoid conflicts or input errors.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=shuu4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21)

* **Project management**:
  * Release support:
    * Contributed to the successful planning and rollout of the v2.0 release by aligning development tasks with key milestones and deliverables set by the team lead.
  * Commitment to quality:
    * Ensured timely delivery of assigned features and bug fixes while adhering to the team’s quality and testing standards, contributing to a stable and on-time release.

* **Enhancements to existing features**:
  * [\#90](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/90): Wrote additional tests for timetable package to increase coverage

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addmod` `removemod` and `addbreak`
  * Developer Guide:
    * Added implementation details of the `addmod` `removemod` and `addbreak`feature: [\#86](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/86), [\#98](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/98)

* **Community**:
  * PRs reviewed: [\#24](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/24), [\#89](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/89), [\#186](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/186)
  * Bug fix: [\#178](https://github.com/AY2425S2-CS2113-W12-1/tp/pull/178)