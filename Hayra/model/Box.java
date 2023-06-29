package studio.materiel;

import FrameWork.*;

public class Box {
    String lieu;

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Lien("/home_add")
    public ModelView add_New_home() {
        System.out.println("Hello word");       
    }

    
}
