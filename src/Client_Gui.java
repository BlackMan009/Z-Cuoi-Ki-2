import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.rmi.Naming;

public class Client_Gui extends JFrame {

	private JPanel contentPane;
	private JTextField tf_name;
	private JTextField tf_pass;
	private JTextField tf_type;
	private JTextField tf_date;
	private JTextField tf_link;
	private JScrollPane scrollPane;
	private JTable tbManager;
	private DefaultTableModel defaul;
	Model model = new Model();
	Dao dao = new Dao();
	private String stt_acc;
	private IServer iServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_Gui frame = new Client_Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client_Gui() {
		init();
		FillDataTable();
		try {
			iServer = (IServer) Naming.lookup("rmi://localhost:1099/db");
			JOptionPane.showMessageDialog(null, "Đã kết nối với server", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Chưa kết nối với server", "Thông Báo", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void init() {
		setTitle("Quản lý tài khoản cá nhân");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 650);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(0, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên ĐN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(21, 24, 101, 30);
		contentPane.add(lblNewLabel);

		JLabel lblMtKhu = new JLabel("Mật Khẩu");
		lblMtKhu.setForeground(Color.WHITE);
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMtKhu.setBounds(21, 64, 101, 30);
		contentPane.add(lblMtKhu);

		JLabel lblLoiTk = new JLabel("Loại TK");
		lblLoiTk.setForeground(Color.WHITE);
		lblLoiTk.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLoiTk.setBounds(21, 104, 84, 30);
		contentPane.add(lblLoiTk);

		JLabel lblNgyThm = new JLabel("Ngày Thêm");
		lblNgyThm.setForeground(Color.WHITE);
		lblNgyThm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNgyThm.setBounds(21, 144, 113, 30);
		contentPane.add(lblNgyThm);

		JLabel lblTruyCp = new JLabel("Truy Cập");
		lblTruyCp.setForeground(Color.WHITE);
		lblTruyCp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTruyCp.setBounds(21, 184, 113, 30);
		contentPane.add(lblTruyCp);

		tf_name = new JTextField();
		tf_name.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_name.setBounds(207, 24, 346, 30);
		contentPane.add(tf_name);
		tf_name.setColumns(10);

		tf_pass = new JTextField();
		tf_pass.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_pass.setColumns(10);
		tf_pass.setBounds(207, 64, 346, 30);
		contentPane.add(tf_pass);

		tf_type = new JTextField();
		tf_type.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_type.setColumns(10);
		tf_type.setBounds(207, 104, 346, 30);
		contentPane.add(tf_type);

		tf_date = new JTextField();
		tf_date.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_date.setColumns(10);
		tf_date.setBounds(207, 144, 346, 30);
		contentPane.add(tf_date);

		tf_link = new JTextField();
		tf_link.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_link.setColumns(10);
		tf_link.setBounds(207, 184, 346, 30);
		contentPane.add(tf_link);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 238, 566, 270);
		tbManager = new JTable();
		tbManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbManager.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int Username = tbManager.rowAtPoint(e.getPoint());
						stt_acc = tbManager.getValueAt(Username, 0).toString();
						Model model = dao.getAccountUsername(stt_acc);
						setModel(model);
					}
				});
			}
		});
		tbManager.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null } },
				new String[] {
						"Tên", "Mật Khẩu", "Loại", "Ngày Thêm", "Truy Cập"
				}));
		scrollPane.setViewportView(tbManager);
		contentPane.add(scrollPane);

		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model model = getModel();
				String mess = dao.add(model);
				if (validateForm()) {
					try {
						dao.add(model);
						JOptionPane.showMessageDialog(null, mess, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						FillDataTable();
						clear();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(10, 551, 124, 40);
		contentPane.add(btnNewButton);

		JButton btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateForm()) {
					Model model = getModel();
					String mess = dao.edit(model);
					try {
						dao.edit(model);
						JOptionPane.showMessageDialog(null, mess, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						FillDataTable();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
				clear();
			}
		});
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSa.setBounds(157, 551, 124, 40);
		contentPane.add(btnSa);

		JButton btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mess = dao.del(stt_acc);

				int op = JOptionPane.showConfirmDialog(null, "Muốn xóa tài khoản này không ?", "Delete",
						JOptionPane.YES_NO_OPTION);

				if (op == JOptionPane.YES_OPTION) {
					try {
						dao.del(stt_acc);
						JOptionPane.showConfirmDialog(null, mess, "Thông báo", JOptionPane.CANCEL_OPTION);
					} catch (Exception o) {
						o.printStackTrace();
					}
					FillDataTable();
					clear();
				}
			}
		});
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnXa.setBounds(301, 551, 124, 40);
		contentPane.add(btnXa);

		JButton btnLmMi = new JButton("Làm Mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnLmMi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLmMi.setBounds(452, 551, 124, 40);
		contentPane.add(btnLmMi);

		JButton load = new JButton("....");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FillDataTable();
			}
		});
		load.setFont(new Font("Tahoma", Font.BOLD, 20));
		load.setBounds(538, 518, 38, 23);
		contentPane.add(load);
	}

	public Model getModel() {
		model.setTentk(tf_name.getText());
		model.setMatkhau(tf_pass.getText());
		model.setLoaitk(tf_type.getText());
		model.setNgaythem(tf_date.getText());
		model.setTruycap(tf_link.getText());
		return model;
	}

	public void setModel(Model model) {
		tf_name.setText(model.getTentk());
		tf_pass.setText(model.getMatkhau());
		tf_type.setText(model.getLoaitk());
		tf_date.setText(model.getNgaythem());
		tf_link.setText(model.getTruycap());
	}

	public boolean validateForm() {
		if (tf_name.getText().isEmpty() || tf_pass.getText().isEmpty() || tf_type.getText().isEmpty()
				|| tf_date.getText().isEmpty() || tf_link.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	public void clear() {
		tf_name.setText("");
		tf_pass.setText("");
		tf_type.setText("");
		tf_date.setText("");
		tf_link.setText("");
	}

	public void FillDataTable() {
		defaul = (DefaultTableModel) tbManager.getModel();
		defaul.setRowCount(0);
		for (Model model : dao.getAllAccount()) {
			Object dataRow[] = new Object[5];
			dataRow[0] = model.getTentk();
			dataRow[1] = model.getMatkhau();
			dataRow[2] = model.getLoaitk();
			dataRow[3] = model.getNgaythem();
			dataRow[4] = model.getTruycap();
			defaul.addRow(dataRow);
		}
	}
}
