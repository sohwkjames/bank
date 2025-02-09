### Requirements

1. Java. You can check if Java is installed by running `java --version`. I'm using openjdk 21.

2. Apache Maven. Check if maven is installed by running `mvn -version`. I'm using 3.9.6.

3. You should have git installed.

### Instructions to run the program

1. Clone the repository. Run `git clone git@github.com:sohwkjames/bank.git`. If that doesnt work, try `git clone https://github.com/sohwkjames/bank.git`

2. `cd bank` to change into the newly cloned repository.

3. To compile and run the app, run `mvn compile exec:java -Dexec.mainClass="com.example.bank.Main"`

4. To run all tests, run `mvn test`.

### Progress

Features that are yet to be implemented

- Computing interest

- Prettify the printing of interest rules and transcations when adding new transaction/rule

- Generating account statement with interest and rolling balance for year month

Known bugs to fix

- Need validation on transactionType

### Design and architecture

https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=bank%20app%20architecture#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1N2nyAzKL0l3MG1IR0FAwcEdS5WwuUsNQ%26export%3Ddownload
