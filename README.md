# InteractiveConversationalAgent
**Purpose:**
The Interactive Conversational Agent allows an individual to hold a conversation of at least 30 turns. The agent is a celebrity and the user can be anyone. The celebrity chosen is Kanye West. The conversation will be primarily question/answer based, but there may be certain specific responses for some non-question statements. The responses from the chat agent is a collection of tweets and song lyrics by Kanye West himself.

## Class Organization

**Breakdown of the classes:**
* BDialog: This class is responsible to create a Graphical User Interface for the conversation between the user and the Chatbot. 
* Conversation: This class is responsible to communicate and transfer user inputs and chatbot outputs. 
* YeBot: YeBot is the main class for the interaction between a user and the chatbot. 

## How to Compile and Run the Code
**Enter the following code into command line to run Yebot:**
* javac BDialog.java Conversation.java YeBot.javajava YeBot

**Or run the yebot.java file**


## Built With

* [Java](https://www.java.com/) - Programming language 
* [AIML](https://www.tutorialspoint.com/aiml/) - AIML dialogue
* [RiTa](https://rednoise.org/rita/) - RiTa API for WordNet

## Features implemented in A3

* Chat Bot GUI
The bot has a simple GUI for a better user experience. This allows the 
user to see a recent chat history. Fixed a bug from A2 that had 2 windows popup making it hard to type as well as some minor aesthetic changes.

* Added extra topics and dialogues for yebot
YeBot is a bot that allows you to talk to the celebrity Kanye West and I’ve added more dialogue options to his repertoire as well as more and different ways of user input being recognized.

* Added a few more reasonable responses for unknown inputs (including a dynamic one that includes a relevent word from the user input)
Added a few more responses for unknown input to make it feel a bit more 
Real. I’ve also added a dynamic unknown response using POS Tagging. 
The  default responses include:
  "wish i could help   i dont know what that means"
  "you got good vibes   but i dont know what to say to that"
  "yo man you gotta slow down   maybe try saying that a different way"
  "maan what are you on about"
  "i dont like where this is going   you need to stop   ye dont like"
  "i dont know about anything about (noun)s " **POS Tagging
  
* Added Synonym recognition for user inputs
I’ve added synonym recognition using the WordNet dictionary and RiTa API. this will check the users input, if there is not already a good response, and will see if there’s a synonym that will make a good response from the bot.

* Added POS Tagging for better responses
Used POS Tagging to find nouns as I found nouns are a good way to feel 
out what the user is trying to say. I’ve mentioned how the bot uses it to 
make a default response. However, it is also used to find a noun in the 
input to see if the bot has an even better response for that noun. In the 
example below POS found ‘rap’ as a noun and check if YeBot had a 
response for ‘rap’.




