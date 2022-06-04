package AmitCohen.Model.Genres;

public class AdventureGenre implements Genre {
	protected String genreName;
	
	public AdventureGenre() {
		genreName = "Adventure";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}

}
