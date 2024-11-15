import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

class Comparing implements ActionListener{
	/*Make Instance*/
    private String CellVal;
    private String POS;
    private String Verb1st;		//原形
    private String Verb2nd;		//3単現
    private String Verb3rd;		//過去形
    private String Verb4th;		//過去分詞
    private String Verb5th;		//現在分詞
    private String MonoNoun;	//単数形
    private String MultiNoun;	//複数形
    private Workbook wb;
    private String POS_V = "V";
    private String POS_N = "N";
    
    static String MatchedWrdPOSforResult;
    private int V_num,N_num,Adj_num,Adv_num,Prep_num,ConjAux_num;
    
    private String MatchedWrd;
    private String MatchedWrdPOS;
    private String RowNumToString;
    
    private String filepath;
    static List<String> MatchedWrdArrTrue = new ArrayList<String>();
    static List<Integer> MatchedWrdNum = new ArrayList<Integer>();
    private List<Integer> MatchedWrdNumTrue = new ArrayList<Integer>();
    private List<String> MatchedWrdPOSList = new ArrayList<String>();
    
	@Override
	public void actionPerformed(ActionEvent e){
		MakeWinFrame.ResultTextField.setText("");
		MakeWinFrame.MatchedWrdTextArea.setText("");
		new GetCheckBox();
    	filepath = GetCheckBox.filepath;
    	if(filepath == null) {
    		MakeWinFrame.ResultTextField.setText("単語帳が選択されていません．");
    		return;
    	}
		try (FileInputStream in = new FileInputStream(filepath)) {
	        wb = WorkbookFactory.create(in);
		} catch (IOException ioexception) {
	        System.out.println(ioexception.toString());
		}
		String[] WordArr = MakeWinFrame.ScriptTextArea.getText().split("\\s|,\\s*|;\\s*|:\\s*|\\.\\s*|\\n\\s*|\\-\\s*");
		
		Sheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
		
        for (String word : WordArr) {
//        	System.out.println(word);	//word配列を全てコンソールに表示するためのコード．テストの時に使用．
//        	System.out.println(rowCount);
            for (int RowNum = 0; RowNum <= rowCount; RowNum++) {
                Row row = sheet.getRow(RowNum);
                if (row != null) {
                	Cell POScell = row.getCell(0);	// 1列目
            			POS = POScell.getStringCellValue();
            			Cell wordcell = row.getCell(1); 	//2列目
                		if(POS.equals(POS_V)) {
//                			System.out.println("品詞は動詞です");
                			/*	動詞の処理	*/
//                        		System.out.println(CellVal + "は動詞");
                    		Cell Verb2ndcell = row.getCell(2);	//3列目(動詞：3単現)
                    		Cell Verb3rdcell = row.getCell(3);	//4列目(動詞：過去形)
                    		Cell Verb4thcell = row.getCell(4);	//5列目(動詞：過去分詞)
                    		Cell Verb5thcell = row.getCell(5);	//6列目(動詞：現在分詞)
                    		
                    		Verb1st = wordcell.getStringCellValue();
                    		Verb2nd = Verb2ndcell.getStringCellValue();
                    		Verb3rd = Verb3rdcell.getStringCellValue();
                    		Verb4th = Verb4thcell.getStringCellValue();
                    		Verb5th = Verb5thcell.getStringCellValue();
                    		
                            if (word.equals(Verb1st)) {
//                            	MatchedWrdArr.add(POS + "\t" + Verb1st);
                            	MatchedWrdNum.add(RowNum);
                            }
                            if (word.equals(Verb2nd)) {
//                            	MatchedWrdArr.add(POS + "\t" + Verb2nd);
                            	MatchedWrdNum.add(RowNum);
                            }
                            if (word.equals(Verb3rd)) {
//                            	MatchedWrdArr.add(POS + "\t" + Verb3rd);
                            	MatchedWrdNum.add(RowNum);
                            }
                            if (word.equals(Verb4th)) {
//                            	MatchedWrdArr.add(POS + "\t" + Verb4th);
                            	MatchedWrdNum.add(RowNum);
                            }
                            if (word.equals(Verb5th)) {
//                            	MatchedWrdArr.add(POS + "\t" + Verb5th);
                            	MatchedWrdNum.add(RowNum);
                            }
                    	}else if(POS.equals(POS_N)) {
                    		Cell MultiNouncell = row.getCell(2);	//3列目(名詞：複数形)
                    		
                    		MonoNoun = wordcell.getStringCellValue();
                    		MultiNoun = MultiNouncell.getStringCellValue();
                			
                    		if (word.equals(MonoNoun)) {
//                				MatchedWrdArr.add(POS + "\t" + MonoNoun);
                				MatchedWrdNum.add(RowNum);
                			}
                			if (word.equals(MultiNoun)) {
//                				MatchedWrdArr.add(POS + "\t" + MultiNoun);
                				MatchedWrdNum.add(RowNum);
                			}
                		}else {
                			CellVal = wordcell.getStringCellValue();
                			
                			if (word.equals(CellVal)) {
//                				MatchedWrdArr.add(POS + "\t" + CellVal);
                    			MatchedWrdNum.add(RowNum);
                			}
                		}
            	}
            }
        }
        MatchedWrdNumTrue = new ArrayList<Integer>(new HashSet<>(MatchedWrdNum));
        Collections.sort(MatchedWrdNumTrue);
        for(int Num:MatchedWrdNumTrue) {
        	Row matchedrow = sheet.getRow(Num);
        	RowNumToString = Integer.toString(Num + 1);
        	MatchedWrdPOS = matchedrow.getCell(0).getStringCellValue();
        	MatchedWrd = matchedrow.getCell(1).getStringCellValue();
        	MatchedWrdPOSList.add(MatchedWrdPOS);
        	MatchedWrdArrTrue.add(RowNumToString + "\t" + MatchedWrdPOS + "\t" + MatchedWrd);
        }
        for(String Str:MatchedWrdPOSList){
        	if(Str.equals("V")){
        		V_num++;
        	}
        	if(Str.equals("N")){
        		N_num++;
        	}
        	if(Str.equals("Adj")){
        		Adj_num++;
        	}
        	if(Str.equals("Adv")){
        		Adv_num++;
        	}
        	if(Str.equals("Prep")) {
        		Prep_num++;
        	}
        	if(Str.equals("Conj")||Str.equals("Aux")){
        		ConjAux_num++;
        	}
        }
        
        MatchedWrdPOSforResult = "名詞"		+ "\t" + Integer.toString(N_num) + "\n";
        MatchedWrdPOSforResult = "動詞"		+ "\t" + Integer.toString(V_num) + "\n";
        MatchedWrdPOSforResult = "形容詞"		+ "\t" + Integer.toString(Adj_num) + "\n";
        MatchedWrdPOSforResult = "副詞"		+ "\t" + Integer.toString(Adv_num) + "\n";
        MatchedWrdPOSforResult = "前置詞"		+ "\t" + Integer.toString(Prep_num) + "\n";
        MatchedWrdPOSforResult = "接続詞・他"	+ "\t" + Integer.toString(ConjAux_num);
        
        new GetResult();
        
        /*workbookを閉じる処理*/
        try {
            if (wb != null) {
                wb.close();
            }
        } catch (IOException e1) {
            System.out.println(e1.toString());
        }
        MatchedWrdNum.clear();
        MatchedWrdNumTrue.clear();
        MatchedWrdArrTrue.clear();
    }
}