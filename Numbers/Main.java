import java.util.Scanner;

public class Main {
	public static int numOfEvenDigits(int num) {//Check How many even digits the number has
		if (num < 10) {//Stop condition
			if (num % 2 == 0)//Digit is even
				return 1;
			else//Digit is odd
				return 0;
		}
		if (num % 2 == 0)//Digit is even
			return (numOfEvenDigits(num / 10) + 1);
		return numOfEvenDigits(num / 10);//Digit is odd
	}
	
	public static boolean allDigitsAreEven(int num) {//Check if all the digits are even
		if (num < 10) {//Stop condition
			if (num % 2 == 0)//Digit is even
				return true;
			else//Digit is odd
				return false;
		}
		if (num % 2 == 0)//Digit is even
			return allDigitsAreEven(num / 10);
		return false;//Digit is odd
	}
	
	public static int numOfEvenNumbersInArr(int[] arrToCheck, int lengthOfArr) {//Check how many even numbers the array has
		if (lengthOfArr == 0)//Stop condition
			return 0;
		if ((arrToCheck[lengthOfArr-1] % 2) == 0)//Number is even
			return numOfEvenNumbersInArr(arrToCheck, lengthOfArr - 1) + 1;
		return numOfEvenNumbersInArr(arrToCheck, lengthOfArr - 1);//Number is odd
	}
	
	public static boolean allNumberInArrAreEven(int[] arrToCheck, int lengthOfArr) {//Check if all the numbers in the array are even
		if (lengthOfArr == 0)//Stop condition
			return true;
		if (arrToCheck[lengthOfArr - 1] % 2 == 0)//Number is even
			return allNumberInArrAreEven(arrToCheck, lengthOfArr - 1);
		return false;//Number is odd
	}
	
	public static boolean checkIfFlipNumber(int num) {//Check if the number is a flip number
		if (num < 100) {//Stop condition
			if ((num / 10) % 2 != (num % 10) % 2)//Check if the digits has different duality
				return true;
			return false;
		}
		if ((num % 10) % 2 != ((num / 10) % 10) % 2)//Check if the digits has different duality
			return checkIfFlipNumber(num / 10);
		return false;
	}
	
	public static boolean checkIfThereIsOneFlipDigits(int num) {//Check if the number contain at least one couple of flip digits
		if (num < 100) {//Stop condition
			if ((num / 10) % 2 != (num % 10) % 2)//Check if the digits has different duality
				return true;
			return false;
		}
		if ((num % 10) % 2 == ((num / 10) % 10) % 2)//Check if the digits has different duality
			return checkIfThereIsOneFlipDigits(num / 10);
		return true;
	}
	
	public static int valueOfTheNumInPlace (int numPlace) {//Check what is the value of the number in place
		if (numPlace < 4) {//Stop condition
			return numPlace;
		}
		if (numPlace % 2 == 0)//Check if the place is in even place or in odd place
			return valueOfTheNumInPlace(numPlace-1) + valueOfTheNumInPlace(numPlace-2) + valueOfTheNumInPlace(numPlace-3);
		else
			return valueOfTheNumInPlace(numPlace-1) - valueOfTheNumInPlace(numPlace-3);
	}
	
	public static void drawNumOfUnits(int numOfUnits) {//Draw number of units in the center
		if (numOfUnits == 0)//Stop condition
			return;
		drawNumOfUnits(numOfUnits - 1);//Draw the first part
		for (int i = 0; i < numOfUnits; i++)//Draw the line with the units that been asked
			System.out.print("-");
		System.out.println();
		drawNumOfUnits(numOfUnits - 1);//Draw the last part
		
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int choiceOption;
		do {
			System.out.println("Enter your choice:");
			System.out.println("1- Check how many even digits the number has");
			System.out.println("2- Check if all the digits of number are even");
			System.out.println("3- Chcek how many even numbers the array has");
			System.out.println("4- Check if all the numbers in the array are even");
			System.out.println("5- Check if the number is a flip number");
			System.out.println("6- Check if the number contain at least one couple of flip digits");
			System.out.println("7- Check what is the value of the number in place");
			System.out.println("8- Draw number of units in the center");
			System.out.println("9- Exit");
			choiceOption = s.nextInt();
			switch (choiceOption) {
			case 1://Check How many even digits the number has
				System.out.println("Enter number");
				int numberToCheck1 = s.nextInt();
				System.out.println("The number has: " + numOfEvenDigits(numberToCheck1) + " even digits");
				break;
			case 2://Check if all the digits are even
				System.out.println("Enter number");
				int numberToCheck2 = s.nextInt();
				if (allDigitsAreEven(numberToCheck2))
					System.out.println("All digits are even");
				else
					System.out.println("Not all digits are even");
				break;
			case 3://Check how many even numbers the array has
				System.out.println("Enter the size of the array");
				int[] arrToCheck3 = new int[s.nextInt()];
				for(int i = 0; i < arrToCheck3.length; i++) {//Fill the array
					System.out.println("Enter number No." + (i + 1));
					arrToCheck3[i] = s.nextInt();
				}
				System.out.println("The Array has: " + numOfEvenNumbersInArr(arrToCheck3, arrToCheck3.length)
									+ " even numbers");
				break;
			case 4://Check if all the numbers in the array are even
				System.out.println("Enter the size of the array");
				int[] arrToCheck4 = new int[s.nextInt()];
				for(int i = 0; i < arrToCheck4.length; i++) {//Fill the array
					System.out.println("Enter number No." + (i + 1));
					arrToCheck4[i] = s.nextInt();
				}
				if (allNumberInArrAreEven(arrToCheck4, arrToCheck4.length))
					System.out.println("All the numbers in the array are even");
				else
					System.out.println("Not all the numbers in the array are even");
				break;
			case 5://Check if the number is a flip number
				System.out.println("Enter number");
				int numberToCheck5 = s.nextInt();
				if (checkIfFlipNumber(numberToCheck5))
					System.out.println("The number is a flip number");
				else
					System.out.println("The number isn't a flip number");
				break;
			case 6://Check if the number contain at least one couple of flip digits
				System.out.println("Enter number");
				int numberToCheck6 = s.nextInt();
				if (checkIfThereIsOneFlipDigits(numberToCheck6))
					System.out.println("The number contain at least one couple of flip digits");
				else
					System.out.println("The number not contain flip digits at all");
				break;
			case 7://Check what is the value of the number in place
				System.out.println("Enter the place of the number in series");
				int numberPlace7 = s.nextInt();
				System.out.println("The value of the number in place: " + numberPlace7 
									+ " is: " + valueOfTheNumInPlace(numberPlace7));
				break;
			case 8://Draw number of units in the center
				System.out.println("Enter the number of units you want in the middle");
				int numOfUnitsInTheMiddle = s.nextInt();
				drawNumOfUnits(numOfUnitsInTheMiddle);
				break;
			case 9:
				System.out.println("Bye Bye have a good day :)");
				break;
			default: 
				System.out.println("You entered a not valid choice");
			}
		} while (choiceOption != 9);
	}

}
