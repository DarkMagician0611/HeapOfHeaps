//This class is responsible for maintaining heap of event heaps
public class Rendezvous{
	private Heap<Event> R;
	private int size;

	public Rendezvous(){
		R = new Heap<>();
		size = 0;
	}

	public void addEvent(Event E){//Add an event heap to the heap of heaps
		R.insert(E.highScore(), E);
		size++;
	}

	public void deleteEvent(Event E){//Removes an event E from the heap of heaps
		if(R.deleteValue(E))
			size--;
	}

	public void deleteParticipant(Participant P){//Deletes the participant P entirely from all events in the heap of heaps
		if(size == 0){
			return;
		}
		Event[] Es = new Event[size];
		int i = 0;
		while(!R.isEmpty()){
			Es[i] = R.removeMax();
			Es[i].deleteParticipant(P);
			i++; 
		}
		for(i = 0;i < size;i++){
			R.insert(Es[i].highScore(),Es[i]);
		}
	}

	public int noOfEvents(){//Returns the number of events in the heap of heaps
		return size;
	}

	public void topThree(){//Returns the top three performer across all events in the heap of heaps
		int i, j, count = 0;
		int[] score = new int[3];
		Event[] events = new Event[3];
		for(i = 0;i < 3;i++){
			score[i] = -1;
		}
		i = 0;
		Event[] Es = new Event[3];
		Participant[] Ps = new Participant[3];
		int[] inserts = new int[3];
		while(i < 3 && !R.isEmpty()){
			Es[i] = R.removeMax();
			j = i;
			count = 0;
			while(j < 3 && !Es[i].E.isEmpty()){
				if(score[j] == -1){
					score[j] = Es[i].E.max();
					Ps[j] = Es[i].E.removeMax();
					inserts[count++] = j;
					events[j] = Es[i];
				}
				else if(score[j] < Es[i].E.max()){
					if(j < 2){
						score[j+1] = score[j];
						Ps[j+1] = Ps[j];
						events[j+1] = events[j];
					}
					score[j] = Es[i].E.max();
					Ps[j] = Es[i].E.removeMax();
					inserts[count++] = j;
					events[j] = Es[i];
				}
				j++;
			}
			for(j = 0;j < count;j++)
				Es[i].E.insert(score[inserts[j]], Ps[inserts[j]]);
			i++;
		}

		for(j = 0;j < i;j++){
			R.insert(Es[j].highScore(), Es[j]);
		}

		for(i = 0;i < 3 && score[i] != -1;i++){
			if(score[i] == 0)
				break;
			System.out.println(Ps[i].ID + ", " + Ps[i].participantName + ", "
				+ Ps[i].universityName + ", " + events[i].ID + ", "
				+ events[i].eventName + ", " + score[i]);
		}
	}

	public void topThreeEvents(){//Returns top three participants from different events in the heap of heaps
		int i = 0;
		int[] score = new int[3];
		Participant[] P = new Participant[3];
		Event[] Es = new Event[3];
		while(i < 3 && !R.isEmpty()){
			score[i] = R.max();
			Es[i] = R.removeMax();
			if(score[i] == 0){
				i++;
				break;
			}
			P[i] = Es[i].E.removeMax();
			System.out.println(P[i].ID + ", " + P[i].participantName + ", "
				+ P[i].universityName + ", " + Es[i].ID + ", "
				+ Es[i].eventName + ", " + score[i]);
			Es[i].E.insert(score[i], P[i]);
			i++;
		}
		for(int j = 0;j < i;j++){
			R.insert(score[j], Es[j]);
		}
	}

	public Event find(String ID) throws NullPointerException{//Search for an event with ID as eventID in heap of heaps
		if(size == 0)
			return null;
		Event E = R.find(new Event(ID, "", ""));
		return E;
	}

	public void print(){//Prints the heap of heaps in preorder fashion
		R.print();
	}
}