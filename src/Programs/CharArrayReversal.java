package Programs;

public class CharArrayReversal 
{
public static void main(String[] args) {      
        
        //Initialize array   
        char [] arr = new char [] {'s','h','a','m','a'};   
          
        System.out.println("Original array: ");  
        for (int i = 0; i < arr.length; i++) 
        {   
            System.out.print(arr[i] + " ");   
        }    
          
        
        System.out.println();  
        
          
        System.out.println("Array in reverse order: ");  
       
        for (int i = arr.length-1; i >= 0; i--) 
        {   
            System.out.print(arr[i] + " ");   
        }    
}}
