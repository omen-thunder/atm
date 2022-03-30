# :atm: Kings ATM 
> Top Secret ATM Project

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

</details>

## Project Details - Agile Software Development Practices (SOFT2412/COMP9412)

**School of Computer Science**  
**Group Project Assignment 1 – Tools for Agile Software Development**

### :family: Members

- bkim6426 - Brian Kim
- cmcb5122 - Cameron McBroom
- cyip9287 - Ricky Yip
- sare9443 - Shane Areni
- sdar2231 - Sandeep Darapuneni
