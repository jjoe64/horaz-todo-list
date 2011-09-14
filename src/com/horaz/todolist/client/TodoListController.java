package com.horaz.todolist.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Event;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.events.ItemClickListener;
import com.jjoe64.gwtmobile_test.client.horaz.widgets.events.TapListener;

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
				itemPage.startEditing(lastSelectedItem);
			}
		};
		indexPage = new TodoIndexPage();
		indexPage.getButtonNew().addTapListener(new TapListener() {
			@Override
			public void onTap(Event event) {
				// no editing, create new
				itemPage.startEditing(null);
			}
		});
		itemPage = new TodoItemPage() {
			@Override
			protected void onNewTodoItem(TodoItem mdl) {
				indexPage.getDatastore().add(mdl);
			}
			@Override
			protected void onUpdateTodoItem(TodoItem mdl) {
				indexPage.getDatastore().update(mdl);
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
