# Flashcard Study System — FlashStudy

## Instructions to Run the Application
- In Eclipse/IntelliJ, clone the repository from Git using smart import.
- Ensure you are using JavaSE-15 when building and running the application.
- In order to utilize the AI Quiz Features, either email mouiza@my.yorku.ca or create a new OpenAI secret key.
  - Sign up for [OpenAI](https://platform.openai.com/account/api-keys) and get your secret key. Note* This is rotated periodically so this may need to be updated.
- create a .env file within the root folder, and create an environment variable.
  - It must include `OPENAI_KEY=*YOUR_KEY`
- Navigate to src/main/java/flashstudy/Main.java.
- Click Run

## Team 4 Group Members

- Mouiz Ahmed (mouizahmed, 218105536)
- Sourav Chandhok (souravC01, 218533976)
- Ojas Taneja (ojastaneja7, #########)
- Daoud Ali (DaoudAli, 216410672)
- Hena Patel (henap741, 218109124)

## Overview
This app is a comprehensive study tool that combines various features to help users learn and retain information efficiently. Users can create unique flashcards, either by drawing them out or recording questions and answers. The app also allows users to search for other flashcard decks through a public directory based on school and class. The study planner feature enables users to create a customized study plan based on their study habits and test dates, using spaced repetition to optimize learning. Additionally, the app includes weekly planned email reminders for tests and assignments, an interactive flashcard game, a point-based system, a public leaderboard, and detailed analytics for each study session. This app is suitable for all types of learners and offers valuable feedback to help users improve their study habits.

## Code Organization
The FlashStudy application utilizes an MVC software design pattern that separates the application into three logical components: the model, the view, and the controller. This design pattern emphasizes the separation between the software's busines logic and display which provide for a better distribution of labour, reduce code duplication, improve maintainability, and make each component easier to test in isolation.

The following is a high-level overview of the highlighted code structure and functionality of FlashStudy. It aims to provide an understanding of the different components, their interactions, and how they work together to achieve the app's objectives.

#### Controller
This folder contains classes that handle the logic and behaviour of the application. This includes classes that handle user input, process data, and coordinate communication between the view and model classes of the application.

#### FlashStudy
The FlashStudy folder contains the main class for the Java app. The main class is the entry point for the application, and it contains a main() method, which is executed when the application is started.
The main() method in the main class is responsible for executing the welcome page of the application. The welcome page is the first thing that the user sees when they open the app, and it provides instructions on how to use the app, and offer options for the user to get started by either creating a new user account or logging in with an existing account.

####  Models 
This folder contains classes that define the underlying data structures used by the application. This includes classes that represent Decks, databases, users, flashcards, EmailSender and other types of data that are necessary for the application to function.

####  View
This folder contains classes that handle the visual aspects of the application using Java Swing GUI. This includes classes that define the layout and appearance of the user interface, handle user input and display output.




