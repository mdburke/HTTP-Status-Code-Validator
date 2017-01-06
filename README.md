##HTTP Status Code Validator

This is a WIP test that will take a list of a base url and the paths to test that each aggregate url returns a 200 status code.

To run currently:

```
mvn clean install
java -jar target/StatusCodeValidator-1.0-SNAPSHOT.jar TestUrls.csv
```