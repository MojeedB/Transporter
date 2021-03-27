
/**
 * The instance of transporter was designed with a default initialization
 * and a specified initialization, essentially defining two types of transporter instance.
 * To keep the instance of transporter simple and prevent me from insanity,
 * some functions that i believe to be basic were implemented and tested.
 * The basic on/off and initiating transportation were modeled below,
 * of course if an actual one were to be built it would be more complicated.
 * I enjoyed working on this, i might try to expand on it.
 */
		


package edu.cuny.csi.csc330.lab4;

import java.util.Date;

import edu.cuny.csi.csc330.util.Randomizer;

public class Transporter {

	// Types of transporter
	protected static String industrialTransporter = "Industrial Transporter";
	protected static String militaryTransporter = "Military Transporter";
	
	// Serial number suffix depending on transporter type
	protected static String industrialPrefix = "IDX-9-8-1966";
	protected static String militaryPrefix = "MIX-8-19-1921";
	
	// Specifies what can be transported by transporter type
	protected static String industryRated = "Non-Biological";
	protected static String militaryRated = "Biological";
	
	// Default variables for transporter instance
	protected static String DEFUALT_TRANSPORTER_TYPE = industrialTransporter;
	protected static String DEFAULT_ENTITY_RATED = industryRated;
	protected static String DEFAULT_SERIAL_PREFIX = industrialPrefix;			
	protected static String DEFAULT_DEPARTURE_SITE = "Eclipse";	
	protected static String DEFAULT_DATA_STREAM = null;
	
	
	// Instance properties
	private boolean powerState;
	private String serialNumber;
	private String matterDataStream;		// Assigns unique code to a transporting entity
	private String ratedEntityType;
	private String transporterType;
	private Date firstTimeOn; 				// Ideally the transporter is never off so only the first instance of power on is needed 
	private String destinationLocation;
	private String departureLocation;
	
	
	
	
	public Transporter() {
		init(DEFUALT_TRANSPORTER_TYPE);
	}
	
	public Transporter(String transporterType) {
		init(transporterType);
	}
	
	// This method initializes a default serial number and transporter type 
	private void init(String transporterType) {
		
		// Serial number if transporter type specified
		if(transporterType == militaryTransporter){
			this.transporterType = militaryTransporter;
			this.ratedEntityType = militaryRated;
			Integer irand = Randomizer.generateInt(0, 99999999); 
			this.serialNumber = militaryPrefix + ":" + irand.toString(); 
		}
		
		// Serial number if no or invalid specifications given
		else {
			this.transporterType = DEFUALT_TRANSPORTER_TYPE;
			this.ratedEntityType = DEFAULT_ENTITY_RATED;
			Integer irand = Randomizer.generateInt(0, 99999999); 
			this.serialNumber = DEFAULT_SERIAL_PREFIX + ":" + irand.toString(); 
		}
		
	}
	
	
	public void on() {
		Date now = new Date(); 
		
		// all the things we need to do the 1st time we turn on the instance ... 
		if(firstTimeOn == null) {
			departureLocation = DEFAULT_DEPARTURE_SITE;
			destinationLocation = "No Destination Selected";
			matterDataStream = "Matter Data Stream is Empty";
			firstTimeOn = now;	
		}
		
		powerState = true;
	}
	
	
	
	/**
	 * turn off the radio instance 
	 */
	public void off() {
		powerState = false; 
		matterDataStream = "Matter Data Stream is Empty";
		destinationLocation = "No Destination Selected";
		departureLocation = DEFAULT_DEPARTURE_SITE;
	}


	/**
	 * is the transporter powerState on/off??
	 * @return
	 */
	public boolean isOn() {
		return powerState == true;
	}

	public String getDestination() {
		return destinationLocation;
	}
	
	public void setDestination(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	
	public String getDeparture() {
		return departureLocation;
	}
	
	public void setDeparture(String departureLocation) {
		this.departureLocation = departureLocation;
	}
	
	// This activate the transportation
	public void transport(String destinationSite) {
		departureLocation = DEFAULT_DEPARTURE_SITE;
		matterDataStream = "MDS" + Randomizer.generateInt(1966, 3189) + "ST";
		this.destinationLocation = destinationSite;
	}
	
	public void transport(String departureSite, String destinationSite) {
		this.departureLocation = departureSite;
		matterDataStream = "MDS" + Randomizer.generateInt(1966, 3189) + "ST";
		this.destinationLocation = destinationSite;
	}
	
	// toString
	@Override
	public String toString() {
		return " Transporter [powerState = " + powerState + ", serialNumber = " + serialNumber + ",\n matterDataStream = "
				+ matterDataStream + ",\n ratedEntityType = " + ratedEntityType + ", transporterType = " + transporterType
				+ ",\n firstTimeOn = " + firstTimeOn + ",\n destinationLocation = " + destinationLocation
				+ ", departureLocation = " + departureLocation + "]";
	}
	
	public static void main(String[] args) {
		
		// Default instance of transporter
		System.out.println("********************************DEFAULT "
				+ "INSTANCE********************************");
		Transporter defaultTransporter = new Transporter();
		System.out.println("New Instance\n" + defaultTransporter + "\n");

		defaultTransporter.on();
		System.out.println("Turned ON\n" + defaultTransporter + "\n");
		
		defaultTransporter.transport("Staten Island");
		System.out.println("Transporting\n" + defaultTransporter + "\n");
		
		defaultTransporter.transport("Staten Island","New Mexico");
		System.out.println("Transporting\n" + defaultTransporter + "\n");
		
		defaultTransporter.off();
		System.out.println("Turned OFF\n" + defaultTransporter);
		System.out.println("*************************************************"
				+ "*******************************\n");
		
		// Specified instance of transporter
		System.out.println("********************************SPECIFIED "
				+ "INSTANCE********************************");
		Transporter transporter = new Transporter("Military Transporter");
		System.out.println("New Instance\n" + transporter + "\n");

		transporter.on();
		System.out.println("Turned ON\n" + transporter + "\n");
		
		transporter.transport("Staten Island");
		System.out.println("Transporting\n" + transporter + "\n");
		
		transporter.transport("Staten Island","New Mexico");
		System.out.println("Transporting\n" + transporter + "\n");
		
		transporter.off();
		System.out.println("Turned OFF\n" + transporter);
		System.out.println("*************************************************"
				+ "*******************************\n");
		
		// Invalid instance of transporter
		System.out.println("********************************INVALID "
				+ "INSTANCE********************************");
		Transporter invalidTransporter = new Transporter("Commercial Transporter");
		System.out.println("New Instance\n" + invalidTransporter + "\n");

		invalidTransporter.on();
		System.out.println("Turned ON\n" + invalidTransporter + "\n");
		
		invalidTransporter.transport("Staten Island");
		System.out.println("Transporting\n" + invalidTransporter + "\n");
		
		invalidTransporter.transport("Staten Island","New Mexico");
		System.out.println("Transporting\n" + invalidTransporter + "\n");
		
		invalidTransporter.off();
		System.out.println("Turned OFF\n" + invalidTransporter);
		System.out.println("*************************************************"
				+ "*******************************\n");
	}

}
