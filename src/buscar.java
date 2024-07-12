import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar extends JFrame {
    private JTextField co;
    private JButton buscarBtn;
    private JLabel cod1;
    private JLabel nm;
    private JLabel des;
    private JLabel pre;
    private JLabel cant;
    private JLabel cat;
    private JButton RGBtn;
    private JButton LoginBtn;
    private JPanel Bsc;

    public buscar() {
        setTitle("Buscar Producto");
        setContentPane(Bsc);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 250));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/productos_cp";
                String user = "root";
                String pass = "123456";
                try (Connection con = DriverManager.getConnection(url, user, pass)){
                    Productos pro = new Productos();
                    pro.setCodigo(co.getText());

                    String querry = "select * from producto where codigo_producto = '"+pro.getCodigo()+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(querry);
                    if (rs.next()){
                        cod1.setText("Código de producto: "+ rs.getString("codigo_producto"));
                        nm.setText("Nombre: "+rs.getString("nombre"));
                        des.setText("Descripción: "+rs.getString("descripcion"));
                        pre.setText("Precio: "+rs.getString("precio"));
                        cant.setText("Cantidad: "+rs.getString("cantidad"));
                        cat.setText("Categoria: "+rs.getString("categoria"));
                        setPreferredSize(new Dimension(500, 400));
                        pack();
                        setLocationRelativeTo(null);
                    }else{
                        cod1.setText("Usuario no encontrado");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                    cod1.setText("Error en la Base de datos");
                }
            }
        });
        RGBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Insertar();
                dispose();
            }
        });
        LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
    }
}
