import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerIMP extends UnicastRemoteObject implements IServer {

	private Dao dao;

	protected ServerIMP() throws RemoteException {
		super();
		this.dao = new Dao();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String add(Model model) throws RemoteException {
		return dao.add(model);
	}

	@Override
	public String edit(Model model) throws RemoteException {
		return dao.edit(model);
	}

	@Override
	public String del(String Username) throws RemoteException {
		return dao.del(Username);
	}

}
