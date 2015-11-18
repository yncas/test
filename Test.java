package ext.org.forms.changeorder.test;

import java.util.ArrayList;
import java.util.List;

public class Test 
{
	public static void main(String[] args)
	{
		List<String> result = new ArrayList<String>();
		
		String numbers = "XXX-50;-01;XXX-100;-01;-02;-03;-04;-05;-07;-08;-10;-12;-15;-18;-19;XXX-150;-01;XXX-200;-01;-02;XXX-500;-01;$end$"; //Список обозначений объектов одинаковых типов и т.д.
		Integer beforeNumber = null;
		int countNumber = 0;
		String startNumber = "<startNumber = 'null'>";
		String numberBefore = "<numberBefore = 'null'>";
		for(String number : numbers.split(";")) 
		{
			System.out.println("number = " + number + ", countNumber = " + countNumber);
			
			if(number.startsWith("-")) //Если элемент таблицы семейств 
			{ 
				int currentNumber = Integer.parseInt(number.substring(number.lastIndexOf("-") + 1));
				if(countNumber == 1) startNumber = numberBefore;
				if(countNumber == 0) startNumber = number;
				if(countNumber == 0 || currentNumber - beforeNumber == 1) 
					countNumber++;
				else {
					if(countNumber > 2) result.add(startNumber + " ... " + numberBefore);
					else {
						result.add(startNumber);
						if(countNumber == 2) 
							result.add(numberBefore);
					}
					
					startNumber = number;
					countNumber = 1;
				}
				beforeNumber = currentNumber;
				numberBefore = number;
			}
			else {
				if(countNumber != 0) {
					if(countNumber > 2) result.add(startNumber + " ... " + numberBefore);
					else {
						System.out.println("startNumber = " + startNumber);
						result.add(startNumber);
						if(countNumber == 2) 
							result.add(numberBefore);
					}
				}
				if(! "$end$".equals(number) ) result.add(number);
				countNumber = 0;
			}			
		}
		
		System.out.println(result);
	}
}
