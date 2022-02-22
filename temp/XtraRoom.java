public class XtraRoom extends Room
{
	XtraRoom(int FlatID)
	{
		super();
		id += 800000;
		name = "Extra Room";

		// INSERT INTO XtraRoom (RoomID, Name, Type, Area, Tiles, FlatID)
		// VALUES (id, name, 8, area, tiles, FlatID);
	}
}