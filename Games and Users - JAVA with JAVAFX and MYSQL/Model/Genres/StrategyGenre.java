package AmitCohen.Model.Genres;

public class StrategyGenre implements Genre {
	protected String genreName;
	
	public StrategyGenre() {
		genreName = "Strategy";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}

}
