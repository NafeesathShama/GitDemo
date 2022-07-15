package Programs;

public class DupCharactersInString 
{
	public static void main(String[] args)
	{  
        String string1 = "Greatree";
    
        int count;  
          
        //Converts given string into character array  
        char Arr[] = string1.toCharArray();  
          
        System.out.println("Duplicate characters in a given string: ");  
       
        //Counts each character present in the string  
        for(int i = 0; i <Arr.length; i++) 
        {  
	            count = 1;             
	            for(int j = i+1; j <Arr.length; j++) 
	            {  
	                if(Arr[i] == Arr[j] && Arr[i] != ' ') 
	                {  
	                    count++;                
	                   Arr[j] = '0';    
	                }     
	            }  
	            
	            if(count > 1 && Arr[i] != '0')  
	                System.out.println(Arr[i] );  
        }  
    }  
}
