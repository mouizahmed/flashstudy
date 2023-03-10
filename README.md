# Flashcard Study System — FlashStudy

To Run: Run src/main/java/flashstudy/Main.java

## Team 4 Group Members

- Mouiz Ahmed (mouizahmed, 218105536)
- Sourav Chandhok (souravC01, 218533976)
- Ojas Taneja (ojastaneja7, 218496240)

## Overview
This app is a comprehensive study tool that combines various features to help users learn and retain information efficiently. Users can create unique flashcards, either by drawing them out or recording questions and answers. The app also allows users to search for other flashcard decks through a public directory based on school and class. The study planner feature enables users to create a customized study plan based on their study habits and test dates, using spaced repetition to optimize learning. Additionally, the app includes weekly planned email reminders for tests and assignments, an interactive flashcard game, a point-based system, a public leaderboard, and detailed analytics for each study session. This app is suitable for all types of learners and offers valuable feedback to help users improve their study habits.

## Code Organization
The following is a high-level overview of the code structure and functionality of Flash Study. It aims to provide an understanding of the different components, their interactions, and how they work together to achieve the app's objectives.

#### Controller
This folder contains classes that handle the logic and behaviour of the application. This includes classes that handle user input, process data, and coordinate communication between the different parts of the application.

<details>
<summary>Java Classes Description in Controller</summary>

#### Controller

* The Controller class includes a constructor that initializes two objects, userDatabase and deckDatabase, which are instances of UserList and DeckList classes respectively.

* The createNewUser method is used to add a new user to the application. It takes in four parameters - username, email, password, and confirmPassword - and attempts to add a new user to the userDatabase. If the user is successfully added, the method returns true, otherwise it returns false.

* The createNewUser method also includes error handling for NoSuchAlgorithmException and InvalidKeySpecException. These are exceptions that may occur when encrypting the password using hashing functions.

* The Controller class appears to work independently of a MySQL connection, suggesting that it may store and manage user data within the application itself, without the need for an external database.
</details> 

#### FlashStudy
The FlashStudy folder contains the main class for the Java app. The main class is the entry point for the application, and it typically contains a main() method, which is executed when the application is started.
The main() method in the main class which is responsible for executing the welcome page of the application. The welcome page is the first thing that the user sees when they open the app, and it provide instructions on how to use the app, and offer options for the user to get started by either creating a new user account or login exsisting account.
####  Models 
This folder contains classes that define the underlying data structures used by the application. This includes classes that represent Decks, databases, users, flashcards, EmailSender and other types of data that are necessary for the application to function.
####  View
This folder might contain classes that handle the visual aspects of the application using Java Swing GUI. This includes classes that define the layout and appearance of the user interface, handle user input and display output.




## Iteration 1 Development Tasks


