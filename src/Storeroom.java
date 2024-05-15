import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;

public class Storeroom extends JPanel implements ActionListener {

    File file = new File("storeroom1.txt");
    File tempFile = new File("storerooms1.txt");

    BufferedReader reader;

    FileWriter writer;
    String[] stockItemList;
    // String[] stockItemList;
    StockItem[] storeroom1;
    int listPosition;

    int itemPosition;

    StockItem item18 = new StockItem(1,"Butter Milk", "LTR", 12.5,10 , 0, 30, 0);
    StockItem item1 = new StockItem(2,"Butter", "KG", 12.5,10 , 0, 30, 0);
    StockItem item2 = new StockItem(3,"Rice", "KG", 10,20 , 0, 50, 0);
    StockItem item3 = new StockItem(4,"Flour", "KG", 20,5 , 0, 30, 0);
    StockItem item4 = new StockItem(5,"Butternut", "KG", 10,10 , 0, 30, 0);
    StockItem item5 = new StockItem(6,"Brown Rice", "KG", 50,2 , 0, 10, 0);
    StockItem item6 = new StockItem(7,"eggs", "ea", 5,100 , 0, 400, 0);
    StockItem item7 = new StockItem(8,"Milk", "LTR", 20,5 , 0, 30, 0);
    StockItem item8 = new StockItem(9,"Chicken", "KG", 85,0 , 0, 150, 0);
    StockItem item9 = new StockItem(10,"Beef Stew", "KG", 149,7.5 , 0, 22.5, 0);
    StockItem item10 = new StockItem(11,"wors", "KG", 109,10 , 0, 20, 0);
    StockItem item11 = new StockItem(12,"Spinach", "KG", 12.5,10 , 0, 20, 0);
    StockItem item12 = new StockItem(13,"Tomato", "KG", 24.5,6 , 0, 24, 0);
    StockItem item13= new StockItem(14,"Onions", "KG", 9.6,5 , 0, 10, 0);
    StockItem item14= new StockItem(15,"oil", "LTR", 44.5,5 , 0, 20, 0);
    StockItem item15= new StockItem(16,"Spice BBQ", "KG", 65.50,1 , 0, 3, 0);
    StockItem item16= new StockItem(17,"Spice Chicken", "KG", 80,1 , 0, 3, 0);
    StockItem item17 = new StockItem(18,"Melie-Meal", "KG", 16.50,12.5 , 0, 50, 0);

    String updatedLine = "Mango       KG       15       7       30       10";
    String removedLine = "Mango       KG       15       2       30       10" ;
    Storeroom() throws FileNotFoundException {

        stockItemList = new String[1000];

        listPosition = 1;
        storeroom1 = new StockItem[1000];
        itemPosition = 0;


        storeroom1[0] = item18;
        storeroom1[1] = item1;
        storeroom1[2] = item2;
        storeroom1[3] = item3;
        storeroom1[4] = item4;
        storeroom1[5] = item5;
        storeroom1[6] = item6;
        storeroom1[7] = item7;
        storeroom1[8] = item8;
        storeroom1[9] = item9;
        storeroom1[10] = item10;
        storeroom1[11] = item11;
        storeroom1[12] = item12;
        storeroom1[13] = item13;
        storeroom1[14] = item14;
        storeroom1[15] = item15;
        storeroom1[16] = item16;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



    public void removeLine(String lineContent) throws IOException, IOException {





        try {
            writer = new FileWriter(file,true);
            writer.write(updatedLine);
            reader = new BufferedReader(new FileReader(file));
            int c = 0;
            int j = 0;
            String data;
            String[] dataLines;
            while ((c = reader.read()) != -1){
                data = String.valueOf((char)c);
                dataLines = data.split("|");
                for(String line : dataLines){
                    System.out.println(line);
                }
                if(dataLines[j].equalsIgnoreCase(removedLine)) {
                    // System.out.print("this has to be removed: " + data);

                }else {

                    //  System.out.print(data);
                }
                j++;
                //     System.out.println(updatedLine);
            } {
                //   System.out.println(removedLine);
            }

            //   writer.append(createStockItem());
            //   writer.close();
        } catch (IOException e) {
            System.out.println("an error occured");
            throw new RuntimeException(e);

        }

    }





}

 /*  public void removeLine(String lineContent) throws IOException, IOException {
             File file = new File("storeroom1.txt");
             File temp = new File()
             PrintWriter out = new PrintWriter(new FileWriter(temp));
             Files.lines(file.toPath())
                     .filter(line -> !line.contains(lineContent))
                     .forEach(out::println);
             out.flush();
             out.close();
             temp.renameTo(file);
         }*/



