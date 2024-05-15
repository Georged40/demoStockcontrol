
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DecimalFormat;
//import com.mysql.cj.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IssuesPanel extends JPanel implements ActionListener {

   Storeroom storeroom = new Storeroom();

    static int itemNumberPassed;
    static double receivedQty;
    static int itemnumber;

    //stockItem attributes
    String issuesDescription;
    String issuedItem;
    JTextArea createdItemsReportField;
    String UOM;
    String uomNew;
    String description;
    double price;
    double issuesQty;
    double receiveQty;
    double value;
    double orderQty;
    double parLevel;
    double issuedQty;
    String stringStockItem;
    String stringItem;
    String createdItemsReportFieldHeading;

    float labelFontSize = 20;

    String[] myUOMCombo = {"KG", "LTR", "EA", "BOX", "PACK"};

    //following will be used to navigate through the storeroom positions
    int itemPosition;

    //stockItem to hold created items
    StockItem newItem;

    //stockItem array to hold the stock
    StockItem[] storeroom1;


    JPanel panel;
    JPanel panelMenu;
    JPanel panelMode;
    JPanel panelCenter;
    JPanel orderCenterPanel;
    JPanel panelEast;

    JPanel panelSouth;
    JPanel headingPanel;
    JPanel buttonPanel;


    OrderPanel2 orderPanel = new OrderPanel2();


    //menu related declarations
    JMenuBar menuBar;

    JMenu issues;
    JMenuItem issueStock;
    JMenuItem issuesReport;

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

    static Connection connection = null;
    static String databaseName = "storeroom";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String password = "Owe22Phila20";
    String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Issues\\myIssues.txt";


    String label1 = "ITEM NUMBER";
    String label2 = "DESCRIPTION";
    String label3 = "UOM";
    String label4 = "PRICE";
    String label5 = "QUANTITY";
    String label6 = "VALUE";
    String label7 = "PAR LEVEL";
    String label8 = "ORDER QTY";
    String label9 = "SIGNATURE";


    IssuesPanel() throws IOException {


         storeroom1 = storeroom.storeroom1;

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
        headingPanel.setOpaque(true);

        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(100, 450, 650, 50);
        buttonPanel.setOpaque(true);

        actionButton = new JButton("Issue Item");
        actionButton.setBounds(350, 0, 250, 50);
        actionButton.setBorderPainted(false);
        actionButton.addActionListener(this);
        actionButton.setVisible(true);


        panelTitle = new JLabel();
        panelTitle.setText("Issues Panel");
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


        issueStock = new JMenuItem("Issue Stock");
        issueStock.addActionListener(this);
        issueStock.setBorderPainted(false);

        issuesReport = new JMenuItem("Issues Report");
        issuesReport.addActionListener(this);
        issuesReport.setBorderPainted(false);

        issues = new JMenu("Issue Stock");
        issues.add(issueStock);
        issues.add(issuesReport);
        issues.setBorderPainted(false);


        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 850, 50);
        menuBar.add(issues);


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
                issueingStock();
                issueFromDatabase();
                //pullaDataFromDatabase();
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
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void issueingStock() throws IOException {
        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Issues\\myIssues.txt";
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
        issuesDescription = descriptionField.getText();
        issuedQty = Double.parseDouble(itemQtyField.getText());
        price = Double.parseDouble(itemPriceField.getText());
        UOM = (String) itemUOMBox.getSelectedItem();


        String itemNumberForamted = String.format("%-15s|",itemNumberPassed);
        String description = String.format("%-15s|", issuesDescription);
        String uomFormated = String.format("%-15s|", UOM);
        String priceFormated = String.format("%-15f|", price);
        String issuedQtyFormated = String.format("%-15f|", issuedQty);
        String issuedItem = itemNumberForamted + description + uomFormated+ priceFormated + issuedQtyFormated;


        writer.append("\n" + issuedItem);
        writer.close();

/*

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));


        String currentLine;
        String[] splitLine;
        String issuesDescription;
        String[] issuedRecords = new String[100];
        int issueRecordsPointer = 0;

        double itemValue = 0;
        double oldQuantity = Double.parseDouble(itemQtyField.getText());


        boolean checkingWith = true;
        while ((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            Thread.currentThread();

            Matcher matcher = pattern.matcher(trimmedLine);
            boolean isFound = matcher.find();
            String checkWith = "";
            System.out.println("this is the value of isFound: " + isFound);
            if (isFound) {
                splitLine = trimmedLine.split(String.format("[.\b|]"));
                checkWith = splitLine[0];
                oldQuantity = Double.parseDouble(splitLine[4]);
            }


            receiveQty = Double.parseDouble(itemQtyField.getText());

            double newQty = oldQuantity - receiveQty;
            double value = newQty * price;
            double issuesValue = receiveQty * price;



            double formatedQty = customFormat("%-15d.2|", newQty);
            double valueFormated = customFormat("%-15d.2|", value);
            double issuesvalueFormated = customFormat("%-15d.2|", issuesValue);
            double issuedQtyFormated = customFormat("%-15d.2|", newQty);
            String issuedItem = description + uomFormated + priceFormated + issuedQtyFormated + issuesvalueFormated;


            String convertedLine;

            System.out.println(issuedItem);


            if (checkingWith) {
                writer.write(issuedItem);
                issuedRecords[issueRecordsPointer] = issuedItem;
                System.out.println(issuedItem + " This is from issues records " + issueRecordsPointer);
                checkingWith = false;

            } else {
                writer.append("\n" + currentLine + System.getProperty("line.separator"));
                System.out.println("It matched, already written");
                reader.readLine();
                issueRecordsPointer++;
            }


        }

        writer.close();
        reader.close();
        inputFile.delete();
        System.out.println(tempFile.isFile());
        tempFile.renameTo(inputFile);*/

        System.out.println("From issues method");
    }

    static public double customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);
        return value;
    }

    public void issueFromDatabase() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException, IOException {




        itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        issuesDescription = descriptionField.getText();
        issuedQty = Double.parseDouble(itemQtyField.getText());
        issuedItem = itemNumberPassed + "|" + issuesDescription+"|" + issuedQty + "|";


        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        String updateStatement = String.format("UPDATE stockitem set qty = qty-%f WHERE itemNumber = %d", issuedQty, itemNumberPassed);
        System.out.println(updateStatement);
        int updatedResultSet = stmt.executeUpdate(updateStatement);
        System.out.println(updatedResultSet + "lines were updated");
        System.out.println(updateStatement);

        String mySelectStatement = String.format("SELECT qty FROM stockitem WHERE 'itemNumber' = %d", itemNumberPassed);
        ResultSet resultSet = stmt.executeQuery(mySelectStatement);

        int rowCount = 0;
        while (resultSet.next()) {
            itemnumber = resultSet.getInt("itemNumber");
            description = resultSet.getString("description");
            uomNew = resultSet.getString("uom");
            price = resultSet.getDouble("price");
            issuesQty = resultSet.getDouble("qty");
            value = resultSet.getDouble("value");
            parLevel = resultSet.getDouble("parlevel");
            orderQty = resultSet.getDouble("orderQty");
            stringItem = resultSet.getString("StockItemcol");

            stringItem = itemnumber + description + uomNew + price + issuesQty + value + parLevel + orderQty + stringItem;


            System.out.println(issuedItem);


        }
    }

    public void pullaDataFromDatabase() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        Statement stmt = connection.createStatement();

        String updateStatement = String.format("UPDATE stockitem set qty = qty-%f WHERE itemNumber = %d", issuedQty, itemNumberPassed);
        System.out.println(updateStatement);
        int updatedResultSet = stmt.executeUpdate(updateStatement);
        System.out.println(updatedResultSet + "lines were updated");
        System.out.println(updateStatement);

        // String mySelectStatement = String.format("SELECT * FROM stockitem INTO OUTFILE %s",myfilePath);
        ResultSet resultSet = stmt.executeQuery(updateStatement);

        int rowCount = 0;
        while (resultSet.next()) {
            itemnumber = resultSet.getInt("itemNumber");
            description = resultSet.getString("description");
            uomNew = resultSet.getString("uom");
            price = resultSet.getDouble("price");
            issuesQty = resultSet.getDouble("qty");
            value = resultSet.getDouble("value");
            parLevel = resultSet.getDouble("parlevel");
            orderQty = resultSet.getDouble("orderQty");
            stringItem = resultSet.getString("StockItemcol");

            stringItem = itemnumber + description + uomNew + price + issuesQty + value + parLevel + orderQty + stringItem;

            System.out.println("items pulled");


        }
    }

    public  String leftPadding(String input, char ch, int L) {

        String result = String.format("%" + L + "s", input).replace(' ', ch);
        return result;
    }

}

