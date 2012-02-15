package com.horaz.todolist.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.user.client.Event;
import com.horaz.client.model.Filter;
import com.horaz.client.model.SQLiteDataStore;
import com.horaz.client.model.SQLiteDataStore.SQLiteColumnDef;
import com.horaz.client.model.events.ModelAddedEvent;
import com.horaz.client.model.events.ModelAddedListener;
import com.horaz.client.model.events.ModelUpdatedEvent;
import com.horaz.client.model.events.ModelUpdatedListener;
import com.horaz.client.widgets.AsynchronousListView;
import com.horaz.client.widgets.Button;
import com.horaz.client.widgets.Page;
import com.horaz.client.widgets.events.ItemApplyListener;

public class TodoIndexPage extends Page {
	private final SQLiteDataStore<TodoItem> datastore;
	private final AsynchronousListView<TodoItem> listTodo;

	public TodoIndexPage() {
		// call super with the page element
		super(getElementById("page_index"));
		listTodo = AsynchronousListView.byId("list_todo");
		datastore = new SQLiteDataStore<TodoItem>("todo.db", "1", 1024*1024*128) {
			@Override
			public TodoItem reflectJavaScriptObject(JavaScriptObject jsObj) {
				ModelWrapperJS mdlJs = (ModelWrapperJS) jsObj;
				TodoItem mdl = new TodoItem();
				mdl.setField(TodoItem.FIELD_TITLE, mdlJs.getFieldString(TodoItem.FIELD_TITLE));
				mdl.setField(TodoItem.FIELD_NOTES, mdlJs.getFieldString(TodoItem.FIELD_NOTES));
				mdl.setField(TodoItem.FIELD_DONE, mdlJs.getFieldBoolean(TodoItem.FIELD_DONE));
				mdl.setField("modelId", (long) mdlJs.getFieldInteger("modelId"));
				return mdl;
			}
		};

		// force reload list
		datastore.addModelAddedListener(new ModelAddedListener<TodoItem>() {
			@Override
			public void onModelAdded(ModelAddedEvent<TodoItem> event) {
				datastore.setFilter(new Filter());
			}
		});
		datastore.addModelUpdatedListener(new ModelUpdatedListener<TodoItem>() {
			@Override
			public void onModelUpdated(ModelUpdatedEvent<TodoItem> event) {
				datastore.setFilter(new Filter());
			}
		});
		listTodo.setDataStore(datastore);

		listTodo.addItemApplyListener(new ItemApplyListener<TodoItem>() {
			@Override
			public void onItemApply(Event event, LIElement liElement, TodoItem model) {
				if (model.isDone()) {
					liElement.addClassName("done");
				}
			}
		});

		// create table if not exists
		datastore.initTable("todotable", new SQLiteColumnDef[] {
			new SQLiteColumnDef(TodoItem.FIELD_TITLE, SQLiteColumnDef.Type.TEXT)
			, new SQLiteColumnDef(TodoItem.FIELD_NOTES, SQLiteColumnDef.Type.TEXT)
			, new SQLiteColumnDef(TodoItem.FIELD_DONE, SQLiteColumnDef.Type.INTEGER)
		});
	}

	public Button getButtonNew() {
		return Button.byId("btn_new");
	}

	/**
	 * @return todo list's datastore
	 */
	public SQLiteDataStore<TodoItem> getDatastore() {
		return datastore;
	}

	public AsynchronousListView<TodoItem> getListView() {
		return listTodo;
	}

	@Override
	public void onCreate() {
	}
}
