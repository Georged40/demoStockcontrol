public class StockItem {

    //Explains the attributes of a stock item

    //code below going with the ones below
    //  static StockItem[] store = new StockItem[1000];


    int itemNumber;
    String description;

    String uom;

    double price;

    double quantity = 0;

    double value;

    double parLevel;
    double orderQty = 0;
    double orderValue;

    public static int i = 0;

    static int maxItems = 1000;

    static String printStockvalue;

    String[] storeroom;

    StockItem(int itemNumber,String description, String uom, double price, double quantity, double value, double parLevel,
              double orderQty){

        this.itemNumber = itemNumber;
        this.description = description;
        this.uom = uom;
        this.price = price;
        this.quantity = quantity;
        this.value = this.price * this.quantity;
        this.orderValue = this.price * this.orderQty;
        this.parLevel = parLevel;
        this.orderQty = this.parLevel - this.quantity;


    }

    StockItem(StockItem stockItem) {
        this.description = stockItem.description;
        this.uom = stockItem.uom;
        this.quantity = stockItem.quantity;
        this.parLevel = stockItem.parLevel;
        this.orderQty = stockItem.orderQty;
        this.value = stockItem.value;
        this.price = stockItem.price;
        this.orderValue = stockItem.orderValue;
        this.value = this.price * this.quantity;
        this.orderValue = this.price * this.orderQty;
    }

    StockItem(String description, String uom, double price, double quantity, double value){
        this.description = description;
        this.uom = uom;
        this.price = price;
        this.quantity = quantity;
        this.value = value;
    }
}
