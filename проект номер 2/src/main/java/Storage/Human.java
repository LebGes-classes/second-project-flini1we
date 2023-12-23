package Storage;

import java.util.Scanner;

public abstract class Human {
    static Scanner scan = new Scanner(System.in);
    private String initials;
    private String dataBirth;

    public String getInitials() {
        return initials;
    }

    public String getDataBirth() {
        return dataBirth;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setDataBirth(String dataBirth) {
        this.dataBirth = dataBirth;
    }

    public void setValue(){
        System.out.print("\tВведите инициалы человека: ");
        setInitials(scan.next());

        System.out.print("\tВведите дату рождения: ");
        setDataBirth(scan.next());
    }

}
