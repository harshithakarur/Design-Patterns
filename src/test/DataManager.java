package test;

import domain.Buyer;
import domain.Person;
import domain.Product;
import domain.Seller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataManager {
    //Should edit file path
    private static final String FILE_PATH = "C:\\Users\\Harshi\\Documents\\SER 515\\DesignPatterns\\DesignPatterns.hkarur\\src\\test files\\";

    public List<Product> initializeAllProducts() throws Exception
    {
        /*Initialize base on Person TYPE */
        return getDataMap("ProductInfo.txt").keySet().stream().map(Product::new).collect(Collectors.toList());
    }

//    public String fetchPassword(String userName) throws Exception
//    {
//        System.out.println(userName + " in fetch password");
//        List<String> str =  getDataMap("SellerInfo.txt").get(userName) ;
//        System.out.println(getDataMap("SellerInfo.txt").get(userName).toString());
//        System.out.println(str.toString());
//        if(str == null)
//        {
//            str = getDataMap("BuyerInfo.txt").get(userName);
////            System.out.println(str);
//        }
//        return str.get(0);
//    }

    public String fetchPassword(String userName) throws Exception
    {

        List<String> str =  getDataMap("SellerInfo.txt").get(userName) ;
        if(str == null)
        {
            str = getDataMap("BuyerInfo.txt").get(userName);
        }
        return str.get(0);
    }

    public Person initializePerson(String userName) throws Exception
    {
        /*Initialize base on Person TYPE */
        if(!getDataMap("SellerInfo.txt").containsKey(userName))
        {
            System.out.println("Going to if");
            return new Buyer(userName);
        }
        System.out.println("Not Going to if");
        return new Seller(userName);
    }
    public List<Product> fetchProductMappingForUser(String name) throws Exception
    {
        /*Fetch course file base on username*/
        Map<String, List<String>> productMapping =  getDataMap("UserProduct.txt");
        List<String> productList = productMapping.get(name);
        return productList.stream().map(strProduct -> new Product(strProduct)).collect(Collectors.toList());
    }
    private Map<String, List<String>> getDataMap(String fileName) throws Exception
    {
//        System.out.println(fileName);
        File file = new File(FILE_PATH+fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        HashMap<String, List<String>> dataMapping = new HashMap<>();
//        str = br.readLine();
//        System.out.println(str);
        while((str = br.readLine()) != null)
        {
//            System.out.println(str);
            String[] token = str.split(":",2);
            List<String> valueList = new ArrayList<>() ;
            if(token.length==2) {
                if(dataMapping.containsKey(token[0])) {
                    valueList = dataMapping.get(token[0]);
                    valueList.add(token[1]);
                }
                else  valueList.add(token[1]);
            }
//            System.out.println(token[0]);


            dataMapping.put(token[0], valueList);
//            System.out.println(dataMapping);
        }
        return dataMapping;
    }

}
