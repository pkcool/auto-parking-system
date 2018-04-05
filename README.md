# Automatic parking system

Cars are placed on a 15 by 15 grid at particular co-ordinates heading north, and the simple
commands Left, right and forward are transmitted to them. The commands will be executed and
the final position calculated.

## command examples

    For the input "5,5:RFLFRFLF"
    We should get the position "7,7"

    For the input "6,6:FFLFFLFFLFF"
    We should get the position "6,6"

    For the input "5,5:FLFLFFRFFF"
    We should get the position "1,4"

## Design
The application was built with multi-layer structure: domain layer, service layer and application layer.
they are separated so the individual components would be re-usable, and the implementation is replaceable.

Spring boot is used to simplify the configuration.

## Development
TDD has been applied, with unit tests developed first, then coding, refactoring.

## How to run

Package it (depending on the os, you might run mvnw or mvnw.cmd)
    
    ./mvnw clean package
    
Run the application with argument, i.e.

    java -jar target/autoparking-0.0.1-SNAPSHOT.jar 5,5:RFLFRFLF

