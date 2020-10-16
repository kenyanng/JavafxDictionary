package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.tools.internal.xjc.Language;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.midi.VoiceStatus;
import javax.swing.JOptionPane;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;


public class Controller extends Dictionary  implements Initializable {
	DictionaryManagement moi = new DictionaryManagement();
	@FXML
	public Button search;
	@FXML
	public Button s;
	@FXML
	public Button Update;
	@FXML
	private TextField textField;
	@FXML
	private TextField FieldAddT;
	@FXML
	private TextField FieldAddE;
	@FXML
	private TextField FieldEditB;
	@FXML
	private TextField FieldEditT;
	@FXML
	private TextField FieldEditE;
	@FXML
	private TextField FieldRemoveT;
	@FXML
	private ListView txtList;
	@FXML
	private TextArea txtListT;

	@FXML
	void searchWord() throws IOException {
		String target = textField.getText().trim().toLowerCase();

		txtListT.setText("");
		int index = moi.dictionaryLookup(target);
		if (index > -1) {
			txtListT.setText(dic.get(index).word_explain);
		} else {
			txtListT.setText("No Find !!!");
			JOptionPane.showMessageDialog(null,
					"No Find !!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@FXML
	void wordadd() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("addword.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	public void addw(ActionEvent e) {
		String target = FieldAddT.getText().toLowerCase();
		String explain = FieldAddE.getText().toLowerCase();
		if(moi.add(target,explain)) {
			JOptionPane.showMessageDialog(null,
					"FAILED ! ! ! \n The dictionary contains  this word !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		} else {
			Word word = new Word(target, explain);
			dic.add(word);
			JOptionPane.showMessageDialog(null,
					"Add Word Successfully!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		}
		Cancel(e);
	}

	@FXML
	void wordedit() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("editword.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void editw(ActionEvent e) {
		String targetbase = FieldEditB.getText().trim().toLowerCase();
		String target = FieldEditT.getText().trim().toLowerCase();
		String explain = FieldEditE.getText().trim().toLowerCase();
		if(moi.edit(targetbase,target,explain)) {
			JOptionPane.showMessageDialog(null,
					"Edit Word Successfully!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null,
					"FAILED ! ! ! \n Words are not available in the library ! ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		Cancel(e);
	}
	@FXML
	void wordremove() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("removeword.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}


	@FXML
	void removew(ActionEvent e) {
		String target = FieldRemoveT.getText().trim();
		if(moi.remove(target)){
			JOptionPane.showMessageDialog(null,
					"Remove Word Successfully!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
					"FAILED! ! !\n Words are not available in the dictionary !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		Cancel(e);
	}

	@FXML
	public void TxtList() {
		txtList.getItems().clear();
		txtListT.setText("");
		String target = textField.getText().toLowerCase().trim();
		if (target.equals("")) {
			txtList.getItems().clear();
		} else {
			int index = 0;
			int index1 =0;
			String[] currentdic = new String[dic.size()];
			String[] currentdic1 = new String[dic.size()];
			for (int i = 0; i < currentdic.length; i++) {
				if (dic.get(i).word_target.startsWith(target)) {
					currentdic[index] = dic.get(i).word_target;
					index++;
				} else if (dic.get(i).word_target.contains(target)) {
					currentdic1[index1] = dic.get(i).word_target;
					index1++;
				}

			}
			for (int i = 0; i < index; i++) {
				txtList.getItems().add(currentdic[i]);
			}
			for (int i = 0; i < index1; i++) {
				txtList.getItems().add(currentdic1[i]);
			}
		}
	}

	@FXML
	public void setTxtList() {
		txtListT.setText("");
	}

	@FXML
	public void listword() {
		txtListT.setText("");
		txtList.getItems().clear();
		txtList.getItems().add("Số lượng từ trong từ điển :");
		txtListT.setText(String.valueOf(dic.size()) + " từ");
		for (int i = 0; i < dic.size(); i++) {
			txtList.getItems().add(dic.get(i).word_target);
		}
	}
	@FXML
	public void click() throws IOException {
		textField.clear();
		String target = String.valueOf(txtList.getSelectionModel().getSelectedItem()).toLowerCase().trim();

		if (txtList.getItems().toString() == "") {
			txtListT.clear();
		}else {
			textField.setText(target);
			txtListT.clear();
		}
	//	searchWord();
	}
	//private com.sun.speech.freetts.Voice;
	/*public void speak(){
		Voice  voice;
		String target = textField.getText().toLowerCase().trim();
		VoiceManager voiceManager = VoiceManager.getInstance();
		System.setProperty("mbrola.base", "C:\\Users\\LENOVO\\Downloads\\freetts-1.2\\mbrola");
		voice = voiceManager.getVoice("mbrola usl");
		voice.allocate();
		voice.speak(target);
	}

	 */
		public void speak() {
			Audio audio = Audio.getInstance();
			InputStream sound = null;
			String target = textField.getText().toLowerCase().trim();
			if (target.isEmpty()){
				try {
					sound = audio.getAudio("Enter English words in the search box", "en");
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					audio.play(sound);
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					sound = audio.getAudio(target, "en");
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					audio.play(sound);
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		}

	public void help() {
		JOptionPane.showMessageDialog(null,
				"Not update ! ! !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	@FXML
	public void update() throws IOException {
		DictionaryManagement moi = new DictionaryManagement();
		moi.dictionaryExportToFile();
		System.out.println("Update Successfully !");
	}

	@FXML
	public void Cancel(ActionEvent e) {
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void Back() {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			if (dic.size() == 0) {
				DictionaryManagement.insertFromFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}