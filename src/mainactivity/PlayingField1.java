package mainactivity;


public class PlayingField1 extends PlayingFieldParent{

	public PlayingField1(int level) {
			super(level);
			readData();
			chooseWords(level);
			createlabels(chosenWordsEng.length,chosenWordsEng,level);

	}

}
