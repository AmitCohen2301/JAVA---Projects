A server that serves as a superapp whose purpose is to respond to two applications that are miniapps in the field of flights and hotels.
To get flight and hotel data the server uses an api interface with tripadvisor.
The data is backed up in mongoDB, so the server must be run on the computer.
The server also manages a pool of users, each user having different permissions according to their type (admin, superapp, miniap).
