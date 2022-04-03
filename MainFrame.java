import PaqC03.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenya;
    private JButton btnLimpiar;
    private JButton btnEntrar;
    private JButton btnRegistrarse;

    public MainFrame() {
        setTitle("Hotel Montealegre**");
        setContentPane(mainPanel);
        setBounds(550, 300, 500, 270);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText("");
                txtContrasenya.setText("");
            }
        });

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(txtUsuario.getText(),"") || Objects.equals(txtContrasenya.getText(),"")) {
                    JOptionPane.showMessageDialog(null, "Error en usuario y/o contraseña", "Error al Entrar", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean encontrado = false;
                    try{
                        File datos = new File(System.getProperty("user.dir") + "/datosRegistro.txt");
                        Scanner sc = new Scanner(datos);
                        while (sc.hasNextLine() && !encontrado){
                            String user = sc.nextLine();
                            String[] login = user.split("#");
                            if (login[0].equals(txtUsuario.getText()) && login[1].equals(txtContrasenya.getText())){
                                encontrado = true;
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (encontrado){
                        MainFrame2 mainFrame2 = new MainFrame2();
                        mainFrame2.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Error en usuario y/o contraseña", "Error al Entrar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(txtUsuario.getText(), "") || Objects.equals(txtContrasenya.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Error en usuario y/o contraseña", "Error al Registrarse", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        FileWriter log = null;
                        log = new FileWriter(System.getProperty("user.dir") + "/datosRegistro.txt",true);
                        log.write(txtUsuario.getText()+"#"+txtContrasenya.getText()+"\n");
                        log.flush();
                        log.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Usuario registrado con exito", "Registro completado", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
    }
}
