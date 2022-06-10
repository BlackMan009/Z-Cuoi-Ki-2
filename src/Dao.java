import java.rmi.Naming;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	public Connection conn = null;
	public PreparedStatement sttm = null;
	public ResultSet rst = null;
	public Statement attm = null;
	private ArrayList list;
	private IServer iServer;

	public String add(Model model) {
		try {
			String sSQL = "insert into QLAccount (Username,Password,Type,DateAdd,Link) values (?,?,?,?,?)";
			conn = ConnectDB.openConnection();
			sttm = conn.prepareStatement(sSQL);
			sttm.setString(1, model.getTentk());
			sttm.setString(2, model.getMatkhau());
			sttm.setString(3, model.getLoaitk());
			sttm.setString(4, model.getNgaythem());
			sttm.setString(5, model.getTruycap());
			try {
				iServer = (IServer) Naming.lookup("rmi://localhost:1099/db");
			} catch (Exception e) {
				return "Vui lòng kết nối với server";
			}
			if (sttm.executeUpdate() > 0) {
				return "Thêm thành công";
			} else {
				return "Thêm Chưa thành công";
			}
		} catch (Exception e) {
			return "Tài khoản đã tồn tại";
		}
	}

	public String edit(Model model) {

		try {
			String sSQL = "update QLAccount set Password = ? ,Type = ? ,DateAdd = ? ,Link = ? where Username = ? ";
			conn = ConnectDB.openConnection();
			sttm = conn.prepareStatement(sSQL);
			sttm.setString(1, model.getMatkhau());
			sttm.setString(2, model.getLoaitk());
			sttm.setString(3, model.getNgaythem());
			sttm.setString(4, model.getTruycap());
			sttm.setString(5, model.getTentk());
			try {
				iServer = (IServer) Naming.lookup("rmi://localhost:1099/db");
			} catch (Exception e) {
				return "Vui lòng kết nối với server";
			}
			if (sttm.executeUpdate() > 0) {
				return "Đã sửa thành công";
			} else {
				return "Đã sửa không thành công";
			}
		} catch (Exception e) {
			return "";
		}
	}

	public String del(String Username) {
		try {
			String sSQL = "DELETE QLAccount where Username = ?";
			conn = ConnectDB.openConnection();
			sttm = conn.prepareStatement(sSQL);
			sttm.setString(1, Username);
			try {
				iServer = (IServer) Naming.lookup("rmi://localhost:1099/db");
			} catch (Exception e) {
				return "Vui lòng kết nối với server";
			}
			sttm.executeUpdate();
			conn.close();
			sttm.close();
		} catch (Exception e) {
			System.out.println("Error :" + e.toString());
		}
		return "Đã xóa";
	}

	public List<Model> getAllAccount() {
		list = new ArrayList<>();
		try {
			String sSQL = "select Username,Password,Type,DateAdd,Link from QLAccount";
			conn = ConnectDB.openConnection();
			attm = conn.createStatement();
			rst = attm.executeQuery(sSQL);
			while (rst.next()) {
				Model model = new Model();
				model.setTentk(rst.getString(1));
				model.setMatkhau(rst.getString(2));
				model.setLoaitk(rst.getString(3));
				model.setNgaythem(rst.getString(4));
				model.setTruycap(rst.getString(5));
				list.add(model);
			}
			conn.close();
			attm.close();
		} catch (Exception e) {
			System.out.println("Error :" + e.toString());
		}
		return list;
	}

	public Model getAccountUsername(String Username) {
		Model model = new Model();
		try {
			String sSQL = "select Username,Password,Type,DateAdd,Link from QLAccount where Username = ?";
			conn = ConnectDB.openConnection();
			sttm = conn.prepareStatement(sSQL);
			sttm.setString(1, Username);
			rst = sttm.executeQuery();
			while (rst.next()) {
				model.setTentk(rst.getString(1));
				model.setMatkhau(rst.getString(2));
				model.setLoaitk(rst.getString(3));
				model.setNgaythem(rst.getString(4));
				model.setTruycap(rst.getString(5));
				return model;
			}
			conn.close();
			sttm.close();
			rst.close();
		} catch (Exception e) {
			System.out.println("Error :" + e.toString());
		}
		return model;
	}
}