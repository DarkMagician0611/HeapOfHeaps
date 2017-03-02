import java.io.*;

public class Simulate{
	public static Rendezvous Rz;
	public static SinglyLinkedList<Participant> S;

	public static void main(String[] args) {//This is the main function to read single query from a given text file
		try{
			Rz = new Rendezvous();
			S = new SinglyLinkedList<>();
			BufferedReader in = new BufferedReader(new FileReader("case_3.txt"));
			String query;
			while((query = in.readLine()) != null){
				perform(query);
			}
			in.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NullPointerException n){
			System.out.println("Illegal Query");
		}
	}

	public static void perform(String query) throws NullPointerException{//Execute a single query from the file
		String[] commands1 = query.split(", ");
		String[] commands2 = commands1[0].split(" ");
		int len = commands2.length + commands1.length - 1;
		String[] commands = new String[len];
		int i;
		for(i = 0;i < commands2.length;i++){
			commands[i] = commands2[i];
		}
		for(i = 1;i < commands1.length;i++){
			commands[commands2.length + i - 1] = commands1[i];
		}
		Participant P;
		Event Ev;

		if(commands[0].equals("ADD")){
			if(commands[1].equals("PARTICIPANT")){
				P = new Participant(commands[2], commands[3], commands[4]);
				S.addFirst(P);		
			}
			else if(commands[1].equals("EVENT")){
				Rz.addEvent(new Event(commands[2], commands[3], commands[4]));
			}
			else{
				P = S.find(commands[1]);
				Ev = Rz.find(commands[2]);
				if(Ev == null || P ==null)
					throw new NullPointerException();
				Ev.addParticipant(P);
				Rz.deleteEvent(Ev);
				Rz.addEvent(Ev);
			}
		}
		else if(commands[0].equals("DELETE")){
			if(commands[1].equals("PARTICIPANT")){
				P = S.find(commands[2]);
				if(P == null)
					throw new NullPointerException();
				Rz.deleteParticipant(P);
			}
			else if(commands[1].equals("EVENT") && commands[2].equals("PARTICIPANT")){
				Ev = Rz.find(commands[4]);
				P = S.find(commands[3]);
				if(P == null || Ev == null)
					throw new NullPointerException();
				Ev.deleteParticipant(P);
			}
			else{
				Ev = Rz.find(commands[2]);
				if(Ev == null)
					throw new NullPointerException();
				Rz.deleteEvent(Ev);
			}
		}
		else if(commands[0].equals("UPDATE")){
			P = S.find(commands[2]);
			Ev = Rz.find(commands[3]);
			if(P == null || Ev == null)
				throw new NullPointerException();
			Ev.updateParticipant(P, Integer.parseInt(commands[4]));
			Rz.deleteEvent(Ev);
			Rz.addEvent(Ev);
		}
		else if(commands.length > 1 && commands[1].equals("IN")){
			Ev = Rz.find(commands[3]);
			if(Ev == null)
				throw new NullPointerException();
			Ev.topThree();
		}
		else if(commands[0].equals("TOP3")){
			Rz.topThree();
		}
	}
}