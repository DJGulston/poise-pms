# Poised App

The Poised App enables a user to add, update and search for information in a MySQL database relating to
specific real estate projects they are developing.

-----------------------------------------------------------------------------------------------------------

## Table Of Contents

1. What the project does
2. Why the project is useful
3. How to get started
4. Where to get help
5. Project contributors and maintainence engineers

-----------------------------------------------------------------------------------------------------------

## What the project does

The Poised App works with project data stored in a MySQL database on a user's local machine. This data
includes a project number, project name, building type, physical address of the project, ERF number, total
fee charged for the project, total amount paid to date for the project, project deadline date, finalised
status ID, finalised status, finalised date, architect details (full name, telephone number, email address
and physical address), contractor details (full name, telephone number, email address and physical address)
and customer details (full name, telephone number, email address and physical address).

The application enables the user to add, update or seek information in the local database. When updating
a project of their choice, the user may simply enter new values for most of the aforementioned attributes.
However, for the finalised status ID and finalised date, the user must choose to finalise a project of
their choice in order to update the finalised status ID and finalised date. A project is finalised when it
has been completed.

When seeking information, the user may simply find all project information by entering the project name
or project number into the program. However, a user may also seek projects that are overdue (i.e. projects
that are past the deadline date) and projects that have not yet been completed (i.e. unfinalised projects).

When adding new information, the user will simply add all information of their choice. If the user does not
add a project name, the project name will be created using a combination of the surname of the customer and
the building type. It must be noted that adding a customer name and building type is compulsory when adding
a new project to the database.

-----------------------------------------------------------------------------------------------------------

## Why the project is useful

The program may prove useful to non-technical individuals who wish to manage all real estate project
information in their database. A user can add, update and search for projects in their database without
having to write their own MySQL queries.

Note that some knowledge in Java and MySQL may be required to setup the program for use.

-----------------------------------------------------------------------------------------------------------

## How to get started

In order to use or make changes to this program, it is recommended that the latest JDK and latest version
of Eclipse IDE is installed. This program makes use of a MySQL database where the data is stored, and thus
the latest version of MySQL also needs to be installed on your local machine. As it stands, the program
interacts with a database called PoisePMS that stores five tables (i.e. Projects, Architects, Contractors,
Customers and Finalised). In order to create the database and its necessary tables, simply execute the
MySQL code in the PoisePMS.sql file. The PoisePMS.sql file also inserts two default project entries in the
Projects, Customers, Architects and Contractors tables. These entries may be removed if deemed necessary.
However, the two entries in the Finalised table should not be removed, as these entries are the only two
indicators for whether a project is finalised or not (i.e. 1 for Finalised and 0 for Unfinalised). These
two entries in the Finalised table should not be mistaken for default project entries.

The PoisePMS database is also accessed via a username and password. The username is "otheruser", and the
password is "swordfish". If not yet done so, create a new MySQL user with the aforementioned username and
password. If you prefer a different username and password, you may alter the username and password in the
PoisedApp.java file.

-----------------------------------------------------------------------------------------------------------

## Where to get help

If any help is required in relation to this project, contact the lead developer for this project, i.e. Dean
Gulston, via email at [deangulston12@gmail.com](mailto:deangulston12@gmail.com).

-----------------------------------------------------------------------------------------------------------

## Project contributors and maintainence engineers

[Dean Gulston](https://github.com/DJGulston)
