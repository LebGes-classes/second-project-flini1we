package Storage;

public class PointOfSale extends Storage{
    int money;
    public void sale(Human human){
        if(isState()) {
            String response;
            do {
                System.out.println("В НАЛИЧИНИИ: ");
                System.out.println(products.toString());
                System.out.println("Выберите товары для покупки: ");
                int temp = scan.nextInt();
                money += products.get(temp - 1).price;
                products.remove(temp - 1);



                System.out.println("Благодарим вас за покупку! Желаете продолжить? ");
                response = scan.next();
            } while (response.equalsIgnoreCase("да"));
        }
//        }else{
//            System.out.println("Братишка пункт закрыт");
//        }
    }
}
