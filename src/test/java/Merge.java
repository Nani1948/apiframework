import java.util.Scanner;

public class Merge {


	    public static void main(String[] args) {
	        Scanner sc1 = new Scanner(System.in);
	        
	        // Input sizes
	        System.out.println("Enter the size of first array");
	        int n = sc1.nextInt();
	        System.out.println("Enter the size of second array");
	        int n1 = sc1.nextInt();
	        
	        // Input elements of first array
	        System.out.println("Enter the elements of first array");
	        int arr[] = new int[n];
	        for (int i = 0; i < arr.length; i++) {
	            arr[i] = sc1.nextInt();
	        }
	        
	        // Input elements of second array
	        System.out.println("Enter the elements of second array");
	        int arr1[] = new int[n1];
	        for (int j = 0; j < arr1.length; j++) {
	            arr1[j] = sc1.nextInt();
	        }
	        
	        // Array to hold the merged result without duplicates
	        int mergedArray[] = new int[n + n1];
	        int index = 0;

	        // Add elements from arr to mergedArray without duplicates
	        for (int i = 0; i < arr.length; i++) {
	            if (!isPresent(mergedArray, index, arr[i])) {
	                mergedArray[index++] = arr[i];
	            }
	        }

	        // Add elements from arr1 to mergedArray without duplicates
	        for (int i = 0; i < arr1.length; i++) {
	            if (!isPresent(mergedArray, index, arr1[i])) {
	                mergedArray[index++] = arr1[i];
	            }
	        }

	        // Print the merged array without duplicates
	        System.out.println("Merged array without duplicates:");
	        for (int i = 0; i < index; i++) {
	            System.out.print(mergedArray[i] + " ");
	        }

	        sc1.close();
	    }

	    // Method to check if an element is present in the array up to the given index
	    private static boolean isPresent(int[] arr, int index, int element) {
	        for (int i = 0; i < index; i++) {
	            if (arr[i] == element) {
	                return true;
	            }
	        }
	        return false;
	    }
}
