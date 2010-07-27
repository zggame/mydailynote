




public class FileKey2 extends FileKey {


	
//	@Override
//	public String getAnswer(String query) {
//		return answerIdMap.get(Util.baseName(keyMap.get(query))).toString();
//	}

	@Override
	public int getAnswerIdByAnswer(String answer){
		return Integer.parseInt(Util.baseName(answer))-1;
	}

}
