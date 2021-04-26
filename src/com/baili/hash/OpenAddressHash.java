package com.baili.hash;

public class OpenAddressHash {
    
    private static String[] arr = new String[10];

    public static void main(String[] args) {
        String[] array = new String[]{"asdf", "fgd", "asdf", "12.asd", "asdg"};
        for(String string : array){
            int index = hashAlgorithm(string);
            if(arr[index] == null){
                arr[index] = string;
            }else{
                int newIndex = nextLocation(index);
                if(newIndex != -1){
                    arr[newIndex] = string;
                }
            }
        }
        
        for(String out : arr){
            System.out.print(out + "   ");
        }
        System.out.println();
    }
    
    private static int hashAlgorithm(String param){
        char[] charArray = param.toCharArray();
        int sum = 0;
        for(int i = 0 ; i < charArray.length; i++){
            sum += Integer.valueOf(charArray[i]) * (i + 1);
        }
        return sum % arr.length;
    }
    
    private static int nextLocation(int index){
        for(int i = 1; i < arr.length - 1; i++ ){
            if(arr[(index + i) % arr.length] == null){
                return (index + i) % arr.length;
            }
        }
        return -1;
    }
    
    private static Object get(String key){
        int index = hashAlgorithm(key);
        
        
        return arr;
    }

}
