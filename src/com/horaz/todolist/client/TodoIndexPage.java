package com.horaz.todolist.client;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.horaz.client.model.PersistentDataStore;
import com.horaz.client.model.SimpleDataStore;
import com.horaz.client.widgets.Button;
import com.horaz.client.widgets.ListView;
import com.horaz.client.widgets.Page;
import com.horaz.client.widgets.events.ItemApplyListener;

public class TodoIndexPage extends Page {
	private PersistentDataStore<TodoItem> datastore;
	private ListView<TodoItem> listTodo;

	public TodoIndexPage() {
		// call super with the page element
		super(getElementById("page_index"));
	}

	public Button getButtonNew() {
		return Button.byId("btn_new");
	}

	/**
	 * @return todo list's datastore
	 */
	public SimpleDataStore<TodoItem> getDatastore() {
		return datastore;
	}

	public ListView<TodoItem> getListView() {
		return listTodo;
	}

	@Override
	public void onCreate() {
		listTodo = ListView.byId("list_todo");
		datastore = new PersistentDataStore<TodoItem>("todoListStore") {
			@Override
			protected TodoItem deserializeModel(JSONObject attrs) {
				TodoItem itm = new TodoItem();
				itm.setField(TodoItem.FIELD_TITLE, attrs.get(TodoItem.FIELD_TITLE).isString().stringValue());
				itm.setField(TodoItem.FIELD_NOTES, attrs.get(TodoItem.FIELD_NOTES).isString().stringValue());
				itm.setField(TodoItem.FIELD_DONE, Boolean.valueOf(attrs.get(TodoItem.FIELD_DONE).isString().stringValue()));
				return itm;
			}

			@Override
			protected JSONObject serializeModel(TodoItem model) {
				JSONObject r = new JSONObject();
				r.put(TodoItem.FIELD_TITLE, new JSONString((String) model.getField(TodoItem.FIELD_TITLE)));
				r.put(TodoItem.FIELD_NOTES, new JSONString((String) model.getField(TodoItem.FIELD_NOTES)));
				r.put(TodoItem.FIELD_DONE, new JSONString(String.valueOf(model.isDone())));
				return r;
			}
		};
		listTodo.setDataStore(datastore);

		listTodo.addItemApplyListener(new ItemApplyListener<TodoItem>() {
			@Override
			public void onItemApply(Event event, LIElement liElement, TodoItem model) {
				if (model.isDone()) {
					liElement.addClassName("done");
				}
			}
		});

		// load data from storage
		datastore.load();

		// register for saving
		Window.addWindowClosingHandler(new ClosingHandler() {
			@Override
			public void onWindowClosing(ClosingEvent event) {
				datastore.save();
			}
		});
	}
}
