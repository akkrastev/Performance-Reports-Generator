# Performance-Reports-Generator

Java console application that generates monthly performance reports.

-Receives a command-line argument that is path to JSON data file

-Receives a command-line argument that is path to JSON report definition file

-Generates CSV (comma separated value) file containing the report result

-Resulting report file should contain only employees that have sales period that 
is equal or less than the periodLimit property and have score that is within the
top X percent of the results, where X is defined by the topPerformersThreshold
property of the report definition.

-The score is calculated based on the following rules:

If useExperienceMultiplier is set to true -> score = totalSales/salesPeriod*experienceMultiplier

If useExperienceMultiplier is set to false -> score = totalSales/salesPeriod
