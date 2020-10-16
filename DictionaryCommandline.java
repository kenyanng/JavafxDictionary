package sample;
import java.io.IOException;

public class DictionaryCommandline extends Dictionary{
	public void showAllWord() {
		System.out.println("No\t\t|English\t\t|Vietnamese");
		for (int i = 0; i < dic.size() ; i++) {
			System.out.println((i + 1) + "\t\t|" +   dic.get(i).getword());
		}
	}

	public void dictionaryBasic() throws IOException {
		DictionaryManagement moi = new DictionaryManagement();
		moi.insertFromCommandline();
		//  showAllWord();
	}
	public void  dictionaryAdvanced() throws IOException  {
		DictionaryManagement moi = new DictionaryManagement();
		moi.insertFromFile();
		/*String s = "Hi";
		moi.remove(s);
		moi.edit(s);
		moi.add();
		moi.edit(s);
		showAllWord();

		 */
		moi.dictionaryExportToFile();
	}
	public  void  dictionarySearcher() throws IOException {
		DictionaryManagement moi = new DictionaryManagement();
		String s = "Hi";
		moi.dictionaryLookup(s);
	}
}
