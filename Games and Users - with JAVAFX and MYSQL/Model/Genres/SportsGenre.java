package AmitCohen.Model.Genres;

public class SportsGenre implements Genre {
	protected String genreName;
	
	public SportsGenre() {
		genreName = "Sports";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}

}
