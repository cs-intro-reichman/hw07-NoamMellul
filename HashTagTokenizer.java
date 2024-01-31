

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
		System.out.println();
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++){// loop for in order to put the dictionary words in the array
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		// Your code here
		for(int i = 0; i < dictionary.length; i++){
			if (dictionary[i].equals(word)){ // check if the given word is in the dictionary
				return true;
			}
		}
			return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}
		hashtag = hashtag.toLowerCase();// the word to lower case
		int N = hashtag.length();

		for (int i = 1; i <= N; i++) {
			String hashString = hashtag.substring(0,i); // create a new string from the index 0 to i (i not include), from the given string
			if (existInDictionary(hashString, dictionary) == true) { // if the substring is in the dictionary
				System.out.println(hashString);// print it
				String hashStringNext = hashtag.substring(i);// create a new string from index i, from the given string for the next iteration
				breakHashTag(hashStringNext, dictionary); // recursive call
				return; // stop the recursive call
			}
		}
	}
}
