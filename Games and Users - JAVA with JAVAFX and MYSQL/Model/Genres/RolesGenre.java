package AmitCohen.Model.Genres;

public class RolesGenre implements Genre {
	protected String genreName;
	
	public RolesGenre() {
		genreName = "Roles";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}

}
