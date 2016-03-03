package risk;

public class risk {
    
    public static void TotElTauler (int [][] tauler, String [] territoris, String[] nomsJugadors) {
        System.out.println("Este es el tablero de adri:");
        for(int territori=0;territori<tauler.length;territori++){
        	//Pais Jugador Tropas
            System.out.println(territoris[territori]+"-"+nomsJugadors[tauler[territori][0]]+"-"+tauler[territori][1]);    
        }
    }
    
    public static void PaisosPropis (int [][] tauler, String [] territoris, String[] nomsJugadors, int tropasactuales) {
    	System.out.println("Estos son tus paises con tus tropas:");
    	 for(int territori=0;territori<tropasactuales;territori++){
             //System.out.println(territoris[territori]+"-"+nomsJugadors[tauler[territori][0]]+"-"+tauler[territori][1]);    
             System.out.println(territoris[territori]+"- Tropas - "+tauler[territori][1]);   
    	 }
    	
    }
    
}