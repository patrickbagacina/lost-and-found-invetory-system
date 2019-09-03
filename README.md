# Lost and Found Inventory System

* Inventory system for keeping track of lost items.
* Add item with specific category. 
* Redeem item once the owner arrives.
* Keep track of the current items in storage and all redeemed items.

### Required
* Java 8
* Gradle v4.4.1
* Angular 6
* PostgreSQL Tool (pgAdmin)

### Configuring Server app
* Create a database in Postgres with the following details:
- database name: `laf`
- username: `postgres`
- password:

### Running Server app using Gradle
* Go to server folder via Terminal `cd server`.
* Run gradle command `gradle bootRun`.
* If the command is successful, server app should be runnning on port 8080.

### Running Client app
* Go to client folder via Terminal `cd client`.
* Install dependencies by running `npm install`.
* Run angular app `ng serve`.
* If the command is successful, client app should be runnning on port 4200.
* Go to `localhost:4200` on your browser.

