//Julio Arath Rosales Oliden
//A01630738
//23 sep 19
//EvaluarExpresion.java
//En ocasiones al elevar a una potencia da numeros muy grandes, pero lo demas funciona bien
public class EvaluarExpresion3 {
    String expresionInfija;

    public EvaluarExpresion3(String expresionInfija) {
        setExpresionInfija(expresionInfija);
    }

    public void setExpresionInfija(String expresionInfija) {
        this.expresionInfija=expresionInfija;
    }

    public double evaluaExpresion() {
        //System.out.println(this.expresionInfija);
        MyStack<Double> operandos=new MyStack();
        MyStack<Character> operadores=new MyStack<>();
        String temp="";
        int seguidos=0;
        for (int i = 0; i < expresionInfija.length(); i++) {
            if (this.expresionInfija.charAt(i)!=' ') {
                try {
                    if (expresionInfija.length()-i==1) {
                        temp+=Integer.parseInt(Character.toString(this.expresionInfija.charAt(i)));
                        System.out.println(operandos.top());
                        operandos.push(Double.parseDouble(temp));
                    }else if (this.expresionInfija.charAt(i)=='.') {
                        temp+=".";
                    }else {
                        temp+=Integer.parseInt(Character.toString(this.expresionInfija.charAt(i)));
                    }
                    seguidos=0;
                } catch (NumberFormatException e) {
                    seguidos++;
                    if (this.expresionInfija.charAt(i)=='(') {
                        operadores.push(this.expresionInfija.charAt(i));
                    }else if (seguidos>1) {
                        if (this.expresionInfija.charAt(i)=='-') {
                            operandos.push(0.0);
                            operadores.push(this.expresionInfija.charAt(i));
                        }
                    }else if (i==0) {
                        operandos.push(0.0);
                        if (this.expresionInfija.charAt(i)=='-') {
                            operandos.push(operandos.pop()-Double.parseDouble(Character.toString(this.expresionInfija.charAt(i+2))));
                            i+=2;
                            //System.out.println(operandos.top());
                        }else {
                            operadores.push(this.expresionInfija.charAt(i));
                        }
                    }else {
                        operandos.push(Double.parseDouble(temp));
                        temp="";
                        if (!operadores.isEmpty()) {
                            if (this.expresionInfija.charAt(i)=='+' || this.expresionInfija.charAt(i)=='-') {
                                if (operadores.top()=='-') {
                                    operadores.pop();
                                    operandos.push(-operandos.pop()+operandos.pop());
                                }else if (operadores.top()=='+') {
                                    operadores.pop();
                                    operandos.push(operandos.pop()+operandos.pop());
                                }else if (operadores.top()=='x') {
                                    operadores.pop();
                                    operandos.push(operandos.pop()*operandos.pop());
                                }else if (operadores.top()=='/') {
                                    operadores.pop();
                                    double div=operandos.pop();
                                    if (div==0) {
                                       // System.out.println("El divisor no puede ser cero.");
                                        return 0;
                                    }
                                    operandos.push(operandos.pop()/div);
                                }else if (operadores.top()=='^') {
                                    operadores.pop();
                                    double exp=operandos.pop();
                                    operandos.push(Math.pow(operandos.pop(), exp));
                                }
                            }else if (this.expresionInfija.charAt(i)=='x' || this.expresionInfija.charAt(i)=='/') {
                                if (operadores.top()=='x') {
                                    operadores.pop();
                                    operandos.push(operandos.pop()*operandos.pop());
                                }else if (operadores.top()=='/') {
                                    operadores.pop();
                                    double div=operandos.pop();
                                    if (div==0) {
                                       // System.out.println("El divisor no puede ser cero.");
                                        return 0;
                                    }
                                    operandos.push(operandos.pop()/div);
                                }else if (operadores.top()=='^') {
                                    operadores.pop();
                                    double exp=operandos.pop();
                                    operandos.push(Math.pow(operandos.pop(), exp));
                                }
                            }else if (this.expresionInfija.charAt(i)=='^') {
                                if (operadores.top()=='^') {
                                    operadores.pop();
                                    double exp=operandos.pop();
                                    operandos.push(Math.pow(operandos.pop(), exp));
                                }
                            }else if (this.expresionInfija.charAt(i)==')') {
                                while(operadores.top()!='(') {
                                    if (operadores.top().equals('+')) {
                                        operadores.pop();
                                        operandos.push(operandos.pop()+operandos.pop());
                                    }else if(operadores.top().equals('-')){
                                        operadores.pop();
                                        operandos.push(-operandos.pop()+operandos.pop());
                                    }else if (operadores.top().equals('x')) {
                                        operadores.pop();
                                        operandos.push(operandos.pop()*operandos.pop());
                                    }else if (operadores.top().equals('/')) {
                                        operadores.pop();
                                        double div=operandos.pop();
                                        if (div==0) {
                                         //   System.out.println("El divisor no puede ser cero.");
                                            return 0;
                                        }
                                        operandos.push(operandos.pop()/div);
                                    }else if (operadores.top().equals('^')) {
                                        operadores.pop();
                                        double exp=operandos.pop();
                                        operandos.push(Math.pow(operandos.pop(), exp));
                                    }
                                }
                                operadores.pop();
                            }
                        }
                        if (this.expresionInfija.charAt(i)!=')') {
                            operadores.push(this.expresionInfija.charAt(i));
                        }
                    }
                }
            }
        }

        int num=operadores.size();
        for (int i = 0; i < num; i++) {
            if (operadores.top().equals('+')) {
                operadores.pop();
                operandos.push(operandos.pop()+operandos.pop());
            }else if(operadores.top().equals('-')){
                //System.out.println("r");
                operadores.pop();
                double sum=operandos.pop();
                operandos.push(-sum+operandos.pop());
            }else if (operadores.top().equals('x')) {
                operadores.pop();
                operandos.push(operandos.pop()*operandos.pop());
            }else if (operadores.top().equals('/')) {
                operadores.pop();
                double div=operandos.pop();
                if (div==0) {
                   // System.out.println("El divisor no puede ser cero.");
                    return 0;
                }
                operandos.push(operandos.pop()/div);
            }else if (operadores.top().equals('^')) {
                operadores.pop();
                double exp=operandos.pop();
                if (operandos.top()<0 && exp%1!=0) {
              //      System.out.println("La solución de esta raíz es compleja.");
                    return 0;
                }else if (operandos.top()==0 && exp<0) {
                 //   System.out.println("El divisor no puede ser cero.");
                    return 0;
                }
                operandos.push(Math.pow(operandos.pop(), exp));
            }
        }
        return operandos.pop();
    }

    public String expresionPostfijo() {
        MyStack<Double> operandos=new MyStack<Double>();
        MyStack<Character> operadores=new MyStack<Character>();
        String postfijo="",
                temp="";
        int seguidos=0;
        for (int i = 0; i < this.expresionInfija.length(); i++) {
            if (this.expresionInfija.charAt(i)!=' ') {
                try {
                    if (expresionInfija.length()-i==1) {
                        temp+=Integer.parseInt(Character.toString(this.expresionInfija.charAt(i)));
                        operandos.push(Double.parseDouble(temp));
                    }else if (this.expresionInfija.charAt(i)=='.') {
                        temp+=".";
                    }else {
                        temp+=Integer.parseInt(Character.toString(this.expresionInfija.charAt(i)));
                    }
                    seguidos=0;
                } catch (NumberFormatException e) {
                    seguidos++;
                    if (seguidos==1) {
                        if (i==0) {
                            operandos.push(0.0);
                        }else {
                            operandos.push(Double.parseDouble(temp));
                            temp="";
                        }
                    }
                    operadores.push(this.expresionInfija.charAt(i));

                }
            }
        }
        int num=operandos.size();
        for (int i = 0; i < num; i++) {
            postfijo+=operandos.pop()+" ";
        }
        num=operadores.size();
        for (int i = 0; i < num; i++) {
            postfijo+=operadores.pop()+" ";
        }
        return postfijo;
    }

    public static void main(String[] args) {
        EvaluarExpresion3 num=new EvaluarExpresion3("10 + 20 * ( 50 / 2 ) - 5.8");

        System.out.println(num.evaluaExpresion());

        //System.out.println(num.expresionPostfijo());
    }
}