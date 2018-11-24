
public class Aeropuerto {
	
	private int id;
	private String name;
	private String city;
	private String country;
	private String code;
	private double latitude;
	private double longitude;
	
	public Aeropuerto(int id, String name, String city, String country, String code, double latitude,
			double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	@Override
	public String toString() {
		return name + ", " + city + ", " + country + ", " + code;
	}
	
	
	

}
