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
    private JTextField txtHabitaciones;
    private JButton btnAnular;

    int precioTotal = 0;
    String opcion="";
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

        try{
        ObjectInputStream leyendoFichero = new ObjectInputStream(
                new FileInputStream("datosHotel.dat"));
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
                    hotel.reservarHab(c1, opcion, Integer.parseInt(txtHabitaciones.getText()));
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
                    //JOptionPane.showMessageDialog(null, "Se ha reservado la hab "+(h[1])+" de la planta "+(h[0]), "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbEstandar.isSelected()){
                    precioTotal = (precioTotal + 20)* Integer.parseInt(txtHabitaciones.getText());
                }else if (cbBalcon.isSelected()){
                    precioTotal = (precioTotal + 50)* Integer.parseInt(txtHabitaciones.getText());
                }else if (cbSuite.isSelected()){
                    precioTotal = (precioTotal + 250)* Integer.parseInt(txtHabitaciones.getText());
                }
                txtPrecioTotal.setText(""+precioTotal);
            }
        });
        cbEstandar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbEstandar.isSelected()){
                    cbBalcon.setEnabled(false);
                    cbSuite.setEnabled(false);
                    opcion="Estandar";
                }else{
                    cbBalcon.setEnabled(true);
                    cbSuite.setEnabled(true);
                    precioTotal = 0;
                }
                JOptionPane.showMessageDialog(null, "Has pulsado "+cbEstandar.getText(), "", JOptionPane.PLAIN_MESSAGE);

            }
        });
        cbBalcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbBalcon.isSelected()){
                    cbEstandar.setEnabled(false);
                    cbSuite.setEnabled(false);
                    opcion="Balcon";
                }else{
                    cbEstandar.setEnabled(true);
                    cbSuite.setEnabled(true);
                    precioTotal = 0;
                }
                JOptionPane.showMessageDialog(null, "Has pulsado "+cbBalcon.getText(), "", JOptionPane.PLAIN_MESSAGE);
            }
        });
        cbSuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbSuite.isSelected()){
                    cbEstandar.setEnabled(false);
                    cbBalcon.setEnabled(false);
                    opcion="Suite";
                }else{
                    cbEstandar.setEnabled(true);
                    cbBalcon.setEnabled(true);
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
                if (Objects.equals(txtDNI.getText(), "") || Objects.equals(txtNombre.getText(), "") || Objects.equals(txtApellidos.getText(), "") || Objects.equals(txtTelefono.getText(), "") || Objects.equals(txtTarjeta.getText(), "") || Objects.equals(txtEntrada.getText(), "") || Objects.equals(txtSalida.getText(), "") || Objects.equals(txtAlimentacion.getText(), "") || Objects.equals(txtHabitaciones.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Faltan datos", "", JOptionPane.WARNING_MESSAGE);
                }
                if (!cbEstandar.isSelected() && !cbBalcon.isSelected() && !cbSuite.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Tipo de Habitacion requerido", "", JOptionPane.WARNING_MESSAGE);
                }
                if (!Objects.equals(txtDNI.getText(), "") && !Objects.equals(txtNombre.getText(), "") && !Objects.equals(txtApellidos.getText(), "") && !Objects.equals(txtTelefono.getText(), "") && !Objects.equals(txtTarjeta.getText(), "") && !Objects.equals(txtEntrada.getText(), "") && !Objects.equals(txtSalida.getText(), "") && !Objects.equals(txtAlimentacion.getText(), "") || !Objects.equals(txtHabitaciones.getText(), "") && cbEstandar.isSelected() || cbBalcon.isSelected() || cbSuite.isSelected()) {
                    Cliente c2 = new Cliente(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtDNI.getText()), Integer.parseInt(txtTelefono.getText()), Integer.parseInt(txtTarjeta.getText()), txtEntrada.getText(), txtSalida.getText(), txtAlimentacion.getText());
                    for (int i = 0; i < (Integer.parseInt(txtHabitaciones.getText())); i++) {
                        hotel.anularReserva(c2, (Integer.parseInt(txtHabitaciones.getText())));
                    }
                    txtMapa.setText(hotel.toString());
                }
            }
        });
    }
}
