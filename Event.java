//Class containing details about an event and heap of participants
public class Event{
	public String ID;
	public String eventName;
	public String eventDescription;
	public int noOfParticipants;
	public Heap<Participant> E;//Heap of participants

	public Event(String id, String name, String desc){
		ID = id;
		eventName = name;
		eventDescription = desc;
		noOfParticipants = 0;
		E = new Heap<>();
	}

	public void addParticipant(Participant P, int score){//To add a new participant to the heap
		E.insert(score, P);
		noOfParticipants++;
	}

	public void addParticipant(Participant P){//To add a new paricipant to the heap without score
		E.insert(0, P);
		noOfParticipants++;
	}

	public void updateParticipant(Participant P, int score) throws NullPointerException{//Updates the score of a given participant
		E.modifyKeyOfNode(P, score);
	}

	public void deleteParticipant(Participant P){//Deletes a specific participant
		if(E.deleteValue(P))
			noOfParticipants--;
	}

	public int highScore(){//Return the maximum score of participant in the event
		if(!E.isEmpty())
			return E.max();
		else
			return -1;
	}

	public void topThree(){//Top three performers in the event
		int i = 0;
		int[] score = new int[3];
		Participant[] P = new Participant[3];
		while(i < 3 && !E.isEmpty()){
			score[i] = E.max();
			P[i] = E.removeMax();
			if(score[i] == 0){
				i++;
				break;
			}
			System.out.println(P[i].ID + ", " + P[i].participantName + ", " + 
				P[i].universityName + ", " + score[i]);
			i++;
		}
		for(int j = 0;j < i;j++){
			E.insert(score[j], P[j]);
		}
	}

	public int getSize(){//Returns the number of participants in the event
		return noOfParticipants;
	}

	public boolean equals(Object o) {//To check whether two objects are equal or not
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }
         
        // typecast o to Event so that we can compare data members 
        Event c = (Event) o;
         
        // Compare the data members and return accordingly 
        return c.ID.equals(ID);
    }

    public void print(){//Prints the event heap in preorder fashion
    	E.print();
    }

    public String toString(){//Returns the string equivalent of Event
    	return eventName;
    }
}