public class NReinasPractica {
    //creamos un tablero
    private int[] reinas;
    public NReinasPractica(int n){
        this.reinas=new int[n];
        for(int i=0;i<this.reinas.length;i++){
            reinas[i]=-1;
        }
    }

    public void busca(){
        busca(0);//fil
    }

    public boolean valida(int fila, int columna) {
        for(int i=0;i<fila;i++) {
            //misma columna
            if(this.reinas[i]==columna) {//ya existe otra reina
                return false;
            }//diagonales
            else if(Math.abs(columna-this.reinas[i])==Math.abs(fila-i)) {
                return false;
            }
        }
        return true;
    }

    /*public void busca(int fila){
        for(int i=0;i<this.reinas.length;i++){
            if(valida(fila,i)){
                this.reinas[fila]=i;
                if(fila<this.reinas.length-1){
                    busca(fila+1);
                }else{
                    this.imprimeSolucion();
                }
            }
        }
    }*/

    public void busca(int fila){
        for(int i =0;i<this.reinas.length;i++){
            if(valida(fila,i)){
                this.reinas[fila]=i;
                if(fila<this.reinas.length-1){
                    busca(fila+1);
                }else{
                    imprimeSolucion();
                }
            }
        }
    }

    public void imprimeSolucion() {
        for(int i=0;i<this.reinas.length;i++) {
            System.out.print(this.reinas[i]+",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NReinasPractica tablero=new NReinasPractica(8);
        //tablero.busca();
        tablero.busca(0);

    }
}
