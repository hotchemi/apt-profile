/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package normal.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.activeandroid.query.Select;
import normal.app.db.Item;
import normal.app.task.InsertTask;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;

import java.util.List;

public class HomeFragment extends Fragment implements Observer<Void> {

    private ListView listView;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscriptions.add(InsertTask.saveItems().subscribe(this));
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);

        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }

    @Override
    public void onCompleted() {
        List<Item> items = new Select().from(Item.class).execute();
        final ItemAdapter adapter = new ItemAdapter(getActivity(), R.layout.row, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                Toast.makeText(getActivity(), item.name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(Void aVoid) {
    }
}
