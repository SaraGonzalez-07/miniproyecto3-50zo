# Cincuentazo 🃏

A poker card game developed in JavaFX with MVC architecture, where players must survive without exceeding a table sum of 50.

## Authors
| Name | ID |
|------|----|----------------|
| Margareth Gamboa | 2518629 |
| Sara González | 2519548 |
| Lissette Patiño | 2520977 |

## Game Description
Cincuentazo is a card game where the human player competes against 1, 2, or 3 machine players. Each player holds a hand of 4 cards and must play one card per turn without the table sum exceeding 50. The last player standing wins.

### Main Rules
- Cards 2 through 8 and 10: add their face value
- Card 9: neither adds nor subtracts
- Cards J, Q, K: subtract 10
- Card A: adds 1 or 10, whichever is more convenient
- If a player cannot play any card without exceeding 50, they are eliminated

## Technologies Used
- Java 17
- JavaFX 17
- Maven
- JUnit 5

## Technical Features
- MVC Architecture (Model - View - Controller)
- Threads to simulate machine player turns
- Custom exception handling with `CardException`
- Unit testing with JUnit 5
- Documentation with Javadoc in English
- Project management with Git and GitHub

## Prerequisites
- Java 17 installed (Amazon Corretto 17 recommended)
- Maven installed
- IntelliJ IDEA

## How to Run the Project
1. Clone the repository:
   git clone https://github.com/SaraGonzalez-07/miniproyecto3-50zo.git
2. Open the project in IntelliJ IDEA
3. In the Maven panel on the right go to:
   `Plugins → javafx → javafx:run`

## Project Structure
src/

├── main/

│   ├── java/com/example/_0zo/

│   │   ├── controller/    # MVC Controller

│   │   ├── events/        # Threads and event handlers

│   │   ├── model/         # Game logic

│   │   └── view/          # View

│   └── resources/         # FXML and CSS

└── test/                  # JUnit 5 unit tests

## Repository
(https://github.com/SaraGonzalez-07/miniproyecto3-50zo)