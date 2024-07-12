import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame{
    private JPanel log;
    private JTextField nm;
    private JTextField cl;
    private JButton ingresarButton;
    private JLabel res;

    public login() {
        setTitle("Login");
        setContentPane(log);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/productos_cp";
                String user = "root";
                String pass = "123456";
                String querry = "select * from usuario where username= '"+nm.getText()+"' and password= '"+cl.getText()+"'";
                if (nm.getText().isEmpty()&&cl.getText().isEmpty()){
                    res.setText("Ingrese todos los campos");
                }
                try (Connection con = DriverManager.getConnection(url, user, pass)){
                    credenciales cre = new credenciales(nm.getText(), cl.getText());
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(querry);
                    if (rs.next()) {
                        String us = rs.getString("username");
                        String pas = rs.getString("password");
                        if (cre.getUser().equals(us) && cre.getPass().equals(pas)) {
                            new Insertar();
                            dispose();
                        }
                    }else{
                        res.setText("Usuario Incorrecto");
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                    res.setText("Error al ingresar a la Base de Datos");
                }

            }
        });
    }
}
