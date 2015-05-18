package normal.app.task;

import normal.app.db.Item;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InsertTask {

    public static Observable<Void> saveItems() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                for (int i = 0; i < 30; i++) {
                    Item item = new Item();
                    item.name = "aaa";
                    item.save();
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
