# How to use Mongo DB on Java

## Technical

- Java
- Eclipse
- Gradle
- MongoDB

## Step

- Download the [MongoDB-Driven](https://mongodb.github.io/mongo-java-driver/)
- add code on `build.gradle`

      dependencies {
          compile 'org.mongodb:mongodb-driver:3.4.2'
      }

Meet Problem `Could not Find or Load Main class`

- Add code on `build.gradle` to specific `main class`

      apply plugin: 'application'
      mainClassName = 'system.TakeLeave'

- Eclipse set MongoDB
  - Download `mongodb-driver.jar`, and copy to `Project/libs`
  - Go to buildpath, add mongodb-driver to project, on Eclipse

## Doc

- [MongoDB concept](http://www.runoob.com/mongodb/mongodb-databases-documents-collections.html)
- [Offical](https://docs.mongodb.com/manual/introduction/)

### Use it

- Install `sudo apt-get install mongodb`
  - Run `mongo`
- Show all DB `show dbs`
  - Current DB `db`
  - Use another DB `use <dbName>`
- DB naming
  - cannot empty `''`
  - cannot include `'', space, . , $, /, \, 0`
  - should small letter `abcd`
  - maximum letter 64

- Create `collection`
  - `db.createCollection()`
  - [Link](https://docs.mongodb.com/manual/reference/method/db.createCollection/#db.createCollection)


        db.createCollection(<name>, { capped: <boolean>,
                                autoIndexId: <boolean>,
                                size: <number>,
                                max: <number>,
                                storageEngine: <document>,
                                validator: <document>,
                                validationLevel: <string>,
                                validationAction: <string>,
                                indexOptionDefaults: <document>,
                                viewOn: <string>,
                                pipeline: <pipeline>,
                                collation: <document> } )

create

    db.createCollection("leave",
      {
          name: {$type:"string"},  
          dateFrom:{$type:"date"},
          dateEnd:{$type:"date"}
      }
    )

insert

    db.leave.insert({name:"william", dateFrom:"2016-3-3", dateEnd:"2016-3-5"})
    db.leave.insert({name:"john", dateFrom:"2016-4-3", dateEnd:"2016-4-5"})

read

    db.leave.find()

delete collection

    db.leave.drop()

## [Update Docuement](http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/)

use the newest API.

like `MongoClient` and `MongoCollection`.
