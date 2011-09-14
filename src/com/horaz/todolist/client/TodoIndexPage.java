package com.horaz.todolist.client;

import com.jjoe64.gwtmobile_test.client.horaz.model.SimpleDataStore;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Button;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.ListView;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Page;

public class TodoIndexPage extends Page {
	private final SimpleDataStore<TodoItem> datastore;
	private final ListView<TodoItem> listTodo;

	public TodoIndexPage() {
		// call super with the page element
		super(getElementById("page_index"));

		listTodo = ListView.byId("list_todo");
		datastore = new SimpleDataStore<TodoItem>();
		listTodo.setDataStore(datastore);
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
}
