package com.horaz.todolist.client;

import com.google.gwt.user.client.Event;
import com.jjoe64.gwtmobile_test.client.horaz.model.SimpleDataStore;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.ListView;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.Page;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.events.ItemClickListener;

public class TodoIndexPage extends Page {
	private final SimpleDataStore<TodoItem> datastore;
	private final TodoItemDialog itemDialog;
	private TodoItem lastSelectedItem;

	public TodoIndexPage() {
		// call super with the page element
		super(getElementById("page_index"));

		// create a simple datastore
		ListView<TodoItem> listTodo = ListView.byId("list_todo");
		datastore = new SimpleDataStore<TodoItem>();
		listTodo.setDataStore(datastore);

		itemDialog = new TodoItemDialog() {
			@Override
			protected void onDeleteClicked() {
				datastore.remove(lastSelectedItem);
			}
			@Override
			protected void onDoneClicked() {
				// TODO Auto-generated method stub
			}
			@Override
			protected void onEditClicked() {
				// TODO Auto-generated method stub
			}
		};

		listTodo.addItemClickListener(new ItemClickListener<TodoItem>() {
			@Override
			public void onItemClick(Event event, TodoItem item) {
				lastSelectedItem = item;
				itemDialog.setSelectedItem(item);
			}
		});
	}

	/**
	 * @return todo list's datastore
	 */
	public SimpleDataStore<TodoItem> getDatastore() {
		return datastore;
	}
}
