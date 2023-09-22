# codacy-plugins-api

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/23d335c474ce4317ad35b19cb90a1f35)](https://www.codacy.com/app/Codacy/codacy-plugins-api?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=codacy/codacy-plugins-api&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/23d335c474ce4317ad35b19cb90a1f35)](https://www.codacy.com/app/Codacy/codacy-plugins-api?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-plugins-api&utm_campaign=Badge_Coverage)
[![CircleCI](https://circleci.com/gh/codacy/codacy-plugins-api.svg?style=svg)](https://circleci.com/gh/codacy/codacy-plugins-api)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codacy/codacy-plugins-api_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.codacy/codacy-plugins-api_2.12)

A dependency free api for codacy-engines

This repository is meant to be the interface between

* Codacy analysis tools (currently via [codacy-engine-scala-seed](https://github.com/codacy/codacy-engine-scala-seed))
* Codacy duplication tools (currently via [codacy-duplication-scala-seed](https://github.com/codacy/codacy-duplication-scala-seed))
* Codacy metrics tools (currently via [codacy-metrics-scala-seed](https://github.com/codacy/codacy-metrics-scala-seed))
* The tests with [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test)
* Codacy's internal component running tools and picking up their results


TEST DONT MEEEEERGE


It's held dependency free so we are flexible on changing (versions of) mainly json-libraries on the components that use it.

## What is Codacy

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features

* Identify new Static Analysis issues
* Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
* Auto-comments on Commits and Pull Requests
* Integrations with Slack, HipChat, Jira, YouTrack
* Track issues in Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
