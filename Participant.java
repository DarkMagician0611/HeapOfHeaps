//Class containing details about participant
public class Participant{
	public String ID; 
	public String participantName; 
    public String universityName;

	public Participant(String id, String name, String univ){
		ID = id;
		participantName = name;
		universityName = univ;
	}

	public String toString(){//String equivalent of object of Participant
		return universityName;
	}

	public boolean equals(Object o) {//To check whether two objects are equal or not
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        if(o instanceof String){
            String s = (String) o;
            return s.equals(ID);
        }

        if (!(o instanceof Participant)) {
            return false;
        } 
        // typecast o to Participant so that we can compare data members 
        Participant c = (Participant) o;
         
        // Compare the data members and return accordingly 
        return c.ID.equals(ID);
    }
}