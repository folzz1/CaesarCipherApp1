package org.example.caesarcipherapp1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

public class HelloController {
    @FXML
    private TextField keyInputField;

    @FXML
    private Label welcomeText;

    String selectedFilePath;
    @FXML
    protected void onHelloButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            selectedFilePath = selectedFile.getAbsolutePath();
            welcomeText.setText("Выбран файл: " + selectedFilePath);
        } else {
            welcomeText.setText("Файл не выбран.");
        }
    }

    @FXML
    protected void onFileEncryptionClick() {
        try
        {
            String directoryPath = "txtfiles/";
            FileReader filereader = new FileReader(selectedFilePath);
            File file = new File(directoryPath+"encrtest_1.txt");
            FileWriter fileWriter = new FileWriter(file);
            //textEncryption(fileWriter, filereader, 5);
            int key = Integer.parseInt(keyInputField.getText());
            key = key % 26;
            int c;
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while((c=filereader.read())!=-1){
                int check = (int) c;

                if (check >= 97 && check <= 122) {
                    c = (char) ((check - 97 + key + 26) % 26 + 97);
                } else if (check >= 65 && check <= 90) {
                    c = (char) ((check - 65 + key + 26) % 26 + 65);
                }
                bufferedWriter.write(c);
            }
            bufferedWriter.close();
            filereader.close();
            fileWriter.close();

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    protected void onFileDecoderClick() {
        try
        {
            String directoryPath = "txtfiles/";
            FileReader filereader = new FileReader(selectedFilePath);
            File file = new File(directoryPath+"dectest_1.txt");
            FileWriter fileWriter = new FileWriter(file);
            //textEncryption(fileWriter, filereader, 5);
            int key = Integer.parseInt(keyInputField.getText());
            key = key % 26;
            int c;
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while((c=filereader.read())!=-1){
                int check = (int) c;
                if (check >= 97 && check <= 122) {
                    c = (char) ((check - 97 - key + 26) % 26 + 97);
                } else if (check >= 65 && check <= 90) {
                    c = (char) ((check - 65 - key + 26) % 26 + 65);
                }
                bufferedWriter.write(c);
            }
            bufferedWriter.close();
            filereader.close();
            fileWriter.close();

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}