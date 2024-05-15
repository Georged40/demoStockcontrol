import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class CreateEditPanel extends JFrame implements ActionListener {


    //Font font =  new Font("TimesRoman", Font.BOLD | Font.ITALIC, 20);

    static int newitemnumber;
    static String newdescription;
    static String newUOM;
    static double newprice;
    static double newqty;
    static double newvalue;
    static double newparLevel;
    static double neworderQty;
    static String newstringItem;
    static String formattedString;
    static String checkUpdatedItem;
    static String newuomNew;

    //For Database connection
    static Connection connection = null;
    static String databaseName = "storeroom";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String password = "Owe22Phila20";

    // creating my storeroom file
    File file = new File("C:\\Users\\ProBook\\Desktop\\Database Files\\storeroom1.txt");
    //creating my filewriter instance
    FileWriter writer;


    //bringing in order propertied

    //commenting the below

   // Storeroom forOrders = new Storeroom();

    //creating a tabbedPane to alternate my panels
    JTabbedPane tabbedPane;

    //creating an instance of the orderPanel

    //commenting the below
  //  OrderPanel2 panel2 = new OrderPanel2();
   // ReceivingPanel panel3 = new ReceivingPanel();
   // IssuesPanel panel4 = new IssuesPanel();
  //  deletepanel panel5 = new deletepanel();


    //stockItem attributes
    int itemNumber;
    String description;
    JTextArea createdItemsReportField;
    String UOM;
    double price;
    double qty;
    double value;
    double parLevel;
    double orderQty;

    float labelFontSize = 20;


    String stringItem;
    String createdItemsReportFieldHeading;

    String[] myUOMCombo = {"KG", "LTR", "EA", "BOX", "PACK"};

    int itemPosition;
    StockItem newItem;

    StockItem[] storeroom1;
    JPanel panel;
    JPanel panelMenu;
    JPanel panelMode;
    JPanel panelCenter;
    JPanel panelSouth;
    JPanel headingPanel;
    JPanel buttonPanel;

    JMenuBar menuBar;
    JMenu mainMenu2;

    JMenu newItemMenu;
    JMenu forStockReports;
    JMenuItem addItem;
    JMenuItem itemReport;
    JMenuItem stockReport;
    JMenu editMenu;
    JMenuItem editItem;

    JLabel itemDescription;
    JLabel itemUOM;
    JLabel itemQty;
    JLabel itemPrice;
    JLabel itemValue;
    JLabel itemParLevel;
    JLabel itemOrderQty;
    JLabel panelTitle;

    JTextField descriptionField;

    JComboBox itemUOMBox;
    JLabel itemNumberToEdit;
    JTextField itemNumberField;
    JTextField itemQtyField;
    JTextField itemPriceField;
    JTextField itemValueField;
    JTextField itemParLevelField;
    JTextField itemOrderField;

    JButton actionButton;
    JButton editButton;

    String[] stockItemList;

    int listPosition;

    String label1 = "ITEM NUMBER";
    String label2 = "DESCRIPTION";
    String label3 = "UOM";
    String label4 = "PRICE";
    String label5 = "QUANTITY";
    String label6 = "VALUE";
    String label7 = "PAR LEVEL";
    String label8 = "ORDER QTY";
    String label9 = "SIGNATURE";


    CreateEditPanel() throws IOException {


        tabbedPane = new JTabbedPane();


        headingPanel = new JPanel(null);
        headingPanel.setBounds(100, 100, 600, 50);
        // headingPanel.setBackground(Color.red);
        headingPanel.setOpaque(true);

        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(100, 400, 650, 50);
        // buttonPanel.setBackground(Color.green);
        buttonPanel.setOpaque(true);

        actionButton = new JButton("Create Item");
        actionButton.setBounds(350, 0, 250, 50);
        actionButton.setBorderPainted(false);
        actionButton.addActionListener(this);
        actionButton.setVisible(true);

        editButton = new JButton("Save Edited Item");
        editButton.setBounds(350, 0, 250, 50);
        editButton.setBorderPainted(false);
        editButton.addActionListener(this);
        editButton.setVisible(false);


        panelTitle = new JLabel();
        panelTitle.setText("Create New Stock Item");
        panelTitle.setBounds(150, 0, 250, 20);
        panelTitle.setFont(panelTitle.getFont().deriveFont(labelFontSize));
        panelTitle.setVisible(true);

        headingPanel.add(panelTitle);
        buttonPanel.add(actionButton);
        buttonPanel.add(editButton);

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

        itemValue = new JLabel();
        itemValue.setText("Line Value: ");
        itemValue.setVisible(true);

        itemValueField = new JTextField(30);
        itemValueField.setVisible(true);

        itemParLevel = new JLabel();
        itemParLevel.setText("Par Level: ");
        itemParLevel.setVisible(true);

        itemParLevelField = new JTextField(30);
        itemParLevelField.setVisible(true);

        itemOrderQty = new JLabel();
        itemOrderQty.setText("Order Qty: ");
        itemOrderQty.setVisible(true);

        itemOrderField = new JTextField(30);
        itemOrderField.setVisible(true);


        addItem = new JMenuItem("New Item");
        addItem.addActionListener(this);
        addItem.setFocusPainted(false);


        newItemMenu = new JMenu("New StockItem");
        newItemMenu.add(addItem);
        newItemMenu.setBorderPainted(false);

        stockReport = new JMenuItem("Current Stock Report");
        stockReport.addActionListener(this);
        stockReport.setFocusPainted(false);


        forStockReports = new JMenu("Get Stock Report");
        forStockReports.add(stockReport);
        forStockReports.setBorderPainted(false);


        editItem = new JMenuItem("Modify Stock Item");
        editItem.addActionListener(this);
        editItem.setBorderPainted(false);

        editMenu = new JMenu("Edit Stock Item");
        editMenu.add(editItem);
        editMenu.addActionListener(this);
        editMenu.setBorderPainted(false);

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 850, 50);
        menuBar.add(newItemMenu);
        menuBar.add(editMenu);
        menuBar.add(forStockReports);



        itemNumberField = new JTextField(30);
        itemNumberField.setVisible(true);

        itemNumberToEdit = new JLabel();
        itemNumberToEdit.setText("Enter Item Number: ");
        itemNumberToEdit.setVisible(true);



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
        panelMode.setVisible(true);

        panelCenter = new JPanel(new GridLayout(8, 2));
        panelCenter.setBounds(100, 150, 650, 250);

        panelCenter.add(itemNumberToEdit);
        panelCenter.add(itemNumberField);
        panelCenter.add(itemDescription);
        panelCenter.add(descriptionField);
        panelCenter.add(itemUOM);
        panelCenter.add(itemUOMBox);
        panelCenter.add(itemPrice);
        panelCenter.add(itemPriceField);
        panelCenter.add(itemQty);
        panelCenter.add(itemQtyField);
        panelCenter.add(itemValue);
        panelCenter.add(itemValueField);
        panelCenter.add(itemParLevel);
        panelCenter.add(itemParLevelField);
        panelCenter.add(itemOrderQty);
        panelCenter.add(itemOrderField);
        panelCenter.setVisible(true);


        panel.add(panelMenu);
        panel.add(headingPanel);
        panel.add(panelMode);
        panel.add(panelCenter);
        panel.add(buttonPanel);
        panel.add(panelSouth);

        tabbedPane.setBounds(0,0,830,580);
        tabbedPane.add("New Stock Item", panel);
        //commented the below
     //   tabbedPane.add("Order Stock", panel2);
    //    tabbedPane.add("Receive Stock", panel3);
     //   tabbedPane.add("Issue Stock", panel4);
     //   tabbedPane.add("Delete Stock Item", panel5);

        //this.setFont(font);
        this.setSize(850, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(tabbedPane);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editItem){
            ChangeFromAddToEdit();
        } else if (e.getSource() == addItem){
            BackToAddItem();
        } else if (e.getSource() == actionButton) {
            try {
                addToDatabaseAndWriteToFile(createStockItem());
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
        }else if(e.getSource() == editButton){
            try {
                savingEditedItem(createStockItem());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchMethodException | IOException ex) {
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
        }else if(e.getSource() == stockReport){
            try {
                pullingStockReports();
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
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Pulling stock reports");
        }

    }

    public StockItem createStockItem () throws IOException {

        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\NewItems\\addedItems.txt";
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




        itemNumber = Integer.parseInt(itemNumberField.getText());
        description = descriptionField.getText();
        price = Double.parseDouble(itemPriceField.getText());
        qty = Double.parseDouble(itemQtyField.getText());
        value = Double.parseDouble(itemValueField.getText());
        parLevel = Double.parseDouble(itemParLevelField.getText());
        orderQty = Double.parseDouble(itemOrderField.getText());
        UOM = itemUOMBox.getSelectedItem().toString();


        String itemNumberForamted = String.format("%-15s|",itemNumber);
        String descriptionFormatted = String.format("%-15s|", description);
        String uomFormated = String.format("%-15s|", UOM);
        String priceFormated = String.format("%-15f|", price);
        String qtyFormated = String.format("%-15f|", qty);
        String valueFormated = String.format("%-15f|", value);
        String parLevelFormated = String.format("%-15f|", parLevel);
        String orderQtyFormatted = String.format("%-15f|", orderQty);
        String createdItem = itemNumberForamted + descriptionFormatted + uomFormated+ priceFormated + qtyFormated +
                valueFormated + parLevelFormated + orderQtyFormatted;

        writer.append("\n" + createdItem);
        writer.close();




        StockItem item = new StockItem(itemNumber,description, UOM, price, qty, price * qty, parLevel, parLevel - qty);

        return item;

    }

    public String savingEditedItem(StockItem item) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, IOException {

        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Modified\\editedItems.txt";
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

        //writer.write(createdItemsReportFieldHeading);


        newitemnumber = item.itemNumber;
        newdescription = item.description;
        newprice = item.price;
        newqty = item.quantity;
        newvalue = item.value;
        newparLevel = item.parLevel;
        neworderQty = item.orderQty;
        newstringItem = "'ToBeRemoved'";
        newuomNew = item.uom;

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);

        formattedString = String.format("UPDATE stockitem set description = \"%s\",uom=\"%s\",qty=%f,price=%f,value=%f,parlevel =%f,orderQty=%f,stockItemcol=%s\n " +
                        "WHERE itemNumber = %d",
                newdescription, newuomNew, newqty, newprice, newvalue, newparLevel, neworderQty, newstringItem,newitemnumber);

        PreparedStatement ps = connection.prepareStatement(formattedString);

        int status = ps.executeUpdate();
        System.out.println("This is the status: "+ status);
        if (status < 0) {
            System.out.println("Item added to database");
        } else {
            System.out.println(status);
        }
        String itemNumberFormated = String.format("%-15s|", newitemnumber);
        //String itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        String descriptionFormated = String.format("%-15s|", newdescription);
        String uomFormated = String.format("%-15s|", newuomNew);
        String priceFormated1 = String.format("%-15s|", newprice);
        String qtyFormated1 = (String.format("%-15s|", newqty));
        String valueFormated1 = (String.format("%-15s|", newvalue));
        String parLevelFormated1 = (String.format("%-15s|", newparLevel));
        String orderqtyFormated1 = (String.format("%-15s|", neworderQty));


        stringItem = itemNumberFormated + descriptionFormated + uomFormated + priceFormated1 + qtyFormated1 +
                valueFormated1 + parLevelFormated1 + orderqtyFormated1;

        writer.append("\n" + stringItem);
        writer.close();

        return stringItem;

    }


    public String addToDatabaseAndWriteToFile(StockItem item) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        newitemnumber = item.itemNumber;
        newdescription = item.description;
        newprice = item.price;
        newqty = item.quantity;
        newvalue = item.value;
        newparLevel = item.parLevel;
        neworderQty = item.orderQty;
        newstringItem = "'ToBeRemoved'";
        newuomNew = item.uom;

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);

        formattedString = String.format("INSERT INTO storeroom.stockitem\n" +
                "VALUES (%d,'%s', '%s',%f ,%f,%f ,%f,%f,%s)", newitemnumber, newdescription, newuomNew, newprice, newqty, newvalue, newparLevel, neworderQty, newstringItem);

        PreparedStatement ps = connection.prepareStatement(formattedString);
        System.out.println(formattedString);


        int status = ps.executeUpdate();
        if (status < 0) {
            System.out.println("Item added to database");
        } else {
            System.out.println(status);
        }

        String itemNumberFormated = String.format("%-15s|", newitemnumber);
        //String itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        String descriptionFormated = String.format("%-15s|", newdescription);
        String uomFormated = String.format("%-15s|", newuomNew);
        String priceFormated1 = String.format("%-15s|", newprice);
        String qtyFormated1 = (String.format("%-15s|", newqty));
        String valueFormated1 = (String.format("%-15s|", newvalue));
        String parLevelFormated1 = (String.format("%-15s|", newparLevel));
        String orderqtyFormated1 = (String.format("%-15s|", neworderQty));


        stringItem = itemNumberFormated + descriptionFormated + uomFormated + priceFormated1 + qtyFormated1 +
                valueFormated1 + parLevelFormated1 + orderqtyFormated1;

        return stringItem;
    }

    public void ChangeFromAddToEdit(){
        panelTitle.setText("Edit Stock Items");
        actionButton.setVisible(false);
        editButton.setVisible(true);

    }
    public void BackToAddItem(){
        panelTitle.setText("Add New Item");
        itemDescription.setText("Item Description: ");
        actionButton.setText("Create Item");

    }
    static public double customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return value;
    }


    public void pullingStockReports() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, IOException {
        int counter = 1;
        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Stock Reports\\currentReport"+counter +".txt";
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

        // Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        String updateStatement = String.format("SELECT * FROM stockitem");
        System.out.println(updateStatement);

        ResultSet resultSet = stmt.executeQuery(updateStatement);

        System.out.println(resultSet + "lines were updated");

        int rowCount = 0;
        while (resultSet.next()) {
            newitemnumber = Integer.parseInt(resultSet.getString("itemNumber"));
            description = resultSet.getString("description");
            newuomNew = resultSet.getString("uom");
            price = resultSet.getDouble("price");
            newqty = resultSet.getDouble("qty");
            newvalue = resultSet.getDouble("value");
            newparLevel = resultSet.getDouble("parlevel");
            neworderQty = resultSet.getDouble("orderQty");
            newstringItem = resultSet.getString("stockItemcol");

            String itemNumberFormated = String.format("%-15s|", newitemnumber);
            //String itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
            String descriptionFormated = String.format("%-15s|", description);
            String uomFormated = String.format("%-15s|", newuomNew);
            String priceFormated1 = String.format("%-15s|", price);
            String qtyFormated1 = (String.format("%-15s|", newqty));
            String valueFormated1 = (String.format("%-15s|", newvalue));
            String parLevelFormated1 = (String.format("%-15s|", newparLevel));
            String orderqtyFormated1 = (String.format("%-15s|", neworderQty));


            stringItem = itemNumberFormated + descriptionFormated + uomFormated + priceFormated1 + qtyFormated1 +
                    valueFormated1 + parLevelFormated1 + orderqtyFormated1;

            writer.append("\n" + stringItem);


        }
        counter ++;
        writer.close();
    }

}
