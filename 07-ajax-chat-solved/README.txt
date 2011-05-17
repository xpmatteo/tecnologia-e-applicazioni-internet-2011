
== Summary ==

The objective is to build a simple chat service with Ajax.  It should have a single page, that contains

+---------------------------------+  /----\
| my message...                   |  | OK |
+---------------------------------+  \----/

+------------------------------------------+
| chat messages...                         |
| other messages ....                      |
| bla bla ....                             |
|                                          |
+------------------------------------------+

Clicking on the OK button sends a new message to the server with Ajax. The
server responds with a list of all current messages, that will replace the div containing all chat messages on the page.

Additional requirement: if there are more than one browsers connected to the chat service, I should see all messages from all browsers.

Additional requirement: the page should refresh the list of messages every two seconds, so that even if I don't type anything I should see the list updated with the messages typed by someone else.

== Where to start ==

There are two parts to this exercise.  The client interface part:

File templates/index.ftl is the main user interface.
File public/javascripts/chat.js should contain the main JavaScript behaviour

The server part:

You should implement a ChatController that talks to a ChatRoom object, shared by all incoming requests.  Remember to synchronize all its public methods.  The ChatController should respond to Ajax requests.  

0. Start by checking that the application works.  It should show the main user interface at "http://localhost:8080/" and return a 404 at "/chat".

1. Have the application return a static list of messages when I call http://localhost:8080/.

2. When I click on the OK button, there should be an Ajax call to the server that calls /chat and copies the messages to the #log user interface element.  This can be done with the $("#log").load jQuery method.

3. When I click on the OK button, there should be a "POST" call to "/chat" that sends the text of the message.  The result of the call should be a list of messages, to be copied over the #log element.  My last message should be on top.

4. If more than one browser are connected, they should each load everyone else's messages.

5. Every two seconds, the page reloads the data from the server.

6. Bonus points: use "etags" or other similar methods to avoid reloading the messages when there is nothing new to show.


== How to run ==

Execute with Eclipse the main function in ...chat.ChatMain then open your browser to http://localhost:8080


