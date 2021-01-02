package petshop;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.*;
import java.util.Date;

public class BuyContent extends JFrame {
    Connector con = new Connector();
    JPanel buyContent = new JPanel();


    public JPanel buyContent(){

        buyContent.setBackground(Color.DARK_GRAY);


        //table.setBackground(Color.DARK_GRAY);
        //table.setForeground(Color.WHITE);

        buyContent.setBounds(300,0,1700,1047);
        buyContent.setLayout(null);

        JLabel pet = new JLabel("PET");
        pet.setBounds(25,250,150,25);
        pet.setFont(new Font("Open Sans",Font.PLAIN,20));
        pet.setForeground(Color.WHITE);
        buyContent.add(pet);

        JLabel type = new JLabel("TYPE/BREED");
        type.setBounds(25,300,150,25);
        type.setFont(new Font("Open Sans",Font.PLAIN,20));
        type.setForeground(Color.WHITE);
        buyContent.add(type);

        JLabel  supplier = new JLabel("SUPPLIER");
        supplier.setBounds(25,350,150,25);
        supplier.setFont(new Font("Open Sans",Font.PLAIN,20));
        supplier.setForeground(Color.WHITE);
        buyContent.add(supplier);


        JLabel jdate = new JLabel("DATE");
        jdate.setBounds(25,400,150,25);
        jdate.setFont(new Font("Open Sans",Font.PLAIN,20));
        jdate.setForeground(Color.WHITE);
        buyContent.add(jdate);

        JLabel price = new JLabel("PRICE");
        price.setBounds(25,450,150,25);
        price.setFont(new Font("Open Sans",Font.PLAIN,20));
        price.setForeground(Color.WHITE);
        buyContent.add(price);



        JTextField petField = new JTextField();
        petField.setBounds(170,250,275,25);
        petField.setFont(new Font("",Font.PLAIN,20));
        petField.setForeground(Color.WHITE);
        petField.setCaretColor(Color.WHITE);
        petField.setBorder(null);
        petField.setOpaque(false);
        buyContent.add(petField);


        JTextField typeField = new JTextField();
        typeField.setBounds(170,300,275,25);
        typeField.setFont(new Font("",Font.PLAIN,20));
        typeField.setCaretColor(Color.WHITE);
        typeField.setForeground(Color.WHITE);
        typeField.setBorder(null);
        typeField.setOpaque(false);
        buyContent.add(typeField);

        JTextField supplierField = new JTextField();
        supplierField.setBounds(170,350,275,25);
        supplierField.setFont(new Font("",Font.PLAIN,20));
        supplierField.setCaretColor(Color.WHITE);
        supplierField.setForeground(Color.WHITE);
        supplierField.setBorder(null);
        supplierField.setOpaque(false);
        buyContent.add(supplierField);

        /*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new Date();
        dateFormat.format(date);*/

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new Date();

        JTextField dateField = new JTextField(dateFormat.format(date));
        dateField.setBounds(170,400,275,25);
        dateField.setFont(new Font("",Font.PLAIN,20));
        dateField.setForeground(Color.WHITE);
        dateField.setCaretColor(Color.WHITE);
        dateField.setBorder(null);
        dateField.setOpaque(false);
        buyContent.add(dateField);

        JTextField priceField = new JTextField();
        priceField.setBounds(170,450,275,25);
        priceField.setFont(new Font("",Font.PLAIN,20));
        priceField.setForeground(Color.WHITE);
        priceField.setCaretColor(Color.WHITE);
        priceField.setBorder(null);
        priceField.setOpaque(false);
        buyContent.add(priceField);

        JSeparator s1 = new JSeparator();
        s1.setBounds(165,275,275,5);
        buyContent.add(s1);

        JSeparator s2 = new JSeparator();
        s2.setBounds(165,325,275,5);
        buyContent.add(s2);

        JSeparator s3 = new JSeparator();
        s3.setBounds(165,375,275,5);
        buyContent.add(s3);

        JSeparator s4 = new JSeparator();
        s4.setBounds(165,425,275,5);
        buyContent.add(s4);

        JSeparator s5 = new JSeparator();
        s5.setBounds(165,475,275,5);
        buyContent.add(s5);


        JLabel submit = new JLabel("ADD TO INVENTORY");
        submit.setForeground(Color.BLACK.darker());
        submit.setFocusable(true);
        //newUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    //Connector con = new Connector();
                    con.s.executeUpdate("INSERT INTO transaction " +
                        "VALUES (DEFAULT,'"+dateField.getText()+"','"+supplierField.getText()+"'," +
                        "'"+dateField.getText()+"','"+priceField.getText() +"','bought')");
                    //JOptionPane.showMessageDialog(null,"added to inventory");
                    //petField.setText("");
                    petField.requestFocus();
                    typeField.setText("");
                    supplierField.setText("");
                    //dateField.setText("");
                    priceField.setText("");

                }catch (Exception se){
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null,se);
                }


               // revalidate();
                //repaint();
                viewTable();
            }

            @Override
            public void mouseEntered(MouseEvent e){
                submit.setForeground(new Color(0x1C97A3));
            }

            @Override
            public void mouseExited(MouseEvent e){
                submit.setForeground(Color.BLACK.darker());
            }
        });
        submit.setBounds(220,500,200,25);
        submit.setFont(new Font("",Font.PLAIN,20));
        buyContent.add(submit);

        viewTable();
        revalidate();
        repaint();
        return buyContent;
    }

    private void viewTable() {
        JTable table = new JTable();
        try{
            ResultSet rs = con.s.executeQuery("SELECT Pet,TypeOrBreed,TotalQuantity,Price" +
                    " FROM INVENTORY i , PET p " +
                    "WHERE p.P_id = i.P_id");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            //SQL command  ---> ALTER TABLE tablename AUTO_INCREMENT = 1
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(900,50,600,200);
            buyContent.add(pane);
        }catch(Exception se){
            System.out.println(se);
        }
        revalidate();
        repaint();
    }
}
