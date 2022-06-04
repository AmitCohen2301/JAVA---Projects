package AmitCohen.Model.Genres;

public class SimulationGenre implements Genre {
	protected String genreName;
	
	public SimulationGenre() {
		genreName = "Simulation";
	}
	
	@Override
	public String genreToString() {
		return genreName;
	}

}
