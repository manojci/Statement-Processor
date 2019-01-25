# Statement-Processor
To fetch the statement details which has failed records use the below POST endpoint

POST: http://localhost:4455/api/v1/get-statement

params:

{
   "fileName": "records",
   "fileType": "csv"
}

Output: End Balance validated with unique references are sent with error description


Below are the set of items covered as part of the work

1) Seggregation of packages

2) Logging using log4j. Levels of logging is differnet for environment

3) Multiple Environment consideration

4) Test Files added (Junit and PowerMockito)

5) Added Java Docs

6) Spring-Boot and Stream API usage

7) Jenkinsfile configuration for CI

8) Code Quality assurance (PMD Plugin)

9) Error response handling
