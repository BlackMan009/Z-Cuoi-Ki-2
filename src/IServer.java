import java.rmi.*;

public interface IServer extends Remote{
	public String add(Model model) throws RemoteException;
	
	public String edit(Model model) throws RemoteException;
	
	public String del(String Username) throws RemoteException;
}
