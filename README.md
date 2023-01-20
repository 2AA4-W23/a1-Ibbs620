# A1 - Piraten Karpen

- Author: Ibrahim Quraishi
- Email: quraishi@mcmaster.ca

## Build and Execution

- To clean your working directory:
  - `mvn clean`
- To compile the project:
  - `mvn compile`
- To run the project in development mode:
  - `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
- To run the project with log messages:
  `mvn -q exec:java -Dexec.args="t"` or `mvn -q exec:java -Dexec.args="trace"`
- To package the project as a turn-key artefact:
  - `mvn package`
- To run the packaged delivery:
  - `java -jar target/piraten-karpen-jar-with-dependencies.jar`

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

- Status:
  - Pending (P), Started (S), Blocked (B), Done (D)
- Definition of Done (DoD):
  - The feature is fully implemented and functions exactly the way it was described. It is also fully tested for bugs, including edge cases.

### Backlog

| MVP? | Id  | Feature                                              | Status | Started  | Delivered |
| :--: | :-: | ---------------------------------------------------- | :----: | :------: | :-------: |
|  x   | F01 | Roll a dice                                          |   D    | 01/01/23 | 01/01/23  |
|  x   | F02 | Roll eight dices                                     |   D    | 01/16/23 | 01/16/23  |
|  x   | F03 | 42 games are simulated                               |   D    | 01/16/23 | 01/16/23  |
|  x   | F04 | End of game with three skulls                        |   D    | 01/16/23 | 01/16/23  |
|  x   | F05 | Player keeping random dice at their turn             |   D    | 01/16/23 | 01/16/23  |
|  x   | F06 | Score points: 100 points for each diamond and gold   |   D    | 01/16/23 | 01/16/23  |
|  x   | F07 | After 42 games, stdout win percentage of each player |   D    | 01/16/23 | 01/16/23  |
