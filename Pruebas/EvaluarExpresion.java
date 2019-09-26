
//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//EvaluarExpresion.java

public class EvaluarExpresion {
    String expresionInfija;

    public void expresionInfija(String expresionInfija) {
        setExpresionInfija(expresionInfija);
    }

    public void setExpresionInfija(String expresionInfija) {
        this.expresionInfija = expresionInfija;
    }

    public double evaluaExpresion() {
        String[] array = expresionPostfijo().split(" ");
        MyStack<String> Entrada = new MyStack<String>();
        MyStack<String> Salida = new MyStack<String>();
        for (int i = array.length - 1; i >= 0; i--) {
            Entrada.push(array[i]);
        }
        String operadores = "+-*/";
        String operadores1 = "^";
        while (!Entrada.isEmpty()) {
            if (operadores.contains("" + Entrada.top())) {
                /*while(!Entrada.isEmpty()) {
                    System.out.print(Entrada.pop() + ",");
                }
                System.out.println();
                while(!Salida.isEmpty()) {
                    System.out.print(Salida.pop() + ",");
                }*/
                Salida.push(Double.toString(calcula(Entrada.pop(), Salida.pop(), Salida.pop() + "")));
            } else if(operadores1.contains(""+Entrada.top())){
                /*
                while(!Entrada.isEmpty()) {
                    System.out.print(Entrada.pop() + ",");
                }
                System.out.println();
                while(!Salida.isEmpty()) {
                    System.out.print(Salida.pop() + ",");
                }*/
                Salida.push(Double.toString(calcula(Entrada.pop(), Entrada.pop(), Salida.pop() + "")));
            }else {
                Salida.push(Entrada.pop());
            }
        }
        return Double.parseDouble(Salida.top());
    }

    private static double calcula(String a, String b, String c) {
        double x = Double.parseDouble(c);
        double y = Double.parseDouble(b);
        if (a.equals("+")) {
            return x + y;
        } else if (a.equals("-")) {
            return x - y;
        } else if (a.equals("*")) {
            return x * y;
        } else if (a.equals("/")) {
            return x / y;
        } else if (a.equals("^")) {
            return Math.pow(x,y);
        } else {
            return 0.0;
        }
    }

    public  String expresionPostfijo() {
        String expr = depurar(expresionInfija);//quitar espacios
        String[] array = expr.split(" ");
        MyStack<String> Entrada = new MyStack<String>();
        MyStack<String> Temporal = new MyStack<String>();
        MyStack<String> Salida = new MyStack<String>();
        for (int i = array.length - 1; i >= 0; i--) {
            Entrada.push(array[i]);
            //System.out.println((arrayInfix[i]));
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
        String simbols = "^+-*/()";
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
        EvaluarExpresion prueba=new EvaluarExpresion();
        prueba.setExpresionInfija("10 + 20 * ( 50 / 2 ) - 5.8");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());

        prueba.setExpresionInfija("10 ^ 2");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());

        prueba.setExpresionInfija("100 * (10 ^ 2) - 10");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());

        prueba.setExpresionInfija("(10 ^ 2) / 10");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());

        prueba.setExpresionInfija("( 10 * 2 ) ^ 2 - 10");
        System.out.println(prueba.expresionPostfijo());
        System.out.println(prueba.evaluaExpresion());
    }
}
