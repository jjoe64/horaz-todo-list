package com.horaz.todolist.client;

import com.jjoe64.gwtmobile_test.client.horaz.model.SimpleDataStore;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.ListView;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Page;

public class TodoIndexPage extends Page {
	private final SimpleDataStore<TodoItem> datastore;

	public TodoIndexPage() {
		// call super with the page element
		super(getElementById("page_index"));

		// create a simple datastore
		ListView<TodoItem> listTodo = ListView.byId("list_todo");
		datastore = new SimpleDataStore<TodoItem>();
		listTodo.setDataStore(datastore);
	}

	/**
	 * @return todo list's datastore
	 */
	public SimpleDataStore<TodoItem> getDatastore() {
		return datastore;
	}
}
