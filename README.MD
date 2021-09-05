# Attendance Management System Installation

## Libraries:
* java-12.0.2
* JavaFx-11.0.2
* controlFx-8.40.14
* MyObserver-1.4
* postgresql-42.2.20

### Add libraries
1. Go to File -> Project Structure -> Libraries -> '+' -> Java.
1. Find and select the specific library.
1. Select from pop-up all modules :
    1. AttendanceManagementSystem
    1. AttendanceManagementSystem (Server)
    1. AttendanceManagementSystem(Client).
1. Repeat the step 2 and 3 for all the libraries mention under the 'Libraries' heading.


### Add connection to Postgres Database
1. Go to View -> Tool Windows -> Database.
1. Press '+' -> 'Data Source' and select PostgreSQL.
1. Enter:
    1. Host: localhost
    1. Port: 5432 
    1. User: postgres 
    1. Password: admin 
    1. Database: postgres
    1. URL: "jdbc:postgresql://localhost:5432/postgres?currentSchema=attendance_management_system"
1. Press Apply and Ok.
1. From the Database panel select 'postgres@localhost' and select 'Edit Source' icon.
1. Paste, in the source file, the text from 'attendance_management_system.sql' file from appendix. 
1. Select everything, press right click and select 'Execute' or use the shortcut 'CTRL/CMD + Enter'


### Run the application
1. Firstly, run the 'MainServer' class from 'AttendanceManagementSystem (Server)' module.
1. Run one or multiple instances of 'MainClient' class from 'AttendanceManagementSystem (Client)' module.


#### Run client on different computer on the same network.
1. Load the 'AttendanceManagementSystem (Client)' module.
1. Make sure the server is running and all the libraries are imported.
1. Run the 'MainClient' class.
