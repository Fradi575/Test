package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Sakktabla {
    private Mezo[][] mezok = new Mezo[5][4];
    public Sakktabla(){
        for(int i=0; i<5; i++)
            for(int j=0; j<4; j++){
                mezok[i][j] = new Mezo(0,0);
            }
        for(int k=0; k<4; k++){
            mezok[0][k].setBabu(1);
            mezok[4][k].setBabu(2);
        }
        for(int i=0; i<5; i++)
            for(int j=0; j<4; j++){
                if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) mezok[i][j].setSzin(1);
                else mezok[i][j].setSzin(0);
            }

    }
    public void mozgat(int honnan_x, int honnan_y, int hova_x, int hova_y) {
        if (mezok[honnan_x][honnan_y].getBabu() == 0) {
            return;
        }
        if (mezok[hova_x][hova_y].getBabu() != 0) {
            return;
        }
        if(!((hova_x-honnan_x == hova_y-honnan_y)||(-hova_x+honnan_x == hova_y-honnan_y))){ //el tud-e indulni az előző helyről a másikra
            return;
        }
        int jelenlegi_babu = mezok[honnan_x][honnan_y].getBabu();
        mezok[honnan_x][honnan_y].setBabu(0);
        mezok[hova_x][hova_y].setBabu(jelenlegi_babu);

    }

    public boolean szabade(int honnan_x, int honnan_y, int hova_x, int hova_y){
        //itt lenne a próbálkozásom, ami szerintem nem jó, de próbálkoztam
        if(((hova_x-honnan_x == hova_y-honnan_y)||(-hova_x+honnan_x == hova_y-honnan_y)) && mezok[honnan_x][honnan_y].getBabu() != mezok[hova_x][hova_y].getBabu()) return false;
        else return true;
    }
}
