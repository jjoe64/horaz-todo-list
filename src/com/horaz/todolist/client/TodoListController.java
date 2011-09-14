package com.horaz.todolist.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Event;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.events.ItemClickListener;

/**
 * Entry point
 */
public class TodoListController implements EntryPoint {
	private TodoItemPage itemPage;
	private TodoIndexPage indexPage;
	private TodoItemDialog itemDialog;
	private TodoItem lastSelectedItem;

	@Override
	public void onModuleLoad() {
		itemDialog = new TodoItemDialog() {
			@Override
			protected void onDeleteClicked() {
				indexPage.getDatastore().remove(lastSelectedItem);
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
		indexPage = new TodoIndexPage();
		itemPage = new TodoItemPage() {
			@Override
			protected void onNewTodoItem(TodoItem mdl) {
				indexPage.getDatastore().add(mdl);
			}
		};
		indexPage.getListView().addItemClickListener(new ItemClickListener<TodoItem>() {
			@Override
			public void onItemClick(Event event, TodoItem item) {
				lastSelectedItem = item;
				itemDialog.setSelectedItem(item);
			}
		});
	}
}
