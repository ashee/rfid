# RFID
A spring boot app to optimize clinical workflow using RFID technology

# Introduction
This app takes http posts from the ThingMagic RFID reader and persists them in a MySQL database. The reader software invokes
a service to write events every n seconds. Each event is a tuple
consisting of(readerName,EPC,timeStamp,peakRSSI,readCount).

From these raw events, presence and co-presence information 
is derived for patient subjects and providers.

## Development Environment Setup
* [Install jdk 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Install Spring Tool Suite (STS)](https://spring.io/tools/sts/all)
* [Install mysql 5.7.13](http://dev.mysql.com/downloads/mysql/)
* Install git

### Git Installation

Windows - [https://git-scm.com/download/win](https://git-scm.com/download/win)

OSX - Use [homebrew](http://brew.sh/) followed by $ brew install git

[Gui Tool](https://desktop.github.com/)

Please note that STS includes a git tool within the IDE. See [http://www.eclipse.org/egit/](http://www.eclipse.org/egit/) for additional documentation.

## Create rfid database after mysql setup
Invoke the following scripts in sequence
$ mysql < src/main/db/createdb.sql 
$ mysql -u rfid -p < src/main/db/schema.ddl.sql 
It will prompt for a password. Please configure 
your mysql password and update it in the following
files 
* src/main/db/createdb.sql
* src/main/resources/application.properties

Note that JPA is configured to autocreate &
update the application database schema.
