/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author Sam
 */
public class FXMLDocumentController implements Initializable {
    private static String tokens =" ";
    private static String result=" ";
    private static String finalresult="";
    private FileChooser fileChooser = new FileChooser();
    private File file;
        @FXML
	protected void openFile(ActionEvent event) {
		this.openFileProgress();
	}
      
    private void openFileProgress() {
		this.file = fileChooser.showOpenDialog(null);
		if(this.file != null) {
			
			BufferedReader bufferedReader = null;
			try {
				String currentLine;
				bufferedReader = new BufferedReader(new FileReader(this.file));
				while((currentLine = bufferedReader.readLine()) != null)
					firstprogram.appendText(currentLine + "\n");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
    @FXML
	protected void saveFile(ActionEvent event) {
		this.saveFileProgress();
	}
     
        private void saveFileProgress() {
		String content = this.firstprogram.getText();
		if(this.file != null) {
			try {
				//wenn die Datei noch nicht existiert, soll sie erstellt werden
				if(!this.file.exists()) 
					this.file.createNewFile();
				
				FileWriter fileWriter = new FileWriter(this.file.getAbsoluteFile());
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(content);
				bufferedWriter.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			this.file = this.fileChooser.showSaveDialog(null);
			if(this.file != null) {
				
				try {
					if(!this.file.exists())
						this.file.createNewFile();
					FileWriter fileWriter = new FileWriter(this.file.getAbsoluteFile());
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(content);
					bufferedWriter.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    @FXML
    private TextArea firstprogram;
    @FXML
    private TextArea secondprogram;
    @FXML
    private TextArea firstresult;
    @FXML
    private TextArea secondresult;
    @FXML
    private void handleButtonAction(ActionEvent event)  {
          
   
    InputStream is=new ByteArrayInputStream(firstprogram.getText().getBytes(Charset.forName("UTF-8")));
    Project cal=new Project(is); 
    
   
        try {
            cal.start(); 
            firstresult.setText("Build Successful. \n" 
            +"=========================================================================\n"
            +getResult(finalresult));
        
            
            
        } catch (ParseException ex) {
            firstresult.setText(ex.toString());
            cal.ReInit(is);
         
            System.out.println("Error encounters");
           System.out.println(ex);
        }
    }
    public String getResult(String a)
    {
     finalresult=a; 
     return finalresult;
    }
    public void Result(double a)
    {
      result=Double.toString(a);
      getResult(result);
      //return (" "+result);
    }
   public String  ListTokens(String a)
    {
        tokens+=a;
        return tokens;
    }
    @FXML
    private void TokenForSecondProgram(ActionEvent event) {
     secondresult.setText(ListTokens(tokens));
   
    }
    @FXML
    private void TokenForFirstProgram(ActionEvent event) {
       
       firstresult.setText(ListTokens(tokens));
    }
    
    @FXML
    private void RunSecondProgram(ActionEvent event) {
          InputStream is=new ByteArrayInputStream(firstprogram.getText().getBytes(Charset.forName("UTF-8")));
    Project cal=new Project(is); 
    
   
        try {
            cal.start(); 
           secondresult.setText("Build Successful. \n" 
            +"=========================================================================\n"
            +getResult(finalresult));
        } catch (ParseException ex) {
            secondprogram.setText(ex.toString());
            cal.ReInit(is);
         
            System.out.println("Error encounters");
           System.out.println(ex);
        }
    }
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
