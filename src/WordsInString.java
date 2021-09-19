import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
* Finds dictionary words in a test string
* 
* @author Agris Klimkans
* 
*/
public class WordsInString
{
	/**
	 * Finds dictionary words in a test string.
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{		
		List<String> commandArgsList = readCommandArgs(args);
		
		boolean searchCaseSensitive = isSearchCaseSensitive(commandArgsList);
		
		String testString = getTestString(commandArgsList, searchCaseSensitive);
		
		List<String> dictionaryWordsList = readDictionaryFromFile(searchCaseSensitive);
		
		findWordsInTestString(testString, dictionaryWordsList);	
	}

	
	/**
	 * 
	 * Finds dictionary words in string 
	 * 
	 * @param testString string to test
	 * @param dictionaryWordsList list of words from dictionary
	 */
	public static void findWordsInTestString(String testString, List<String> dictionaryWordsList) {
		int wordsFound = 0;
		List<String> foundWordsList = new ArrayList<>();
		for(String s : dictionaryWordsList)
		{
			try
			{
				int occurencesOfWord = ( testString.split(s).length ) - 1;
				if (occurencesOfWord > 0)
				{
					foundWordsList.add(s); 
					wordsFound += occurencesOfWord;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		
		System.out.println("Number of words found: " + wordsFound);
		System.out.println("Words found: " + foundWordsList);
	}

	/**
	 * 
	 * Reads dictionary from filesystem
	 * 
	 * @param searchCaseSensitive, true if search is case sensitive
	 * @return list of words from dictionary
	 */
	public static List<String> readDictionaryFromFile(boolean searchCaseSensitive) {
		//read dictionary from file
		
		String fileName = "input.txt";
		List<String> dictionaryWordsList = new ArrayList<>();

		try (Stream<String> str = Files.lines(Paths.get(fileName)))
		{
			dictionaryWordsList = str.collect(Collectors.toList());
			if(!searchCaseSensitive)
				dictionaryWordsList = dictionaryWordsList.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Dictionary words:");
		dictionaryWordsList.forEach(System.out::println);
		return dictionaryWordsList;
	}

	/**
	 * 
	 * Reads test string (after the -s command line switch)
	 * 
	 * @param commandArgsList list of command line arguments
	 * @param searchCaseSensitive, true if search is case sensitive
	 * @return test string
	 */
	public static String getTestString(List<String> commandArgsList, boolean searchCaseSensitive) {
		// read test string after "-s"
		int indexOfs = commandArgsList.indexOf("-s");
		String testString = "";
		   if (indexOfs > -1)
		   {
			   try
			   {
			       testString = commandArgsList.get(indexOfs + 1);
			       if(!searchCaseSensitive)
			       {
			    	   testString = testString.toLowerCase();
			       }
			   }
			   catch ( IndexOutOfBoundsException e)
			   {
			       System.out.println("Input string not specified");
			   }
		   }
		   
		   System.out.println("Test string = " + testString);
		return testString;
	}

	/**
	 * 
	 * Determines if search is case sensitive
	 * 
	 * @param commandArgsList list of command line arguments
	 * @return true if search is case sensitive
	 */
	public static boolean isSearchCaseSensitive(List<String> commandArgsList) {
		// if "-i" then search case insensitive
		boolean searchCaseSensitive = (commandArgsList.contains("-i")) ? false : true;
		if(searchCaseSensitive)
		{
			System.out.println("Searching case sensitive");
		}
		else
		{
			System.out.println("Searching case insensitive");
		}
		return searchCaseSensitive;
	}

	/**
	 * 
	 * Reads command line arguments
	 * 
	 * @param args cli arguments from the program entry point
	 * @return cli arguments in a list
	 */
	public static List<String> readCommandArgs(String[] args) {
		Stream<String> commandArgsStream = Arrays.stream(args);
		List<String> commandArgsList = commandArgsStream.collect(Collectors.toList());
		return commandArgsList;
	}
}
