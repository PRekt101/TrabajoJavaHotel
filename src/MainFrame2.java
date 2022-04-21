import PaqC03.Cliente;
import PaqC03.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;

public class MainFrame2 extends JFrame{
    private JPanel mainPanel2;
    private JTextField txtNombre;
    private JCheckBox cbEstandar;
    private JCheckBox cbSuite;
    private JCheckBox cbBalcon;
    private JTextField txtPrecioTotal;
    private JButton btnCalcular;
    private JButton btnLimpiar;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JComboBox cbBOpciones;
    private JTextField txtDNI;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtTarjeta;
    private JTextField txtEntrada;
    private JTextField txtSalida;
    private JTextField txtAlimentacion;
    private JTextArea txtMapa;
    private JTextField txtBalcon;
    private JButton btnAnular;
    private JTextField txtEstandar;
    private JTextField txtSuite;
    private JButton btnBuscar;

    int precioTotal = 0;
    String opcion1="";
    String opcion2="";
    String opcion3="";
    static Hotel hotel = new Hotel();

    public MainFrame2() {
        setTitle("Reservas");
        setContentPane(mainPanel2);
        setBounds(400, 150, 800, 570);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        cbBOpciones.addItem("Sin desayuno");
        cbBOpciones.addItem("Con desayuno");
        cbBOpciones.addItem("Media Pensión");
        cbBOpciones.addItem("Pensión Completa");

        cbBOpciones.setSelectedItem(null);

        try(ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream("datosHotel.dat"))){
        hotel = (Hotel) leyendoFichero.readObject();
        leyendoFichero.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        txtMapa.setText(hotel.toString());


        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDNI.setText("");
                txtNombre.setText("");
                txtApellidos.setText("");
                txtTelefono.setText("");
                txtTarjeta.setText("");
                txtEntrada.setText("");
                txtSalida.setText("");
                txtAlimentacion.setText("");
                txtPrecioTotal.setText("");
                cbEstandar.setSelected(false);
                cbBalcon.setSelected(false);
                cbSuite.setSelected(false);
                precioTotal = 0;
                cbBOpciones.setSelectedItem(null);
                JOptionPane.showMessageDialog(null, "Se han borrado los datos rellenados", "", JOptionPane.PLAIN_MESSAGE);
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(txtDNI.getText(), "") || Objects.equals(txtNombre.getText(), "") || Objects.equals(txtApellidos.getText(), "") || Objects.equals(txtTelefono.getText(), "") || Objects.equals(txtTarjeta.getText(), "") || Objects.equals(txtEntrada.getText(), "") || Objects.equals(txtSalida.getText(), "") || Objects.equals(txtAlimentacion.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Faltan datos", "", JOptionPane.WARNING_MESSAGE);
                }
                if (!cbEstandar.isSelected() && !cbBalcon.isSelected() && !cbSuite.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Tipo de Habitacion requerido", "", JOptionPane.WARNING_MESSAGE);
                }
                if (!Objects.equals(txtDNI.getText(), "") && !Objects.equals(txtNombre.getText(), "") && !Objects.equals(txtApellidos.getText(), "") && !Objects.equals(txtTelefono.getText(), "") && !Objects.equals(txtTarjeta.getText(), "") && !Objects.equals(txtEntrada.getText(), "") && !Objects.equals(txtSalida.getText(), "") && !Objects.equals(txtAlimentacion.getText(), "") && cbEstandar.isSelected() || cbBalcon.isSelected() || cbSuite.isSelected()) {
                    Cliente c1 = new Cliente(txtNombre.getText(),txtApellidos.getText(),Integer.parseInt(txtDNI.getText()),Integer.parseInt(txtTelefono.getText()),Integer.parseInt(txtTarjeta.getText()),txtEntrada.getText(),txtSalida.getText(),txtAlimentacion.getText());
                    hotel.reservarHab(c1, opcion1, Integer.parseInt(txtEstandar.getText()));
                    hotel.reservarHab(c1, opcion2, Integer.parseInt(txtBalcon.getText()));
                    hotel.reservarHab(c1, opcion3, Integer.parseInt(txtSuite.getText()));
                    txtMapa.setText(hotel.toString());
                    try {
                    ObjectOutputStream escribiendoFichero = null;
                    escribiendoFichero = new ObjectOutputStream(
                            new FileOutputStream("datosHotel.dat"));
                    escribiendoFichero.writeObject(hotel);
                    escribiendoFichero.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbEstandar.isSelected()){
                    precioTotal = (precioTotal + 20)* Integer.parseInt(txtEstandar.getText());
                }else if (cbBalcon.isSelected()){
                    precioTotal = (precioTotal + 50)* Integer.parseInt(txtBalcon.getText());
                }else if (cbSuite.isSelected()){
                    precioTotal = (precioTotal + 250)* Integer.parseInt(txtSuite.getText());
                }
                txtPrecioTotal.setText(""+precioTotal);
            }
        });
        cbEstandar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbEstandar.isSelected()){
                    opcion1="Estandar";
                }else{
                    opcion1="";
                    precioTotal = 0;
                }
                JOptionPane.showMessageDialog(null, "Has pulsado "+cbEstandar.getText(), "", JOptionPane.PLAIN_MESSAGE);

            }
        });
        cbBalcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbBalcon.isSelected()){
                    opcion2="Balcon";
                }else{
                    opcion2="";
                    precioTotal = 0;
                }
                JOptionPane.showMessageDialog(null, "Has pulsado "+cbBalcon.getText(), "", JOptionPane.PLAIN_MESSAGE);
            }
        });
        cbSuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbSuite.isSelected()){
                    opcion3="Suite";
                }else{
                    opcion3="";
                    precioTotal = 0;
                }
                JOptionPane.showMessageDialog(null, "Has pulsado "+cbSuite.getText(), "", JOptionPane.PLAIN_MESSAGE);
            }
        });
        cbBOpciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbBOpciones.getSelectedItem()==null){

                }else{
                    JOptionPane.showMessageDialog(null, "Has seleccionado " + cbBOpciones.getSelectedItem(), "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        btnAnular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(txtDNI.getText(), "") || Objects.equals(txtNombre.getText(), "") || Objects.equals(txtApellidos.getText(), "") || Objects.equals(txtTelefono.getText(), "") || Objects.equals(txtTarjeta.getText(), "") || Objects.equals(txtEntrada.getText(), "") || Objects.equals(txtSalida.getText(), "") || Objects.equals(txtAlimentacion.getText(), "") || Objects.equals(txtBalcon.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Faltan datos", "", JOptionPane.WARNING_MESSAGE);
                }
                if (!cbEstandar.isSelected() && !cbBalcon.isSelected() && !cbSuite.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Tipo de Habitacion requerido", "", JOptionPane.WARNING_MESSAGE);
                }
                if (!Objects.equals(txtDNI.getText(), "") && !Objects.equals(txtNombre.getText(), "") && !Objects.equals(txtApellidos.getText(), "") && !Objects.equals(txtTelefono.getText(), "") && !Objects.equals(txtTarjeta.getText(), "") && !Objects.equals(txtEntrada.getText(), "") && !Objects.equals(txtSalida.getText(), "") && !Objects.equals(txtAlimentacion.getText(), "") || !Objects.equals(txtBalcon.getText(), "") && cbEstandar.isSelected() || cbBalcon.isSelected() || cbSuite.isSelected()) {
                    Cliente c2 = new Cliente(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtDNI.getText()), Integer.parseInt(txtTelefono.getText()), Integer.parseInt(txtTarjeta.getText()), txtEntrada.getText(), txtSalida.getText(), txtAlimentacion.getText());
                    hotel.anularReserva(c2, opcion1, (Integer.parseInt(txtEstandar.getText())));
                    hotel.anularReserva(c2, opcion2, (Integer.parseInt(txtBalcon.getText())));
                    hotel.anularReserva(c2, opcion3, (Integer.parseInt(txtSuite.getText())));
                    txtMapa.setText(hotel.toString());
                    try {
                        ObjectOutputStream escribiendoFichero = null;
                        escribiendoFichero = new ObjectOutputStream(
                                new FileOutputStream("datosHotel.dat"));
                        escribiendoFichero.writeObject(hotel);
                        escribiendoFichero.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente c3 = new Cliente(Integer.parseInt(txtDNI.getText()));
                Cliente c4 = new Cliente(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtDNI.getText()), Integer.parseInt(txtTelefono.getText()), Integer.parseInt(txtTarjeta.getText()), txtEntrada.getText(), txtSalida.getText(), txtAlimentacion.getText());
                c4 = hotel.DevolverDni(c3);
                if(c4 == null) {
                    JOptionPane.showMessageDialog(null, "Cliente no Encontrado", "", JOptionPane.WARNING_MESSAGE);
                } else {
                    txtDNI.setText(c4.getDNI() +"");
                    txtNombre.setText(c4.getNombre());
                    txtApellidos.setText(c4.getApellidos());
                    txtTelefono.setText(c4.getNumTelef() + "");
                    txtTarjeta.setText(c4.getNumTarjet() + "");
                    txtEntrada.setText(c4.getFechaEntrada());
                    txtSalida.setText(c4.getFechaSalida());
                    txtAlimentacion.setText(c4.getRegAlimenticio());
                }

            }
        });
    }
}
