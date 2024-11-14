import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxBanMultiSelect implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        // チェックされているかどうかを判定
        if (e.getStateChange() == ItemEvent.SELECTED) {
//            System.out.println("チェックボックスが選択されました。");
        	if(MakeWinFrame.WordList_Target1900.isSelected()) {
        		MakeWinFrame.WordList_Target1400.setEnabled(false);
        		MakeWinFrame.WordList_SysTan.setEnabled(false);
        		MakeWinFrame.LEAP.setEnabled(false);
        	}else if(MakeWinFrame.WordList_Target1400.isSelected()) {
        		MakeWinFrame.WordList_Target1900.setEnabled(false);
        		MakeWinFrame.WordList_SysTan.setEnabled(false);
        		MakeWinFrame.LEAP.setEnabled(false);
        	}else if(MakeWinFrame.WordList_SysTan.isSelected()) {
        		MakeWinFrame.WordList_Target1900.setEnabled(false);
        		MakeWinFrame.WordList_Target1400.setEnabled(false);
        		MakeWinFrame.LEAP.setEnabled(false);
        	}else if(MakeWinFrame.LEAP.isSelected()) {
	    		MakeWinFrame.WordList_Target1900.setEnabled(false);
	    		MakeWinFrame.WordList_Target1400.setEnabled(false);
	    		MakeWinFrame.WordList_SysTan.setEnabled(false);
        	}
    	} else {
//            System.out.println("チェックボックスが解除されました。");
    		MakeWinFrame.WordList_Target1900.setEnabled(true);
    		MakeWinFrame.WordList_Target1400.setEnabled(true);
    		MakeWinFrame.WordList_SysTan.setEnabled(true);
    		MakeWinFrame.LEAP.setEnabled(true);
        }
    }
}