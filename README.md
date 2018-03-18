# Plotkoin Server

## About
The purpose of this server is to simulate a real block chain for interaction with student projects

 ## Example Plotkoin Transaction
 `{"id":1, "sender":661224649, "receiver":661224648, "amount":1, "message":"Here you go", "data":"al;ksdjflkasdjflkjasd;fkljasldfaskjdf"}`

## Endpoints
 - `/` A GET request made to view the entire blockchain
 - `/` A POST request made to get your initial Plotkoin. Post your RIN to receive Plotkoin.
 - `/validate` A POST request made to validate your initial Plotkoin. Post what you believe is the secret for each Plotkoin. Hint: It's your RIN scrambled
 - `/send` A POST request to send money to another user. The payload for this is a JSON version of Plotkoin without the data field.
 - `/clear` A GET request used to clear the database of all transactions. *REMOVE THIS BEFORE USING IN CLASS*
 
 ## Initialization
 Initialize the `template.application.properties` file with your own configuration variables so that the server can connect to your database. Any SQL database will work. PostgreSQL is recommended. When done rename this file to application.properties. You can find this file in `src/main/resources`. Once completed, you can initialize this like any Spring Boot program. It should import natively to Eclipse IDE or you can run in from the command line.