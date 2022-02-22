public class StoreRoom extends Room
{
	StoreRoom(int FlatID)
	{
		super();
		id += 700000;
		name = "Store Room";

		// INSERT INTO StoreRoom (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 7, area, tiles, FlatID);
	}
}