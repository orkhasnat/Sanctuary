public class XtraRoom extends Room
{
	XtraRoom(int FlatID)
	{
		super();
		id += 800000;
		name = "Extra Room";

		// INSERT INTO XtraRoom (RoomID, Name, Area, Tiles, Type, FlatID)
		// VALUES (id, name, area, tiles, 8, FlatID);
	}
}