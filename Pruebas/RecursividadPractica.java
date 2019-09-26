public class RecursividadPractica{
    /*public static long fibonacciPreparacion(int n ){
        long[] array=new long[n+1];
        array[0]=array[1]=1;
        return fibonacci(n,array);
    }

        private  static long fibonacci(int n, long[] array){
            if(0<array[n]){
                return array[n];
            }else{
                return array[n]= fibonacci(n-1,array)+fibonacci(n-2,array);
            }
        }¨*/

    public static long fibonacciPreparacion(int n){
        long[] array= new long[n+1];
        array[0]=array[1]=1;
        return fibonacci(n,array);
    }

    public static long fibonacci(int n, long[] array){
        if(0<array[n]){
            return array[n];
        }else{
            return array[n]=fibonacci(n-1,array)+fibonacci(n-2,array);
        }

    }

        /*private  static long factorial(long n){
            if(n<=1) {
                return 1;
            }else{
                return n*factorial(n-1);
            }
        }*/

        public static long factorial(long n){
            if(n<=1){
                return 1;
            }else{
                return n*factorial(n-1);
            }
    }


        public static String base10a2(int n) {
            if(0<n) {
                //return base10a2(n/2)+(n%2);
                return base10a2(n/2)+(n%2);
            }else {
                return "";
            }
        }
        public static String alreves(int n){
            if(0<n){
                return Integer.toString(n%10)+alreves(n/10);
            }
            else{return "";}
        }


        public static void main(String[] args){
            System.out.println(fibonacciPreparacion(10));
            System.out.println(factorial(4));
           // System.out.println(base10a2(10));
            System.out.println(alreves(123));
        }
}


