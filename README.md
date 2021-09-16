# :atm: Kings ATM 
> Top Secret ATM Project

### Project Links
[:clipboard: Trello](https://trello.com/invite/b/GH4QKB36/9b4734b33bfc7c8f13887f5fe98c9b3c/kings-atm) 
[:man_singer: Jenkins](https://kingsatm-jenkins.codepass.dev)
[:rocket: Production App](https://kingsatm.codepass.dev)
[:martial_arts_uniform: Staging App](https://kingsatm-staging.codepass.dev)

## How to run the application locally

<details>
   <summary> <strong> :elephant: Run with gradle </strong> </summary>

**Requirements**

- Postgres datbase installed locally
- Postgres database with the name `kingsatm`
- `kingsatm` database set up with credentials `client:client`
- Postgres exposed on localhost:5432
- Gradle installed (or use graddle wrapper)

You can run `./gradlew bootRun` and this will start the project locally and can
be accessed at `localhost:7000`. This will build the frontend, backend, run tests
and then start a localserver.

</details>

<details> 
   <summary> <strong> :sparkles: Run the frontend server (better for development) </strong> </summary>
   
   **Requirements**

   - NodeJs installed locally
   - Npm installed locally
   
In the terminal run `cd /frontend` , then run `npm run serve`. This will start a local development
sever on port **3000**. When you make changes to the frontend, this will automatically recompile on save to update realtime


</details>

<details> 
   <summary> <strong>:whale: Run with Docker </strong> </summary> 
   
**Requirements** 

- [Docker Desktop (Windows/Mac)](https://www.docker.com/get-started) / [Docker Engine (Linux)](https://hub.docker.com/search?offering=community&operating_system=linux&q=&type=edition)
- [docker-compose](https://docs.docker.com/compose/install/)

1. At the project root folder, where `docker-compose.yml` is located, run `docker-compose up -d` .
2. Run `docker ps` to see running containers. You should see a container with the image name `*_kingsatm` .
3. The app is available at `http://localhost:8090` .

To stop the container, run `docker-compose down` .

## Project Details - Agile Software Development Practices (SOFT2412/COMP9412)

**School of Computer Science**  
**Group Project Assignment 1 – Tools for Agile Software Development**

### :family: Members

- bkim6426 - Brian Kim
- cmcb5122 - Cameron McBroom
- cyip9287 - Ricky Yip
- sare9443 - Shane Areni
- sdar2231 - Sandeep Darapuneni


<details> 
   <summary><strong>Background</strong></summary>

Agile software development is centered around teams and teamwork. In Agile team environments, a set of practices and principles should be followed to guide teams through the development process to build quality software products efficiently and effectively. Implementing Agile practices and principles in a software development project require developing skills that fall in two main areas namely, technical tooling, and social interactions. In this unit, you are required to work in Agile software development teams to experience and develop both technical and social skills.

The goal of the first group project assignment is to work as a team and to use and apply Agile software development tools in the context of a software development project.

</details>


### Main Requirements

<details> 
   <summary> <strong> Overview </strong> </summary> 

In this group project, teams of 5 students (must be enrolled in the same lab) will be required to build an ATM software application in Java. All teams are required to use this software project as the starting point of their work. As a team, you will be required to develop specific features/functionalities of the ATM software application.

Following Agile practices, each team is required to work collaboratively and carry out software development activities to build the ATM software application. During the development, each team is required to use the set of software tools covered in this course following Agile development practices. Below are more details of the project requirements and deliverables for the assignment.

</details>

<details> 
   <summary> <strong> :hammer_and_wrench: Part 1 - Agile Tools </strong> </summary> 

Each group must collaboratively work to setup a set of software tools that will help them to prepare to prepare agile development environment. All these tools have been/will be introduced in the weekly labs including GitHub, Gradle, JUnit (including code coverage) and Jenkins. In your tool setup, you will need to meet the following requirements:

- [x] There must be a single shared GitHub repository in the supplied [GitHub organization](https://github.sydney.edu.au/SOFT2412-2020S2) for the entire group. Every member must contribute to this repository.
- [ ] Gradle must be used for build automation, and JUnit for automated testing. JUnit must be integrated with Gradle and a code coverage tool. For JUnit testing, code coverage must be at least 75%.
- [x] Continuous integration should be done with Jenkins, which must be hooked with GitHub. Both Gradle and JUnit must be integrated on Jenkins and run automatically for every new commit in a particular GitHub branch after Jenkins has been setup correctly.
- [x] Jenkins must be integrated with GitHub via webhooks. Also, ngrok can be used for continuous integration with Jenkins. Alternatively, Jenkins may be hosted on public cloud service providers if your team is happy with that solution. Jenkins must show test reports for the application. **Only one member is required to setup Jenkins.**
- [x] It is recommended to use an IDE like IntelliJ or Eclipse, whatever your group feels comfortable with, to assist you throughout the software development.

The tool setup might become more challenging. So, it is crucial that every group members participate and practice all exercises in the tutorials (week 2 – week 6).

</details>


<details> 
   <summary> <strong> :atm: Part 2 - Automated Teller Machine (ATM) software for XYZ Banks </strong> </summary> 

Each group will be required to develop a simple ATM software in JAVA. All team members must collaboratively build this application using the agile tools they have setup in the previous part. All teams will have to implement following application requirements:

1. The XYZ Bank Inc. can have many automated teller machines (ATMs), and the new software system shall provide functionality on all ATMs.
2. The system shall enable the customers of XYZ Bank, who have valid ATM cards , to perform three types of transactions:
   1. Withdrawal of funds
   2. Deposit of Funds
   3. Balance Check
3. The ATM should allow the user to enter the card number (5 digits) which should be validated against a list of valid cards.
4. An ATM card usage shall be considered valid if it meets the following conditions:
   1. The card number entered by the user is valid
   2. The card is used after the start date, i.e., the date when the card was issued.
   3. The card is used before the expiration date, i.e., the date when the card expires.
   4. The card has not been reported lost or stolen by the customer, who had been issued that card.
   5. The customer provides correct personal identification number (PIN), which matches the PIN maintained by the system.
5. The system shall confiscate the ATM card if it detects that a lost or stolen card has been inserted by a customer. The system shall also display an apology to the customer
6. The system shall allow the customer to enter the correct PIN in no more three attempts. The failure to provide correct PIN in three attempts shall result in the ATM card being blocked.
7. The system shall ask for the transaction type after satisfactory validation of the customer PIN only. The customer shall be given the three options: Withdraw money, deposit money or balance check.
8. If a customer selects Withdraw money, the system shall prompt the customer to enter the amount to be dispensed. **The ATM has no restriction on the amount to be dispensed and dispenses both coins and notes.**
9. If a withdrawal transaction is approved, the requested amount of cash shall be dispensed, a receipt shall be printed containing information about the transaction, and the card shall be ejected. The information printed on the receipt includes transaction number, transaction type, amount withdrawn, and account balance
10. The system shall also check if there are enough funds available in the account and if there are insufficient funds should display the available account balance
11. If a customer selects Deposited money, the system shall prompt the customer to enter the amount to be deposited. **The ATM can deposit Australian notes and no coins can be deposited.**
12. If a deposit transaction is approved, the requested amount of cash shall be deposited, a receipt shall be printed containing information about the transaction, and the card shall be ejected. The information printed on the receipt includes transaction number, transaction type, amount deposited, and account balance.
13. If a customer selects Check Balance, the account balance should be displayed on the screen.
14. The system shall cancel any transaction if it has not been completed if the user selects the Cancel option
15. The system should also ATM administrators to perform routine maintenance by adding cash in the system.
16. If the ATM has insufficient cash available, it should provide an error message and the transaction should be cancelled

The application design can be using any UI (text or GUI) for the functionality. You can decide on the application design/architecture; both text file or persistent database are acceptable. The software must always produce correct output and maintain correct and consistent state of all included entities.

</details>


<details> 
   <summary> <strong> :page_facing_up: Part 3 - Technical Report </strong> </summary> 


Each group must prepare a technical report that record evidence of the above development activities (besides the actual Github repo. logs and reports which will be checked as part  of the marking). This specifically includes:

1. Explain how the group collaborated to complete the development of the Application. This should include individual and group contributions, group communication (recorded minutes for all meetings). Each individual team member must explain how they contributed towards implementing the features of the application.
2. A README file or a page in the GitHub wiki explaining how to run the program, and how to test it. You may also include other instructions as to how to contribute/collaborate on the codebase.
3. GitHub collaboration. You must explain how GitHub was used for building the application. This might include Project boards, issues, pull requests etc. and how they were used in favor of completing the implementation of the application.
4. Explain how Gradle was used. This may include any extra tasks/dependencies used for the application including brief comments on how those extra tasks/dependencies helped you build the application.
5. Code coverage report for the final commit, including JUnit results for the commits. All Junit test cases that are written must be documented and explained (i.e. why the unit test was chosen and what does it test (regular input, edge cases etc). Document any tests that may have failed. You will also need to explain what the test coverage report is displaying.

</details>

<details> 
   <summary> <strong> :sparkles: Project Demonstration </strong> </summary> 

Each group must demonstrate their project work. You will have to demo your group work and individual contributions to your tutor.
More details about the demos will be announced prior to the submission deadline.

</details>


<details> 
   <summary> <strong> :heavy_check_mark: Marking Details </strong> </summary> 

## Group Member Contribution

If members of your group do not contribute sufficiently you should alert your tutor as soon as possible. The course instructor has the discretion to scale the group’s mark for each member as follows:

Level of Contribution|Proportion of final grade received
:-----:|:-----:
No contribution |0%
poor/partial contribution  | 1% - 49%
Partial/poor contribution | 50% - 99%
Full contribution | 100%

Each team must submit their technical report as PDF (__one submission per group__) through the provided link this Canvas page. All group members must sign the [assignment coversheet](https://canvas.sydney.edu.au/courses/36380/files/18516331/download?wrap=1) and attach it as the first page of the technical report. (reports that does not include the signed assignment cover sheet will not be marked). Turnitin will be used to check similarity with resources. You may need to reference any information you may use from other resources.

Each team must submit their latest source code version of the Automated Teller Machine (ATM) application as zip file with all your project files (a separate submission link will be supplied prior to the deadline).  A specialized software might be used to check code similarity.


## Marking Guide

The marking criteria will be based on the above requirements. A marking guide will be added once the details of the project demos are finalized.

</details>
