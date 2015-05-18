package apt.addiction.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = ItemDatabase.NAME)
public class Item extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    public String name;

}
