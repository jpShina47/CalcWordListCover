class GetResult {
	public GetResult() {
		for(String word : Comparing.MatchedWrdArrTrue) {
//        	System.out.println(word);
        	MakeWinFrame.MatchedWrdTextArea.append(word);
        	MakeWinFrame.MatchedWrdTextArea.append("\n");
        }
        MakeWinFrame.ResultTextField.setText(String.valueOf("結果：" + Comparing.MatchedWrdArrTrue.size() + "word"));
        
        MakeWinFrame.AnalizedResultTextArea.setText(Comparing.MatchedWrdPOSforResult);
	}
}
