# COSC 516 - Cloud Databases<br/>Lab 3 - Google Cloud Bigtable

## Setup

Create a Google Cloud free tier account at: [https://cloud.google.com/free](https://cloud.google.com/free).

The free tier account allows for free trials for certain products and $300 in free credits for new users. In a Canvas announcement there is also the option to get an additional $50 USD credit for use with this course. You will need an email address and credit card to sign up. 

## Google Cloud Portal

Login to Google Cloud. In the Google Cloud Portal, search for `Bigtable`, click on `Bigtable` then `Create Instance`.

![Google Cloud Portal](img/1_google_cloud_portal.png)

## Create Google Cloud Bigtable Instance

Click on `Create Instance`. Given your instance a name.

<img src="img/2a_create_instance_name.png" alt="Create Google Bigtable Instance with a Name" width="800">

Select `SSD` as the storage type.

<img src="img/2b_create_instance_ssd.png" alt="Use SSD Storage" width="800">

The cluster id should be automatically assigned, but you may change it. The cheapest region is `us-central1 (Iowa)`. It is also possible to use `northamerica-northeast2 (Toronto)`.

<img src="img/2c_create_instance_cluster.png" alt="Create Cluster with One Node" width="1000">

**The cost of a 1 node cluster is $.70 USD per hour. Make sure you destroy cluster as soon as you are complete and only have a cluster active while you are working on the assignment.**

## Connecting to the Instance

Connecting to the database can be done using the cbt command-line tool or using a Bigtable client library. Google Cloud Bigtable is not a relational database and is **NOT** accessible using SQuirreL or other SQL tools.

### Accessing using cbt command-line tool

The cbt command-line interface allows performing basic administrative tasks and reading/writing data from tables. There is a [tutorial on cbt CLI](https://cloud.google.com/bigtable/docs/create-instance-write-data-cbt-cli?_ga=2.111890764.-913511634.1664467746).

### Accessing using Client Library

The lab will use the Java client library. An example code file called `SampleBigtable.java` is in the lab. This sample creates a table, writes data, reads data, then deletes the table. There is [more information on this "Hello world" example](https://cloud.google.com/bigtable/docs/samples-java-hello-world).

For setup, follow [these instructions](https://cloud.google.com/docs/authentication/provide-credentials-adc).

You will need to install the Google Cloud CLI then run the command: `gcloud auth application-default login`.

## Tasks

To test your database, write Java code using VS Code. The file to edit is `Bigtable.java`.  The test file is `TestBigtable.java`.  Fill in the methods requested (search for **TODO**).  Marks for each method are below.  You receive the marks if you pass the JUnit tests AND have followed the requirements asked in the question (including documentation and proper formatting).

- +1 mark - Write the method `connect()` to create a connection. Create a Bigtable data client and admin client. See [SampleBigtable.java](SampleBigtable.java) for starter code.
- +1 mark - Write the method `createTable()` to create a table to store the sensor data.
- +5 marks - Write the method `load()` to load the sensor data into the database. The data files are in the [data](src/data) folder. 
- +3 marks - Write the method `query1()` that returns the temperature at Vancouver on 2022-10-01 at 10 a.m.
- +3 marks - Write the method `query2()` that returns the highest wind speed in the month of September 2022 in Portland.
- +3 marks - Write the method `query3()` that returns all the readings for SeaTac for October 2, 2022.
- +4 marks - Write the method `query4()` that returns the highest temperature at any station in the summer months of 2022 (July (7), August (8)).

**Total Marks: 20**


## Bonus Marks: (up to 2)

Up to +2 bonus marks for demonstrating some other feature of Google Cloud Bigtable or writing your own unique query and test case.

## Submission

The lab can be marked immediately by the professor or TA by showing the output of the JUnit tests and by a quick code review.  Otherwise, submit the URL of your GitHub repository on Canvas. **Make sure to commit and push your updates to GitHub.**

