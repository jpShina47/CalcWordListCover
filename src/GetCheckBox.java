import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
class GetCheckBox {
	Boolean CheckBox01_isSelected;
	Boolean CheckBox02_isSelected;
	Boolean CheckBox03_isSelected;
	Boolean CheckBox04_isSelected;
	static String filepath;
	private Path PathToProperties = Paths.get("");
	private String PathToPropertiesToString;
	
	public GetCheckBox() {
		CheckBox01_isSelected = MakeWinFrame.WordList_Target1900.isSelected();
		CheckBox02_isSelected = MakeWinFrame.WordList_Target1400.isSelected();
		CheckBox03_isSelected = MakeWinFrame.WordList_SysTan.isSelected();
		CheckBox04_isSelected = MakeWinFrame.LEAP.isSelected();
		
		PathToProperties = PathToProperties.toAbsolutePath();
		PathToPropertiesToString = PathToProperties.toString();
		PathToPropertiesToString = PathToPropertiesToString + "\\config.properties";
		
//		System.out.println(PathToPropertiesToString);
		
		Properties properties = new Properties();
		InputStream istream;
		InputStreamReader isr;
		
		try {
			istream = new FileInputStream(PathToPropertiesToString);
			isr = new InputStreamReader(istream, "UTF-8");
			properties.load(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println("比較する単語帳として");
		if(CheckBox01_isSelected) {
			filepath = properties.getProperty("target1900");
		}
		if(CheckBox02_isSelected) {
			filepath = properties.getProperty("target1400");
		}
		if(CheckBox03_isSelected) {
			filepath = properties.getProperty("SystemEitango");
		}
		if(CheckBox04_isSelected) {
			filepath = properties.getProperty("LEAP");
		}
		if(!CheckBox01_isSelected && !CheckBox02_isSelected && !CheckBox03_isSelected && !CheckBox04_isSelected){
			filepath = null;
		}
	}
}