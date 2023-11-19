# Domain counter

This application receives an input from emails and counts top 10 valid domains.

## Installation

Go to project directory and run:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="counter.Main"
```



## What could be improved

- Address potential incorrect scenarios during data input to provide more informative and user-friendly error messages
- Related tests could be added for previous point
- Extend the email validation process to handle specific scenarios, considering more precise requirements if available
- Improve the print functionality
