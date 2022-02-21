public class StoreRoom extends Room
{
	StoreRoom(int FlatID)
	{
		super();
		id += 700000;
		name = "Store Room";

		// INSERT INTO StoreRoom (RoomID, Name, Area, Tiles, Type, FlatID)
		// VALUES (id, name, area, tiles, 7, FlatID);
	}
}