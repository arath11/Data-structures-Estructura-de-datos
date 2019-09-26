//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//EvaluarExpresion.java
//--


import java.util.function.DoubleUnaryOperator;

public class EvaluaExpresion1 {
    String expresionInfija;

    public void expresionInfija(String expresionInfija) {
        setExpresionInfija(expresionInfija);
    }

    public void setExpresionInfija(String expresionInfija) {
        this.expresionInfija = expresionInfija;
    }

    public double evaluaExpresion() {
        String posfija=expresionPostfijo();
        MyStack<String> pila= new MyStack<String>();
        for(int i =0;i<posfija.length();i++){
            char letra=posfija.charAt(i);
            if(!esOperador(letra)){
                double temp= Double.parseDouble(Character.toString(letra));
                pila.push(Double.toString(temp));
            }else{
                double temp2 = Double.parseDouble(pila.pop());
                double temp1 = Double.parseDouble(pila.pop());
                pila.push(Double.toString(operacion(letra,temp1,temp2)));
            }
        }

        return Double.parseDouble(pila.top());
    }


    private static double operacion(char oper, double temp1, double temp2){
        if(oper=='('){
            return 0;
        }else if(oper=='^'){
            return Math.pow(temp1,temp2);
        }else if(oper=='*'){
            return temp1*temp2;
        }else if(oper=='/') {
            return temp1/temp2;
        }else if(oper=='+') {
            return temp1+temp2;
        }else if(oper=='-') {
            return temp1-temp2;
        }
        return 0;

    }

    public  String expresionPostfijo(){
        String postfija="";
        MyStack<String> pila= new MyStack<String>();
        for(int i=0;i<expresionInfija.length();i++){
            char letra= expresionInfija.charAt(i);
            if(esOperador(expresionInfija.charAt(i))){
                if(pila.isEmpty()){
                    pila.push(Character.toString(letra));
                }else{
                    String a=pila.top();
                    if(pri(letra)>priPila(a.charAt(0))){
                        pila.push(Character.toString(letra));
                    }else {
                        postfija+=pila.pop();
                        pila.push((Character.toString(letra)));
                    }
                }
            }else{
                postfija+=letra;
            }
        }
        while(!pila.isEmpty()){
            postfija+=pila.pop();
        }
        return postfija;
    }


    private static int pri(char oper) {
        if(oper=='('){
            return 5;
        }else if(oper=='^'){
            return 4;
        }else if(oper=='*'){
            return 2;
        }else if(oper=='/') {
            return 2;
        }else if(oper=='+') {
            return 1;
        }else if(oper=='-') {
            return 1;
        }
        return 0;
    }

    private static int priPila(char oper){
        if(oper=='('){
            return 0;
        }else if(oper=='^'){
            return 3;
        }else if(oper=='*'){
            return 2;
        }else if(oper=='/') {
            return 2;
        }else if(oper=='+') {
            return 1;
        }else if(oper=='-') {
            return 1;
        }
        return 0;
    }

    private static boolean esOperador(char a){
        if(a=='^'||a=='+'||a=='-'||a=='/'||a=='*'||a=='('||a=='('){
            return true;
        }
        return false;
    }





    public static void main(String[] args){
        EvaluaExpresion1 prueba=new EvaluaExpresion1();
        //prueba.setExpresionInfija(" 20 * ( 50 / 2 ) ");

        prueba.setExpresionInfija("2+2");
       // prueba.setExpresionInfija("2 + 3 * 4 ^ 5");
        //prueba.setExpresionInfija("( 5 + 10 ) * 3");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());



    }
}
