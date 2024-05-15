import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class OrderPanel2 extends JPanel implements ActionListener {

    File file = new File("orderFile.txt");
    FileWriter writer;
    FileReader reader;

    // create a file to hold orders, upon submitting the order it clears


    //comment below
   // Storeroom orderRelatedPanel = new Storeroom();

    StockItem[] storeroom1;

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

    int itemNumberPassed;
    String stringItem;
    String createdItemsReportFieldHeading;

    float labelFontSize = 20;

    String[] myUOMCombo = {"KG", "LTR", "EA", "BOX", "PACK"};

    String orderItemDescription;
    String stringUOM;
    String stringPrice;
    String stringOrderQty;

    String itemStringRepr;

    //following will be used to navigate through the storeroom positions


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
    JMenuItem placeOrders;
    JMenuItem ordersReport;

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
    JButton autoOrder;

    String orderDescription;


    String orderReportHeadline;

    String autoOrderDisplay;

    String orderItem;
    String OrderedDescription;
    String OrderedUOM;
    double OrderedItemPrice;
    double OrderedQty;
    String[] orderList;

    String label1 = "ITEM NUMBER";
    String label2 = "DESCRIPTION";
    String label3 = "UOM";
    String label4 = "PRICE";
    String label5 = "QUANTITY";
    String label6 = "VALUE";
    String label7 = "PAR LEVEL";
    String label8 = "ORDER QTY";
    String label9 = "SIGNATURE";



    OrderPanel2() throws IOException {


//
        headingPanel = new JPanel(null);
        headingPanel.setBounds(100, 100, 600, 50);

        // headingPanel.setBackground(Color.red);
        headingPanel.setOpaque(true);

        buttonPanel = new JPanel(null);
        buttonPanel.setBounds(100, 400, 650, 100);
        // buttonPanel.setBackground(Color.green);
        buttonPanel.setOpaque(true);

        actionButton = new JButton("Add To Cart");
        actionButton.setBounds(350, 0, 250, 50);
        actionButton.setBorderPainted(false);
        actionButton.addActionListener(this);
        actionButton.setVisible(true);


        panelTitle = new JLabel();
        panelTitle.setText("Place Orders");
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
        itemUOMBox.addKeyListener((KeyListener) KeyStroke.getKeyStroke(descriptionField.getText()));
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
        itemPriceField.addKeyListener((KeyListener) KeyStroke.getKeyStroke(descriptionField.getText()));
        itemPriceField.setVisible(true);


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

        placeOrders = new JMenuItem("Order Stock");
        placeOrders.addActionListener(this);
        placeOrders.setBorderPainted(false);

        ordersReport = new JMenuItem("Orders Report");
        ordersReport.addActionListener(this);
        ordersReport.setBorderPainted(false);


        orders = new JMenu("Place Stock Orders");
        orders.add(placeOrders);
        orders.add(ordersReport);
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
        panelCenter.add(itemQty);
        panelCenter.add(itemQtyField);

        panelCenter.setVisible(true);

        autoPanelCenter = new JPanel(new GridLayout(7, 2));
        autoPanelCenter.setBounds(100, 150, 650, 250);
        autoPanelCenter.setVisible(true);


        panel.add(panelMenu);
        panel.add(headingPanel);
        panel.add(panelMode);
        panel.add(panelCenter);
        panel.add(buttonPanel);
        panel.add(orderDisplayPanel);
        panel.add(panelSouth);

        this.setSize(850, 600);
        this.setLayout(null);
        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            if (!panelCenter.isVisible()) {
                panelCenter.setVisible(true);
            }
            try {
                manualOrder();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }



    public void manualOrder() throws IOException {

        String baseFilePath = "C:\\Users\\ProBook\\Desktop\\Database Files\\Orders\\orders.txt";
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

        //  writer.write(createdItemsReportFieldHeading);

        itemNumberPassed = (int) Double.parseDouble(itemNumberField.getText());
        orderItemDescription = descriptionField.getText();
        orderQty = Double.parseDouble(itemQtyField.getText());
        price = Double.parseDouble(itemPriceField.getText());
        UOM = (String) itemUOMBox.getSelectedItem();


        String itemNumberForamted = String.format("%-15s|",itemNumberPassed);
        String description = String.format("%-15s|", orderItemDescription);
        String uomFormated = String.format("%-15s|", UOM);
        String priceFormated = String.format("%-15f|", price);
        String orderedQtyFormated = String.format("%-15f|", orderQty);
        String orderedItem = itemNumberForamted + description + uomFormated+ priceFormated + orderedQtyFormated;


        writer.append("\n" + orderedItem);
        writer.close();

    }

    static public double customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return value;
    }



}