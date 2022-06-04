package AmitCohen.Model.Genres;

public class DrivingGenre implements Genre {
	protected String genreName;
	
	public DrivingGenre() {
		genreName = "Driving";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}

}
