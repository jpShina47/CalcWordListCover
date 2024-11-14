import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

class TXTFileReader implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e){
		/*---------------------------------- Make Instance ----------------------------------*/
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		int selected;
		
		filechooser.setFileFilter(filter);
		
		selected = filechooser.showOpenDialog(filechooser);//ファイルが開かれる操作がされたかを取得する処理
		
		if(selected == JFileChooser.APPROVE_OPTION) {
			try {
				FileReader fileReader = new FileReader(filechooser.getSelectedFile());
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                StringBuilder sb = new StringBuilder();
                
                MakeWinFrame.FileNameTextField.setText(filechooser.getName(filechooser.getSelectedFile()));
                
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                reader.close();
                MakeWinFrame.ScriptTextArea.setText(sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage());
            }
		}
	}
}