public class Balcony extends Room
{
	Balcony(int FlatID)
	{
		super();
		id += 600000;
		name = "Balcony";

		// INSERT INTO Balcony (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 6, area, tiles, FlatID);
	}
}