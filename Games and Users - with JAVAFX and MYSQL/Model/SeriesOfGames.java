package AmitCohen.Model;

import java.util.ArrayList;

public class SeriesOfGames {
	protected String seriesOfGamesName;
	protected ArrayList<VideoGame> seriesOfGames;
	
	//Constructors
	public SeriesOfGames(String seriesOfGamesName) throws Exception {
		setSeriesOfGamesName(seriesOfGamesName);
		this.seriesOfGames = new ArrayList<VideoGame>();
	}

	//Sets
	private void setSeriesOfGamesName(String seriesOfGamesName) throws Exception {
		try {
			checkValidInputs.getSingleCheckValidInputsObject().checkSeriesOfGamesNameIsOk(seriesOfGamesName);
			this.seriesOfGamesName = seriesOfGamesName;
		}
		catch(Exception e){
			throw new Exception("Name of series of games should contain at least 1 char");
		}
	}
	
	//Functions
	protected void addGameToSeriesOfGames(VideoGame gameToAddToSeries) {
		seriesOfGames.add(gameToAddToSeries);
	}
	
}
