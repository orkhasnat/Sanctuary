public class DiningRoom extends Room
{
	DiningRoom(int FlatID)
	{
		super();
		id += 200000;
		name = "Dining Room";

		// INSERT INTO DiningRoom (RoomID, Name, Area, Tiles, Type, FlatID)
		// VALUES (id, name, area, tiles, 2, FlatID);
	}
}