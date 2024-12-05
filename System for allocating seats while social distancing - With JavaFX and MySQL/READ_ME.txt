The planned program aims to provide an efficient and friendly platform for moviegoers so that they can choose and secure a movie 
theater easily within the constraints of social distancing and taking into account the changing assumptions.
The purpose of the project is to create a system for the optimal allocation of seats in the stadium/hall in accordance with the distancing 
instructions for infectious diseases, quality requirements, seating and price constraints.
The system uses heuristics to find the ideal place for seating, taking into account the prices of the seats, the loss that will result from the 
seating and the number of free seats that will remain after the seating.

In order to run the program u need to follow the following instructions:
1. Install eclipse.
2. Install MySql.
3. In your C drive make directory called "MySQLJDBC".
4. Extract "mysql-connector-j-8.4.0" to "MySQLJDBC" directory.
5. Open "Create DataBase And Tables" using MySQL.
6. Run all lines in order to create the data base.
7. Extract "javafx-sdk-16" to C drive.
8. Move the code directories to directory called "Final Project".
9. Open the directory "Final Project" using eclipse.
10. In the project that opened in eclipse go to: database -> DataBaseCommands.java.
11. In the USER_NAME_FOR_MYSQL variable put your User name to MySQL.
12. In the PASSWORD_FOR_MYSQL variable put your Password to MySQL.
13. Right click on the project.
14. Click on: Run As -> Run Configurations...
15. Click on: Arguments.
16. In the VM arguments put: --module-path C:\javafx-sdk-16\lib  --add-modules=javafx.controls,javafx.fxml.
17. Uncheck the box: Use the -XX...
18. Click apply.
19. Click close.
20. In the project go to: general->Main.java.
21. Click Run.