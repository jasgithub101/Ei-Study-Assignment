# Singleton Pattern Example

## Overview
This project demonstrates the Singleton design pattern in Java. The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. In this example, a `Logger` class is implemented as a Singleton.

## Components
- **Logger**: A class that follows the Singleton pattern to ensure only one instance of the logger exists. It provides a `log` method to log messages.
- **Main**: A client class that demonstrates the use of the `Logger` singleton.

## How to Run
1. Ensure you have Java installed on your machine.
2. Compile the Java files:
javac src/*.java Main.java
3. Run the Main class
Log: This is the first log message.
Log: This is the second log message.
Both loggers are the same instance.
