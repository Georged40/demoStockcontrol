import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceivingPanel extends JPanel implements ActionListener {
    //stockItem attributes
    // receiving works but i need to add a line to pass the item number, maybe modify to leave whats really required.
    static int itemNumberPassed;
    static double updatedQty;
    static int itemnumber;
    static String description;
    static String UOM;
    static double price;
    static double qty;
    static double newQty;
    static double value;
    static double parLevel;

    static double receivedQty;
    static double orderQty;
    static String stringItem;
    static String formattedString;
    static String uomNew;
    static Connection connection = null;
    static String databaseName = "storeroom";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String password = "Owe22Phila20";
    String createdItemsReportFieldHeading;
    float labelFontSize = 20;

    String[] myUOMCombo = {"KG", "LTR", "EA", "BOX", "PACK"};

    //following will be used to navigate through the storeroom positions
    int itemPosition;

    //stockItem to hold created items
    StockItem newItem;

    //stockItem array to hold the stock
    String[] receivedStockList;
    ;
    //editPanel(Panel5)

    int listPosition = 0;
    JPanel panel;
    JPanel panelMenu;
    JPanel panelMode;
    JPanel panelCenter;
    JPanel orderCenterPanel;
    JPanel panelEast;

    JPanel panelSouth;
    JPanel headingPanel;
    JPanel buttonPanel;


    // OrderPanel2 orderPanel = new OrderPanel();


    //menu related declarations
    JMenuBar menuBar;
    JMenu mainMenu2;

    //JMenu newItemMenu;
    //  JMenuItem addItem;
    //  JMenuItem itemReport;

    JMenu receiving;
    JMenuItem receiveStock;
    JMenuItem receivedreports;

    JLabel itemNumber;
    JLabel itemDescription;
    JLabel itemUOM;
    JLabel itemQty;
    JLabel itemPrice;

    JLabel panelTitle;

    JTextField itemNumberField;
    JTextField descriptionField;

    JComboBox itemUOMBox;
    JTextField itemQtyField;
    JTextField itemPriceField;

    JButton actionButton;

    String receiveDescription;

    String stringStockItem;

    JTextArea createdItemsReportField;

    String label1 = "ITEM NUMBER";
    String label2 = "DESCRIPTION";
    String label3 = "UOM";
    String label4 = "PRICE";
    String label5 = "QUANTITY";
    String label6 = "VALUE";
    String label7 = "PAR LEVEL";
    String label8 = "ORDER QTY";
    String label9 = "SIGNATURE";

    int fileCounter = 1;
    String fileName = "received" + fileCounter + ".txt";


    ReceivingPanel() throws IOException {

        addHeading();

        receivedStockList = new String[100];
        itemPosition = 0;


        createdItemsReportField = new JTextArea();
        createdItemsReportFieldHeading = "Item Description" + "     " + "UOM" + "        " + "Qty" + "       " + "Price";
        createdItemsReportField.setVisible(true);
        createdItemsReportField.setBounds(100, 10, 590, 690);
        createdItemsReportField.append(createdItemsReportFieldHeading);
        createdItemsReportField.setVisible(false);


//
        headingPanel = new JPanel(null);
        headingPanel.setBounds(100, 100, 600, 50);
        // headingPanel.setBackground(Color.red);
        headingPanel.setOpaque(true);

        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(100, 450, 650, 50);
        // buttonPanel.setBackground(Color.green);
        buttonPanel.setOpaque(true);

        actionButton = new JButton("Receive Stock");
        actionButton.setBounds(350, 0, 250, 50);
        actionButton.setBorderPainted(false);
        actionButton.addActionListener(this);
        actionButton.setVisible(true);


        panelTitle = new JLabel();
        panelTitle.setText("Stock Receiving");
        panelTitle.setBounds(150, 0, 250, 50);
        panelTitle.setFont(panelTitle.getFont().deriveFont(labelFontSize));
        panelTitle.setVisible(true);

        headingPanel.add(panelTitle);
        buttonPanel.add(actionButton);

        itemNumber = new JLabel();
        itemNumber.setText("Enter Item Number: ");
        itemNumber.setVisible(true);

        itemNumberField = new JTextField(30);
        itemNumberField.setVisible(true);

        itemDescription = new JLabel();
        itemDescription.setText("Item Description: ");
        itemDescription.setVisible(true);

        descriptionField = new JTextField(30);
        descriptionField.setVisible(true);

        itemUOM = new JLabel();
        itemUOM.setText("Select UOM:  ");
        itemUOM.setVisible(true);

        itemUOMBox = new JComboBox<>(myUOMCombo);
        itemUOMBox.setVisible(true);

        itemQty = new JLabel();
        itemQty.setText("Enter Quantity: ");
        itemQty.setVisible(true);

        itemQtyField = new JTextField(30);
        itemQtyField.setVisible(true);

        itemPrice = new JLabel();
        itemPrice.setText("Cost Price: ");
        itemPrice.setVisible(true);

        itemPriceField = new JTextField(30);
        itemPriceField.setVisible(true);


        //initializing the menu items
      /*  addItem = new JMenuItem("New Item");
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

        receiveStock = new JMenuItem("Receive Stock");
        // receiveStock.setBounds(0, 0, 200, 20);
        receiveStock.addActionListener(this);
        receiveStock.setBorderPainted(false);

        receivedreports = new JMenuItem("Received Stock Report");
        // receivedreports.setBounds(0, 0, 200, 20);
        receivedreports.addActionListener(this);
        receivedreports.setBorderPainted(false);

        receiving = new JMenu("Receive Orders");
        //  receiving.setBounds(0, 120, 200, 40);
        receiving.add(receiveStock);
        receiving.add(receivedreports);
        receiving.setBorderPainted(false);


        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 850, 50);
        //  menuBar.add(newItemMenu);
        menuBar.add(receiving);


        panel = new JPanel(null);
        panel.setBounds(0, 0, 850, 600);
        panel.setVisible(true);


        panelMenu = new JPanel(null);
        panelMenu.setBounds(0, 0, 850, 50);
        panelMenu.add(menuBar);
        panelMenu.setVisible(true);

        panelSouth = new JPanel(null);
        panelSouth.setBounds(0, 550, 850, 50);
        panelSouth.setVisible(true);

        panelMode = new JPanel(null);
        panelMode.setBounds(0, 50, 100, 550);
        //  panelMode.add(modeButton, BorderLayout.CENTER);
        panelMode.setVisible(true);

        panelCenter = new JPanel(new GridLayout(7, 2));
        panelCenter.setBounds(100, 200, 650, 250);

        panelCenter.add(itemNumber);
        panelCenter.add(itemNumberField);
        panelCenter.add(itemDescription);
        panelCenter.add(descriptionField);
        panelCenter.add(itemUOM);
        panelCenter.add(itemUOMBox);
        panelCenter.add(itemPrice);
        panelCenter.add(itemPriceField);
        panelCenter.add(itemQty);
        panelCenter.add(itemQtyField);

        panelCenter.setVisible(true);


        panel.add(panelMenu);
        panel.add(headingPanel);
        panel.add(panelMode);
        panel.add(panelCenter);
        panel.add(buttonPanel);
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
                receiveOrderUpdated();
                upDateDatabase();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    //need to find a way that will reference the updated file
    public void receiveOrderUpdated() throws IOException {

        // addHeading();

        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Receiving\\"+ fileName;
        File tempFile1 = new File(baseFilePath);
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(tempFile1,true));
        Pattern pattern = Pattern.compile("|", Pattern.LITERAL);

        itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        receiveDescription = descriptionField.getText();
        receivedQty = Double.parseDouble(itemQtyField.getText());
        price = Double.parseDouble(itemPriceField.getText());
        UOM = (String) itemUOMBox.getSelectedItem();


        String itemNumberForamted = String.format("%-15s|",itemNumberPassed);
        String description = String.format("%-15s|", receiveDescription);
        String uomFormated = String.format("%-15s|", UOM);
        String priceFormated = String.format("%-15f|", price);
        String receivedQtyFormated = String.format("%-15f|", receivedQty);
        String receivedItem = itemNumberForamted + description + uomFormated+ priceFormated + receivedQtyFormated;


        writer1.append("\n" + receivedItem);
        writer1.close();

        fileCounter++;

    }

    public void upDateDatabase() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        receivedQty = Double.parseDouble(itemQtyField.getText());


        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        String updateStatement = String.format("UPDATE stockitem set qty = qty+%f WHERE itemNumber = %d", receivedQty, itemNumberPassed);
        System.out.println(updateStatement);
        int updatedResultSet = stmt.executeUpdate(updateStatement);
        System.out.println(updatedResultSet + "lines were updated");

        String mySelectStatement = String.format("SELECT qty FROM stockitem WHERE 'itemNumber' = %d", itemNumberPassed);
        ResultSet resultSet = stmt.executeQuery(mySelectStatement);

        int rowCount = 0;
        while (resultSet.next()) {
            itemnumber = resultSet.getInt("itemNumber");
            description = resultSet.getString("description");
            uomNew = resultSet.getString("uom");
            price = resultSet.getDouble("price");
            qty = resultSet.getDouble("qty");
            value = resultSet.getDouble("value");
            parLevel = resultSet.getDouble("parlevel");
            orderQty = resultSet.getDouble("orderQty");
            stringItem = resultSet.getString("StockItemcol");

            stringItem = itemnumber + description + uomNew + price + qty + value + parLevel + orderQty + stringItem;

            System.out.println(stringItem);




        }
    }

    public void addHeading() throws IOException {
        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Receiving\\" + fileName;
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

        writer.write(createdItemsReportFieldHeading);
    }
}

