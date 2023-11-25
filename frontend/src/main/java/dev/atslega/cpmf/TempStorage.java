package dev.atslega.cpmf;

import dev.atslega.cpmf.model.Customer;
import dev.atslega.cpmf.model.Product;
import dev.atslega.cpmf.model.Sale;

import java.util.ArrayList;

public class TempStorage {
    public ArrayList<Customer> customerList = new ArrayList<>();
    public ArrayList<Product> productList = new ArrayList<>();
    public ArrayList<Sale> saleList = new ArrayList<>();

    private final String space = "               ";

    public TempStorage(){
        Customer customer = new Customer("Lasse","Hüls","#3547","lassehuels@gmx.de","+49 1517 25542525","Bierweg 12a \n"+space+"32825 Bomberg \n"+space+"Germany","");
        Customer customer1 = new Customer("Keena-Sophie","Notzon","#2476","jasndja@jansda","+49 1547 51515515","eichenweg 3b \n"+space+"32805 Bad Meinberg \n"+space+"Germany","");
        Customer customer2 = new Customer("Paul","Nixmac","#6542","paulnixmac@gmx.de","+335 15542521","krumestraße 89b \n"+space+"2547 Bremen \n"+space+"Germany","IT mac");
        Customer customer3 = new Customer("Oli","Maxstone","#5894","olimaxstone@gmail.com","+000 148826221251","Himmel 54 \n"+space+"2647 Berlin \n"+space+"Germany","Stone Factory");
        Customer customer4 = new Customer("Max","Sauer","#3617","maxsauer@gmx.de","+49 1578 154628627","Blomberger Str. 24a \n"+space+"32825 Blomberg \n"+space+"Germany","");
        Customer customer5 = new Customer("Lea","Xeno","#3787","leaxeno@gmx.de","+49 1658 176521627","Blomberger Str. 24b \n"+space+"32825 Blomberg \n"+space+"Germany","");
        Customer customer6 = new Customer("Fabian","Dinter","#4829","fabiandinter@gmx.de","+49 1579 151225446","Seeweg 15 \n"+space+"32825 Blomberg \n"+space+"Germany","");
        Customer customer7 = new Customer("Felix","Neuman","#3617","felixneuman@gmx.de","+49 1871 26222061551","Fischerweg 1b \n"+space+"32825 Blomberg \n"+space+"Germany","");
        Customer customer8 = new Customer("Dennis", "Rybin","#3457","dennisrybin@gmx.de","+49 1577 1621142236","Hohenkamp 3b"+space+"32825 Bomberg"+space+"Germany","");

        customerList.add(customer);
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerList.add(customer5);
        customerList.add(customer6);
        customerList.add(customer7);
        customerList.add(customer8);

        Product product = new Product("Handball","#2547","Phoenix Contact","A good Ball for every Handball Player",10,24.50);
        Product product1 = new Product("Bier","#5478","Kronbacher","Wiederliches Bier",1000,2.75);
        Product product2 = new Product("Bundstifte","#6258","Faberkastel","Bundstiffte fürs malen kleiner kinder",100,21.24);
        Product product3 = new Product("Auto","#5487","BMW","Bollerwagen",5,4658.20);
        Product product4 = new Product("Kamera","#2456","Canon","Gut auflösende Kamera",40,560.00);
        Product product5 = new Product("Tastertur","#1348","Lenovo","Scheiß ding!",20,60);
        Product product6 = new Product("Handy","#6549","Sony","Scam",120,542.30);
        Product product7 = new Product("Maus","#3205","ksmkad","Keine sim-card",30,54.4);
        Product product8 = new Product("Wecker","#8426","Schreihals","Nervtötend aber efficent",20,60.54);

        productList.add(product);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);

        Sale sale = new Sale(product,customer,"#2457",19.0,2,product.getPrice(),4,300.2);
        Sale sale1 = new Sale(product2,customer1,"#8754",7.5,0,product2.getPrice(),50,400.54);
        Sale sale2 = new Sale(product3,customer4,"#3514",5.4,2,product3.getPrice(),54,201.20);

        saleList.add(sale);
        saleList.add(sale1);
        saleList.add(sale2);

    }

}
