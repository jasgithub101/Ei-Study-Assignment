# Interpreter Pattern Example

## Overview
This project demonstrates the Interpreter design pattern in Java. The pattern defines a grammatical representation for a language and provides an interpreter to interpret the grammar. In this example, mathematical expressions are interpreted.

## Components
- **Expression**: An interface defining the `interpret` method.
- **Number**: A terminal expression representing a number.
- **Add, Subtract, Multiply, Division**: Non-terminal expressions that represent operations in an expression tree.
- **InterpreterPatternDemo**: The client class that parses and interprets a mathematical expression.

## How to Run
1. Ensure you have Java installed on your machine.
2. Compile the Java files:
javac src/*.java
3. Run the InterpreterPatternDemoclass:
4. Input a postfix mathematical expression (e.g., `5 3 + 8 2 - 3 5 * 12 3 / -`) and press Enter. The program will output the result.


