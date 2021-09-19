import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
* JUnit test cases for class WordsInString
* 
* @author Agris Klimkans
* 
*/
public class WordsInStringTest {

	@Test
	void testReadCommandArgs() {
		
		List<String> actual = WordsInString.readCommandArgs(new String[] {"-s", "djkhofjkhaCatalogdsahf", "-i"});
        List<String> expected = Arrays.asList("-s", "djkhofjkhaCatalogdsahf", "-i");
		
        assertEquals(actual, expected);
	}

	@Test
	void testIsSearchCaseSensitive() {
			
        assertEquals(false, WordsInString.isSearchCaseSensitive(Arrays.asList("-s", "djkhofjkhaCatalogdsahf", "-i")));
	}
	
	@Test
	void testGetTestString() {
		assertEquals("djkhofjkhaCatalogdsahf", WordsInString.getTestString(Arrays.asList("-s", "djkhofjkhaCatalogdsahf", "-i"), true));
	}
	
	@Test
	void testReadDictionaryFromFile() {
		assertEquals(Arrays.asList("can", "Cat", "dog", "Catalog", "log", "able", "of", "an"), WordsInString.readDictionaryFromFile(true));
	}
	
}