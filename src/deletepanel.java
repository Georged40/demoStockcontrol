import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class deletepanel extends JPanel implements ActionListener {


    // create a file to hold orders, upon submitting the order it clears

    String myHeadLine = "Item Description" + "     " + "UOM" + "        " + "Price" + "        " + "Order Qty" + "\n";

    int listPosition = 0;

    //stockItem attributes
    String description;
    JTextArea createdItemsReportField;
    String UOM;
    double price;
    double qty;
    double value;
    double parLevel;
    double orderQty;
    static String uomNew;

    static int itemNumberPassed;
    static String formattedString;
    String stringItem;
    String createdItemsReportFieldHeading;

    float labelFontSize = 20;

    String[] myUOMCombo = {"KG", "LTR", "EA", "BOX", "PACK"};

    String stringDescription;
    String stringUOM;
    String stringPrice;
    String stringOrderQty;

    String[] stockItemList;
    String itemStringRepr;

    //following will be used to navigate through the storeroom positions
    int itemPosition;

    //stockItem to hold created items
    StockItem newItem;

    //stockItem array to hold the stock

    //editPanel(Panel5)


    JPanel panel;
    JPanel panelMenu;
    JPanel panelMode;
    JPanel panelCenter;
    JPanel orderCenterPanel;
    JPanel panelEast;
    static JPanel autoPanelCenter;
    JPanel panelSouth;
    JPanel headingPanel;
    JPanel buttonPanel;

    //menu related declarations
    JMenuBar menuBar;

    JMenu newItemMenu;
    JMenuItem addItem;
    JMenuItem itemReport;

    JMenu orders;

    JLabel itemNumber;
    JLabel itemDescription;
    JLabel itemUOM;
    JLabel itemQty;
    JLabel itemPrice;

    JLabel panelTitle;

    JTextField itemNumberField;
    JTextField descriptionField;

    JPanel orderDisplayPanel;

    JComboBox itemUOMBox;
    JTextField itemQtyField;
    JTextField itemPriceField;

    JTextArea autoOrderField;


    JTextArea orderReportsField;

    JTextArea orderFiled;

    JButton actionButton;
    JButton deleteButton;

    String orderDescription;


    String orderReportHeadline;

    String autoOrderDisplay;

    String orderItem;
    String OrderedDescription;
    String OrderedUOM;
    double OrderedItemPrice;
    double OrderedQty;
    String[] orderList;
    static Connection connection = null;
    static String databaseName = "storeroom";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String password = "Owe22Phila20";
    static String newdescription;
    static String newUOM;
    static double newprice;
    static double newqty;

    static double newvalue;
    static double newparLevel;
    static double neworderQty;
    static String newstringItem;
    static String newuomNew;


    String label1 = "ITEM NUMBER";
    String label2 = "DESCRIPTION";
    String label3 = "UOM";
    String label4 = "PRICE";
    String label5 = "QUANTITY";
    String label6 = "VALUE";
    String label7 = "PAR LEVEL";
    String label8 = "ORDER QTY";
    String label9 = "SIGNATURE";


    deletepanel() throws IOException {

        //oderList = new String[100];

        itemPosition = 0;

//
        headingPanel = new JPanel(null);
        headingPanel.setBounds(100, 100, 600, 50);

        // headingPanel.setBackground(Color.red);
        headingPanel.setOpaque(true);

        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(100, 400, 650, 100);
        // buttonPanel.setBackground(Color.green);
        buttonPanel.setOpaque(true);

        actionButton = new JButton("Fetch Item Details");
        actionButton.setBounds(350, 0, 250, 50);
        actionButton.setBorderPainted(false);
        actionButton.addActionListener(this);
        actionButton.setVisible(true);

        deleteButton = new JButton("Delete Item");
        deleteButton.setBounds(350, 0, 250, 50);
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(this);
        deleteButton.setVisible(false);


        panelTitle = new JLabel();
        panelTitle.setText("Select Item To Delete");
        panelTitle.setBounds(150, 0, 250, 50);
        panelTitle.setFont(panelTitle.getFont().deriveFont(labelFontSize));
        panelTitle.setVisible(true);

        headingPanel.add(panelTitle);
        buttonPanel.add(actionButton);
        buttonPanel.add(deleteButton);

        itemNumber = new JLabel();
        itemNumber.setText("Enter Item Number: ");
        itemNumber.setVisible(true);

        itemNumberField = new JTextField(30);
        itemNumberField.setVisible(true);

        itemDescription = new JLabel();
        itemDescription.setText("Item Description: ");
        itemDescription.setVisible(true);

        descriptionField = new JTextField(30);
        descriptionField.setVisible(false);

        itemUOM = new JLabel();
        itemUOM.setText("UOM:  ");
        itemUOM.setVisible(true);

        itemUOMBox = new JComboBox<>(myUOMCombo);
        itemUOMBox.addKeyListener((KeyListener) KeyStroke.getKeyStroke(descriptionField.getText()));
        itemUOMBox.setVisible(false);

        itemPrice = new JLabel();
        itemPrice.setText("Cost Price: ");
        itemPrice.setVisible(true);

        itemPriceField = new JTextField(30);
        itemPriceField.addKeyListener((KeyListener) KeyStroke.getKeyStroke(descriptionField.getText()));
        itemPriceField.setVisible(false);


        orderReportHeadline = "Testing Item Description" + "     " + "UOM" + "        " + "Price" + "        " + "Order Qty" + "\n";
        orderReportsField = new JTextArea();
        orderReportsField.setBounds(150, 10, 590, 500);
        orderReportsField.append(orderReportHeadline);
        orderReportsField.setVisible(true);


        orderFiled = new JTextArea();
        orderFiled.setVisible(false);
        orderFiled.setBounds(0, 0, 650, 200);
        orderFiled.append("\n" + myHeadLine);


        //initializing the menu items
     /*   addItem = new JMenuItem("New Item");
        // addItem.setBounds(0, 0, 200, 20);
        addItem.addActionListener(this);
        addItem.setFocusPainted(false);

        itemReport = new JMenuItem("New Items Report");
        //  itemReport.setBounds(0, 0, 200, 20);
        itemReport.addActionListener(this);
        itemReport.setBorderPainted(false);

        newItemMenu = new JMenu("Add New StockItem");
        //   newItemMenu.setBounds(0, 0, 200, 40);
        newItemMenu.add(addItem);
        newItemMenu.add(itemReport);
        newItemMenu.setBorderPainted(false);
        */


        orders = new JMenu("Delete Stock Item");
        //  orders.setBounds(0, 80, 200, 40);
        orders.setBorderPainted(false);


        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 850, 50);
        menuBar.add(orders);


        panel = new JPanel(null);
        panel.setBounds(0, 0, 850, 600);
        panel.setVisible(true);


        panelMenu = new JPanel(null);
        panelMenu.setBounds(0, 0, 850, 50);
        panelMenu.add(menuBar);
        panelMenu.setVisible(true);

        orderDisplayPanel = new JPanel(null);
        orderDisplayPanel.setBounds(0, 0, 850, 50);
        orderDisplayPanel.add(orderFiled);
        orderDisplayPanel.setVisible(true);

        panelSouth = new JPanel(null);
        panelSouth.setBounds(0, 650, 850, 50);
        panelSouth.setVisible(true);

        panelMode = new JPanel(null);
        panelMode.setBounds(0, 50, 100, 550);
        //  panelMode.add(modeButton, BorderLayout.CENTER);
        panelMode.setVisible(true);

        panelCenter = new JPanel(new GridLayout(7, 2));
        panelCenter.setBounds(100, 200, 650, 200);

        panelCenter.add(itemNumber);
        panelCenter.add(itemNumberField);
        panelCenter.add(itemDescription);
        panelCenter.add(descriptionField);
        panelCenter.add(itemUOM);
        panelCenter.add(itemUOMBox);
        panelCenter.add(itemPrice);
        panelCenter.add(itemPriceField);
        // panelCenter.add(itemQty);

        panelCenter.setVisible(true);

        autoPanelCenter = new JPanel(new GridLayout(7, 2));
        autoPanelCenter.setBounds(100, 150, 650, 250);
        // autoPanelCenter.add(autoOrderField);
        autoPanelCenter.setVisible(true);


        panel.add(panelMenu);
        panel.add(headingPanel);
        panel.add(panelMode);
        panel.add(panelCenter);
        // panel.add(autoPanelCenter);
        panel.add(buttonPanel);
        panel.add(orderDisplayPanel);
        //  panel.add(panelEast);
        panel.add(panelSouth);

        this.setSize(850, 600);
        this.setLayout(null);
        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            try {
                upDateDatabase();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Fetching items");
            deleteButton.setVisible(true);
            descriptionField.setVisible(true);
            itemUOMBox.setVisible(true);
            itemPriceField.setVisible(true);
            actionButton.setVisible(false);


        } else if (e.getSource() == deleteButton) {
            try {
                deleteTheItem();

                System.out.println("Delete button clicked");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
            deleteButton.setVisible(false);
            actionButton.setVisible(true);
            itemNumberField.setVisible(false);
            itemUOMBox.setVisible(false);
            itemPriceField.setVisible(false);

        }
    }

    public void upDateDatabase() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());


        // Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        String updateStatement = String.format("SELECT description,price FROM stockitem WHERE itemNumber = %d", itemNumberPassed);
        System.out.println(updateStatement);

        ResultSet resultSet = stmt.executeQuery(updateStatement);

        System.out.println(resultSet + "lines were updated");

        int rowCount = 0;
        while (resultSet.next()) {
            description = resultSet.getString("description");
            price = resultSet.getDouble("price");
            descriptionField.setText(description);
            itemPriceField.setText(String.valueOf(price));

        }
    }

    public void deleteTheItem() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, IOException {

        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Deleted\\deleted.txt";
        File tempFile = new File(baseFilePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile,true));
        Pattern pattern = Pattern.compile("|", Pattern.LITERAL);



        String label1Formatted = String.format("%-15s|", label1);
        String label2Formatted = String.format("%-15s|", label2);
        String label3Formatted = String.format("%-15s|", label3);
        String label4Formatted = String.format("%-15s|", label4);
        String label5Formatted = String.format("%-15s|", label5);
        String label6Formatted = String.format("%-15s|", label6);
        String label7Formatted = String.format("%-15s|", label7);
        String label8Formatted = String.format("%-15s|", label8);
        String label9Formatted = String.format("%-15s|", label9);

        createdItemsReportFieldHeading = label1Formatted + label2Formatted + label3Formatted + label4Formatted + label5Formatted +
                label6Formatted + label7Formatted + label8Formatted + label9Formatted;

        // writer.write(createdItemsReportFieldHeading);


        itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        // Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();
        String updateStatement = String.format("DELETE FROM stockitem WHERE itemNumber = %d", itemNumberPassed);

        String itemDesription = descriptionField.getText();
        String uomNew = itemUOM.getText();
        double price = Double.parseDouble(itemPriceField.getText());

        String itemNumberForamted = String.format("%-15s|",itemNumberPassed);
        String description = String.format("%-15s|", itemDesription);
        String uomFormated = String.format("%-15s|", uomNew);
        String priceFormated = String.format("%-15f|", price);

        String stringItem = itemNumberForamted + description + uomFormated + priceFormated;

        writer.append("\n" + stringItem);
        writer.close();



        int resultSet = stmt.executeUpdate(updateStatement);
        System.out.println(resultSet);


        itemNumberField.setText("");
        descriptionField.setText("");
        itemPriceField.setText("");


        System.out.println("From the delete");

    }
}