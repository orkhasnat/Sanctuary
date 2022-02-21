public class Balcony extends Room
{
	Balcony(int FlatID)
	{
		super();
		id += 600000;
		name = "Balcony";

		// INSERT INTO Balcony (RoomID, Name, Area, Tiles, Type, FlatID)
		// VALUES (id, name, area, tiles, 6, FlatID);
	}
}