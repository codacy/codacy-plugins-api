# codacy-plugins-api
A dependency free api for codacy-engines

This repository is ment to be the interface between 
.) codacy engines (currently via https://github.com/codacy/codacy-engine-scala-seed), 
.) the plugins-test and
.) codacy's internal component running engines and picking up their results

it's held dependency free so we are flexible on changing (versions of) mainly json-libraries on the components that use it.
