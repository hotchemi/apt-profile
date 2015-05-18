package apt.addiction.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = ItemDatabase.NAME, version = ItemDatabase.VERSION)
public class ItemDatabase {

    public static final String NAME = "Names";

    public static final int VERSION = 1;
}
