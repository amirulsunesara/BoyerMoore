import java.util.*;
public class BoyerMooreAlgorithm 
{

public static int alphabetSize=128; 
public static int sSize=1000;
	public static void main(String args[])
	{
		
BoyerMooreMatcher();

	}
	public static void BoyerMooreMatcher()
	{
		
		int shifts[] = new int[alphabetSize];
		
		System.out.println("Enter Text: ");
		Scanner scan = new Scanner(System.in);
		String text = scan.nextLine();
		
		
		char [] Text = text.toCharArray();
	
		System.out.println("Enter Pattern: ");
	
		String pattern = scan.nextLine();
		
		char [] Pattern = pattern.toCharArray();
		
int n=text.length();
int m=pattern.length();


	int[] lambda = ComputeLastOccurrence(pattern,m,shifts);	
	int[]	gamma= ComputeGoodSuffix(pattern,m);
	int s=0;
	int j;

	while(s<=n-m)
	{
	  j=m-1;
	 	while(j>0 && Pattern[j]==Text[s+j])
	 	
	 		j=j-1;
	 	
	 		if(j==0)
	 		{
	 			
	 			
	 			System.out.println("Pattern Occurs at Shift "+s);
	 			s=s+gamma[0];
	 		}
	 		else
	 		{
	 				 		
	 			s=s+Math.min(gamma[j], j-lambda[Text[s+j]]);
	 			
	 		}
	 	
	 }	
	 	
	}
	public static int[] ComputeLastOccurrence(String Pattern,int m,int E[] )
	{
	char[] pat=Pattern.toCharArray();
	
		for(int a : E)
			{
				E[a]=0;
							
			}
			
		
		for(int j=1 ; j<=m-1 ; j++)
			{
				
				E[pat[j]]=j;
				
			}
	
	
	return E;
		
	}
	public static int[] ComputeGoodSuffix(String Pattern,int m)
	{
		int[] gamma = new int[sSize] ;
		int[] pi = ComputePrefix(Pattern);
		String RevPattern = Reverse(Pattern);
		int[] piRev = ComputePrefix(RevPattern);
		int j;
		for( j=0 ; j<m ; j++)
		
			gamma[j]= m-pi[m-1];
			
				for(int l=1; l<m ; l++)
				{
					j=m-piRev[l];
					if(gamma[j]>l-piRev[l])
						gamma[j]=l-piRev[l];
					
				}
		
		
			
		return gamma;	
		
	}
	public static int[] ComputePrefix(String pattern)
	
	{
		
		int m = pattern.length();
		char [] Pattern = pattern.toCharArray();
		int[] pi = new int[m];
		pi[1]=0;
		int k =0;
		for(int q=2 ; q<m ; q++)
		{
			while(k>0 && Pattern[k+1] != Pattern[q])
			
				k=pi[k];
			
			if(Pattern[k+1]==Pattern[q])
				
				k=k+1;
			
			pi[q]=k;
			
		}
		return pi;
		
	}
	public static String Reverse(String Pattern)
	{
		String reverse="";
		 int length = Pattern.length();
		 
	      for ( int i = length - 1 ; i >= 0 ; i-- )
	         reverse = reverse + Pattern.charAt(i);
	      
	      return reverse;
		
	}
	

}
