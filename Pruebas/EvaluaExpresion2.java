//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//EvaluarExpresion.java
//--


import java.util.function.DoubleUnaryOperator;

public class EvaluaExpresion2 {
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




    private static double operacion(char oper, double temp2, double temp1){
        if(oper=='^'){
            return Math.pow(temp1,temp2);
        }else if(oper=='*'){
            return temp1*temp2;
        }else if(oper=='/') {
            return temp2/temp1;
        }else if(oper=='+') {
            return temp1+temp2;
        }else if(oper=='-') {
            return temp1-temp2;
        }
        return 0;

    }

    public  String expresionPostfijo1(){
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

    public  String expresionPostfijo() {
        String expr = depurar(expresionInfija);//quitar espacios
        String[] arrayInfix = expr.split(" ");
        MyStack<String> Entrada = new MyStack<String>();
        MyStack<String> Temporal = new MyStack<String>();
        MyStack<String> Salida = new MyStack<String>();
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            Entrada.push(arrayInfix[i]);
        }

        while (!Entrada.isEmpty()) {
            switch (prefijo(Entrada.top())) {
                case 1:
                    Temporal.push(Entrada.pop());
                    break;
                case 3:
                case 4:
                    while (prefijo(Temporal.top()) >= prefijo(Entrada.top())) {
                        Salida.push(Temporal.pop());
                    }
                    Temporal.push(Entrada.pop());
                    break;
                case 2:
                    while (!Temporal.top().equals("(")) {
                        Salida.push(Temporal.pop());
                    }
                    Temporal.pop();
                    Entrada.pop();
                    break;
                default:
                    Salida.push(Entrada.pop());
            }
        }
        String regresar = "";
        while (!Salida.isEmpty()) {
            regresar = Salida.pop() + " " + regresar + " ";
        }
        return regresar;
    }

    private static String depurar(String entrada) {
        entrada = entrada.replaceAll("\\s+", "");
        entrada = "(" + entrada + ")";
        String simbols = "+-*/()";
        String str = "";
        for (int i = 0; i < entrada.length(); i++) {
            if (simbols.contains("" + entrada.charAt(i))) {
                str += " " + entrada.charAt(i) + " ";
            }else str += entrada.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
    }


    private static int prefijo(String operador) {
        int prioridad=0;
        if (operador.equals("^")) {prioridad = 5;
        }else if (operador.equals("*") || operador.equals("/")){
            prioridad = 4;
        }else if (operador.equals("+") || operador.equals("-")){
            prioridad = 3;
        }else if (operador.equals(")")){
            prioridad = 2;
        }else if (operador.equals("(")){
            prioridad = 1;
        }
        return prioridad;
    }









    public static void main(String[] args){
        EvaluaExpresion2 prueba=new EvaluaExpresion2();
        //prueba.setExpresionInfija(" 20 * ( 50 / 2 ) ");


        // prueba.setExpresionInfija("2 + 3 * 4 ^ 5");
        prueba.setExpresionInfija("10 + 20 * ( 50 / 2 ) - 5.8");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());



    }
}
