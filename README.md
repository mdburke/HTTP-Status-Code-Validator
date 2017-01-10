##HTTP Status Code Validator

This is a WIP test that will take a list of a base url and the paths to test that each aggregate url returns a 200 status code.

The jar will be output as "StatusCodeValidator-0.1.jar". To run:

```
java -jar [path to jar] [test data path] [optional log file path]
```

The format of the csv for the test data should be similar to the TestUrls.csv located in this repository.

```
http://ahs.uic.i.pixotech.com
/news
/asdfsa
```

The first line needs to be the base url and each subsequent line should have a single extension that could be combined with the base to form a test url. To skip a test, prepend the line with a '$' character. This way, we can easily update the tests as the product is updated with new paths.

The log file is output as XML in a format intended to be read by the junit plug-in in Jenkins.

Any failures will 'fail' the build if this is implemented with that plug-in on a build.