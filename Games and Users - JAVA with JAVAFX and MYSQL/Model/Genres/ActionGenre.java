package AmitCohen.Model.Genres;

public class ActionGenre implements Genre {
	protected String genreName;
	
	public ActionGenre() {
		genreName = "Action";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}
}
