# json-data-processing-java-maven

This project demonstrates how to deal with large JSON files using Java 8, Jackson, Stream and Maven.

This demo firstly serializes large JSON files and processes it by calculating its attributes and then generates a new JSON file with calculated/sorted values.

Tools and techs used:

* Java 8+
* Maven 3+
* Stream
* Jackson

## Usage

Run the following command in a terminal environment:

```powershell
mvn clean compile exec:java
```
The summary JSON file will be output to `target/Profit_loss_summary.json`.
