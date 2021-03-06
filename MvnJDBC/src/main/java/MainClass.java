import java.sql.*;
import java.util.*;

public class MainClass {
	
	private ArrayList<Automobil> masini;
	
	MainClass(){
		masini = new ArrayList<Automobil>();
		try {
			Connection mcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/alg", "root", "kroot");
			Statement mystmnt = mcon.createStatement();
			ResultSet myRes = mystmnt.executeQuery("select * from Masini");
			while(myRes.next()) {
				Automobil auto = new Automobil();
				auto.setId(myRes.getInt("ID"));
				auto.setMarca(myRes.getString("Marca"));
				auto.setModel(myRes.getString("Model"));
				auto.setPutere(myRes.getDouble("Putere_hp"));
				auto.setPret(myRes.getDouble("Pret_$"));
				masini.add(auto);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void secvential(int id) {
		int count = 1;
		for(Automobil i : masini) {
			if(i.getId() == id) break;
			count++;
		}
		if(count == masini.size())
			System.err.println("elementul cu id " + id + " nu a fost gasit");
		else System.out.println("segvential >> id = " + id + " " + count + " pasi");
	}
	
	public void binarySearch(int id) {
		int count = 1;
		int stg = 0;
		int drt = masini.size() - 1;
		if (id > drt) {
			System.err.println("nu exista");
			return;
		}
		int mij = (stg + drt)/2;
		while(stg <= drt && masini.get(mij - 1).getId() != id) {
			mij = (stg + drt)/2;
			if (masini.get(mij - 1).getId() < id) {
				stg = mij + 1;
			}
			else {
				drt = mij - 1;
			}
			count++;
		}
		if(count == masini.size())
			System.err.println("elementul cu id " + id + " nu a fost gasit");
		else
				System.out.println("binara >> id = " + id + " " + count + " pasi");
			
	}
	
	public void afisare() {
		for(Automobil i : masini) {
			System.out.println("id >> " + i.getId());
			System.out.println("model >> " + i.getModel());
			System.out.println("marca >> " + i.getMarca());
			System.out.println("puterea masinii >> " + i.getPutere());
			System.out.println("pretul masinii >> " + i.getPret());
		}
	}
	
	public ArrayList<Automobil> getList(){
		return masini;
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		MainClass mc = new MainClass();
		System.out.println("Id-ul elementului cautat:");
		int id = scanner.nextInt();
		if (!(mc.getList().isEmpty())) {
			//mc.afisare();
			System.out.println();
			mc.secvential(id);
			System.out.println();
			mc.binarySearch(id);
		scanner.close();
		}
	}
}
