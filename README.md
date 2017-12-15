# Plotkoin Server

## About
The purpose of this server is to simulate a real block chain for interaction with student projects

 ## Example Plotkoin Transaction
 `{"id":1, "sender":661224649, "receiver":661224648, "amount":1, "message":"Here you go", "data":"al;ksdjflkasdjflkjasd;fkljasldfaskjdf"}`

## Endpoints
 - `/blocks` A GET request made to view the entire blockchain
 - `/send` A POST request made to simulate sending an amount of Plotkoin
 - `/recv` A POST request made to simulate receiving an amount of Plotkoin. This should be the first request you make as you get your initial amount of Plotkoin via this command.  