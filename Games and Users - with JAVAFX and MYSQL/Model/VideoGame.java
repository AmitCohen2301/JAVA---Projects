package AmitCohen.Model;

import AmitCohen.Model.Genres.*;

public class VideoGame {
	protected String videoGameName;
	protected String videoGameDistributionDate;
	protected String videoGameDistributionCompany;
	protected String videoGameCreativeCompany;
	protected String videoGameTrailerLink;
	protected String videoGameDescription;
	protected Genre videoGameGenre;
	protected int videoGameManufactureYear;
	protected SeriesOfGames videoGameSeriesOfGames;
	protected int videoGamePrice;
	
	//Constructors
	public VideoGame(String videoGameName, String videoGameDistributionDate, String videoGameDistributionCompany
			, String videoGameCreativeCompany, String videoGameTrailerLink, String videoGameDescription
			, String videoGameGenre, int videoGameManufactureYear, SeriesOfGames videoGameSeriesOfGames, int videoGamePrice) throws Exception {
		setVideoGameName(videoGameName);
		setVideoGameDistributionDate(videoGameDistributionDate);
		setVideoGameDistributionCompany(videoGameDistributionCompany);
		setVideoGameCreativeCompany(videoGameCreativeCompany);
		setVideoGameTrailerLink(videoGameTrailerLink);
		setVideoGameDescription(videoGameDescription);
		setVideoGameGenre(videoGameGenre);
		setVideoGameManufactureYear(videoGameManufactureYear);
		setVideoGameSeriesOfGames(videoGameSeriesOfGames);
		setVideoGamePrice(videoGamePrice);
	}
	
	//Sets
	private void setVideoGameName(String videoGameName) throws Exception {
		checkValidInputs.getSingleCheckValidInputsObject().checkGameNameIsOk(videoGameName);
		this.videoGameName = videoGameName;
	}
	private void setVideoGameDistributionDate(String videoGameDistributionDate) throws Exception {
		checkValidInputs.getSingleCheckValidInputsObject().checkDistributionDateInput(videoGameDistributionDate);
		this.videoGameDistributionDate = videoGameDistributionDate;
	}
	private void setVideoGameDistributionCompany(String videoGameDistributionCompany) throws Exception {
		checkValidInputs.getSingleCheckValidInputsObject().checkDistributionCompanyName(videoGameDistributionCompany);
		this.videoGameDistributionCompany = videoGameDistributionCompany;
	}
	private void setVideoGameCreativeCompany(String videoGameCreativeCompany) throws Exception {
		checkValidInputs.getSingleCheckValidInputsObject().checkCreativeCompanyName(videoGameCreativeCompany);
		this.videoGameCreativeCompany = videoGameCreativeCompany;
	}
	private void setVideoGameTrailerLink(String videoGameTrailerLink) throws Exception {
		this.videoGameTrailerLink = checkValidInputs.getSingleCheckValidInputsObject().checkTrailerLink(videoGameTrailerLink);
	}
	private void setVideoGameDescription(String videoGameDescription) throws Exception {
		this.videoGameDescription = checkValidInputs.getSingleCheckValidInputsObject().checkGameDescription(videoGameDescription);
	}
	private void setVideoGameGenre(String videoGameGenre) throws Exception {
		this.videoGameGenre = GenreFactory.getSingleGenreFactoryObject().getGenre(videoGameGenre);
	}
	private void setVideoGameManufactureYear(int videoGameManufactureYear) throws Exception {
		checkValidInputs.getSingleCheckValidInputsObject().checkManufactureYearInput(videoGameManufactureYear);
		this.videoGameManufactureYear = videoGameManufactureYear;
	}
	private void setVideoGameSeriesOfGames(SeriesOfGames videoGameSeriesOfGames) {
		this.videoGameSeriesOfGames = videoGameSeriesOfGames;
	}
	private void setVideoGamePrice(int videoGamePrice) throws Exception {
		checkValidInputs.getSingleCheckValidInputsObject().checkPriceInput(videoGamePrice);
		this.videoGamePrice = videoGamePrice;
	}

	//Gets
	public String getVideoGameName() {
		return videoGameName;
	}

	public String getVideoGameDistributionDate() {
		return videoGameDistributionDate;
	}

	public String getVideoGameDistributionCompany() {
		return videoGameDistributionCompany;
	}

	public String getVideoGameCreativeCompany() {
		return videoGameCreativeCompany;
	}

	public String getVideoGameTrailerLink() {
		return videoGameTrailerLink;
	}

	public String getVideoGameDescription() {
		return videoGameDescription;
	}

	public Genre getVideoGameGenre() {
		return videoGameGenre;
	}

	public int getVideoGameManufactureYear() {
		return videoGameManufactureYear;
	}

	public SeriesOfGames getVideoGameSeriesOfGames() {
		return videoGameSeriesOfGames;
	}

	public int getVideoGamePrice() {
		return videoGamePrice;
	}
}
