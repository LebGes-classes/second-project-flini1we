package Storage;

public class ResponsiblePerson extends Human{
    private static String id;
    private static int quantityWorker;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public static int getQuantityWorker() {
        return quantityWorker;
    }

    public static void setQuantityWorker(int quantityWorker) {
        ResponsiblePerson.quantityWorker = quantityWorker;
    }

    public void hireWorker(){
        int k = 0;
        Worker worker = new Worker();
        worker.setValue();
        k++;
        setQuantityWorker(k);
    }
    public void firedWorker(){
        if(getQuantityWorker()>0){
            setQuantityWorker(getQuantityWorker()-1);
            System.out.println("Один работник удален");
        }else{
            System.out.println("Господин я надеюсь это рофл, если нет, то я твоего деда...");
        }
    }
}
