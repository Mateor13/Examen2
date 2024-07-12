import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertar extends JFrame {
    private JTextField cod;
    private JTextField nm;
    private JTextField des;
    private JTextField pre;
    private JTextField can;
    private JTextField cat;
    private JButton ingresarBtn;
    private JButton BusqBtn;
    private JButton salBtn;
    private JPanel inse;
    private JLabel res;

    public Insertar() {
        setTitle("Ingresar Producto");
        setContentPane(inse);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 600));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/productos_cp";
                String user = "root";
                String pass = "123456";
                String querry = "Insert into producto (codigo_producto, nombre, descripcion, precio, cantidad, categoria) values (?,?,?,?,?,?)";
                if (cod.getText().isEmpty()||nm.getText().isEmpty()||des.getText().isEmpty()||pre.getText().isEmpty()||can.getText().isEmpty()||cat.getText().isEmpty()){
                    res.setText("Llene todos los campos");
                }else {
                    try (Connection con = DriverManager.getConnection(url, user, pass)) {
                        PreparedStatement ps = con.prepareStatement(querry);
                        int cant = Integer.parseInt(can.getText());
                        double pre1 = Double.parseDouble(pre.getText());
                        ps.setString(1, cod.getText());
                        ps.setString(2, nm.getText());
                        ps.setString(3, des.getText());
                        ps.setDouble(4, pre1);
                        ps.setInt(5, cant);
                        ps.setString(6, cat.getText());
                        ps.executeUpdate();
                        res.setText("Ingresado Exitosamente");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        res.setText("Error al ingresar Datos");
                    }
                }
            }
        });
        BusqBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new buscar();
                dispose();
            }
        });
        salBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
    }
}
