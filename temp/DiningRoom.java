public class DiningRoom extends Room
{
	DiningRoom(int FlatID)
	{
		super();
		id += 200000;
		name = "Dining Room";

		// INSERT INTO DiningRoom (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 2, area, tiles, FlatID);
	}
}