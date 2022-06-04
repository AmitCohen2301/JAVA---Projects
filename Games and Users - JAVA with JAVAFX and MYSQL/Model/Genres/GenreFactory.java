package AmitCohen.Model.Genres;

public class GenreFactory {
	private static GenreFactory singleGenreFactory = new GenreFactory();
	
	//Constructor
	private GenreFactory() {}
	
	//Get single object
	public static GenreFactory getSingleGenreFactoryObject() {
		return singleGenreFactory;
	}
	
	//Factory
	public Genre getGenre(String genreName) {
		if(genreName == null) {
			return null;
		}
		if(genreName.equalsIgnoreCase("ACTION")) {
			return new ActionGenre();
		}
		else if(genreName.equalsIgnoreCase("ADVENTURE")) {
			return new AdventureGenre();
		}
		else if(genreName.equalsIgnoreCase("STRATEGY")) {
			return new StrategyGenre();
		}
		else if(genreName.equalsIgnoreCase("ROLES")) {
			return new RolesGenre();
		}
		else if(genreName.equalsIgnoreCase("SPORTS")) {
			return new SportsGenre();
		}
		else if(genreName.equalsIgnoreCase("DRIVING")) {
			return new DrivingGenre();
		}
		else if(genreName.equalsIgnoreCase("SIMULATION")) {
			return new SimulationGenre();
		}
		return null;
	}
}
