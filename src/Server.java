import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String[] args) {
		try {
			IServer iServer = new ServerIMP();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/db", iServer);
			System.out.println("Server is running now .....");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server Error");
		}
	
	}
}
