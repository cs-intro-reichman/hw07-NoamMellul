
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1); // return a substring from index 1, from the given string
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.length() == 0){
			return word2.length();
		}
		if (word2.length() == 0){
			return word1.length();
		}
		// if the first letter of each word is the same
		if (word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));// recursive call without the first letter of each word
		}
        // return the sum of 1 and the minimum of the remaining possibilities
		return 1 + Math.min(Math.min(levenshtein(tail(word1), word2) , levenshtein(word1, tail(word2))) , levenshtein(tail(word1), tail(word2)));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++){// loop for in order to put the dictionary words in the array
			dictionary[i] = in.readString();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		String target = word;// the target, so it's the word given or word that closely resembles
		int min = threshold + 1;
		for(int i = 0; i < dictionary.length; i++){
			int lev = levenshtein(dictionary[i], word);// the distance between the given word and the word in the dictionary at index i
			if(lev < min){
				min = lev; // change the value of the minimum
				target = dictionary[i]; // the target is now this word

			}
		}
		return target;
	}

}
