package dev.atslega.cpmf.model;

public class Company {
    private String companyName;
    private String companyAddress;

    private Product latestProduct;
    private Customer latestCustomer;
    private Order latestOrder;

    private long customerCount;
    private long orderCount;
    private long productCount;
    private long userCount;

    public Company() {
    }

    public Company(String companyName, String companyAddress, Product latestProduct, Customer latestCustomer, Order latestOrder, long customerCount, long orderCount, long productCount, long userCount) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.latestProduct = latestProduct;
        this.latestCustomer = latestCustomer;
        this.latestOrder = latestOrder;
        this.customerCount = customerCount;
        this.orderCount = orderCount;
        this.productCount = productCount;
        this.userCount = userCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Product getLatestProduct() {
        return latestProduct;
    }

    public void setLatestProduct(Product latestProduct) {
        this.latestProduct = latestProduct;
    }

    public Customer getLatestCustomer() {
        return latestCustomer;
    }

    public void setLatestCustomer(Customer latestCustomer) {
        this.latestCustomer = latestCustomer;
    }

    public Order getLatestOrder() {
        return latestOrder;
    }

    public void setLatestOrder(Order latestOrder) {
        this.latestOrder = latestOrder;
    }

    public long getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(long customerCount) {
        this.customerCount = customerCount;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", latestProduct=" + latestProduct +
                ", latestCustomer=" + latestCustomer +
                ", latestOrder=" + latestOrder +
                ", customerCount=" + customerCount +
                ", orderCount=" + orderCount +
                ", productCount=" + productCount +
                ", userCount=" + userCount +
                '}';
    }
}
