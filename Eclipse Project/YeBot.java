import java.util.*;
import org.alicebot.ab.*;
import java.io.File;
import rita.*;
/**
 * YeBot is the main class where the conversation is held.
 */
public class YeBot {

	static Conversation conversation;	
	public static Bot yebot;
	public static ArrayList<String> unknownResponse;
	public static Chat session;
	public static RiWordNet rw;

	public static void main(String[] args) {
		//initialize
		//RiTa is my api for WordNet
		//WordNet is required to be install in this directory
		rw = new RiWordNet("C://Program Files (x86)//WordNet//2.1//dict");//directory for wordnet 2.1
		rw.randomizeResults(false); //do not randomize rita results
		
		String dir = new File(".").getAbsolutePath();
		System.out.println(dir.substring(0,dir.length()-2));
		MagicBooleans.trace_mode = false;
		yebot = new Bot("YeBot",dir.substring(0,dir.length()-2));
		yebot.writeAIMLFiles();
		
		//Boolean bk; //break boolean -- if true break out of both loops
		
		//arraylist of unknown responses
		unknownResponse = new ArrayList<String>();
		unknownResponse.add("wish i could help   i dont know what that means");
		unknownResponse.add("you got good vibes   but i dont know what to say to that");
		unknownResponse.add("yo man you gotta slow down   maybe try saying that a different way");
		unknownResponse.add("maan what are you on about");
		unknownResponse.add("i dont like where this is going   you need to stop   ye dont like");
		
		session = new Chat(yebot);
		conversation = new Conversation();

		String input = "test";
		String output;
		int i = 1;
			
		while(!conversation.isContained(input)){
			String noun = "";
			input = null;
			input = conversation.recieveInput();
			//System.out.println(input);
			//bk = false;
			if (input!=""&&input!=null&&input.length()>1||i==1) {
				if(input==""||input==null||input.length()<1) {
					//start conversation
					output = conversation.response("Ye is in the BUILDING!");
					i=0;	
				}
				else if(conversation.isContained(input)) {
					//user calls for exiting the conversation
					try {
						Thread.sleep(500);
						output = conversation.response(session.multisentenceRespond(input));
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				else {
					
					String response = getResponse(input);
					output = conversation.response(response);
				}
			}	
		}
		System.exit(1); 	//This statement terminates the program	
		

	}
	/* if we get to a point where yebot does not understand the user's input we should
	 * check for synonyms to see if yebot understand a different version of the input
	 * and if that check is successful then we should output yebots response to that
	 * otherwise if we still dont get a response we find a noun in the statement to see
	 * if yebot has a response for that noun and if not yebot will use that nouns to make
	 * the chat seem more fluid by saying "i dont know anything about "+noun"'s" 
	 * */
	public static String getResponse (String input){
		String response = session.multisentenceRespond(input);
		
		if (!unknownResponse.contains(response)){ //if is a known phrase continue as normal 						
			response = session.multisentenceRespond(input);
			}
		else { //check for synonyms
			String[] words = input.split(" ");
			outerloop:
			for (int i = 0; i<words.length; i++){ //loop through each word and check for synonyms that match
				String temp = words[i];
				String[] synonyms = rw.getAllSynonyms(words[i],"n");
				for (int j = 0; j<synonyms.length; j++){
					words[i] = synonyms[j];
					String test = String.join(" ", words);
					System.out.println(test);
					response = session.multisentenceRespond(test);
					if (!unknownResponse.contains(response)){
						//if we find an a good response break the loops
						break outerloop;
					}
				}
				words[i]=temp;
			}
			//we find a noun then check that nouns synonyms to see if we have an appropriate
			//response for that noun
			if(unknownResponse.contains(response)){
				words = input.split(" ");
				outerloop:
				for (int i = 0; i<words.length; i++){
					String[] pos = rw.getPos(words[i]);
					for (int j = 0; j<pos.length; j++){
						if (pos[j].equals("n")){
							String[] synonyms = rw.getAllSynonyms(words[i], "n");
							response = session.multisentenceRespond(words[i]);
							if (!unknownResponse.contains(response)){
								break outerloop;
							}
							for (int k = 0; k<synonyms.length; k++){
								response = session.multisentenceRespond(synonyms[k]);
								if (!unknownResponse.contains(response)){
									break outerloop;
								}
							}
						}
							
					}
				}
			}
			//if we still get an unknown response after checking for synonyms
			//then we should look for a noun in the statement and have yebot tell the user
			// they don't know anything about that to make the conversation more fluid
			if(unknownResponse.contains(response)){
				words = input.split(" ");
				for (int i = 0; i<words.length; i++){
					String[] pos = rw.getPos(words[i]);
					for (int j = 0; j<pos.length; j++){
						if (pos[j].equals("n"))
							response = "i dont know about anything about "+words[i]+"s ";
					}
				}
			}
		}
		System.out.println(response);
		return response;
		
	}
}
